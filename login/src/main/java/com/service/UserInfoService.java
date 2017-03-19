package com.service;

import com.dao.UserInfoDao;
import com.po.UserInfo;

public class UserInfoService {
	UserInfoDao userDao;
	public UserInfoService(){
		userDao = new UserInfoDao();
	}
	public UserInfo userLogin(UserInfo userInfo){
		UserInfo ui = userDao.queryUserInfoByUser(userInfo);
		return ui;
	}
}
