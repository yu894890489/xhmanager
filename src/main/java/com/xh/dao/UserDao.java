package com.xh.dao;

import java.util.List;
import java.util.Map;

import com.xh.comm.entry.UserBean;

public interface UserDao {

	public List<UserBean> getUser(UserBean user);
	
	@SuppressWarnings("rawtypes")
	public List<Map> getUserMap(UserBean user);

	public void addUserLoginInfo(UserBean user);
}
