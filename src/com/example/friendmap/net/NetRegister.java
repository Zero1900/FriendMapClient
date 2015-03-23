package com.example.friendmap.net;

import org.json.JSONException;
import org.json.JSONObject;




import com.example.friendmap.utils.MD5;

import android.os.Handler;

public class NetRegister extends NetBase {
	public NetRegister(Handler handler,String username,String nickname,String passwordsrc) {
		super(handler);
		// TODO Auto-generated constructor stub
		setUrl(encodeUrl("Client/Register"));
		try {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("username", username);
			jsonObject.put("nickname", nickname);
			String password=MD5.getMD5(passwordsrc);
			jsonObject.put("password", password);
			setRequest(jsonObject.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void onRecv(FMResponse response){
		super.onRecv(response);
	}
}
