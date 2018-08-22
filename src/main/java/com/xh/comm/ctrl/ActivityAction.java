package com.xh.comm.ctrl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.valid.ActDeployValidBean;
import com.xh.comm.service.ActivityService;
/**
 * act的action
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO 删除未做
 */
@Controller
@RequestMapping("/comm/act")
public class ActivityAction {

	
	@Autowired
	private ActivityService act;
	
	
	@RequestMapping(value="/deploy.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
    public @ResponseBody ResultBean deploy(@Valid @RequestBody ActDeployValidBean bean){
		return act.deploy(bean); 
    }
	
	@RequestMapping(value="/deployList.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
    public @ResponseBody ResultBean deployList( @RequestBody Map<String,Object> map){
		return act.deployList(map); 
    }
	
	@RequestMapping(value="/startProcess.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
    public @ResponseBody ResultBean startProcess( @RequestBody Map<String,Object> map){
		return act.startProcess(map); 
    }
	
	
	@RequestMapping(value="/processList.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
    public @ResponseBody ResultBean processList( @RequestBody Map<String,Object> map){
		return act.queryPro(map); 
    }
	
	@RequestMapping(value="/claim.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	public @ResponseBody ResultBean claim( @RequestBody Map<String,Object> map){
		return act.claim(map); 
	}
	
	@RequestMapping(value="/findPersonnelTaskList.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
    public @ResponseBody ResultBean findPersonnelTaskList( @RequestBody Map<String,Object> map){
		return act.findPersonnelTaskList(map); 
    }
	
	@RequestMapping(value="/complete.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
    public @ResponseBody ResultBean complete( @RequestBody Map<String,Object> map){
		return act.complete(map); 
    }
	
	@RequestMapping(value="/history.do")  
    public  @ResponseBody ResultBean history(HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getParameterMap());
		return act.history(null); 
    }
	
	
}
