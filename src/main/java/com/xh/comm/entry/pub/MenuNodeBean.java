package com.xh.comm.entry.pub;

import java.util.List;

import com.xh.comm.entry.MenuBean;
/**
 * 暂未使用
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO
 */
public class MenuNodeBean extends MenuBean{

	private List<MenuBean> children;

	public List<MenuBean> getChildren() {
		return children;
	}

	public void setChildren(List<MenuBean> children) {
		this.children = children;
	}
	
	
}
