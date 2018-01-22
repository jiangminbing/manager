package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.parent.basic.req.AdSearchDto;
import com.soft.parent.basic.res.AdDto;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author jiangmb
 * @Time 2018/1/22.
 */
@Controller
@RequestMapping(value = "/font",produces = "application/json; charset=utf-8")
public class AdFontContrller {
    private static final Logger logger = LoggerFactory.getLogger(AdFontContrller.class);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @RequestMapping("/getAdByPage")
    @ResponseBody
    public PageResult<AdDto> getAdByPage(SearchDTO dto) {
        try {


        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }


}
