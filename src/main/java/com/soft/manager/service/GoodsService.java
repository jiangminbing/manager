package com.soft.manager.service;

import com.soft.manager.dao.GoodsMapper;
import com.soft.manager.po.Goods;
import com.soft.manager.po.GoodsExample;
import com.soft.parent.basic.req.GoodsSearchDto;
import com.soft.parent.basic.res.GoodsDto;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/1/27.
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    
    public PageResult<GoodsDto> getGoodsByPage(GoodsSearchDto dto)throws Exception{
        GoodsExample goodsExample = createCriteria(dto);
        long total = goodsMapper.countByExample(goodsExample);
        if(total>0){
            List<Goods> list = goodsMapper.selectByExample(goodsExample);
            List<GoodsDto> resList = new ArrayList<>();
            for(Goods goods:list){
                GoodsDto goodsDto = new GoodsDto();
                BeanUtils.copyProperties(goods,goodsDto);
                resList.add(goodsDto);
            }
            PageResult<GoodsDto> pageResult = new PageResult<>(ResCode.SUCCESS);
            pageResult.setData(resList);
            pageResult.setTotal(total);
            return pageResult;
        }

        return new PageResult<GoodsDto>(ResCode.NO_DATA);
    }
    public GoodsDto getGoodById(Integer id) throws Exception{
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        GoodsDto dto = new GoodsDto();
        BeanUtils.copyProperties(dto,goods);
        return dto;
    }

    /**
     * 查询所有的商品
     * @return
     * @throws Exception
     */
    public List<GoodsDto> getGoodsAll() throws Exception{
        List<Goods> list = goodsMapper.selectByExample(null);
        if(list==null||list.isEmpty()){
            return null;
        }
        List<GoodsDto> resList = new ArrayList<>();
        for(Goods goods:list){
            GoodsDto dto = new GoodsDto();
            BeanUtils.copyProperties(dto,goods);
            resList.add(dto);
        }
        return  resList;
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
        if(dto.getPageSize()!=null){
            example.setBegin(example.getBegin());
            example.setLength(example.getLength());
        }
        GoodsExample.Criteria criteria = example.createCriteria();
        return example;

    }
}
