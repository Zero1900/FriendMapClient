package com.example.friendmap.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.friendmap.FMApplication;
import com.example.friendmap.user.DataLogin;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.utils.FMCallBack;
import com.example.friendmap.utils.MD5;

import android.os.Handler;

public class NetLogin extends NetBase{

	public NetLogin(FMCallBack fmCallBack,String username,String passwordsrc) {
		super(fmCallBack);
		// TODO Auto-generated constructor stub
		setUrl(encodeUrl("Client/Login"));
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("username", username);
			String password=MD5.getMD5(passwordsrc);
			jsonObject.put("password", password);
			setRequest(jsonObject.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void onRecv(FMResponse response){
		JSONObject jsonResponse=response.getResult();
		try {
			String session=jsonResponse.getString("session");
			DataLogin dataLogin=DataManager.getInstance().getDataLogin();
			dataLogin.login(session);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		super.onRecv(response);
	}
}
