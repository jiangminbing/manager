package com.soft.manager.service;

import com.alibaba.fastjson.JSON;
import com.soft.parent.basic.enums.PoolEnum;
import com.soft.parent.basic.redis.RedisConnection;
import com.soft.parent.basic.redis.RedisLock;
import com.soft.parent.basic.redis.SequensUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 事务控制 当中步骤出错 将事务进行回滚
     * 1、在订单表增加订单记录 2、在订单详情表批量添加订单详情记录 3、处理购物车的记录
     */

//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, noRollbackFor = { Exception.class })
//    public DetailResult<String> addBatch(Order order) throws Exception{
//        String orderId = getOrderId();
//
//
//    }
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
}
