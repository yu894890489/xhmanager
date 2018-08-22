package com.xh.comm.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Task;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xh.comm.activiti.ActivitiComm;
import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.valid.ActDeployValidBean;
import com.xh.comm.service.ActivityService;
import com.xh.comm.util.StaticDict;
import com.xh.comm.util.StringUtils;
import com.xh.comm.util.base.BASEUtils;
import com.xh.dao.UserBeanMapper;
/**
 * 
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO 与业务的结合
 */
@Service
public class ActivityServiceImpl implements ActivityService{

	
	@Autowired
	private ActivitiComm comm;
	@Autowired
	private UserBeanMapper dao;
	
	@Override
	public ResultBean deploy(ActDeployValidBean bean) {
		comm.deploy(bean.getProcessCode(), bean.getProcessName());
		ResultBean result = new ResultBean();
		result.setResultType(ResultBean.RESULTTYPE_SUCCESS);
		return result;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public ResultBean deployList(Map<String, Object> map) {
		List list = comm.deployList();
		
		ResultBean resultBean = new ResultBean();
		resultBean.setJson(new JSONArray(list).toString());
		resultBean.setResultType(ResultBean.RESULTTYPE_SUCCESS);
		return resultBean;
	}
	

	@SuppressWarnings("unused")
	@Override
	public ResultBean startProcess(Map<String,Object> map) {
		String startProcessById = "";
		UserBean user = getUser(map);
		// TODO 这里的坏代码仅做测试用  后期换掉
		if(false&&StringUtils.isNotEmpty((String) map.get("processID"))){
			startProcessById = comm.startProcessById((String)map.get("processID"), map, user);//以下所有的传入的map都是测试用   不是正规的业务数据
		}else if(StringUtils.isNotEmpty((String)map.get("processDefinitionId"))){
			startProcessById = comm.startProcessById((String)map.get("processDefinitionId"), map, user);
		}else{
			startProcessById = comm.startProcess("myProcess", map, user);
			
		}
		ResultBean result = new ResultBean();
		result.setMsg(startProcessById);
		return result;
	}


	@Override
	public ResultBean queryPro(Map<String, Object> map) {
		List<Task> list = null;
		ResultBean result = new ResultBean();
		if(StringUtils.isNotEmpty((String)map.get("group"))){
			list = comm.queryPro((String)map.get("group"));
			result.setJson(new JSONArray(list).toString());
			return result;
		}
		list = comm.queryPro(null);
		result.setJson(new JSONArray(list).toString());
		return result;
	}


	@Override
	public ResultBean claim(Map<String, Object> map) {
		UserBean user = this.getUser(map);
		comm.Claim((String)map.get("taskId"), user.getUserId());
		ResultBean result = new ResultBean();
		result.setMsg((String)map.get("taskId"));
		return result;
	}


	@Override
	public ResultBean findPersonnelTaskList(Map<String, Object> map) {
		UserBean user = getUser(map);
		List<Task> taskList = comm.findPersonnelTaskList(user.getUserId());
		ResultBean result = new ResultBean();
		result.setJson(new JSONArray(taskList).toString());
		return result;
	}


	@Override
	public ResultBean complete(Map<String,Object> map) {
		comm.complete((String)map.get("taskId"), map);//该传入的map只是测试 不是正规的业务数据
		ResultBean result = new ResultBean();
		result.setJson(new JSONArray((String)map.get("taskId")).toString());
		return result;
	}

	/**
	 * 从seesion里取出用户ID去DB里查询  防止用户更改Session
	 * @param map
	 * @return
	 */
	private UserBean getUser(Map<String,Object> map){
		@SuppressWarnings("unchecked")
		Map<String,String> data = (Map<String,String>) map.get(StaticDict.DATA_NAME);
		return dao.selectByPrimaryKey(BASEUtils.encUser(data.get(StaticDict.SESSION_NAME)));
	}

	@Override
	public ResultBean history(Map<String, Object> map) {
		List<HistoricActivityInstance> list = comm.history();
		ResultBean result = new ResultBean();
		result.setJson(new JSONArray(list).toString());
		return result;
	}
	
	
}
