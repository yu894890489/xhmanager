package com.xh.comm.entry.valid;

import org.hibernate.validator.constraints.NotBlank;

import com.xh.comm.entry.pub.BaseBean;

/**
 * validBean
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO 信息补全
 */
public class RoleValidBean extends BaseBean{

	@NotBlank
	private String roleName;
	
	@NotBlank
	private String roleDesc;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
}
