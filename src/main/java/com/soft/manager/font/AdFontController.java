package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.AdService;
import com.soft.parent.basic.req.AdSearchDto;
import com.soft.parent.basic.res.AdDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @Author jiangmb
 * @Time 2018/1/22.
 */
@Controller
@RequestMapping(value = "/manager")
public class AdFontController extends BaseFontContrller {
//    private static final Logger logger = LoggerFactory.getLogger(AdFontContrller.class);

    @Autowired
    private AdService adService;

    public AdFontController() {
        super("AdFontContrller");
    }

    /**
     * 分页查询广告内容
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getAdByPage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageResult<AdDto> getAdByPage(@RequestBody AdSearchDto dto) {
        try {
            printParam("getAdByPage==>"+JSON.toJSONString(dto));
            PageResult<AdDto> result = adService.getAdByPage(dto);
            return  result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }

    /**
     * id 查询广告内容
     * @param id
     * @return
     */
    @RequestMapping("/queryAdByAdId")
    @ResponseBody
    public Result queryAdByAdId(@RequestParam Integer id){
        try {
            printParam("queryAdByAdId==>"+id);
            AdDto ad = adService.getAdById(id);
            DetailResult<AdDto> result = new DetailResult(ResCode.SUCCESS);
            result.setData(ad);
            return  result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }



}
