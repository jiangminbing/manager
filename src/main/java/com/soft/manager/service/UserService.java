package com.soft.manager.service;

import com.soft.manager.dao.UserMapper;
import com.soft.manager.po.User;
import com.soft.manager.po.UserExample;
import com.soft.parent.basic.res.UserDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.springframework.beans.BeanUtils;
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
    public Result getUserByMobile(String mobile)throws Exception{
        DetailResult<UserDto> result = new DetailResult<>(ResCode.SUCCESS);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(mobile);
        List<User> list = userMapper.selectByExample(example);
        if(list!=null||list.isEmpty()) return result;
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(dto,list.get(0));
        result.setData(dto);
        return result;
    }

}
