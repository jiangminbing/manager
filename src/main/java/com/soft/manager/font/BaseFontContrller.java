package com.soft.manager.font;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author jiangmb
 * @Time 2018/1/25.
 */
public class BaseFontContrller {
    private static final Logger logger = LoggerFactory.getLogger(BaseFontContrller.class);

    public void printParam(String param){
        logger.info("请求参数:{}",param);
    }
}
