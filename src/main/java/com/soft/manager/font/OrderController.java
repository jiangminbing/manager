package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.OrderService;
import com.soft.parent.basic.req.OrderSearchDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author jiangmb
 * @Time 2018/2/9.
 */
@Controller
@RequestMapping(value = "/manager/")
public class OrderController extends BaseFontContrller{
    public OrderController() {
        super("OrderController");
    }
    @Autowired
    private OrderService orderService;

    /**
     * 新增订单
     * @param order
     * @return
     */
    @RequestMapping(value = "addOrder",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<String> addOrder(@RequestBody OrderDto order){
        try{
            return orderService.addBatch(order);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }

    /**
     * 查询订单信息
     * @param dto
     * @return
     */
    @RequestMapping(value = "searchOrder",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageResult<OrderDto> searchOrder(@RequestBody OrderSearchDto dto){
        try{
            return orderService.searchOrder(dto);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "getOrderByOrderNum",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<OrderDto> getOrderByOrderNum(@RequestParam String orderNum){
        try{
           DetailResult<OrderDto> result = new DetailResult<>(ResCode.SUCCESS);
           OrderDto order = orderService.getOrderByOrderNum(orderNum);
           result.setData(order);
           return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult(ResCode.SYS_ERR);
        }
    }
}
