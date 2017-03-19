<%@ page language="java" import="java.util.*" pageEncoding="UTF8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <div>
    	<%
    		Map<String, String> map = System.getenv();
			String host = map.get("MYSQL_HOST");
			String port = map.get("MYSQL_PORT");
    	 %>
    	 <%
    	 	out.println("MYSQL_HOST = "+host);
    	 	out.println("MYSQL_PORT = "+port);
    	  %>
        登陆
    	<form action="UserServlet" method="post">
    		用户名：<input type="text" name="username"/><br/>
    		密码： <input type="password" name="password" /><br/>
    		<input type="submit" value="ok" />
    	</form>
    </div>
  </body>
</html>
