package com.xh.comm.entry.valid;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.xh.comm.entry.pub.BaseBean;

/**
 * validBean
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO 信息补全
 */
public class RoleMenuValidBean extends BaseBean{
	
	@NotBlank
	private String roleId;
	
	private String roleIdName;
	
	private String roleDesc;
	
	@NotBlank
	private List<String> roleTree;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleIdName() {
		return roleIdName;
	}

	public void setRoleIdName(String roleIdName) {
		this.roleIdName = roleIdName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<String> getRoleTree() {
		return roleTree;
	}

	public void setRoleTree(List<String> roleTree) {
		this.roleTree = roleTree;
	}
	
	
	
}
