package com.test;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class test {

	public static void main(String[] args) {
		Map<String,Object> map =  new HashMap<>();
		Map<String,Object> map2 =  new HashMap<>();
		map.put("data", "dsf");
		map2.put("data", map);
		System.out.println(new JSONObject(map2).toString());
	}
}