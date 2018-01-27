package com.soft.manager.font;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author jiangmb
 * @Time 2018/1/25.
 */
public abstract class BaseFontContrller {
    protected static Logger logger ;

    public BaseFontContrller(String name){
        logger= LoggerFactory.getLogger(name);
    }

    public void printParam(String param){
        logger.info("请求参数:{}",param);
    }
}
