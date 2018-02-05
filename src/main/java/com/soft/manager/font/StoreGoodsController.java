package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.StoreGoodsService;
import com.soft.parent.basic.res.StoreGoodsDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/4.
 */
@Controller
@RequestMapping(value = "/manager")
public class StoreGoodsController extends BaseFontContrller {
    public StoreGoodsController() {
        super("StoreGoodsController");
    }
    @Autowired
    private StoreGoodsService storeGoodsService;
    @RequestMapping(value = "/queryStoreGoodsByUserIdAndGoodsId")
    @ResponseBody
    public DetailResult<List<StoreGoodsDto>> queryStoreGoodsByUserIdAndGoodsId(@RequestParam Integer userId,@RequestParam Integer goodsId){
        try{
            return storeGoodsService.queryStoreGoodsByUserIdAndGoodsId(userId,goodsId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
}
