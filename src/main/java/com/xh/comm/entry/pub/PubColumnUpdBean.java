package com.xh.comm.entry.pub;

import java.util.Date;

/**
 * 更新表时的公共字段
 * @author yuq
 * @date 2017年7月6日
 */
public  class PubColumnUpdBean{

    private String updater;

    private Date updateTime;


	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}
