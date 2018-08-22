package com.xh.comm.activiti;

import java.util.Date;
/**
 * act参数的Entry
 * @author yuq
 * @date 2017年7月18日
 * @todo TODO actBean
 */
public class ActivityBean {

	private String userId;
	private Object data;
	private Date createTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
