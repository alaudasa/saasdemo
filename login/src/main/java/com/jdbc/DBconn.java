package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

public class DBconn{
	private static String host;
	private static String port;
	private static String user = "root";
	private static String password = "123456";
	private static String url;
	private static Connection conn;
	
	static{
		try{
			Map<String, String> map = System.getenv();
			host = map.get("MYSQL_HOST");
			port = map.get("MYSQL_PORT");
			url = "jdbc:mysql://"+host+":"+port+"/regdb"; 
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private DBconn(){
	}
	
	public static Connection getConnection(){
		if(null == conn){
			try{
				Logger log = Logger.getLogger("lavasoft");
				log.info(url);
				log.info(password);
				log.info(user);
				conn = DriverManager.getConnection(url,user,password);
			}catch(SQLException e){
				
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
