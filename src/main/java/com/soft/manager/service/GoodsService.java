package com.soft.manager.service;

import com.soft.parent.basic.req.GoodsCategoryDto;
import com.soft.parent.basic.req.GoodsSearchDto;
import com.soft.parent.basic.result.Page;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.GoodsMapper;
import com.soft.parent.manager.dao.GoodsMapperEx;
import com.soft.parent.manager.po.Goods;
import com.soft.parent.manager.po.GoodsExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jiangmb
 * @Time 2018/1/27.
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsMapperEx goodsMapperEx;
    
    public PageResult<Goods> getGoodsByPage(GoodsSearchDto dto)throws Exception{
        PageResult<Goods> pageResult = new PageResult<>(ResCode.SUCCESS);
        GoodsExample goodsExample = createCriteria(dto);
        long total = goodsMapper.countByExample(goodsExample);
        if(total>0){
            List<Goods> list = goodsMapper.selectByExample(goodsExample);
            pageResult.setData(list);
            pageResult.setTotal(total);
            return pageResult;
        }
        return pageResult;
    }
    public Goods getGoodById(Integer id) throws Exception{
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    /**
     * 查询所有的商品
     * @return
     * @throws Exception
     */
    public List<Goods> getGoodsAll() throws Exception{
        List<Goods> list = goodsMapper.selectByExample(null);
        return list;
    }
    public List<Goods> getGoodsByGoodsCategory(GoodsCategoryDto dto)throws Exception{
        Map<String,Object> params = new HashMap<>();
        params.put("categoryId",dto.getCategoryId());
        params.put("recommend",dto.getRecommend());
        List<Goods> list = goodsMapperEx.selectGoodsByGoodsCategory(params);
        return list;
    }

    /**
     * 查询用户个人收藏的商品
     * @param page
     * @param userId
     * @return
     * @throws Exception
     */
    public PageResult<Goods> getPageByMyStoreGoods(Page page,Integer userId)throws Exception{
        PageResult<Goods> result = new PageResult<>();
        long total = goodsMapperEx.countByMyStoreGoods(userId);
        if(total>0){
            Map<String,Object> params = new HashMap<>();
            params.put("userId",userId);
            params.put("begin",page.getBegin());
            params.put("length",page.getLength());
            List<Goods> list = goodsMapperEx.selectByMyStoreGoods(params);
            result.setTotal(total);
            result.setData(list);
        }
        return result;
    }

    /**
     * 分页查询商品类别
     * @param page
     * @param dto
     * @return
     */
    public PageResult<Goods> getPageGoodsByGoodsCategory(Page page,GoodsCategoryDto dto) throws Exception{
        Map<String,Object> params = new HashMap<>();
        params.put("categoryId",dto.getCategoryId());
        params.put("recommend",dto.getRecommend());
        PageResult<Goods> pageResult = new PageResult<>(ResCode.SUCCESS);
        long total = goodsMapperEx.countGoodsByGoodsCategory(params);
        if(total>0){
            params.put("begin",page.getBegin());
            params.put("length",page.getLength());
            List<Goods> list = goodsMapperEx.selectGoodsByGoodsCategory(params);
            pageResult.setData(list);
            pageResult.setTotal(total);
        }
        return pageResult;
    }
    public Goods poToDto(Goods po) throws Exception{
        Goods dto = new Goods();
        return dto;
    }
    public GoodsExample createCriteria(GoodsSearchDto dto) throws Exception{
        GoodsExample example = new GoodsExample();
        if(StringUtils.isNotEmpty(dto.getOrderBy())){
            if(!dto.getReverse()) {
                example.setOrderByClause(dto.getOrderBy()+" desc");
            }else {
                example.setOrderByClause(dto.getOrderBy());
            }
        }
        if(dto.getPaging()){
            example.setBegin(example.getBegin());
            example.setLength(example.getLength());
        }
        GoodsExample.Criteria criteria = example.createCriteria();
        if(dto.getDelState()!=null){
            criteria.andDelStateEqualTo(dto.getDelState());
        }
        if(dto.getIsMarketable()!=null){
            criteria.andIsMarketableEqualTo(dto.getIsMarketable());
        }
        if(dto.getRecommend()!=null){
            criteria.andRecommendEqualTo(dto.getRecommend());
        }
        if(dto.getGoodsName()!=null){
            criteria.andGoodsNameLike(dto.getGoodsName());
        }
        return example;

    }
}
