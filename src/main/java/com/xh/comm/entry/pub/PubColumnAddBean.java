package com.xh.comm.entry.pub;

import java.util.Date;

/**
 * 插入时的公共字段
 * @author yuq
 * @date 2017年7月6日
 */
public class PubColumnAddBean extends PubColumnUpdBean {
	
	private String creater;
	private Date createTime;
	
	
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
