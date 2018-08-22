package com.xh.comm.util;

/**
 * String工具类d
 * @author yuq
 * @date 2017年6月29日
 * @todo TODO
 */
public class StringUtils {
	
	/**
	 * "" null 和字符串的null undefined都为空
	 * @param str
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str){
		return str == null||str.equals("undefined")||str.equals("null")||str.equals("");
	}
	
	/**
	 *  isEmpty的相反
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
}
