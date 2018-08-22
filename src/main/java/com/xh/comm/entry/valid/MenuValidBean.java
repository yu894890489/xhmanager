package com.xh.comm.entry.valid;

import org.hibernate.validator.constraints.NotBlank;

import com.xh.comm.entry.pub.BaseBean;
/**
 * validBean
 * @author yuq
 * @date 2017年8月9日
 */
public class MenuValidBean extends BaseBean{

	@NotBlank(message = "all.notblank")
    private String menuName;
	
	@NotBlank(message = "all.notblank")
    private String menuParentId;

	@NotBlank(message = "all.notblank")
    private String menuUrl;

    private String menuIconUrl;

    private String menuDesc;

    private Integer sortKey;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuIconUrl() {
		return menuIconUrl;
	}

	public void setMenuIconUrl(String menuIconUrl) {
		this.menuIconUrl = menuIconUrl;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public Integer getSortKey() {
		return sortKey;
	}

	public void setSortKey(Integer sortKey) {
		this.sortKey = sortKey;
	}
    
    
    
    
}
