package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.po.Logistics;
import com.soft.manager.service.LogisticsService;
import com.soft.parent.basic.res.LogisticsDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/5.
 */
@Controller
@RequestMapping(value = "/manager")
public class LogisticsController extends BaseFontContrller{
    public LogisticsController() {
        super("LogisticsController");
    }
    @Autowired
    private LogisticsService logisticsService;
    @RequestMapping(value = "/getListLogistics",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<LogisticsDto>> getListLogistics(){
        try{
            return logisticsService.getListLogistics();
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
}
