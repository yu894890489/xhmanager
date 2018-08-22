package com.xh.comm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xh.comm.entry.AutoIdBean;
import com.xh.comm.entry.DictBean;
import com.xh.comm.entry.MenuBean;
import com.xh.comm.entry.MenuBeanExample;
import com.xh.comm.entry.MenuBeanExample.Criteria;
import com.xh.comm.entry.OrgBean;
import com.xh.comm.entry.OrgBeanExample;
import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.RoleBean;
import com.xh.comm.entry.RoleBeanExample;
import com.xh.comm.entry.RoleMenuBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.UserOrgBean;
import com.xh.comm.entry.UserOrgBeanExample;
import com.xh.comm.entry.UserRoleBean;
import com.xh.comm.entry.UserRoleBeanExample;
import com.xh.comm.entry.valid.AutoIdValidBean;
import com.xh.comm.entry.valid.EditMenuValidBean;
import com.xh.comm.entry.valid.EditOrgValidBean;
import com.xh.comm.entry.valid.EditUserOrgValidBean;
import com.xh.comm.entry.valid.MenuValidBean;
import com.xh.comm.entry.valid.OrgValidBean;
import com.xh.comm.entry.valid.RoleMenuValidBean;
import com.xh.comm.entry.valid.RoleValidBean;
import com.xh.comm.service.CommService;
import com.xh.comm.service.UserService;
import com.xh.comm.util.BeanUtil;
import com.xh.comm.util.CommUtils;
import com.xh.comm.util.Dict;
import com.xh.comm.util.JSONUtils;
import com.xh.comm.util.StaticDict;
import com.xh.comm.util.StringUtils;
import com.xh.comm.util.base.Sequence;
import com.xh.dao.AutoIdBeanMapper;
import com.xh.dao.DictDao;
import com.xh.dao.MenuBeanMapper;
import com.xh.dao.OrgBeanMapper;
import com.xh.dao.RoleBeanMapper;
import com.xh.dao.UserOrgBeanMapper;
import com.xh.dao.UserRoleBeanMapper;
/**
 * 
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO  方法功能不是很符合类名  后期做迁移
 */
@Service
public class CommServiceImpl implements CommService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DictDao dictDao;
	
	@Autowired
	private MenuBeanMapper menuDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleBeanMapper roleDao;
	
	@Autowired
	private UserRoleBeanMapper userRoleDao;
	
	@Autowired
	private Sequence sequence;
	
	@Autowired
	private AutoIdBeanMapper autoIdDao;
	
	@Autowired
	private OrgBeanMapper orgDao;
	
	@Autowired
	private UserOrgBeanMapper userOrgDao;
	
	@Override
	public ResultBean getDict(Map<String, Object> map) {
		String key = (String) map.get(StaticDict.DICT_DATA_KEY);
		List<DictBean> dict = this.getDict(key);
		ResultBean result = new ResultBean();
		result.setJson(new JSONArray(dict).toString());
		return result;
	}
	
	private List<DictBean> getDict(String key){
		if(key.startsWith(Dict.DICT_ROLE_KEY_NAME)){
			List<DictBean> list = dictDao.getRoleDict();
			return list;
		}
		return null;
	}

	@Override
	public ResultBean getMenu(Map<String, Object> map) {
		String roleId = (String)map.get("roleId");
		List<MenuBean> list = null;
		List<String> roleIds = new ArrayList<String>();
		if(StringUtils.isEmpty(roleId)){
			UserBean user = userService.getUserFormSession(CommUtils.getSession(map));
			UserRoleBeanExample exm = new UserRoleBeanExample();
			com.xh.comm.entry.UserRoleBeanExample.Criteria createCriteria = exm.createCriteria();
			createCriteria.andUserIdEqualTo(user.getUserId());
			List<UserRoleBean> userRoleList = userRoleDao.selectByExample(exm);
			for(UserRoleBean u :userRoleList){
				roleIds.add(u.getRoleId());
			}
		}else{
			roleIds.add(roleId);
		}
		list = dictDao.getRoleMenu(roleIds);
		
		
		
		
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(BeanUtil.getTree(list,"menuId","menuParentId")));
		return result;
	}

//	private List<MenuBean> getTree(List<MenuBean> list){
//		List<MenuBean> nodeList = new ArrayList<MenuBean>();      
//		for(MenuBean node1 : list){//taskDTOList 是数据库获取的List列表数据或者来自其他数据源的List  
//		  
//		            boolean mark = false;  
//		            for(MenuBean node2 : list){  
//		                if(node1.getMenuParentId()!=null && node1.getMenuParentId().equals(node2.getMenuId())){  
//		                    mark = true;  
//		                    if(node2.getChildren() == null)  
//		                        node2.setChildren(new ArrayList<MenuBean>());  
//		                    node2.getChildren().add(node1);  
//		                    break;  
//		                }  
//		            }  
//		            if(!mark){  
//		                nodeList.add(node1);  
//		            }  
//		        }
//		return nodeList;
//	}
	
	
	
	
	@Override
	public ResultBean addMenu(MenuValidBean bean) {
		UserBean user = userService.getUserFormSession(CommUtils.getSession(bean));
		MenuBean menu = new MenuBean();
		BeanUtil.BeanToBean( menu,bean);
		BeanUtil.addCommData(user, menu, false);
		menu.setMenuId(sequence.getSequenceStr(Dict.MENU_STR, Dict.PROJECT_NAME));
		menuDao.insert(menu);
		return new ResultBean();
	}

	@Override
	public  ResultBean getRoleList(Map<String, Object> map) {
		RoleBeanExample example = new RoleBeanExample();
		com.xh.comm.entry.RoleBeanExample.Criteria criteria = example.createCriteria();
		criteria.getAllCriteria();
		List<RoleBean> list = roleDao.selectByExample(example);
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}

	@Override
	public ResultBean addRole(RoleValidBean bean) {
		RoleBean record = new RoleBean();
		BeanUtil.BeanToBean(record, bean);
		BeanUtil.addCommData(userService.getUserFormSession(CommUtils.getSession(bean)), record, false);
		record.setRoleId(sequence.getSequenceStr(Dict.ROLE_STR, Dict.PROJECT_NAME));
		roleDao.insert(record);
		return new ResultBean();
	}

	@Override
	public ResultBean getRoleById(Map<String, Object> map) {
		RoleBean bean = roleDao.selectByPrimaryKey((String)map.get("roleId"));
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.objToJson(bean));
		return result;
	}

	@Override
	public ResultBean addRoleMenu(RoleMenuValidBean roleMenuBean) {
		ArrayList<RoleMenuBean> list = new ArrayList<RoleMenuBean>();
		UserBean user = userService.getUserFormSession(CommUtils.getSession(roleMenuBean));
		
		for(String s : roleMenuBean.getRoleTree()){
			RoleMenuBean bean = new RoleMenuBean();
			bean.setRoleId(roleMenuBean.getRoleId());
			BeanUtil.addCommData(user, bean, false);
			bean.setRoleMenuId(sequence.getSequenceStr(Dict.ROLE_MENU, Dict.PROJECT_NAME));
			bean.setMenuId(s);
			list.add(bean);
		}
		dictDao.addRoleMenu(list);
		return new ResultBean();
	}
	
	/**
	 * 插入序列规则
	 */
	@Override
	public ResultBean addSeq(AutoIdValidBean bean) {
		AutoIdBean autoIdBean = new AutoIdBean();
		BeanUtil.BeanToBean(autoIdBean, bean);
		BeanUtil.addCommData(userService.getUserFormSession(CommUtils.getSession(bean)), autoIdBean, false);
		autoIdDao.insert(autoIdBean);
		return new ResultBean();
	}

	@Override
	public ResultBean updateSeq(AutoIdValidBean bean) {
		AutoIdBean autoIdBean = new AutoIdBean();
		BeanUtil.BeanToBean(autoIdBean, bean);
		BeanUtil.addCommData(userService.getUserFormSession(CommUtils.getSession(bean)), autoIdBean, true);
		autoIdDao.updateByPrimaryKey(autoIdBean);
		return new ResultBean();
	}

	@Override
	public ResultBean getSeq(Map<String, Object> map) {
		AutoIdBean bean = autoIdDao.selectByPrimaryKey((String)map.get("code"));
		ResultBean result = new ResultBean();
		result.setValid(false);
		if(CommUtils.isEmpty(bean)){
			result.setValid(true);
		}
		return result;
	}

	@Override
	@Cacheable(value="xhcache",key="'dict'+methodName+#code")
	public ResultBean getSeq(String code) {
		AutoIdBean bean = autoIdDao.selectByPrimaryKey(code);
		ResultBean result = new ResultBean();
		result.setValid(false);
		if(CommUtils.isEmpty(bean)){
			result.setValid(true);
		}
		return result;
	}

	@Override
	public ResultBean getMenuList(Map<String, Object> map) {
		MenuBeanExample example = new MenuBeanExample();
		Criteria criteria = example.createCriteria();
		criteria.getAllCriteria();
		List<MenuBean> list = menuDao.selectByExample(example);
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}

	@Override
	public ResultBean getMenuForOne(Map<String, Object> map) {
		MenuBean bean = menuDao.selectByPrimaryKey((String)map.get("menuId"));
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.objToJson(bean));
		return result;
	}

	@Override
	public ResultBean updateMenu(EditMenuValidBean bean) {
		MenuBean menuBean = new MenuBean();
		BeanUtil.BeanToBean(menuBean, bean);
		BeanUtil.addCommData(userService.getUserFormSession(bean), menuBean, true);
		menuDao.updateByPrimaryKey(menuBean);
		return new ResultBean();
	}

	@Override
	public ResultBean addOrg(OrgValidBean bean) {
		OrgBean orgBean = (OrgBean) BeanUtil.commForAU(bean, userService.getUserFormSession(bean), OrgBean.class, false);
		if(StringUtils.isEmpty(orgBean.getOrgParentId())){
			orgBean.setOrgParentId(StaticDict.ROOT_NODE);
		}
		orgBean.setOrgId(sequence.getSequenceStr(Dict.ORG_CODE, Dict.PROJECT_NAME));
		orgDao.insert(orgBean);
		return new ResultBean();
	}
	
	@Override
	public ResultBean updateOrg(EditOrgValidBean bean){
		OrgBean orgBean = (OrgBean) BeanUtil.commForAU(bean, userService.getUserFormSession(bean), OrgBean.class, true);
		orgDao.updateByPrimaryKey(orgBean);
		return new ResultBean();
	}
	
	@Override
	public ResultBean delOrg(Map<String,Object> map){
		OrgBean orgBean = (OrgBean) BeanUtil.commForDel(map, userService.getUserFormSession(map), OrgBean.class);
		orgDao.updateByPrimaryKey(orgBean);
		return new ResultBean();
	}

	@Override
	public ResultBean getOrgTree(Map<String, Object> map) {
		OrgBeanExample example = new OrgBeanExample();
		example.createCriteria().getAllCriteria();
		List<OrgBean> list = orgDao.selectByExample(example);
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(BeanUtil.getTree(list, "orgId", "orgParentId")));
		return result;
	}

	@Override
	public ResultBean getOrgList(Map<String, Object> map) {
		OrgBeanExample example = new OrgBeanExample();
		example.createCriteria().getAllCriteria();
		List<OrgBean> list = orgDao.selectByExample(example);
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}

	@Override
	public ResultBean getOrgForOne(Map<String, Object> map) {
		OrgBean bean = orgDao.selectByPrimaryKey((String)map.get("orgId"));
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.objToJson(bean));
		return result;
	}

	@Override
	public ResultBean getUsersByOrg(Map<String, Object> map) {
		UserOrgBeanExample example = new UserOrgBeanExample();
		com.xh.comm.entry.UserOrgBeanExample.Criteria criteria = example.createCriteria();
		criteria.andOrgIdEqualTo((String)map.get("orgId"));
		List<UserOrgBean> list = userOrgDao.selectByExample(example);
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}
	
	@Override
	public ResultBean addUsersByOrg(Map<String, Object> map){
		@SuppressWarnings("unchecked")
		List<String> ids = (List<String>) map.get("ids"); 
		List<UserOrgBean> list = new ArrayList<UserOrgBean>();
		
		String[] sequences = sequence.getSequenceStr("UO", ids.size());
		for (int i = 0; i < ids.size(); i++) {
			UserOrgBean bean = (UserOrgBean) BeanUtil.commForAU(map, userService.getUserFormSession(map), UserOrgBean.class, false);
			bean.setUserId(ids.get(i));
			bean.setUserOrgId(sequences[i]);
			list.add(bean);
		}
		dictDao.addUserOrg(list);
		return new ResultBean();
	}
	
	@Override
	public ResultBean updateUserOrg(EditUserOrgValidBean bean){
		UserOrgBean userOrg = (UserOrgBean) BeanUtil.commForAU(bean, userService.getUserFormSession(bean), UserOrgBean.class, true);
		userOrgDao.updateByPrimaryKey(userOrg);
		return new ResultBean();
	}

	@Override
	public ResultBean delUserOrg(Map<String, Object> map) {
		UserOrgBeanExample example = new UserOrgBeanExample();
		com.xh.comm.entry.UserOrgBeanExample.Criteria criteria = example.createCriteria();
		criteria.andOrgIdEqualTo((String)map.get("orgId"));
		criteria.andUserIdEqualTo((String)map.get("userId"));
		userOrgDao.deleteByExample(example);
		return new ResultBean();
	}
	
	
	
	
	

}
