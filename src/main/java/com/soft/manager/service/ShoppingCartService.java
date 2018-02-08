package com.soft.manager.service;

import com.soft.parent.manager.dao.ShoppingCartMapper;
import com.soft.parent.manager.po.ShoppingCart;
import com.soft.parent.manager.po.ShoppingCartExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/3.
 */
@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    public ShoppingCart queryShoppingCartByUserIdAndPriceId(Integer userId, Integer priceId)throws Exception{
        ShoppingCartExample example = new ShoppingCartExample();
        ShoppingCartExample.Criteria  criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPriceIdEqualTo(priceId);
        List<ShoppingCart> list = shoppingCartMapper.selectByExample(example);
        if(list==null||list.isEmpty())return null;
        return list.get(0);

    }
}
