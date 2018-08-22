package com.xh.dao;

import com.xh.comm.entry.OrgBean;
import com.xh.comm.entry.OrgBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgBeanMapper {
    int countByExample(OrgBeanExample example);

    int deleteByExample(OrgBeanExample example);

    int deleteByPrimaryKey(String orgId);

    int insert(OrgBean record);

    int insertSelective(OrgBean record);

    List<OrgBean> selectByExample(OrgBeanExample example);

    OrgBean selectByPrimaryKey(String orgId);

    int updateByExampleSelective(@Param("record") OrgBean record, @Param("example") OrgBeanExample example);

    int updateByExample(@Param("record") OrgBean record, @Param("example") OrgBeanExample example);

    int updateByPrimaryKeySelective(OrgBean record);

    int updateByPrimaryKey(OrgBean record);
}