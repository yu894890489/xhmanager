package com.xh.comm.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * json工具类
 * @author yuq
 * @date 2017年8月9日
 * @todo TODO
 */
public class JSONUtils {

	public static <T> String listToJson(List<T> list){
		return new JSONArray(list).toString();
	}
	
	public static <T> String objToJson(T obj){
		return new JSONObject(obj).toString();
	}
}
