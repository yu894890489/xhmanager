package com.xh.comm.ctrl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.valid.AutoIdValidBean;
import com.xh.comm.entry.valid.EditMenuValidBean;
import com.xh.comm.entry.valid.EditOrgValidBean;
import com.xh.comm.entry.valid.MenuValidBean;
import com.xh.comm.entry.valid.OrgValidBean;
import com.xh.comm.entry.valid.RoleMenuValidBean;
import com.xh.comm.entry.valid.RoleValidBean;
import com.xh.comm.service.CommService;
import com.xh.comm.util.BeanUtil;
import com.xh.comm.util.StaticDict;

/**
 * 公共功能和一些不好区分的功能的Action
 * @author yuq
 * @date 2017年6月28日
 * @todo 暂无
 */
@Controller
@RequestMapping("/comm")
public class CommAction {

	@Autowired
	private CommService service;
	 @RequestMapping(value="/dict.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean dict(  @RequestBody Map<String,Object> map){
		 return service.getDict(map);
		 
	 }
	 
	 /**
	  * 为菜单获取树数据
	  * @param map
	  * @return
	  */
	 @RequestMapping(value="/getMenu.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getMenu(  @RequestBody Map<String,Object> map){
		 return service.getMenu(map);
	 }
	 
	 /**
	  * 获取单个数据
	  * @param map
	  * @return
	  */
	 @RequestMapping(value="/getMenuForOne.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getMenuForOne(  @RequestBody Map<String,Object> map){
		 return service.getMenuForOne(map);
	 }
	 
	 @RequestMapping(value="/addMenu.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean addMenu(@Valid  @RequestBody MenuValidBean bean){
		 return service.addMenu(bean);
	 }
	 
	 @RequestMapping(value="/getMenuList.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getMenuList( @RequestBody Map<String,Object> map){
		 return service.getMenuList(map);
	 }
	 
	 @RequestMapping(value="/updateMenu.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean updateMenu(@Valid  @RequestBody EditMenuValidBean bean){
		 return service.updateMenu(bean);
	 }
	 
	 @RequestMapping(value="/getRoleList.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getRoleList( @RequestBody Map<String,Object> map){
		 return service.getRoleList(map);
		 
	 }
	 
	 @RequestMapping(value="/addRole.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean addRole( @RequestBody RoleValidBean bean){
		 return service.addRole(bean);
		 
	 }
	
	 @RequestMapping(value="/getRoleById.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getRoleById( @RequestBody Map<String ,Object>map){
		 return service.getRoleById(map);
	 }
	
	 
	@RequestMapping(value="/addRoleMenu.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean addRoleMenu( @RequestBody Map<String,Object> map){
		 RoleMenuValidBean bean = new RoleMenuValidBean();
		 BeanUtil.mapToBean(map, bean);
		 bean.setData(map.get(StaticDict.DATA_NAME));
		 String str = (String) map.get("roleTree");
		 String[] split = str.split(",");
		 List<String> list = java.util.Arrays.asList(split);
		 bean.setRoleTree(list);
		 return service.addRoleMenu(bean);
	 }
	
	
	@RequestMapping(value="/addSeq.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean addSeq(@Valid @RequestBody AutoIdValidBean bean){
		 return service.addSeq(bean);
	 }
	
	
	@RequestMapping(value="/getSeq.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getSeq( @RequestBody Map<String,Object> map){
		 return service.getSeq((String)map.get("code"));
	 }
	
	@RequestMapping(value="/addOrg.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean addOrg(@Valid @RequestBody OrgValidBean bean){
		 return service.addOrg(bean);
	 }
	
	@RequestMapping(value="/updateOrg.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean updateOrg(@Valid @RequestBody EditOrgValidBean bean){
		 return service.updateOrg(bean);
	 }
	
	@RequestMapping(value="/delOrg.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean delOrg(@RequestBody Map<String,Object> map){
		 return service.delOrg(map);
	 }
	
	@RequestMapping(value="/getOrgTree.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getOrgTree(@RequestBody Map<String,Object> map){
		 return service.getOrgTree(map);
	 }
	
	@RequestMapping(value="/getOrgList.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getOrgList(@RequestBody Map<String,Object> map){
		 return service.getOrgList(map);
	 }
	
	@RequestMapping(value="/getOrgForOne.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getOrgForOne(@RequestBody Map<String,Object> map){
		 return service.getOrgForOne(map);
	 }
	
	@RequestMapping(value="/getUsersByOrg.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean getUsersByOrg(@RequestBody Map<String,Object> map){
		 return service.getUsersByOrg(map);
	 }
	
	@RequestMapping(value="/addUsersByOrg.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean addUsersByOrg( @RequestBody Map<String,Object> map ){
		 return service.addUsersByOrg(map);
	 }
	
	@RequestMapping(value="/delUserOrg.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean delUserOrg(@RequestBody Map<String,Object> map){
		 return service.delUserOrg(map);
	 }
	
	
	
	
	@RequestMapping(value="/checkExist.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody ResultBean checkExist(@RequestBody Map<String,Object> map,HttpServletRequest request){
		String preFile = request.getSession().getServletContext().getRealPath(File.separator);
		String fileName = "/page/org/addUserForOrg.html";
		String[] fileNames = fileName.split("/");
		String fileStr = "";
		for (int i = 0; i < fileNames.length; i++) {
			fileStr += fileNames[i]+File.separator;
			File file = new File(preFile+fileStr);
			System.out.println(preFile+fileStr);
			System.out.println(file.exists());
			if(i == fileNames.length){
				//file.mkdir();
			}
		} 
		 return new ResultBean();
	 }
	
	
	
	/* @RequestMapping(value="/login2.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody Map<String,String> testValid2( @Valid @RequestBody UserBean user){
		 log.debug(">>>>>>>>>> "+user.getUsername());
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("user", user.getUsername());
		return map;
		 
	 }*/
	
	
	
	 @RequestMapping(value="/testValid.do", consumes = { "application/json" }, produces = { "application/json;charset=UTF-8" })
	 public @ResponseBody Map<String,String> testValid( @Valid @RequestBody UserBean user){
		 Map<String,String> map = new HashMap<String,String>();
		 map.put("user", user.getUsername());
		return map;
		 
	 }
}
