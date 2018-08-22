package com.xh.dao;

import com.xh.comm.entry.UserLoginInfoBean;
import com.xh.comm.entry.UserLoginInfoBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLoginInfoBeanMapper {
    int countByExample(UserLoginInfoBeanExample example);

    int deleteByExample(UserLoginInfoBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginInfoBean record);

    int insertSelective(UserLoginInfoBean record);

    List<UserLoginInfoBean> selectByExample(UserLoginInfoBeanExample example);

    UserLoginInfoBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLoginInfoBean record, @Param("example") UserLoginInfoBeanExample example);

    int updateByExample(@Param("record") UserLoginInfoBean record, @Param("example") UserLoginInfoBeanExample example);

    int updateByPrimaryKeySelective(UserLoginInfoBean record);

    int updateByPrimaryKey(UserLoginInfoBean record);
}