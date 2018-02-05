package com.soft.manager.dao;

import com.soft.manager.po.UserPrivilege;
import com.soft.manager.po.UserPrivilegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPrivilegeMapper {
    long countByExample(UserPrivilegeExample example);

    int deleteByExample(UserPrivilegeExample example);

    int deleteByPrimaryKey(Integer privilegeId);

    int insert(UserPrivilege record);

    int insertSelective(UserPrivilege record);

    List<UserPrivilege> selectByExample(UserPrivilegeExample example);

    UserPrivilege selectByPrimaryKey(Integer privilegeId);

    int updateByExampleSelective(@Param("record") UserPrivilege record, @Param("example") UserPrivilegeExample example);

    int updateByExample(@Param("record") UserPrivilege record, @Param("example") UserPrivilegeExample example);

    int updateByPrimaryKeySelective(UserPrivilege record);

    int updateByPrimaryKey(UserPrivilege record);
}