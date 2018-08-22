package com.xh.comm.entry.valid;

import com.xh.comm.entry.pub.BaseBean;

public class AutoIdValidBean extends BaseBean{

	private String code;

    private Integer curNum;


    private Integer maxNum;

    private String projectName;

    private Integer minNum;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCurNum() {
		return curNum;
	}

	public void setCurNum(Integer curNum) {
		this.curNum = curNum;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}
    
    
}
