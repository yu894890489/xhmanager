package com.xh.dao;

import com.xh.comm.entry.UserOrgBean;
import com.xh.comm.entry.UserOrgBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOrgBeanMapper {
    int countByExample(UserOrgBeanExample example);

    int deleteByExample(UserOrgBeanExample example);

    int deleteByPrimaryKey(String userOrgId);

    int insert(UserOrgBean record);

    int insertSelective(UserOrgBean record);

    List<UserOrgBean> selectByExample(UserOrgBeanExample example);

    UserOrgBean selectByPrimaryKey(String userOrgId);

    int updateByExampleSelective(@Param("record") UserOrgBean record, @Param("example") UserOrgBeanExample example);

    int updateByExample(@Param("record") UserOrgBean record, @Param("example") UserOrgBeanExample example);

    int updateByPrimaryKeySelective(UserOrgBean record);

    int updateByPrimaryKey(UserOrgBean record);
}