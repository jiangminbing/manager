package com.soft.manager.util;

import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @Author jiangmb
 * @Time 2018/1/18.
 */
@WebFilter(filterName="druidWebStatFilter",
        urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })
public class DruidStatFilter extends DruidStatProperties.WebStatFilter {
}
