package com.soft.manager.service;

import com.alibaba.fastjson.JSON;
import com.soft.parent.basic.enums.PoolEnum;
import com.soft.parent.basic.redis.RedisConnection;
import com.soft.parent.basic.redis.RedisLock;
import com.soft.parent.basic.redis.SequensUtil;
import com.soft.parent.basic.req.OrderSearchDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.OrderDetailsMapper;
import com.soft.parent.manager.dao.OrderMapper;
import com.soft.parent.manager.dao.OrderMapperEx;
import com.soft.parent.manager.model.OrderDto;
import com.soft.parent.manager.po.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/8.
 */
@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private RedisConnection redisConnection;

    @Autowired
    private SequensUtil sequensUtil;

    @Autowired
    private UserPrivilegeService userPrivilegeService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsPriceService goodsPriceService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderMapperEx orderMapperEx;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 事务控制 当中步骤出错 将事务进行回滚 后面测试下
     * 1、在订单表增加订单记录 2、在订单详情表批量添加订单详情记录 3、处理购物车的记录
     */

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = { Exception.class })
    public DetailResult<String> addBatch(OrderDto order) throws Exception{
        String orderNum = getOrderId();
        if (order.getPayment().getPaymentId() == 1) {
            orderNum = "H" + orderNum;
        } else if (order.getPayment().getPaymentId() == 2) {
            orderNum = "S" + orderNum;
        } else if (order.getPayment().getPaymentId() == 3) {
            orderNum = "Y" + orderNum;
        }
        order.setOrderNumber(orderNum);
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        order.setPayState((byte) 1);// 设置状态 未支付
        order.setDelState((byte) 2);

        User user = order.getUser();
/*        if (null == user) {
            // TODO 添加异常信息
            throw new Exception("user is null...");
        }*/
        // 获取用户的优惠信息
        UserPrivilege userPrivilege = userPrivilegeService.getUserPrivilegeByUser(user.getUserId());
        boolean isWholeSalePrice = false;
        boolean isDiscount = false;
        int discount = 100;
        if (null != userPrivilege) {
            Byte isWholeSalePriceByte = userPrivilege.getIsWholesaleprice();
            if (null != isWholeSalePriceByte && isWholeSalePriceByte == 1) {
                isWholeSalePrice = true;
            }
            Byte isDiscountByte = userPrivilege.getIsdiscount();
            if (null != isDiscountByte && isDiscountByte == 1) {
                isDiscount = true;
            }
            Integer discountInt = userPrivilege.getDiscount();
            if (isDiscount && null != discountInt) {
                if (discountInt > 100) {
                    discount = 100;
                } else if (discountInt <= 0) {
                    discount = 0;
                } else {
                    discount = discountInt;
                }
            }
        }

        int totalAmount = 0;

        int price = 0;
        int detailsAmount = 0;

        int goodsId = 0;
        int priceId = 0;
        int num = 0;

        Goods goods = null;
        GoodsPrice goodsPrice = null;
        List<OrderDetails> orderDetailsList = order.getOrderDetailsList();
        if (null != orderDetailsList) {
            for (OrderDetails orderDetails : order.getOrderDetailsList()) {
                goodsId = orderDetails.getGoodsId();
                priceId = orderDetails.getPriceId();
                num = orderDetails.getNum();

                // 查询商品信息
                goods = goodsService.getGoodById(goodsId);
                goodsPrice = goodsPriceService.getGoodsPriceById(priceId);

                if (isWholeSalePrice) {
                    price = goodsPrice.getWholesalePrice();
                } else {
                    price = goodsPrice.getRetailPrice();
                }

                detailsAmount = price * num;
                totalAmount += detailsAmount;

                // 订单项信息
                orderDetails.setOrderNumber(order.getOrderNumber());
                orderDetails.setGoodsName(goods.getGoodsName());
                orderDetails.setUnitName(goodsPrice.getUnitName());
                orderDetails.setUnitPrice(price);
                orderDetails.setImage(goods.getImage());
                orderDetails.setDetailsAmount(detailsAmount * discount / 100);//修改  订单单品的价格应该是实付价
                orderDetails.setCreateTime(new Date());
            }
        }
        int paidAmount = totalAmount * discount / 100;
        int discountAmount = totalAmount - paidAmount;

        order.setTotalAmount(totalAmount);
        order.setPaidAmount(paidAmount);
        order.setDiscountAmount(discountAmount);

        Order orderNew = new Order();
        BeanUtils.copyProperties(order,orderNew);
        orderMapper.insertSelective(orderNew);
        if (null != orderDetailsList) {
            for (OrderDetails orderDetails : order.getOrderDetailsList()) {
                orderDetails.setOrderId(orderNew.getOrderId());
                orderDetailsMapper.insert(orderDetails);
            }
        }
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllBuyShoppingCartByUserId(user.getUserId());
        // 处理购物车信息，如果购物车存在该id，并且数量不大于购买的数量，则删除该记录；如果购物车存在该id，但数量大于购买的数量，则数量减去购买的数量，更新该记录；否则，不处理
        if (null != orderDetailsList) {
            for (OrderDetails orderDetails : order.getOrderDetailsList()) {
                goodsId = orderDetails.getGoodsId();
                priceId = orderDetails.getPriceId();
                num = orderDetails.getNum();

                if (null != shoppingCarts) {
                    for (ShoppingCart shoppingCart : shoppingCarts) {
                        if ((shoppingCart.getGoodsId() == goodsId) && (shoppingCart.getPriceId() == priceId)) {
                            if (shoppingCart.getQuantity() <= num) {
                                // 删除该购物车信息
                                shoppingCartService.deleteShoppingCartById(shoppingCart.getCartId());
                            } else {
                                // 更新 购物车的数量
                                shoppingCart.setQuantity(shoppingCart.getQuantity() - num);
                                shoppingCartService.updateShoppingCart(shoppingCart);
                            }
                        }
                    }
                }
            }
        }
        DetailResult<String> result = new DetailResult<>(ResCode.SUCCESS);
        result.setData(orderNum);
        return result;



    }
    protected String getOrderId(){
        JedisPool pool = redisConnection.getPool(PoolEnum.LOOK_KEY.getPool());
        String key = PoolEnum.LOOK_KEY.getKey()+"ORDER";
        RedisLock lock = new RedisLock(key, pool);
        String id=null;
        try{
            if (lock.lock()){
                id = sdf.format(new Date())+ sequensUtil.getNextVal("order", 7);
            }
        }catch (Exception e){
            logger.info("获取id失败,{}", JSON.toJSONString(e));
        }finally {
            lock.unlock();
        }
        return id;
    }

    /**
     * 分页查询订单
     * @param dto
     * @return
     * @throws Exception
     */
    public PageResult<OrderDto> searchOrder(OrderSearchDto dto) throws Exception{
        PageResult<OrderDto> result = new PageResult<>();
        OrderExample example = createCriteria(dto);
        long total =  orderMapper.countByExample(example);
        if(total>0){
            List<Order> list = orderMapper.selectByExample(example);
            List<OrderDto> resList = new ArrayList<>();
            for(Order order :list){
                resList.add(poToDto(order));
            }
            result.setTotal(total);
            result.setData(resList);
        }
        return result;

    }
    public OrderDto getOrderByOrderNum(String orderNum)throws Exception{
        OrderDto order = orderMapperEx.selectOrderByOrderNum(orderNum);
        return order;
    }
    private OrderDto poToDto(Order order) throws Exception{
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order,dto);
        Payment payment = paymentService.getPaymentById(order.getPaymentId());
        dto.setPayment(payment);
        OrderDetailsExample example = new OrderDetailsExample();
        OrderDetailsExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNumberEqualTo(order.getOrderNumber());
        List<OrderDetails> list = orderDetailsMapper.selectByExample(example);
        dto.setOrderDetailsList(list);
        return dto;
    }
    private OrderExample createCriteria(OrderSearchDto dto) throws Exception{
        OrderExample example = new OrderExample();
        if(StringUtils.isNotEmpty(dto.getOrderBy())){
            if(!dto.getReverse()) {
                example.setOrderByClause(dto.getOrderBy()+" desc");
            }else {
                example.setOrderByClause(dto.getOrderBy());
            }
        }
        if(dto.getPageSize()!=null){
            example.setBegin(example.getBegin());
            example.setLength(example.getLength());
        }

        OrderExample.Criteria criteria = example.createCriteria();
        if(dto.getPayState()!=null){
            criteria.andPayStateEqualTo(dto.getPayState());
        }
        if(dto.getPaymentId()!=null){
            criteria.andPaymentIdEqualTo(dto.getPaymentId());
        }
        if(dto.getLogisticsState()!=null){
            criteria.andLogisticsStateEqualTo(dto.getLogisticsState());
        }
        if(dto.getUserId()!=null){
            criteria.andUserIdEqualTo(dto.getUserId());
        }
        return example;

    }
}
