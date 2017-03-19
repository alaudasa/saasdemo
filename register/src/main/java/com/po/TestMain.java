package com.po;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

// import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class TestMain {

	private static byte[] loadImage(File file) {
		// 用于返回的字节数组
		byte[] data = null;
		// 打开文件输入流
		FileInputStream fin = null;
		// 打开字节输出流
		ByteArrayOutputStream bout = null;
		try {
			// 文件输入流获取对应文件
			fin = new FileInputStream(file);
			// 输出流定义缓冲区大小
			bout = new ByteArrayOutputStream((int) file.length());
			// 定义字节数组，用于读取文件流
			byte[] buffer = new byte[1024];
			// 用于表示读取的位置
			int len = -1;
			// 开始读取文件
			while ((len = fin.read(buffer)) != -1) {
				// 从buffer的第0位置开始，读取至第len位置，结果写入bout
				bout.write(buffer, 0, len);
			}
			// 将输出流转为字节数组
			data = bout.toByteArray();
			// 关闭输入输出流
			fin.close();
			bout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private static String byteToString(byte[] data) {
		String dataString = null;
		try {
			// 将字节数组转为字符串，编码格式为ISO-8859-1
			dataString = new String(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataString;
	}

	private static String compress(String data) {
		String finalData = null;
		try {
			// 打开字节输出流
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			// 打开压缩用的输出流,压缩后的结果放在bout中
			GZIPOutputStream gout = new GZIPOutputStream(bout);
			// 写入待压缩的字节数组
			gout.write(data.getBytes("utf-8"));
			// 完成压缩写入
			gout.finish();
			// 关闭输出流
			gout.close();
			finalData = bout.toString("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalData;
	}

	public static void main(String[] args) {

		HttpClient httpclient = new DefaultHttpClient();
		String url = "https://api.alauda.cn/v1/applications/alaudaorg";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;)");
		// httpPost.setHeader("Content-Type", "multipart/form-data");
		httpPost.setHeader("Authorization",
				"Token 8ed8848e7d14fbb4dca04efa67b4ada5d4f7c277");
		FileBody fileBody = new FileBody(new File(
				"WebRoot/resource/compose.yaml"));
		MultipartEntity mutiEntity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			mutiEntity.addPart("services", fileBody);
			mutiEntity.addPart("app_name", new StringBody("testDemo001"));
			mutiEntity.addPart("region", new StringBody("INT_ALI"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpPost.setEntity(mutiEntity);
		// FileBody fileBody = new FileBody(new
		// File("WebRoot/resource/compose.yaml"));
		// JSONObject json = new JSONObject();
		// File file = new File("WebRoot/resource/compose.yaml");
		// byte[] data = loadImage(file);
		// String str = byteToString(data);
		// String finstr = compress(str);
		// json.put("services", str);
		// json.put("app_name", "testDemo001");
		// json.put("region", "INT_ALI");
		// System.out.println(json.toString());
		// StringEntity s = new StringEntity(json.toString(), "utf-8");
		// s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
		// "application/json"));
		// httpPost.setEntity(s);
		String content="";
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

}
