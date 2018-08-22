package com.xh.comm.service;

import java.util.List;
import java.util.Map;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.valid.AddUserValidBean;
import com.xh.comm.entry.valid.EditUserValidBean;

public interface LoginService {
	
	/**
	 * 获取用户
	 * @param user
	 * @return
	 */
	public List<UserBean> getUsers(UserBean user);
	
	/**
	 * 校验
	 * @param user
	 * @return
	 */
	public ResultBean checkLogin(UserBean user);
	
	/**
	 * add
	 * @param bean
	 * @return
	 */
	public ResultBean addUser(AddUserValidBean bean);
	
	/**
	 * update
	 * @param user
	 * @return
	 */
	public ResultBean updateUser(UserBean user);
	
	/**
	 * del
	 * @param user
	 * @return
	 */
	public ResultBean delUser(UserBean user);

	public ResultBean getUserList(Map<String, Object> map);

	public ResultBean getUserListByCon(Map<String, Object> map);

	public ResultBean getUserById(Map<String, Object> map);

	public ResultBean updateEditUser(EditUserValidBean user);

	public ResultBean deleteUserById(Map<String, Object> map);
	
	public Object testEhcache(String a);

	public ResultBean getUserListByIds(Map<String, Object> map, boolean b);
}
