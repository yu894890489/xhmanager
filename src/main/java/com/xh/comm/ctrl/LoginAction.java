package com.xh.comm.ctrl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.valid.AddUserValidBean;
import com.xh.comm.entry.valid.EditUserValidBean;
import com.xh.comm.entry.valid.UserValidBean;
import com.xh.comm.service.LoginService;
import com.xh.comm.util.BeanUtil;
/**
 * 主要是对用户操作的Ctr
 * @author yuq
 * @date 2017年8月31日
 */
@Controller
@RequestMapping("/comm")
public class LoginAction {
	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
	
	@Autowired
	private LoginService loginService;
	
	
	 @RequestMapping(value="/login.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean login(HttpServletRequest request, HttpServletResponse response,
	    		@Valid @RequestBody UserValidBean user){
		 log.info("/comm/login.do");
		 UserBean userBean = new UserBean();
		 BeanUtil.BeanToBean(userBean, user);
		 return loginService.checkLogin(userBean);
	    }
	 
	 @RequestMapping(value="/register.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean register(@Valid @RequestBody AddUserValidBean bean){
		 	log.info("/comm/register.do");
	        return loginService.addUser(bean);  
	    }
	 
	 @RequestMapping(value="/getUserList.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean getUserList(@Valid @RequestBody Map<String,Object> map){
		 log.info("/comm/getUserList.do");
	        return loginService.getUserList(map);  
	    }
	 
	 @RequestMapping(value="/getUserById.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean getUserById(@Valid @RequestBody Map<String,Object> map){
		 log.info("/comm/getUserById.do");
	        return loginService.getUserById(map);  
	    }
	 
	 @RequestMapping(value="/getUserListByCon.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean getUserListByCon(@Valid @RequestBody Map<String,Object> map){
		 log.info("/comm/getUserListByCon.do");
	        return loginService.getUserListByCon(map);  
	    }
	 
	 	@RequestMapping(value="/getUserListByInIds.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean getUserListByInIds(@Valid @RequestBody Map<String,Object> map){
		 log.info("/comm/getUserListByCon.do");
	        return loginService.getUserListByIds(map,true);  
	    }
	 	
	 	@RequestMapping(value="/getUserListByNotInIds.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean getUserListByNotInIds(@Valid @RequestBody Map<String,Object> map){
		 log.info("/comm/getUserListByCon.do");
	        return loginService.getUserListByIds(map,false);  
	    }
	 
	 @RequestMapping(value="/updateEditUser.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean updateEditUser(@Valid @RequestBody EditUserValidBean bean){
		 log.info("/comm/updateEditUser.do");
	        return loginService.updateEditUser(bean);  
	    }
	 
	 @RequestMapping(value="/deleteUserById.do",consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })  
	    public @ResponseBody ResultBean deleteUserById(@Valid @RequestBody Map<String,Object> map){
		 log.info("/comm/deleteUserById.do");
	        return loginService.deleteUserById(map);  
	    }
}
