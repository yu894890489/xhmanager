package com.test.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xh.comm.entry.ResultBean;
import com.xh.comm.service.CommService;

import base.BaseJunitSpring;

public class TestCommServiceImpl extends BaseJunitSpring{
	@Autowired
	private CommService service;
	
	@Test
	public void test(){
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("key", "role");
		ResultBean dict = service.getDict(map);
		@SuppressWarnings("unused")
		JSONArray jsonArray = new JSONArray(dict.getJson());
	}
	
	@Test
	public void testTree(){
		service.getMenu(null);
	}
}
