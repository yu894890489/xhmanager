package com.test.activity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseJunitSpring;

public class testActivity extends BaseJunitSpring {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService ts;
	@Autowired
	private HistoryService hs;
	
	@Test
	public void testQueryHistory(){
		List<HistoricActivityInstance> list = hs.createHistoricActivityInstanceQuery().list();
		System.out.println(new JSONArray(list));
	}
	
	
	String assignee = "总经理";// 当前任务办理人
	@Test
	public void testComplete() {
		List<Task> tasks = ts// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				.taskAssignee(assignee).list();
		if (tasks != null && tasks.size() > 0) {
			for (Task task : tasks) {
				//完成任务的同时，设置流程变量，让流程变量判断连线该如何执行  
			    Map<String, Object> variables = new HashMap<String, Object>();  
			    variables.put("pass", "1");  
			    ts.complete(task.getId(),variables);
			    System.out.println(task);
			    System.out.println("完成");
			}
		}
	}

	/** 查看当前任务办理人的个人任务 */
	@Test
	public void findPersonnelTaskList() {
		List<Task> tasks = ts// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				.taskAssignee(assignee).list();
		if (tasks != null && tasks.size() > 0) {
			for (Task task : tasks) {
				System.out.println("任务ID:" + task.getId());
				System.out.println("任务的办理人:" + task.getAssignee());
				System.out.println("任务名称:" + task.getName());
				System.out.println("任务的创建时间:" + task.getCreateTime());
				System.out.println("任务ID:" + task.getId());
				System.out.println("流程实例ID:" + task.getProcessInstanceId());
				System.out.println("#####################################");
			}
		}
	}

	@Test
	public void getBusinessObjId() {
		// //1 获取任务对象
		// Task task =
		// taskService.createTaskQuery().taskId(taskId).singleResult();
		//
		// //2 通过任务对象获取流程实例
		// ProcessInstance pi =
		// runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		// //3 通过流程实例获取“业务键”
		// String businessKey = pi.getBusinessKey();
		// //4 拆分业务键，拆分成“业务对象名称”和“业务对象ID”的数组
		// // a=b LeaveBill.1
		// String objId = null;
		// if(StringUtils.isNotBlank(businessKey)){
		// objId = businessKey.split("\\.")[1];
		// }
		// return objId;
	}
	String group = "tt";
	@Test
	public void testClaim() {
		String claimer = "总经理";
		List<Task> tasks = ts// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				.taskCandidateGroup(group).list();
		for (Task task : tasks) {
			System.out.println("任务ID:" + task.getId());
			System.out.println("任务的办理人:" + task.getAssignee());
			System.out.println("任务名称:" + task.getName());
			System.out.println("任务的创建时间:" + task.getCreateTime());
			System.out.println("任务ID:" + task.getId());
			System.out.println("流程实例ID:" + task.getProcessInstanceId());
			System.out.println(ts.getVariables(task.getId()));
			System.out.println("#####################################");
			ts.claim(task.getId(), claimer);
		}

	}
	
	
	@Test
	public void queryPro(){
		List<Task> tasks = ts// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				.taskCandidateGroup(group).list();
		System.out.println(tasks);
	}
	
	@Test
	public void queryAllTask(){
		List<Task> tasks = ts// 与任务相关的Service
				.createTaskQuery()// 创建一个任务查询对象
				.list();
		System.out.println(tasks);
	}
	
	@Test
	public void testStartById(){
		String id = "myProcess:1:4";
		ProcessInstance pi = runtimeService.startProcessInstanceById(id);
		System.out.println("流程实例ID：" + pi.getId());// 流程实例ID：101
		System.out.println(pi.getName());
		System.out.println("流程实例ID：" + pi.getProcessInstanceId());// 流程实例ID：101
		System.out.println("流程实例ID:" + pi.getProcessDefinitionId());// myMyHelloWorld:1:4
	}
	
	
	@Test
	public void testStart() {
		String processDefinitionKey = "myProcess";// 流程定义的key,也就是bpmn中存在的ID
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sdsd", "sds");
		map.put("a", "asdadad");
		// = runtimeService.startProcessInstanceByKey(processDefinitionKey,new
		// JSONObject(map).toString());////按照流程定义的key启动流程实例
		// ProcessInstance pi =
		// runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
		// runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey,
		// "yyyyyyy", map);
		System.out.println("流程实例ID：" + pi.getId());// 流程实例ID：101

		System.out.println("流程实例ID：" + pi.getProcessInstanceId());// 流程实例ID：101
		System.out.println("流程实例ID:" + pi.getProcessDefinitionId());// myMyHelloWorld:1:4
	}
	
	@Test
	public void testProcessList() throws IOException{
		System.out.println(repositoryService.createDeploymentQuery().list());
		
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		for (ProcessDefinition p : list) {
			System.out.println(p.getDiagramResourceName());
			InputStream stream = repositoryService.getResourceAsStream(p.getDeploymentId(), p.getDiagramResourceName());
			OutputStream  outputStream = new FileOutputStream(p.getDeploymentId()+p.getDiagramResourceName());
			int byteCount = 1024;
			byte[] bytes = new byte[byteCount];
			while ((byteCount = stream.read(bytes)) != -1)
			{
			          outputStream.write(bytes);
			}
			stream.close();
			outputStream.close();

		}
		}
	
	@Test
	public void testDelpoyList(){
		
		List<Deployment> list = repositoryService.createDeploymentQuery().list();
		for (Deployment d : list) {
			d.getDeploymentTime();
		}
		System.out.println();
	}
	
	/**
	 * 部署
	 */
	@Test
	public void testDeploy() {
		String fileName = "E:/ideaWorkspace/my/code/xhBeta3/src/test/java/com/test/activity/testOne.bpmn";
		String baseName = FilenameUtils.getBaseName(fileName);
		System.out.println(baseName);
		InputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(fileName);
			Deployment deploy = repositoryService.createDeployment().name("testiiii")
					.addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
			System.out.println(deploy);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testXmlDeploy() throws IOException{
		int len=0;
		StringBuffer str=new StringBuffer("");
		 FileInputStream fileInputStream = new FileInputStream("C:/Users/Administrator/Desktop/testFile.txt");
		 InputStreamReader is = new InputStreamReader(fileInputStream);
		 BufferedReader buffer = new BufferedReader(is);
		 String xml = "";
		 while((xml=buffer.readLine())!=null){
			 if(len != 0)  // 处理换行符的问题
             {

                 str.append("\r\n"+xml);
             } else

             {

                 str.append(xml);

             }
			 
		 }
		 buffer.close();

         is.close();
         Deployment deploy = repositoryService.createDeployment().name("yu").addString("yu",str.toString()) 
         .deploy();//完成部署 
         System.out.println("部署ID："+deploy.getId());//1  
         System.out.println("部署时间："+deploy.getDeploymentTime());  
	}
}
