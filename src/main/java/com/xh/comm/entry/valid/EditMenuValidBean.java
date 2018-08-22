package com.xh.comm.entry.valid;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author yuq
 * @date 2017年9月12日
 */
public class EditMenuValidBean extends MenuValidBean{

	@NotBlank(message = "all.notblank")
	private String menuId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
