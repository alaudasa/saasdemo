package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jdbc.DBconn;
import com.po.UserInfo;

public class UserInfoDao {

	public UserInfo queryUserInfoByUser(UserInfo userInfo) {
		UserInfo ui = null;
		Connection conn = DBconn.getConnection();
		try {
			String sql = "select * from userinfo where username=? and password=?";
			PreparedStatement pstemt = conn.prepareStatement(sql);
			pstemt.setString(1, userInfo.getUsername());
			pstemt.setString(2, userInfo.getPassword());
			ResultSet result = pstemt.executeQuery();
			if(result.next()){
				ui = new UserInfo();
				ui.setUsername(result.getString("username"));
				ui.setPassword(result.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ui;
	}
}
