package com.soft.manager.service;

import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.ReceiveMapper;
import com.soft.parent.manager.po.Receive;
import com.soft.parent.manager.po.ReceiveExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/10.
 */
@Service
public class UserReceiveService {
    @Autowired
    private ReceiveMapper receiveMapper;
    /**
     * 获取用户默认收货地址
     * @param userId
     * @return
     * @throws Exception
     */
    public DetailResult<Receive> getUserDefaultReceive(Integer userId) throws Exception{
        DetailResult<Receive> result = new DetailResult<>(ResCode.SUCCESS);
        ReceiveExample example = new ReceiveExample();
        ReceiveExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        /*获取用户设置的默认收货地址*/
        List<Receive> list = null;
        receiveMapper.selectByExample(example);
        if(list!=null||!list.isEmpty()){
            result.setData(list.get(0));
        }
        /*当用户没有设置默认地址取倒序*/
        criteria.andIsdefaultEqualTo((byte)0);
        example.setOrderByClause("create_time desc");
        example.setBegin(0);
        example.setLength(1);
        list = receiveMapper.selectByExample(example);
        if(list!=null||!list.isEmpty()){
            result.setData(list.get(0));
        }
        return result;
    }
}
