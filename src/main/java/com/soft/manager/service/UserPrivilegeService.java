package com.soft.manager.service;

import com.soft.manager.dao.UserPrivilegeMapper;
import com.soft.manager.po.UserPrivilege;
import com.soft.manager.po.UserPrivilegeExample;
import com.soft.parent.basic.res.UserPrivilegeDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/2.
 */
@Service
public class UserPrivilegeService {
    @Autowired
    private UserPrivilegeMapper userPrivilegeMapper;

    public DetailResult<UserPrivilegeDto> getUserPrivilegeByUser(Integer userId)throws Exception{
        UserPrivilegeExample example = new UserPrivilegeExample();
        UserPrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserPrivilege> list = userPrivilegeMapper.selectByExample(example);
        DetailResult<UserPrivilegeDto> result = new DetailResult<>(ResCode.SUCCESS);
        if(list==null||list.isEmpty())return result;
        UserPrivilege userPrivilege = list.get(0);
        UserPrivilegeDto dto = new UserPrivilegeDto();
        BeanUtils.copyProperties(dto,userPrivilege);
        result.setData(dto);
        return result;
    }
}
