package com.xh.comm.entry;

import java.util.List;
import java.util.Map;

public class ResultBean {

	/**
	 * 严重的失败或登陆不通过和session过期的标志
	 */
	public final static int  RESULTTYPE_FAILURE = 6;
	/**成功 */
	public final static int RESULTTYPE_SUCCESS = 1;
	/** 程序级的错误 只会提示 */
	public final static int RESULTTYPE_ERROR = 2;
	
	
	private String msg = "it is Good!";
	
	/** 此字段会在ajax里做校验如果为6时会返回登陆页面，默认为成功 */
	private int resultType = RESULTTYPE_SUCCESS;
	
	private List<Object> list;
	
	private Map<String,Object> map;
	
	/**存放JSON */
	private String json;
	
	/**
	 * 
	 * 1.当返回时需要带boolean类型的数据时使用，即某种开关数据
	 * 2.传给前台基于后台的检验结果（如果为true则检验通过，否则不通过）注：使用valid字段源于bootstrapvalidator(nghuuphuoc版)）
	 */
	private boolean valid;
	
	
	
	
	public boolean getValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public int getResultType() {
		return resultType;
	}
	public void setResultType(int resultType) {
		this.resultType = resultType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
	
}
