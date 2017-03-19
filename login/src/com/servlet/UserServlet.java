package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.UserInfo;
import com.service.UserInfoService;

public class UserServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest rq, HttpServletResponse resp){
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");
		UserInfo ui = new UserInfo();
		ui.setUsername(username);
		ui.setPassword(password);
		UserInfoService uis = new UserInfoService();
		UserInfo userInfo = uis.userLogin(ui);
		if(userInfo != null){
			try {
				resp.sendRedirect("success.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				resp.sendRedirect("fail.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doGet(HttpServletRequest rq, HttpServletResponse resp){
		doPost(rq, resp);
	}
}
