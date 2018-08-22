package com.xh.comm.util;

import java.util.Collection;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.util.base.BASEUtils;
/**
 * 
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO
 */
public class CommUtils {
	
	/**
	 * 校验数据库增删改操作（暂时只对单条数据）
	 * @param count
	 */
	public static ResultBean checkCountForDB(int count){
		ResultBean bean = new ResultBean();
		if(count==1){
			bean.setMsg(StaticDict.DB_MSG_SUCCESS);
			bean.setResultType(ResultBean.RESULTTYPE_SUCCESS);
			return bean;
		}
		bean.setMsg(StaticDict.DB_MSG_FAILURE);
		bean.setResultType(ResultBean.RESULTTYPE_ERROR);
		return bean;
	}
	
	
	
	/** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map,String为empty 
     *  bean为null
     * @param obj 
     * @return 
     */  
    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        if (obj instanceof String) {
			String str = (String) obj;
			return str == null||str.equals("undefined")||str.equals("null")||str.equals("");
		}
        return false;  
    }  
    
    
    /**
     * 获取session
     * @param t
     * @return
     */
	public static <T> String getSession(T t){
    	JSONObject jsonObject = null;
    	try {
    		jsonObject = new JSONObject(t);
    		if (t instanceof Map) {
    			@SuppressWarnings("unchecked")
				Map<String,Object> m = (Map<String,Object>)t; 
    			@SuppressWarnings("rawtypes")
				Map map = (Map) m.get(StaticDict.DATA_NAME);
    			return (String) map.get(StaticDict.SESSION_NAME);
    		}
		} catch (JSONException e) {
		}
		return jsonObject.getJSONObject(StaticDict.DATA_NAME).getString(StaticDict.SESSION_NAME);
    	
    }
	
	/**
	 * 从session中获取userid
	 * @param t
	 * @return
	 */
	public static <T> String getUserId(T t){
		String session = getSession(t);
		return BASEUtils.encUser(session);
		
	}
}
