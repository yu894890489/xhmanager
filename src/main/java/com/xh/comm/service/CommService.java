package com.xh.comm.service;

import java.util.Map;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.valid.AutoIdValidBean;
import com.xh.comm.entry.valid.EditMenuValidBean;
import com.xh.comm.entry.valid.EditOrgValidBean;
import com.xh.comm.entry.valid.EditUserOrgValidBean;
import com.xh.comm.entry.valid.MenuValidBean;
import com.xh.comm.entry.valid.OrgValidBean;
import com.xh.comm.entry.valid.RoleMenuValidBean;
import com.xh.comm.entry.valid.RoleValidBean;

/**
 * 
 * @author yuq
 * @date 2017年9月13日
 * @todo TODO
 */
public interface CommService {
	ResultBean getDict(Map<String,Object> map);

	ResultBean getMenu(Map<String, Object> map);

	ResultBean addMenu(MenuValidBean bean);

	ResultBean getRoleList(Map<String, Object> map);

	ResultBean addRole(RoleValidBean bean);

	ResultBean getRoleById(Map<String, Object> map);

	ResultBean addRoleMenu(RoleMenuValidBean bean);

	ResultBean addSeq(AutoIdValidBean bean);
	
	ResultBean updateSeq(AutoIdValidBean bean);
	
	ResultBean getSeq(Map<String, Object> map);

	ResultBean getSeq(String string);

	ResultBean getMenuList(Map<String, Object> map);

	ResultBean getMenuForOne(Map<String, Object> map);

	ResultBean updateMenu(EditMenuValidBean bean);

	ResultBean addOrg(OrgValidBean bean);

	ResultBean updateOrg(EditOrgValidBean bean);

	ResultBean delOrg(Map<String, Object> map);

	ResultBean getOrgTree(Map<String, Object> map);

	ResultBean getOrgList(Map<String, Object> map);

	ResultBean getOrgForOne(Map<String, Object> map);

	ResultBean getUsersByOrg(Map<String, Object> map);

	ResultBean addUsersByOrg(Map<String, Object> map);

	ResultBean updateUserOrg(EditUserOrgValidBean bean);

	ResultBean delUserOrg(Map<String, Object> map);
}
