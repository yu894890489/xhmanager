package com.xh.comm.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xh.comm.entry.UserBean;

/**
 * 
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO 暂时的思路是用做获取用户信息等等公共信息的，但由于request不能直接获取数据  先停止使用
 */
public class BaseAction {

	
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}
	
	public UserBean getUser(){
		//HttpServletRequest request = getRequest();
		//String request.getAttribute(StaticDict.SESSION_NAME);
		return null;
	}

}
