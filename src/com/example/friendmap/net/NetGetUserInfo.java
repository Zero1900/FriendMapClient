package com.example.friendmap.net;

import org.json.JSONObject;

import com.example.friendmap.FMApplication;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.user.DataUserInfo;
import com.example.friendmap.utils.FMCallBack;

import android.os.Handler;

public class NetGetUserInfo extends NetBase {

	public NetGetUserInfo(FMCallBack fmCallBack) {
		super(fmCallBack);
		// TODO Auto-generated constructor stub
		setUrl(encodeUrl("Client/GetUserInfo"));
	}
	
	public void onRecv(FMResponse response){
		JSONObject jsonResponse=response.getResult();
		
		try {
			JSONObject jsonUserInfo=jsonResponse.getJSONObject("userInfo");
			String username=jsonUserInfo.getString("username");
			String nickname=jsonUserInfo.getString("nickname");
			DataUserInfo dataUserInfo=DataManager.getInstance().getDataUserInfo();
			dataUserInfo.setUser(username, nickname);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		super.onRecv(response);
	}
}
