package com.xh.comm.entry.pub;
/**
 * 检验bean的公共数据bean 主要是为了接收公共数据减少bean字段的重复
 * @author yuq
 * @date 2017年8月9日
 * @todo 
 */
public class BaseBean {

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
