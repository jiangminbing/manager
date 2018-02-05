package com.soft.manager.service;

import com.soft.manager.dao.LogisticsMapper;
import com.soft.manager.po.Logistics;
import com.soft.manager.po.LogisticsExample;
import com.soft.parent.basic.res.LogisticsDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/5.
 */
@Service
public class LogisticsService {
    @Autowired
    private LogisticsMapper logisticsMapper;

    public DetailResult<List<LogisticsDto>> getListLogistics()throws Exception{
        DetailResult<List<LogisticsDto>> result = new DetailResult<>(ResCode.SUCCESS);
        LogisticsExample example = new LogisticsExample();
        LogisticsExample.Criteria criteria = example.createCriteria();
        criteria.andDelStateEqualTo((byte)2);
        criteria.andStateEqualTo((byte)1);
        List<Logistics> list = logisticsMapper.selectByExample(example);
        if(list==null||list.isEmpty())return result;
        List<LogisticsDto> resList = new ArrayList<>();
        for(Logistics logistics:list){
            LogisticsDto dto = new LogisticsDto();
            BeanUtils.copyProperties(logistics,dto);
            resList.add(dto);
        }
        result.setData(resList);
        return result;
    }
}
