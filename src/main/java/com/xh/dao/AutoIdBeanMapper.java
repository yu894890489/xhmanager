package com.xh.dao;

import com.xh.comm.entry.AutoIdBean;
import com.xh.comm.entry.AutoIdBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AutoIdBeanMapper {
    int countByExample(AutoIdBeanExample example);

    int deleteByExample(AutoIdBeanExample example);

    int deleteByPrimaryKey(String code);

    int insert(AutoIdBean record);

    int insertSelective(AutoIdBean record);

    List<AutoIdBean> selectByExample(AutoIdBeanExample example);

    AutoIdBean selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") AutoIdBean record, @Param("example") AutoIdBeanExample example);

    int updateByExample(@Param("record") AutoIdBean record, @Param("example") AutoIdBeanExample example);

    int updateByPrimaryKeySelective(AutoIdBean record);

    int updateByPrimaryKey(AutoIdBean record);
}