package com.soft.manager.service;

import com.soft.manager.dao.GoodsPriceMapper;
import com.soft.manager.po.GoodsPrice;
import com.soft.manager.po.GoodsPriceExample;
import com.soft.parent.basic.res.GoodsPriceDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
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
        GoodsPriceExample example = new GoodsPriceExample();
        GoodsPriceExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        List<GoodsPrice> list = goodsPriceMapper.selectByExample(example);
        List<GoodsPriceDto> resList = new ArrayList<>();
        if(list==null||list.isEmpty()) return new DetailResult<>(ResCode.NO_DATA);
        for(GoodsPrice goodsPrice:list){
            GoodsPriceDto dto = new GoodsPriceDto();
            BeanUtils.copyProperties(dto,goodsPrice);
            resList.add(dto);
        }
        DetailResult<List<GoodsPriceDto>> result = new DetailResult<>(ResCode.SUCCESS);
        result.setData(resList);
        return result;
    }

}
