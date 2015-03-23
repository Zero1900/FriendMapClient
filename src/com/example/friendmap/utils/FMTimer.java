package com.example.friendmap.utils;

import android.os.Handler;

public class FMTimer {
	private long timeInterval;
	private FMCallBack callBack;
	private boolean run;

	public FMTimer(long timeInterval,FMCallBack callBack) {
		this.callBack = callBack;
		this.timeInterval = timeInterval;
	}

	public void start() {
		run=true;
		doFunc();
	}

	public void stop() {
		run=false;
	}

	private void doFunc() {
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(!run){
					return;
				}
				callBack.callback(null);
				doFunc();
			}
		};
		handler.postDelayed(runnable, timeInterval);
	}

}
