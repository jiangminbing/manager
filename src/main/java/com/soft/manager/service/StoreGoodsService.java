package com.soft.manager.service;


import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.StoreGoodsMapper;
import com.soft.parent.manager.po.StoreGoods;
import com.soft.parent.manager.po.StoreGoodsExample;
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

    public List<StoreGoods> queryStoreGoodsByUserIdAndGoodsId(Integer userId,Integer goodsId) throws Exception{
        DetailResult<List<StoreGoods>> result = new DetailResult<>(ResCode.SUCCESS);
        StoreGoodsExample example = new StoreGoodsExample();
        StoreGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodsIdEqualTo(goodsId);
        List<StoreGoods> list  = storeGoodsMapper.selectByExample(example);
        return list;
    }

}
