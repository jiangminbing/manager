package com.soft.manager.service;

import com.soft.manager.dao.ShoppingCartMapper;
import com.soft.manager.po.ShoppingCart;
import com.soft.manager.po.ShoppingCartExample;
import com.soft.parent.basic.res.ShoppingCartDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import org.springframework.beans.BeanUtils;
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
    public DetailResult<ShoppingCartDto> queryShoppingCartByUserIdAndPriceId(Integer userId,Integer priceId)throws Exception{
        ShoppingCartExample example = new ShoppingCartExample();
        ShoppingCartExample.Criteria  criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPriceIdEqualTo(priceId);
        List<ShoppingCart> list = shoppingCartMapper.selectByExample(example);
        DetailResult<ShoppingCartDto> result = new DetailResult<>(ResCode.SUCCESS);
        if(list==null||list.isEmpty()){
            result.setData(null);
            return result;
        }else {
            ShoppingCartDto dto = new ShoppingCartDto();
            BeanUtils.copyProperties(list.get(0),dto);
            result.setData(dto);
            return result;
        }

    }
}
