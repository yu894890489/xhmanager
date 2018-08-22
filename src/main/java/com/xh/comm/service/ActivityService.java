package com.xh.comm.service;

import java.util.Map;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.valid.ActDeployValidBean;

public interface ActivityService {

	ResultBean deploy(ActDeployValidBean bean);
	
	ResultBean  startProcess(Map<String,Object> map);
	
	ResultBean  queryPro(Map<String, Object> map);
	
	ResultBean  findPersonnelTaskList(Map<String, Object> map);
	
	ResultBean  complete(Map<String,Object> vars);

	ResultBean deployList(Map<String, Object> map);

	ResultBean claim(Map<String, Object> map);

	ResultBean history(Map<String, Object> map);
}
