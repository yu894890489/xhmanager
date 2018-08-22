package com.xh.dao;

import java.util.List;

import com.xh.comm.entry.DictBean;
import com.xh.comm.entry.MenuBean;
import com.xh.comm.entry.RoleMenuBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.UserOrgBean;

public interface DictDao {

	List<DictBean> getRoleDict ();
	
	
	List<MenuBean> getRoleMenu (List<String> list);
	
	void addRoleMenu (List<RoleMenuBean> list);
	
	void addUserOrg(List<UserOrgBean> list);
	
	List<UserBean> getUserIdByOrgId(String orgId);
	
	List<UserBean> getUserIdByNotOrgId(String orgId);
	
}
