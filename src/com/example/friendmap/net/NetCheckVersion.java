package com.example.friendmap.net;

import android.os.Handler;


public class NetCheckVersion extends NetBase{
	public NetCheckVersion(Handler handler){
		super(handler);
		setUrl(encodeUrl("Client/CheckVersion"));
	}

	@Override
	public void onRecv(FMResponse response) {
		// TODO Auto-generated method stub
		super.onRecv(response);
	}

	@Override
	public String getRequest() {
		// TODO Auto-generated method stub
		return "";
	}
}
