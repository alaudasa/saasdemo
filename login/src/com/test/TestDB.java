package com.test;

import org.junit.Test;

import com.dao.UserInfoDao;
import com.po.UserInfo;

public class TestDB {
	
	@Test
	public void testDBconn(){
		UserInfoDao uid = new UserInfoDao();
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("test");
		userInfo.setPassword("123456");
		UserInfo ui = uid.queryUserInfoByUser(userInfo);
		System.out.println(ui.getUsername());
		System.out.println(ui.getPassword());
	}
	
}
