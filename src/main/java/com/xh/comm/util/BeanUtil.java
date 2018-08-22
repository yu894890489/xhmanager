package com.xh.comm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.xh.comm.entry.UserBean;
import com.xh.comm.entry.pub.PubColumnAddBean;
import com.xh.comm.entry.pub.PubColumnUpdBean;

/**
 * bean工具类
 * 
 * @author sunll
 * 
 */
public class BeanUtil {


	private static final String STR_GET = "get";

	private static final String STR_SET = "set";
	
	private static final String STR_IS_DELETE = "isDelete";
	
	private static final String STR_CHILDREN = "children";
	
	private static final String STR_1 = "1";
	
	private static final String STR_0 = "0";
	
	/**
	 * 将数据转换成树结构
	 * @param list 要转换的数据(每列都应该有id和其parentId 实体Bean里应该有其children字段,可以叫其它名称)
	 * @param name id的字段名
	 * @param parentName parentId的字段名
	 * @return 
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> List<Object> getTree(List<T> list,String name,String parentName){
		if(CommUtils.isEmpty(list)){
			return null;
		}
		List<Object> nodeList = new ArrayList<Object>();
		T t = list.get(0);
		Class<? extends Object> clazz = t.getClass();
		try {
			Method getParentId = clazz.getDeclaredMethod(STR_GET+StringUtils.capitalize(parentName));
			Method getSelfId = clazz.getDeclaredMethod(STR_GET+StringUtils.capitalize(name));
	    	Method getChildren = clazz.getDeclaredMethod(STR_GET+StringUtils.capitalize(STR_CHILDREN));//默认其bean里对应的children字段和get set方法
	    	Method setCHildren = clazz.getDeclaredMethod(STR_SET+StringUtils.capitalize(STR_CHILDREN), clazz.getDeclaredField(STR_CHILDREN).getType());
	    	for(T node1 : list){//taskDTOList 是数据库获取的List列表数据或者来自其他数据源的List  
	            boolean mark = false;  
	            for(T node2 : list){
	                if(getParentId.invoke(node1)!=null && getParentId.invoke(node1).equals(getSelfId.invoke(node2))){  
	                    mark = true;  
	                    if(getChildren.invoke(node2) == null)  
	                    	setCHildren.invoke(node2,new ArrayList<T>());  
	                    ((List<T>) getChildren.invoke(node2)).add(node1);  
	                    break;  
	                }  
	            }  
	            if(!mark){  
	                nodeList.add(node1);  
	            }  
	        }
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return nodeList;
	}
	
	/**
	 * 解决当POJO有父POJO时无法正确获取所有的字段
	 * 功能：获取bean类的所有字段包括父类的字段
	 * @param bean 要获取所有字段的类
	 * @param fields 调用时直接传空
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Field[] getAllFields(Object bean,Field[] fields){
		Field[] fs = null;
		Field[] newFs = null;
		if(bean instanceof Class){
			@SuppressWarnings("rawtypes")
			Class clazz = (Class)bean;
			bean = clazz;
		}else{
			bean = bean.getClass();
		}
		fs = ((Class<? extends Object>) bean).getDeclaredFields();
		newFs = fields != null?(Field[]) ArrayUtils.addAll(fs, fields):fs;
		if(bean.equals(Object.class)){
			return newFs;
		}
		
		return getAllFields(((Class<? extends Object>) bean).getSuperclass(),newFs);
		
	}     

	/**
	 * 
	 * 获取类的方法，包括父类的方法（方法是有单个参的——主要是Set方法）
	 * 
	 * @param bean 要获取方法的类
	 * @param methodName 方法名
	 * @param fs 调用直接传null
	 * @param field 参数字段类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Method getMethod(Object bean,String methodName,Method fs,Field field){
		if(bean instanceof Class){
			@SuppressWarnings("rawtypes")
			Class clazz = (Class)bean;
			bean = clazz;
		}else{
			bean = bean.getClass();
		}
		try {
			fs = ((Class<? extends Object>) bean).getDeclaredMethod(methodName,field.getType());
		} catch (NoSuchMethodException e) {
			fs = null;
			return getMethod(((Class<? extends Object>) bean).getSuperclass(),methodName,fs,field);
		}
		if(!CommUtils.isEmpty(fs)){
			return fs;
		}
		return null;
		
	}
	
	/**
	 * 插入和更新统一调用（将map,validbean转成需要的bean,并增加更新和创建的人和时间）
	 * 减少代码重复调用和多次初始化
	 * @param t
	 * @param user
	 * @param clazz
	 * @param flag
	 * @return clazz的实体对象，需要对返回的对象强转，故要小心使用
	 */
	@SuppressWarnings("unchecked")
	public static <T> Object commForAU(T t,UserBean user,Class<?> clazz,boolean flag){
		Object instance = null;
		try {
			instance = clazz.newInstance();
			if(t instanceof Map){
				BeanUtil.mapToBean((Map<String, Object>) t, instance);
			}else{
				BeanUtil.BeanToBean(instance, t);
			}
			BeanUtil.addCommData(user, instance, flag);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		addDelData(instance, STR_0);
		return instance;
	}
	
	/**
	 * 对删除的公共调用 参考commForAU
	 * @param t
	 * @param user
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Object commForDel(T t,UserBean user,Class<?> clazz){
		Object instance = null;
			try {
				instance = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(t instanceof Map){
				BeanUtil.mapToBean((Map<String, Object>) t, instance);
			}else{
				BeanUtil.BeanToBean(instance, t);
			}
			BeanUtil.addDelData(instance,user);
		return instance;
	}
	
	/**
	 * 当删除时执行逻辑删除 不直接物理删除
	 * @param t 要删除的bean
	 * @param user 用户bean
	 */
	public static  <T> void addDelData(T t,UserBean user){
		BeanUtil.addCommData(user, t, true);
		Method method;
		try {
			method = t.getClass().getDeclaredMethod(STR_SET+StringUtils.capitalize(STR_IS_DELETE),
					t.getClass().getDeclaredField(STR_IS_DELETE).getType());
			method.invoke(t, STR_1);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 当删除时执行逻辑删除 不直接物理删除
	 * @param t 要删除的bean
	 * @param user 用户bean
	 * @param para 1为删除0为不删除
	 */
	public static  <T> void addDelData(T t,String para){
		Method method;
		try {
			method = t.getClass().getDeclaredMethod(STR_SET+StringUtils.capitalize(STR_IS_DELETE),
					t.getClass().getDeclaredField(STR_IS_DELETE).getType());
			method.invoke(t, para);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 给表添加公共信息
	 * @param user
	 * @param bean
	 * @param flag 是否是更新
	 * @return
	 */
	public static Object addCommData(UserBean user,Object bean,boolean flag){
		PubColumnAddBean pubColumnBean = new PubColumnAddBean();
		PubColumnUpdBean pubColumnUpdBean = new PubColumnUpdBean();
		pubColumnBean.setCreater(user.getUserId());
		pubColumnBean.setCreateTime(new Date());
		pubColumnBean.setUpdater(user.getUserId());
		pubColumnBean.setUpdateTime(new Date());
		BeanUtil.BeanToBean(bean,pubColumnBean);
		if(flag){
			BeanUtil.BeanToBean(pubColumnUpdBean, pubColumnBean);//当是更新时去除创建的信息
			BeanUtil.BeanToBean(bean,pubColumnUpdBean);
		}
		return bean;
		
	}
	
	
	/**
	 * bean及其父类所有的属性转化为map，属性必须有set、get方法
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> result = new HashMap<>();

		Field[] fields = bean.getClass().getDeclaredFields();

		try {
			String getMethodName;
			for (Field field : fields) {
				if (!field.getName().equals("serialVersionUID")) {
					getMethodName = STR_GET
							+ StringUtils.capitalize(field.getName());
					result.put(field.getName(), bean.getClass()
							.getDeclaredMethod(getMethodName).invoke(bean));
				}
			}

			Class<?> superBeanClass = bean.getClass().getSuperclass();
			if (superBeanClass != Object.class) {
				fields = superBeanClass.getDeclaredFields();

				for (Field field : fields) {
					if (!field.getName().equals("serialVersionUID")) {
						getMethodName = STR_GET
								+ StringUtils.capitalize(field.getName());
						result.put(field.getName(), superBeanClass
								.getDeclaredMethod(getMethodName).invoke(bean));
					}
				}
			}
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * map转化为bean及其父类所有的属性，属性必须有set、get方法
	 * 
	 * @param map
	 * @param bean
	 * @return
	 */
	public static void mapToBean(Map<String, Object> map, Object bean) {
		Field[] fields = getAllFields(bean, null);
		try {
			String setMethodName;
			for (Field field : fields) {
				String name = field.getName();
				Object tempObj;// 根据查出来的类型set值 oracle寻找big类型的重载set方法
				if (map.containsKey(name) && !CommUtils.isEmpty(map.get(name))) {
					tempObj = map.get(name);
					setMethodName = STR_SET + StringUtils.capitalize(name);
					Method method = getMethod(bean, setMethodName, null, field);
					method.invoke(bean, map.get(name));
				} else if (map.containsKey(name.toUpperCase())
						&& map.get(name.toUpperCase()) != null) {
					// oracle 字段为全大写
					tempObj = map.get(name.toUpperCase());
					setMethodName = STR_SET + StringUtils.capitalize(name);
					Method method = bean.getClass().getDeclaredMethod(
							setMethodName, tempObj.getClass());
					method.invoke(bean, map.get(name.toUpperCase()));

				}
			}

			Class<?> superBeanClass = bean.getClass().getSuperclass();
			if (superBeanClass != null && superBeanClass != Object.class) {
				fields = superBeanClass.getDeclaredFields();

				for (Field field : fields) {
					if (map.containsKey(field.getName())
							&& map.get(field.getName()) != null) {
						setMethodName = STR_SET
								+ StringUtils.capitalize(field.getName());
						Method method = superBeanClass.getDeclaredMethod(
								setMethodName, field.getType());
						method.invoke(bean, map.get(field.getName()));
					}
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Bean与Bean之间同属性复制
	 * 
	 * @param destBean 复制者
	 * @param origBean 传递者
	 */
	public static void BeanToBean(Object destBean, Object origBean) {
		try {
			BeanUtils.copyProperties(destBean, origBean);
		} catch (Exception e) {
		}
	}

	public static String getValue(Map<String, Object> map, String key) {
		return map.containsKey(key) ? String.valueOf(map.get(key)) : "";
	}

	public static String getValue(Map<String, Object> map, String key,
			String defaultValue) {
		return map.containsKey(key) ? String.valueOf(map.get(key))
				: defaultValue;
	}
}
