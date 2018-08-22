package com.xh.dao;

import com.xh.comm.entry.RoleMenuBean;
import com.xh.comm.entry.RoleMenuBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuBeanMapper {
    int countByExample(RoleMenuBeanExample example);

    int deleteByExample(RoleMenuBeanExample example);

    int deleteByPrimaryKey(String roleMenuId);

    int insert(RoleMenuBean record);

    int insertSelective(RoleMenuBean record);

    List<RoleMenuBean> selectByExample(RoleMenuBeanExample example);

    RoleMenuBean selectByPrimaryKey(String roleMenuId);

    int updateByExampleSelective(@Param("record") RoleMenuBean record, @Param("example") RoleMenuBeanExample example);

    int updateByExample(@Param("record") RoleMenuBean record, @Param("example") RoleMenuBeanExample example);

    int updateByPrimaryKeySelective(RoleMenuBean record);

    int updateByPrimaryKey(RoleMenuBean record);
}