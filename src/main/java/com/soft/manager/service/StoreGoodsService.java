package com.soft.manager.service;

import com.soft.manager.dao.StoreGoodsMapper;
import com.soft.manager.po.StoreGoods;
import com.soft.manager.po.StoreGoodsExample;
import com.soft.parent.basic.res.StoreGoodsDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/4.
 */
@Service
public class StoreGoodsService {
    @Autowired
    private StoreGoodsMapper storeGoodsMapper;

    public DetailResult<List<StoreGoodsDto>> queryStoreGoodsByUserIdAndGoodsId(Integer userId,Integer goodsId) throws Exception{
        StoreGoodsExample example = new StoreGoodsExample();
        StoreGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdEqualTo(goodsId);
        List<StoreGoods> list  = storeGoodsMapper.selectByExample(example);
        DetailResult<List<StoreGoodsDto>> result = new DetailResult<>(ResCode.SUCCESS);
        if(list==null||list.isEmpty())return  result;
        List<StoreGoodsDto> resList  = new ArrayList<>();
        for(StoreGoods storeGoods:list){
            StoreGoodsDto dto = new StoreGoodsDto();
            BeanUtils.copyProperties(storeGoods,dto);
            resList.add(dto);
        }
        result.setData(resList);
        return result;
    }

}
