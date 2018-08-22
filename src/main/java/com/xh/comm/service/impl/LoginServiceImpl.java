package com.xh.comm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.UserBeanExample;
import com.xh.comm.entry.UserBeanExample.Criteria;
import com.xh.comm.entry.UserLoginInfoBean;
import com.xh.comm.entry.UserRoleBean;
import com.xh.comm.entry.valid.AddUserValidBean;
import com.xh.comm.entry.valid.EditUserValidBean;
import com.xh.comm.service.LoginService;
import com.xh.comm.service.UserService;
import com.xh.comm.util.BeanUtil;
import com.xh.comm.util.CommUtils;
import com.xh.comm.util.Dict;
import com.xh.comm.util.JSONUtils;
import com.xh.comm.util.StaticDict;
import com.xh.comm.util.base.BASEUtils;
import com.xh.comm.util.base.Sequence;
import com.xh.comm.util.cache.CacheUtils;
import com.xh.dao.DictDao;
import com.xh.dao.UserBeanMapper;
import com.xh.dao.UserLoginInfoBeanMapper;
import com.xh.dao.UserRoleBeanMapper;
/**
 * 
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO 登陆功能的补全
 */
@Service
public class LoginServiceImpl implements LoginService {
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private UserBeanMapper dao;
	
	@Autowired
	private UserLoginInfoBeanMapper loginInfoDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleBeanMapper userRoleDao;
	
	@Autowired
	private Sequence sequence;
	
	@Autowired
	private DictDao dictDao;
	
	/**
	 * 如果当前缓存存在一个用户 才能算登陆成功
	 * @param user
	 * @param resultBean
	 * @return
	 */
	@SuppressWarnings("unused")
	private ResultBean addSession(UserBean user,ResultBean resultBean){
		//将用户的ID和当前时间加密并转16进制
		String decUserId = getDecUserId(user.getUserId());
		CacheUtils cache =  CacheUtils.getInstance();
		if(false&&cache.hasSession(user.getUserId())){//先下掉用户重复登陆检验
			recordLoginInfo(user);
			resultBean.setMsg(StaticDict.LOGIN_FAILURE_MSG_EXIST_USER);
			resultBean.setResultType(ResultBean.RESULTTYPE_ERROR);;
			return resultBean;
		}
		if(cache.getj()!=null){
			cache.getj().set(user.getUserId(), decUserId);
		}
		resultBean.setMsg(StaticDict.LOGIN_SUCCESS_MSG);
		resultBean.setResultType(ResultBean.RESULTTYPE_SUCCESS);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(StaticDict.SESSION_NAME, decUserId);
		resultBean.setMap(map);
		return resultBean;
	}
	
	@Cacheable(value="xhcache", key="methodName+#userId")
	private String getDecUserId(String userId){
		return BASEUtils.decUserId(userId);
	}
	/**
	 * 记录用户登陆
	 * @param user
	 */
	private void recordLoginInfo(UserBean user){
		//记录用户登陆
		UserLoginInfoBean userLoginInfoBean = new UserLoginInfoBean();
		BeanUtil.addCommData(user, userLoginInfoBean, false);
		userLoginInfoBean.setUserId(user.getUserId());
		userLoginInfoBean.setLoginTime(new Date());
		loginInfoDao.insert(userLoginInfoBean);
	}
	
	/**
	 * 获取用户
	 */
	@Override
	@Cacheable(value =  "xhcache",key="methodName+#user.username" )
	public List<UserBean> getUsers(UserBean user) {
		UserBeanExample example = new UserBeanExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		criteria.andPasswordEqualTo(BASEUtils.decStr(user.getPassword()));
		return dao.selectByExample(example);
	}
	/**
	 * 检查登陆
	 */
	@Override
	public ResultBean checkLogin(UserBean user) {
		List<UserBean> users = getUsers(user);
		ResultBean resultBean = new ResultBean();
		if(users.size()>1||users.size()<=0){
			 resultBean.setMsg(StaticDict.LOGIN_FAILURE_MSG_NO_USER);
			 resultBean.setResultType(ResultBean.RESULTTYPE_ERROR);
			 return resultBean;
		}
		if(BASEUtils.encStr(users.get(0).getPassword()).equals(user.getPassword())){
			return addSession(users.get(0),resultBean);
		}
		resultBean.setMsg(StaticDict.LOGIN_FAILURE_MSG_ERROR_PWD);
		resultBean.setResultType(ResultBean.RESULTTYPE_ERROR);
		return resultBean;
	}
	
	
	/**
	 * 新增用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultBean addUser(AddUserValidBean bean) {
		UserBean user = new UserBean();
		BeanUtil.BeanToBean(user,bean);
		BeanUtil.addCommData(userService.getUserFormSession(((Map<String,String>)bean.getData()).get(StaticDict.SESSION_NAME)), 
				user, false);
		user.setPassword(BASEUtils.decStr("yu"));
		user.setUserId(sequence.getSequenceStr(Dict.USER_STR,Dict.PROJECT_NAME));
		
		
		UserRoleBean userRole = new UserRoleBean();
		BeanUtil.BeanToBean(userRole,bean);
		BeanUtil.addCommData(userService.getUserFormSession(CommUtils.getSession(bean)), 
				userRole, false);
		userRole.setRecordId(sequence.getSequenceStr(Dict.USER_ROLE,Dict.PROJECT_NAME));
		userRole.setUserId(user.getUserId());
		dao.insert(user);
		userRoleDao.insert(userRole);
		return new ResultBean();
	}
	/**
	 * 更新
	 */
	@Override
	@CachePut(value = "xhcache" ,key="'getUsers'+#user.username")
	public ResultBean updateUser(UserBean user) {
		return CommUtils.checkCountForDB(dao.updateByPrimaryKey(user));
	}
	/**
	 * 删除用户
	 */
	@Override
	public ResultBean delUser(UserBean user) {
		return CommUtils.checkCountForDB(dao.deleteByPrimaryKey(user.getUserId()));
	}
	/**
	 * 获取多个用户
	 */
	@Override
	public ResultBean getUserList(Map<String, Object> map) {
		UserBeanExample example = new UserBeanExample();
		Criteria criteria = example.createCriteria();
		criteria.getAllCriteria();
		List<UserBean> list = dao.selectByExample(example);
		
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}
	@Override
	public ResultBean getUserListByCon(Map<String, Object> map) {
		UserBeanExample example = new UserBeanExample();
		Criteria criteria = example.createCriteria();
		criteria.getAllCriteria();
		
		List<UserBean> list = dao.selectByExample(example);
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}
	@Override
	public ResultBean getUserById(Map<String, Object> map) {
		if(CommUtils.isEmpty(map.get("userId"))){
			map.put("userId", CommUtils.getUserId(map));
		}
		UserBean bean = dao.selectByPrimaryKey((String) map.get("userId"));
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.objToJson(bean));
		return result;
	}
	
	@Override
	public ResultBean updateEditUser(EditUserValidBean bean) {
		UserBean user = new UserBean();
		BeanUtil.BeanToBean(user,bean);
		BeanUtil.addCommData(userService.getUserFormSession(CommUtils.getSession(bean)), 
				user, true);
		dao.updateByPrimaryKey(user);
		return new ResultBean();
	}
	
	@Override
	public ResultBean deleteUserById(Map<String, Object> map) {//bug:删除时缓存中不会删除
		dao.deleteByPrimaryKey((String) map.get("userId"));
		return new ResultBean();
	}
	
	
	@Cacheable(value = "xhcache",key="#a")
	public Object testEhcache(String a){
		log.info("********************************");
		UserBeanExample example = new UserBeanExample();
		Criteria criteria = example.createCriteria();
		criteria.getAllCriteria();
		List<UserBean> list = dao.selectByExample(example);
		return list;
	}
	@Override
	public ResultBean getUserListByIds(Map<String, Object> map, boolean b) {
		List<UserBean> list = null;
		if(b){
			list = dictDao.getUserIdByOrgId((String)map.get("orgId"));
		}else{
			list = dictDao.getUserIdByNotOrgId((String)map.get("orgId"));
		}
		ResultBean result = new ResultBean();
		result.setJson(JSONUtils.listToJson(list));
		return result;
	}
}
