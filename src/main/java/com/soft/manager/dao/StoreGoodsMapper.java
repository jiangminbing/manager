package com.soft.manager.dao;

import com.soft.manager.po.StoreGoods;
import com.soft.manager.po.StoreGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreGoodsMapper {
    long countByExample(StoreGoodsExample example);

    int deleteByExample(StoreGoodsExample example);

    int deleteByPrimaryKey(Integer storeId);

    int insert(StoreGoods record);

    int insertSelective(StoreGoods record);

    List<StoreGoods> selectByExample(StoreGoodsExample example);

    StoreGoods selectByPrimaryKey(Integer storeId);

    int updateByExampleSelective(@Param("record") StoreGoods record, @Param("example") StoreGoodsExample example);

    int updateByExample(@Param("record") StoreGoods record, @Param("example") StoreGoodsExample example);

    int updateByPrimaryKeySelective(StoreGoods record);

    int updateByPrimaryKey(StoreGoods record);
}