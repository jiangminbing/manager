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

    /**
     * 查询第一条配送信息
     * @return
     * @throws Exception
     */
    public DetailResult<Logistics> getFristLogistics()throws Exception{
        DetailResult result = new DetailResult(ResCode.SUCCESS);
        LogisticsExample example = new LogisticsExample();
        LogisticsExample.Criteria criteria = example.createCriteria();
        criteria.andDelStateEqualTo((byte)2);
        criteria.andStateEqualTo((byte)1);
        example.setOrderByClause("create_time desc");
        example.setBegin(0);
        example.setLength(1);
        List<Logistics> list = logisticsMapper.selectByExample(example);
        result.setData(list.get(0));
        return result;
    }
}
