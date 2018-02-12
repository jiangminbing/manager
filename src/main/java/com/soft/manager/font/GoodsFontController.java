package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.GoodsService;
import com.soft.parent.basic.req.GoodsCategoryDto;
import com.soft.parent.basic.req.GoodsSearchDto;
import com.soft.parent.basic.result.*;
import com.soft.parent.manager.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/1/27.
 */
@Controller
@RequestMapping(value = "/manager")
public class GoodsFontController extends BaseFontContrller{

    public GoodsFontController() {
        super("GoodsFontController");
    }
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/getGoodsByPage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageResult<Goods> getGoodsByPage(@RequestBody GoodsSearchDto dto){
        try {
            printParam("getGoodsByPage==>"+ JSON.toJSONString(dto));
            PageResult<Goods> result = goodsService.getGoodsByPage(dto);
            return  result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }

    /**
     * 商品详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGoodById",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<Goods> getGoodById(@RequestParam Integer id) {
        try {
            printParam("getGoodById==>"+ id);
            Goods dto = goodsService.getGoodById(id);
            DetailResult<Goods> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(dto);
            return  result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    /**
     * 获取商品信息
     */
    @RequestMapping(value = "/getGoodsAll",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<Goods>> getGoodsAll(){
        try{
            DetailResult<List<Goods>> result = new DetailResult<>(ResCode.SUCCESS);
            List<Goods> list = goodsService.getGoodsAll();
            result.setData(list);
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "/getGoodsByGoodsCategory",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<Goods>> getGoodsByGoodsCategory(@RequestBody GoodsCategoryDto dto){
        try{
            List<Goods> list = goodsService.getGoodsByGoodsCategory(dto);
            DetailResult<List<Goods>> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(list);
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }

    /**
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getPageGoodsByGoodsCategory",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageResult<Goods> getPageGoodsByGoodsCategory(@RequestBody Page page,@RequestBody GoodsCategoryDto dto){
        try{
            return goodsService.getPageGoodsByGoodsCategory(page,dto);
        }catch (Exception e){
        logger.error("系统异常:{}",JSON.toJSONString(e));
        return new PageResult<>(ResCode.SYS_ERR);
    }

    }
    /**
     * 分页查询个人收藏商品
     */
    @RequestMapping(value = "/getPageByMyStoreGoods",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageResult<Goods> getPageByMyStoreGoods(@RequestBody Page page,@RequestParam Integer userId){
        try{
            return goodsService.getPageByMyStoreGoods(page,userId);
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }

    /**
     * 用户查询选择的商品
     * @param goodIds
     * @return
     */
    @RequestMapping(value = "/getAllSelectGoods",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<Goods>> getAllSelectGoods (@RequestParam List<Integer> goodIds){
        try{
            DetailResult<List<Goods>> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(goodsService.getGoodsByGoodIds(goodIds));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult(ResCode.SYS_ERR);
        }
    }





}
