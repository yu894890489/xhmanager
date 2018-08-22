package com.xh.dao;

import com.xh.comm.entry.MenuBean;
import com.xh.comm.entry.MenuBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuBeanMapper {
    int countByExample(MenuBeanExample example);

    int deleteByExample(MenuBeanExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(MenuBean record);

    int insertSelective(MenuBean record);

    List<MenuBean> selectByExample(MenuBeanExample example);

    MenuBean selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") MenuBean record, @Param("example") MenuBeanExample example);

    int updateByExample(@Param("record") MenuBean record, @Param("example") MenuBeanExample example);

    int updateByPrimaryKeySelective(MenuBean record);

    int updateByPrimaryKey(MenuBean record);
}