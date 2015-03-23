package com.example.friendmap;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Message;
import android.util.Log;

import com.example.friendmap.net.FMResponse;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetCheckVersion;
import com.example.friendmap.user.DataManager;


public class HttpManager {
	public static void sendMsg(final NetBase msg) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					doPost(msg);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	private static String doPost(NetBase msg) throws Exception{
		String strResponse = "";
		Log.i("Net Send Url:",""+msg.getUrl());
		Log.i("Net Send Request:",""+msg.getRequest());
		HttpPost httpPost = new HttpPost(msg.getUrl());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//请求参数
		params.add(new BasicNameValuePair("params", msg.getRequest()));
		//版本信息
		params.add(new BasicNameValuePair("clientVersion", ""+Global.getVersionCode()));
		HttpEntity entity;
		entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
		httpPost.setEntity(entity);
		
		String session=DataManager.getInstance().getDataLogin().getSession();
		if(session !=null){
			httpPost.setHeader("Cookie", "JSESSIONID=" + session);
			
		}
		
		
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Consts.NET_CONNECTION_TIMEOUT);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Consts.NET_SO_TIMEOUT);
		try {
			HttpResponse httpResp = httpClient.execute(httpPost);
			if (httpResp.getStatusLine().getStatusCode() == 200) {
				strResponse = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
			} else {
				Log.i("HttpPost", "Http error");
			}
		} catch (Exception e) {
			strResponse = "{\"errorCode\":-1}";
		}
		Log.i("Net Recv:", ""+strResponse);
		FMResponse response=new FMResponse(strResponse);
		msg.onRecv(response);
		return strResponse;
	}
}
