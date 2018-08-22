package com.xh.comm.util.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.xh.comm.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 
 * @author yuq
 * @date 2017年6月30日
 * @todo 连接Resis和对数据和缓存和取   需要和spring的cache结合起来，后期将弃用
 */
public class CacheUtils {
	public static CacheUtils cache;
	private static JedisPool jedisPool;
	private Jedis jedis;
	
	private CacheUtils() {
		jedisPool = new JedisPool("192.168.1.254", 6379);
		// jedis = new Jedis("118.89.148.163",6379);
	}
	
	private CacheUtils(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}
	public static CacheUtils getInstance(String ip, int port) {
		if (cache == null) {
			cache = new CacheUtils( ip,  port);
		}
		return cache;
	}
	public static CacheUtils getInstance() {
		if (jedisPool == null&&cache == null) {
			cache = new CacheUtils();
		}
		return cache;
	}

	public Jedis getj() {
		if (jedis == null) {
			try {
				return jedisPool.getResource();
			} catch (JedisConnectionException e) {
			}
			return null;
		}
		return jedis;
	}
	
	
	
	
	/**
	 * 所有的Session以其用户的Id为key,不可有重复
	 * @param id
	 * @param value
	 */
	public void addSession(String id, String value) {
		getj().set(id, value);
	}
	public String getSession(String id){
		return getj().get(id);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean hasSession(String id){
		
		if(StringUtils.isNotEmpty(getj().get(id))){
			return true;
		}
		return false;
	}
	
	/**
	 * 删除用户的Session
	 */
	public void  delSession(String id){
		getj().del(id);
	}

	// 序列化
	public static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 反序列化
	public static Object unserizlize(byte[] byt) {
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(byt);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		CacheUtils instance = CacheUtils.getInstance();
		instance.getj();
	}
	

}
