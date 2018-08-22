package com.xh.comm.service;

import com.xh.comm.entry.UserBean;

public interface UserService {
	
	/**
	 * 查询用户的角色或分组
	 * @param userId
	 */
	void getUserOfGroup(String userId);
	
	UserBean getUserFormSession(String session);

	<T> UserBean getUserFormSession(T bean);
}
