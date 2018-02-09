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
    public ShoppingCart getShoppingCartByUserIdAndPriceId(Integer userId, Integer priceId)throws Exception{
        ShoppingCartExample example = new ShoppingCartExample();
        ShoppingCartExample.Criteria  criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPriceIdEqualTo(priceId);
        List<ShoppingCart> list = shoppingCartMapper.selectByExample(example);
        if(list==null||list.isEmpty())return null;
        return list.get(0);
    }

    public List<ShoppingCart> getAllBuyShoppingCartByUserId(Integer userId)throws Exception{
        ShoppingCartExample example = new ShoppingCartExample();
        ShoppingCartExample.Criteria  criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<ShoppingCart> list = shoppingCartMapper.selectByExample(example);
        return list;
    }

    public boolean deleteShoppingCartById(Integer id)throws Exception{
        if(id==null)return false;
        return shoppingCartMapper.deleteByPrimaryKey(id)>0;
    }
    public boolean updateShoppingCart(ShoppingCart cart)throws Exception{
        if(cart==null)return false;
        return shoppingCartMapper.updateByPrimaryKey(cart)>0;
    }
}
