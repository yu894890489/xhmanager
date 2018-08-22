package com.xh.comm.activiti;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.xh.comm.entry.UserBean;
import com.xh.comm.util.StringUtils;


/**
 * act的公共方法
 * 
 * @author yuq
 * @date 2017年7月17日
 * @todo TODO 基本功能完成需要和具体业务结合
 */
@Component
public class ActivitiComm {

//	@Autowired
//	private ProcessEngineFactoryBean processEngine;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
//	@Autowired
//	private FormService formService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	
	//字符串方式  
	public void deploy(String text,String name){
		try {
			repositoryService.createDeployment()//创建部署对象  
            .addString(name+".bpmn",text)                    
            .deploy();//完成部署
		} catch (Exception e) {
			throw new ActivitiException("部署失败！", e);
		}
	}  
	/**
	 * 部署流程
	 * 
	 * @param exportDir
	 * @param category
	 * @param file
	 * @return
	 */
	public String deploy(String exportDir, String category, MultipartFile file) {

		String message = "";

		String fileName = file.getOriginalFilename();

		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment = null;
			String extension = FilenameUtils.getExtension(fileName);
			DeploymentBuilder builder = repositoryService.createDeployment().name(extension);
			if (extension.equals("zip")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = builder.addZipInputStream(zip).deploy();
			} else if (extension.equals("png")) {
				deployment = builder.addInputStream(fileName, fileInputStream).deploy();
			} else if (fileName.indexOf("bpmn20.xml") != -1) {
				deployment = builder.addInputStream(fileName, fileInputStream).deploy();
			} else if (extension.equals("bpmn")) { // bpmn扩展名特殊处理，转换为bpmn20.xml
				String baseName = FilenameUtils.getBaseName(fileName);
				deployment = builder.addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
			} else {
				message = "不支持的文件类型：" + extension;
			}
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
					.deploymentId(deployment.getId()).list();

			// 设置流程分类
			for (ProcessDefinition processDefinition : list) {
				// ActUtils.exportDiagramToFile(repositoryService,
				// processDefinition, exportDir);
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
				message += "部署成功，流程ID=" + processDefinition.getId() + "<br/>";
			}

			if (list.size() == 0) {
				message = "部署失败，没有流程。";
			}

		} catch (Exception e) {
			throw new ActivitiException("部署失败！", e);
		}
		return message;
	}
	
	/**
	 * 查看所有部署实例
	 * @return
	 */
	public List<Deployment> deployList(){
		return repositoryService.createDeploymentQuery().list();
	}
	
	/**
	 * 启动流程和插入流程数据
	 * @param key 流程的key
	 * @param title
	 * @param vars 参数bean
	 * @return
	 */
	public String startProcess(String key,Map<String,Object> vars,UserBean user) {
		if(vars == null){
			vars = new HashMap<String,Object>();
		}
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(user.getUserId());
		// 启动流程
		ProcessInstance procIns = runtimeService.startProcessInstanceByKey(key,vars);
		return procIns.getId();
	}
	
	/**
	 * 启动流程和插入流程数据
	 * @param id 流程的id
	 * @param title
	 * @param vars 参数bean
	 * @return
	 */
	public String startProcessById(String id,Map<String,Object> vars,UserBean user) {
		if(vars == null){
			vars = new HashMap<String,Object>();
		}
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(user.getUserId());
		// 启动流程
		ProcessInstance procIns = runtimeService.startProcessInstanceById(id,vars);
		return procIns.getId();
	}
	
	/**
	 * 查询所有该用户权限下的任务
	 * @param group
	 * @return
	 * @TODO 没有测试认领的任务也能被查询到
	 */
	public List<Task> queryPro(String group){
		if(StringUtils.isEmpty(group)){
			return taskService.createTaskQuery().list();
		}
		return taskService// 与任务相关的Service
		.createTaskQuery()// 创建一个任务查询对象
		.taskCandidateGroup(group).list();
	}
	
	/**
	 * 认领任务
	 * @param taskId
	 * @param userId
	 */
	public void Claim(String taskId,String userId){
		 taskService.claim(taskId, userId);
	}
	
	
	/**
	 *  查看当前任务办理人的个人任务
	 * @param user
	 * @return
	 */
	public List<Task> findPersonnelTaskList(String userId){
		return taskService// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				.taskAssignee(userId).list();
	}
	
	
	/**
	 * 完成当前步流程
	 * @param flag
	 * @param vars
	 * @return
	 */
	public void complete(String taskId,Map<String,Object> vars){
		 taskService.complete(taskId,vars);
	}
	
	/**
	 * 查看历史实例 暂时是所有的
	 * @return
	 */
	public List<HistoricActivityInstance> history() {
		return historyService.createHistoricActivityInstanceQuery().list();
	}
	
}
