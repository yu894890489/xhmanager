package com.xh.comm.entry.valid;

import org.hibernate.validator.constraints.NotBlank;

import com.xh.comm.entry.pub.BaseBean;
/**
 * validBean
 * @author yuq
 * @date 2017年8月9日
 */
public class ActDeployValidBean extends BaseBean{
	
	@NotBlank(message = "{all.notblank}")
	private String processName;
	
	@NotBlank(message = "{all.notblank}")
	private String processCode;
	

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	
	
}
