package com.example.friendmap.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.TextureView.SurfaceTextureListener;

import com.example.friendmap.Consts;
import com.example.friendmap.Global;
import com.example.friendmap.HttpManager;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.utils.FMCallBack;

public abstract class NetBase {
	private String url = "", request = "";
	protected FMCallBack fmCallBack;
	
	public NetBase(FMCallBack fmCallBack) {
		this.fmCallBack=fmCallBack;
	}
	public void post(){
		HttpManager.sendMsg(this);
	}
	public void onRecv(final FMResponse response) {
		if(fmCallBack!=null){
			fmCallBack.post(response);
		}
	}

	public void setUrl(String url) {
		this.url = new String(url);
	}

	public void setRequest(String request) {
		this.request = new String(request);
	}

	protected String encodeUrl(String pattern) {
		return "http://" + Global.getIpMain() + "/FriendMap/" + pattern;
	}

	public String getUrl() {
		return url;
	}

	public String getRequest() {
		return request;
	}
}
