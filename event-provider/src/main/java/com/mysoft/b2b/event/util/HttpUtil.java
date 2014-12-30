/**
 * Copyright mysoft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of mysoft Limited. Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from mysoft or an authorized sublicensor.
 */
package com.mysoft.b2b.event.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * Administrator: Change to the actual description of this class
 * @version   Revision History
 * <pre>
 * Author     Version       Date        Changes
 * Administrator    1.0           2014年8月25日     Created
 *
 * </pre>
 * @since b2b 1.5.0
 */

public class HttpUtil {
	
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	
	/**
	 * 通过http协议下发事件到客户端
	 * @param uri 目标地址
	 * @param body 下发内容 
	 */
	public static Integer send(String uri,String body){
		if(StringUtils.isEmpty(uri) || StringUtils.isEmpty(body))
			return null;
		HttpClient client = new DefaultHttpClient();
		try{
			HttpPost post = new HttpPost(uri+"/dealEvent.do");
			post.addHeader("Content-Type", "application/json;charset=UTF-8"); 
			HttpEntity entity = new StringEntity(body);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			return code;
		}catch(Exception e){
			logger.error("通过http协议下发事件到客户端异常："+e);
		}
		return null;
	}
}
