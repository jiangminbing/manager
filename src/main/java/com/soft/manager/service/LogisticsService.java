package com.soft.manager.service;


import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.LogisticsMapper;
import com.soft.parent.manager.po.Logistics;
import com.soft.parent.manager.po.LogisticsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/5.
 */
@Service
public class LogisticsService {
    @Autowired
    private LogisticsMapper logisticsMapper;

    public List<Logistics> getListLogistics()throws Exception{
        LogisticsExample example = new LogisticsExample();
        LogisticsExample.Criteria criteria = example.createCriteria();
        criteria.andDelStateEqualTo((byte)2);
        criteria.andStateEqualTo((byte)1);
        List<Logistics> list = logisticsMapper.selectByExample(example);;
        return list;
    }
}
