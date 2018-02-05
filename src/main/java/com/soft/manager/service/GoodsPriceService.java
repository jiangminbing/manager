package com.soft.manager.service;

import com.soft.manager.dao.GoodsPriceMapper;
import com.soft.manager.po.GoodsExample;
import com.soft.manager.po.GoodsPrice;
import com.soft.manager.po.GoodsPriceExample;
import com.soft.parent.basic.req.GoodsPriceSearchDto;
import com.soft.parent.basic.req.GoodsSearchDto;
import com.soft.parent.basic.res.GoodsPriceDto;
import com.soft.parent.basic.result.DetailResult;
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
 * @Time 2018/2/1.
 */
@Service
public class GoodsPriceService {
    @Autowired
    private GoodsPriceMapper goodsPriceMapper;

    /**
     * 根据商品Id获取价格
     * @param goodsId
     * @return
     * @throws Exception
     */
    public DetailResult<List<GoodsPriceDto>> findAllNormalGoodsPriceByGoodsId(Integer goodsId) throws Exception{
        DetailResult<List<GoodsPriceDto>> result = new DetailResult<>(ResCode.SUCCESS);
        GoodsPriceExample example = new GoodsPriceExample();
        GoodsPriceExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
        List<GoodsPriceDto> resList = new ArrayList<>();
        if(list==null||list.isEmpty()) return result;
        for(GoodsPrice goodsPrice:list){
            GoodsPriceDto dto = new GoodsPriceDto();
            BeanUtils.copyProperties(dto,goodsPrice);
            resList.add(dto);
        }
        result.setData(resList);
        return result;
    }

    /**
     * 查询
     * @param searchDto
     * @return
     * @throws Exception
     */
    public DetailResult<List<GoodsPriceDto>> searchGoodsPrice(GoodsPriceSearchDto searchDto) throws Exception{
        DetailResult<List<GoodsPriceDto>> result = new DetailResult<>(ResCode.SUCCESS);
        GoodsPriceExample example = createCriteria(searchDto);
        List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
        if(list==null||list.isEmpty()) return result;
        List<GoodsPriceDto> resList = new ArrayList<>();
        for(GoodsPrice goodsPrice:list){
            GoodsPriceDto dto = new GoodsPriceDto();
            BeanUtils.copyProperties(dto,goodsPrice);
            resList.add(dto);
        }
        result.setData(resList);
        return result;
    }

    public PageResult<GoodsPriceDto> searchPageGoodsPrice(GoodsPriceSearchDto searchDto) throws Exception{
        PageResult<GoodsPriceDto> result = new PageResult<>();
        GoodsPriceExample example = createCriteria(searchDto);
        long total = goodsPriceMapper.countByExample(example);
        if(total>0){
            List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
            List<GoodsPriceDto> resList = new ArrayList<>();
            for(GoodsPrice goodsPrice:list){
                GoodsPriceDto dto = new GoodsPriceDto();
                BeanUtils.copyProperties(dto,goodsPrice);
                resList.add(dto);
            }
            result.setData(resList);
            result.setTotal(total);
        }
        return result;

    }

    public GoodsPriceExample createCriteria(GoodsPriceSearchDto dto) throws Exception {
        GoodsPriceExample example = new GoodsPriceExample();
        if (StringUtils.isNotEmpty(dto.getOrderBy())) {
            if (!dto.getReverse()) {
                example.setOrderByClause(dto.getOrderBy() + " desc");
            } else {
                example.setOrderByClause(dto.getOrderBy());
            }
        }
        if (dto.getPaging()) {
            example.setBegin(example.getBegin());
            example.setLength(example.getLength());
        }
        GoodsPriceExample.Criteria criteria = example.createCriteria();
        if (dto.getGoodsId() != null) {
            criteria.andGoodsIdEqualTo(dto.getGoodsId());
        }
        if (dto.getState() != null) {
            criteria.andStateEqualTo(dto.getState());
        }
        return example;
    }


}
