package com.xh.dao;

import com.xh.comm.entry.UserRoleBean;
import com.xh.comm.entry.UserRoleBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleBeanMapper {
    int countByExample(UserRoleBeanExample example);

    int deleteByExample(UserRoleBeanExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(UserRoleBean record);

    int insertSelective(UserRoleBean record);

    List<UserRoleBean> selectByExample(UserRoleBeanExample example);

    UserRoleBean selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") UserRoleBean record, @Param("example") UserRoleBeanExample example);

    int updateByExample(@Param("record") UserRoleBean record, @Param("example") UserRoleBeanExample example);

    int updateByPrimaryKeySelective(UserRoleBean record);

    int updateByPrimaryKey(UserRoleBean record);
}