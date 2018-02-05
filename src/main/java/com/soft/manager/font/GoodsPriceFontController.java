package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.po.GoodsPrice;
import com.soft.manager.service.GoodsPriceService;
import com.soft.parent.basic.req.GoodsPriceSearchDto;
import com.soft.parent.basic.res.GoodsPriceDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/1.
 */
@Controller
@RequestMapping(value = "/manager")
public class GoodsPriceFontController extends BaseFontContrller{
    @Autowired
    private GoodsPriceService goodsPriceService;

    public GoodsPriceFontController() {
        super("GoodsFontPriceController");
    }

    @RequestMapping(value = "/findAllNormalGoodsPriceByGoodsId",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<GoodsPriceDto>> findAllNormalGoodsPriceByGoodsId(@RequestParam Integer goodsId){
        try{
            return goodsPriceService.findAllNormalGoodsPriceByGoodsId(goodsId);
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "/searchGoodsPrice",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<GoodsPriceDto>> searchGoodsPrice(@RequestBody GoodsPriceSearchDto searchDto){
        try {
            return goodsPriceService.searchGoodsPrice(searchDto);
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult(ResCode.SYS_ERR);
        }
    }
}
