package com.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xh.comm.util.JSONUtils;
import com.xh.dao.DictDao;

import base.BaseJunitSpring;

public class TestDao extends BaseJunitSpring{

	@Autowired
	private DictDao dao;
	@Test
	public void test(){
		List<String> s = new ArrayList<String>();
		s.add("asdda");
		s.add("sdfdsfsdf");
		System.out.println(JSONUtils.listToJson(dao.getRoleMenu(s)));
	}
}
