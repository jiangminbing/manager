package com.soft.manager.service;

import com.soft.parent.basic.req.GoodsPriceSearchDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.GoodsPriceMapper;
import com.soft.parent.manager.po.GoodsPrice;
import com.soft.parent.manager.po.GoodsPriceExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/1.
 */
@Service
public class GoodsPriceService {
    @Autowired
    private GoodsPriceMapper goodsPriceMapper;

    public GoodsPrice getGoodsPriceById(Integer priceId)throws Exception{
        GoodsPrice goodsPrice = goodsPriceMapper.selectByPrimaryKey(priceId);
        return goodsPrice;
    }

    /**
     * 根据商品Id获取价格
     * @param goodsId
     * @return
     * @throws Exception
     */
    public List<GoodsPrice> getGoodsPriceByGoodsId(Integer goodsId) throws Exception{
        GoodsPriceExample example = new GoodsPriceExample();
        GoodsPriceExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
        return list;
    }

    /**
     * 查询
     * @param searchDto
     * @return
     * @throws Exception
     */
    public DetailResult<List<GoodsPrice>> searchGoodsPrice(GoodsPriceSearchDto searchDto) throws Exception{
        DetailResult<List<GoodsPrice>> result = new DetailResult<>(ResCode.SUCCESS);
        GoodsPriceExample example = createCriteria(searchDto);
        List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
        result.setData(list);
        return result;
    }

    public PageResult<GoodsPrice> searchPageGoodsPrice(GoodsPriceSearchDto searchDto) throws Exception{
        PageResult<GoodsPrice> result = new PageResult<>();
        GoodsPriceExample example = createCriteria(searchDto);
        long total = goodsPriceMapper.countByExample(example);
        if(total>0){
            List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
            result.setData(list);
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
