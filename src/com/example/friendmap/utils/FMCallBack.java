package com.example.friendmap.utils;

import android.os.Handler;

public abstract class FMCallBack {
	Handler handler;
	public FMCallBack(Handler handler){
		this.handler=handler;
	}
	public void post(final Object data){
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				callback(data);
			}
		});
	}
	public abstract void callback(Object data);
}
