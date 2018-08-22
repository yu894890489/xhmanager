package com.test.BeanUtil;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.UserLoginInfoBean;
import com.xh.comm.util.BeanUtil;

public class TestBeanu {
	
	@Test
	public void Test() throws IllegalAccessException, InvocationTargetException{
		UserBean userBean = new UserBean();
		//userBean.setUserId("asdasd");
		userBean.setCreater("yu");
		UserLoginInfoBean bean = new UserLoginInfoBean();
		bean.setUpdater("asda");
		bean.setUserId(">>>");
		BeanUtil.BeanToBean(bean, userBean);
		//System.out.println(bean.getCreater()+bean.getUpdater()+bean.getUserId()+bean.getUserLoginInfoId());
		
	}
}
