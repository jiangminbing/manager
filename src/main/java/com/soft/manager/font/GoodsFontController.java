package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.GoodsService;
import com.soft.parent.basic.req.GoodsCategoryDto;
import com.soft.parent.basic.req.GoodsSearchDto;
import com.soft.parent.basic.res.GoodsDto;
import com.soft.parent.basic.result.*;
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
    public PageResult<GoodsDto> getGoodsByPage(@RequestBody GoodsSearchDto dto){
        try {
            printParam("getGoodsByPage==>"+ JSON.toJSONString(dto));
            PageResult<GoodsDto> result = goodsService.getGoodsByPage(dto);
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
    public DetailResult<GoodsDto> getGoodById(@RequestParam Integer id) {
        try {
            printParam("getGoodById==>"+ id);
            GoodsDto dto = goodsService.getGoodById(id);
            DetailResult<GoodsDto> result = new DetailResult<>(ResCode.SUCCESS);
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
    public DetailResult<List<GoodsDto>> getGoodsAll(){
        try{
            List<GoodsDto> list = goodsService.getGoodsAll();
            if(list==null){
                return new DetailResult<>(ResCode.NO_DATA);
            }else {
                DetailResult<List<GoodsDto>> result = new DetailResult<>(ResCode.SUCCESS);
                result.setData(list);
                return result;
            }
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "/getGoodsByGoodsCategory",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<GoodsDto>> getGoodsByGoodsCategory(@RequestBody GoodsCategoryDto dto){
        try{
            List<GoodsDto> list = goodsService.getGoodsByGoodsCategory(dto);
            DetailResult<List<GoodsDto>> result = new DetailResult<>(ResCode.SUCCESS);
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
    public PageResult<GoodsDto> getPageGoodsByGoodsCategory(@RequestBody Page page,@RequestBody GoodsCategoryDto dto){
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
    public PageResult<GoodsDto> getPageByMyStoreGoods(@RequestBody Page page,@RequestParam Integer userId){
        try{
            return goodsService.getPageByMyStoreGoods(page,userId);
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }




}
