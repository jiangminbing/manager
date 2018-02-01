package com.soft.manager.dao;

import com.soft.manager.po.GoodsPrice;
import com.soft.manager.po.GoodsPriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsPriceMapper {
    long countByExample(GoodsPriceExample example);

    int deleteByExample(GoodsPriceExample example);

    int deleteByPrimaryKey(Integer priceId);

    int insert(GoodsPrice record);

    int insertSelective(GoodsPrice record);

    List<GoodsPrice> selectByExample(GoodsPriceExample example);

    GoodsPrice selectByPrimaryKey(Integer priceId);

    int updateByExampleSelective(@Param("record") GoodsPrice record, @Param("example") GoodsPriceExample example);

    int updateByExample(@Param("record") GoodsPrice record, @Param("example") GoodsPriceExample example);

    int updateByPrimaryKeySelective(GoodsPrice record);

    int updateByPrimaryKey(GoodsPrice record);
}