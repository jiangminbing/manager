package com.soft.manager.service;

import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.manager.dao.UserMapper;
import com.soft.parent.manager.po.Receive;
import com.soft.parent.manager.po.ReceiveExample;
import com.soft.parent.manager.po.User;
import com.soft.parent.manager.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/1.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 根据电话号码获取用户信息
     * @param mobile
     * @return
     * @throws Exception
     */
    public User getUserByMobile(String mobile)throws Exception{
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(mobile);
        List<User> list = userMapper.selectByExample(example);
        if(list==null||list.isEmpty())return null;
        return list.get(0);
    }

    /**
     * openId 获取用户信息
     * @param openId
     * @return
     * @throws Exception
     */
    public User getUserByOpenId(String openId)throws Exception{
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openId);
        List<User> list = userMapper.selectByExample(example);
        if(list==null||list.isEmpty())return null;
        return list.get(0);
    }


}
