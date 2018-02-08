package com.soft.manager.service;

import com.soft.parent.manager.dao.UserPrivilegeMapper;
import com.soft.parent.manager.po.UserPrivilege;
import com.soft.parent.manager.po.UserPrivilegeExample;
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

    public UserPrivilege getUserPrivilegeByUser(Integer userId)throws Exception{
        UserPrivilegeExample example = new UserPrivilegeExample();
        UserPrivilegeExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserPrivilege> list = userPrivilegeMapper.selectByExample(example);
        if(list!=null&&!list.isEmpty()){
           return list.get(0);
        }
        return  null;
    }
}
