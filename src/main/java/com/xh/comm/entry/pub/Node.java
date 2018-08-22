package com.xh.comm.entry.pub;

import java.util.List;
/**
 * 暂未使用
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO
 * @param <T>
 */
public  class  Node<T> {
	
 	  private String name;  
 	  private Long parentId;  
 	  private Long id;  
 	  private List<T> childrenList;
 	  
 	  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<T> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<T> childrenList) {
		this.childrenList = childrenList;
	}
 	  
}
