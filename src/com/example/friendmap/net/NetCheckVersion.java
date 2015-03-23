package com.example.friendmap.net;

import com.example.friendmap.utils.FMCallBack;

import android.os.Handler;


public class NetCheckVersion extends NetBase{
	public NetCheckVersion(FMCallBack fmCallBack){
		super(fmCallBack);
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
