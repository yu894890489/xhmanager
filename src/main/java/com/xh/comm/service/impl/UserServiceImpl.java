package com.xh.comm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xh.comm.entry.UserBean;
import com.xh.comm.service.UserService;
import com.xh.comm.util.CommUtils;
import com.xh.comm.util.StringUtils;
import com.xh.comm.util.base.BASEUtils;
import com.xh.dao.UserBeanMapper;

/**
 * 
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserBeanMapper dao;
	
	@Override
	public void getUserOfGroup(String userId) {
		//dao.sele

	}
	/**
	 * 目前的全局获取用户的方法
	 */
	@Cacheable(value="xhcache",key="#session")
	public UserBean getUserFormSession(String session){
		if(StringUtils.isEmpty(session)){
			return null;
		}
		return dao.selectByPrimaryKey(BASEUtils.encUser(session));
	}
	
	/**
	 * 目前的全局获取用户的方法
	 * @param <T>
	 */
	@Override
	public <T> UserBean getUserFormSession(T bean){
		String session = CommUtils.getSession(bean);
		return getUserFormSession(session);
	}
}
