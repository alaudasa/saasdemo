package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class UserServlet extends HttpServlet {

	public void doPost(HttpServletRequest rq, HttpServletResponse resp) {
		String username = rq.getParameter("username");
		String password = rq.getParameter("password");
		System.out.println(username + "+++++++++++++++++++++++++++++");
		System.out.println(password + "+++++++++++++++++++++++++++++");
		
		String[] str = new String [25];
		str[0]="db:\n";
		str[1]="  alauda_lb: HA\n";
		str[2]="  environment:\n";
		str[3]="  - MYSQL_ROOT_PASSWORD=123456\n";
		str[4]="  - DEMOUSER="+username+"\n";
		str[5]="  - DEMOPASS="+password+"\n";
		str[6]="  image: registry.alauda.cn/alaudasa/saasdemo-mysql\n";
		str[7]="  net: bridge\n";
		str[8]="  number: 1\n";
		str[9]="  ports:\n";
		str[10]="  - '3306'\n";
		str[11]="  size: S\n";
		str[12]="login:\n";
		str[13]="  alauda_lb: HA\n";
		str[14]="  environment:\n";
		str[15]="  - MYSQL_HOST=$DB_PORT_3306_TCP_ADDR\n";
		str[16]="  - MYSQL_PORT=$DB_PORT_3306_TCP_PORT\n";
		str[17]="  image: registry.alauda.cn/alaudasa/saasdemo-login\n";
		str[18]="  links:\n";
		str[19]="  - db:DB\n";
		str[20]="  net: bridge\n";
		str[21]="  number: 1\n";
		str[22]="  ports:\n";
		str[23]="  - 8080/http\n";
		str[24]="  size: XS\n";
		File file = new File("compose.yaml");
		FileOutputStream in = null;
		try {
			in = new FileOutputStream("compose.yaml");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(int i=0;i<25;i++){
        	byte bt[] = new byte[1024];  
            bt = str[i].getBytes(); 
                try {  
                    in.write(bt, 0, bt.length);  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
        }
            
                    try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		
        Map<String, String> map = System.getenv();
        String token = map.get("TOKEN");
		HttpClient httpclient = new DefaultHttpClient();
		String url = "https://api.alauda.cn/v1/applications/alaudademo01";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;)");
		// httpPost.setHeader("Content-Type", "multipart/form-data");
		httpPost.setHeader("Authorization", token);
		FileBody fileBody = new FileBody(new File(
				"compose.yaml"));
		MultipartEntity mutiEntity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			mutiEntity.addPart("services", fileBody);
			mutiEntity.addPart("app_name", new StringBody("testDemo"+(int) (Math.random() * 100)));
			mutiEntity.addPart("region", new StringBody("alaudademo01baidu"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpPost.setEntity(mutiEntity);
		String content = "";
		try {
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			content = EntityUtils.toString(httpEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(content);
		
	}

	public void doGet(HttpServletRequest rq, HttpServletResponse resp) {
		doPost(rq, resp);
	}
}
