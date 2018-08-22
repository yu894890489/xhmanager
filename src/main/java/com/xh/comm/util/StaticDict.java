package com.xh.comm.util;

/**
 * 静态字面变量类
 * @author yuq
 * @date 2017年7月5日
 * @todo TODO
 */
public class StaticDict {
	
	/**
	 * 登陆成功信息
	 */
	public static final String LOGIN_SUCCESS_MSG = "登陆成功！";
	
	/**
	 * 登陆错误之没有该用户信息
	 */
	public static final String LOGIN_FAILURE_MSG_NO_USER = "没有该用户";
	
	/**
	 * 登陆错误信息之用户密码错误
	 */
	public static final String LOGIN_FAILURE_MSG_ERROR_PWD = "用户密码错误";
	
	
	/**
	 * 登陆错误信息之用户重复登陆
	 */
	public static final String LOGIN_FAILURE_MSG_EXIST_USER = "该用户已经登陆，如果被盗用请联系管理员！";
	
	/**
	 * 数据库增删改操作之成功
	 */
	public static final String DB_MSG_SUCCESS ="数据操作成功";
	
	/**
	 * 数据库增删改操作之失败
	 */
	public static final String DB_MSG_FAILURE ="数据操作失败";
	
	
	
	
	/**
	 * 存放在act里的业务数据的Key
	 */
	public static final String ACT_DATA_NAME = "DATAJSONSTR";
	
	
	/**
	 * 前台传入的session的key和XHUtils的对应 
	 */
	public static final String SESSION_NAME = "xhSession";
	
	/**
	 * 前台传入公共数据的key
	 */
	public static final String DATA_NAME = "data";
	
	
	/**
	 * 数据字典data的key
	 */
	public static final String DICT_DATA_KEY = "key";
	
	
	/**
	 * 树的根节点
	 */
	public static final String ROOT_NODE = "root";
}
