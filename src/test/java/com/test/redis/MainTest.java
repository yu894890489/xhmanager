package com.test.redis;

import redis.clients.jedis.Jedis;

public class MainTest {
	
	
	public static void main(String[] args) {
//		try {
		    Jedis jedis = new Jedis("103.45.7.37",6379);
		    System.out.println(jedis.ping());
//		} catch (Exception e) {
//		    //如果缓存连不上，则不处理
//		    System.out.println("登录无法更新该用户缓存");
//		    }
	}
}
