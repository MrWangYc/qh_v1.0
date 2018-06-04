package com.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator HTTP网络操作工具
 */
public class HttpUtil {

	public static void main(String[] a) {
		Map<String, String> q = new HashMap<String, String>();
		q.put("q", "哈哈");
		post("http://www.baidu.com/", q);
	}

	/**
	 * Post提交
	 * @param url		请求的url
	 * @param params	请求的参数
	 * @return
	 */
	public static String post(String url, Map<String, String> params) {
		String result = null;
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口

		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用POST方法
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");

		// 将表单的值放入postMethod中
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String value = params.get(key);
			if (StringUtils.isNotBlank(value))
				method.addParameter(key, value);
		}

		// method.addParameter(key, value);
		// NameValuePair[] nameValuesPairs = new NameValuePair[params.size()];
		// Iterator iter = params.entrySet().iterator();
		// int i = 0;
		// while (iter.hasNext()) {
		// Map.Entry<String, String> entry = (Map.Entry<String, String>)
		// iter.next();
		// nameValuesPairs[i] = new NameValuePair(entry.getKey(),
		// entry.getValue());
		// i++;
		// }
		// method.setQueryString(nameValuesPairs);
		try {
			client.executeMethod(method);
			// 打印服务器返回的状态
			System.out.println(method.getStatusLine());

			result = method.getResponseBodyAsString();
			// 打印返回的信息
			System.out.println(result);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 释放连接
		method.releaseConnection();

		return result;
	}

	/**
	 * get请求
	 * @param url		请求的url
	 * @param params	请求的参数
	 * @return
	 */
	public static String get(String url, Map<String, String> params) {
		String result = null;
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口

		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod(url);

		NameValuePair[] nameValuesPairs = new NameValuePair[params.size()];
		Iterator iter = params.entrySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
					.next();
			nameValuesPairs[i] = new NameValuePair(entry.getKey(), entry
					.getValue());
			i++;
		}

		method.setQueryString(nameValuesPairs);

		try {
			client.executeMethod(method);
			// 打印服务器返回的状态
			System.out.println(method.getStatusLine());

			result = method.getResponseBodyAsString();
			// 打印返回的信息
			System.out.println(result);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 释放连接
		method.releaseConnection();
		return result;
	}

}