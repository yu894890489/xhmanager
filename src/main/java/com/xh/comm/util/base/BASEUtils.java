package com.xh.comm.util.base;

import java.util.Date;

/**
 * 生成密令（直接使用）
 * @author yuq
 * @date 2017年6月28日
 * @todo 
 */
public class BASEUtils {
	
	/**
	 * 
	 * @param str 将str加密
	 * @return
	 */
	public static String decStr(String str){
		Des des = Des.getInstance();
		return des.setEbcByByte2HexStr(str);
	}
	
	/**
	 * 
	 * @param str 将str解密
	 * @return
	 */
	public static String encStr(String str){
		Des des = Des.getInstance();
		return des.getDesCodeHexStr2Byte(str);
	}
	
	/**
	 * 根据用户Id和当前时间加密
	 * @param id
	 * @return
	 */
	public static String decUserId(String id){
		return decStr(new Date().getTime()+id);
	}
	
	/**
	 * 根据session解密用户
	 * @param token
	 * @return
	 */
	public static String  encUser(String token){
		String str = encStr(token);
		String a = new Date().getTime()+"";
		return str.substring(a.length(), str.length());
	}
	
}
