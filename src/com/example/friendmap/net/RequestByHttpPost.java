package com.example.friendmap.net;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class RequestByHttpPost {

	public static String TIME_OUT = "������ʱ";

	public static String doPost(List<NameValuePair> params, String url) throws Exception {
		String result = null;
		// �½�HttpPost����
		HttpPost httpPost = new HttpPost(url);
		// �����ַ���
		HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
		// ���ò���ʵ��
		httpPost.setEntity(entity);
		// ��ȡHttpClient����
		HttpClient httpClient = new DefaultHttpClient();
		// ���ӳ�ʱ
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		// ����ʱ
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		try {
			// ��ȡHttpResponseʵ��
			HttpResponse httpResp = httpClient.execute(httpPost);
			// �ж��ǹ�����ɹ�
			if (httpResp.getStatusLine().getStatusCode() == 200) {
				// ��ȡ���ص�����
				result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
				Log.i("HttpPost", "HttpPost��ʽ����ɹ��������������£�");
				Log.i("result", result);
			} else {
				Log.i("HttpPost", "HttpPost��ʽ����ʧ��");
			}
		} catch (ConnectTimeoutException e) {
			result = TIME_OUT;
		}

		return result;
	}

	public static String doGet(String url) {
		String result="";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			// �µ�ַ����ֱ�Ӹ��������磺http://127.0.0.1:8080/test/test.php?name=;
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			result=result+httpResponse.getStatusLine().getStatusCode();
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			result=result+"exception";
		}
		return result;
	}
}