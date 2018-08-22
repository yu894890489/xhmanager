package com.xh.dao;

import com.xh.comm.entry.RoleBean;
import com.xh.comm.entry.RoleBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleBeanMapper {
    int countByExample(RoleBeanExample example);

    int deleteByExample(RoleBeanExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(RoleBean record);

    int insertSelective(RoleBean record);

    List<RoleBean> selectByExample(RoleBeanExample example);

    RoleBean selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") RoleBean record, @Param("example") RoleBeanExample example);

    int updateByExample(@Param("record") RoleBean record, @Param("example") RoleBeanExample example);

    int updateByPrimaryKeySelective(RoleBean record);

    int updateByPrimaryKey(RoleBean record);
}