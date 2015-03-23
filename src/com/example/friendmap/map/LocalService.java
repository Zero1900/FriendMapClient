package com.example.friendmap.map;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.example.friendmap.Consts;
import com.example.friendmap.FMApplication;
import com.example.friendmap.HttpManager;
import com.example.friendmap.R;
import com.example.friendmap.net.FMResponse;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetCommitPosition;
import com.example.friendmap.net.NetLogin;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.utils.FMCallBack;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class LocalService extends Service {

	private static final String TAG = "LocalService";
	private IBinder binder = new LocalService.LocalBinder();
	private long timeLocaleInterval = 0;

	@Override
	public IBinder onBind(Intent intent) {

		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 默认设置
		startLocationClient();
		commitPosition();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		LocationClient locationClient = FMApplication.getInstance().getLocationClient();
		if (locationClient.isStarted()) {
			//locationClient.stop();
		}

		super.onDestroy();
	}

	// 定义内容类继承Binder
	public class LocalBinder extends Binder {
		// 返回本地服务
		LocalService getService() {
			return LocalService.this;
		}
	}

	public void updateTimeLocaleIntercal() {
		if (timeLocaleInterval == DataManager.getInstance().getDataLocation().getTimeLocaleInterval()) {
			return;
		}
		LocationClient locationClient = FMApplication.getInstance().getLocationClient();
		if (locationClient.isStarted()) {
			locationClient.stop();
		}
		startLocationClient();
	}

	private void startLocationClient() {
		timeLocaleInterval = DataManager.getInstance().getDataLocation().getTimeLocaleInterval();
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);
		option.setScanSpan((int) timeLocaleInterval);
		option.setIsNeedAddress(false);
		option.setCoorType("bd09ll");

		LocationClient locationClient = FMApplication.getInstance().getLocationClient();
		locationClient.setLocOption(option);

		locationClient.start();
	}

	public void commitPosition() {
		updateTimeLocaleIntercal();
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FMLocation fmLocation = DataManager.getInstance().getDataLocation().getLastLocation();
				if (fmLocation != null) {
					sendCommit(fmLocation);
				}else{
					commitPosition();
				}
			}
		};
		handler.postDelayed(runnable, DataManager.getInstance().getDataLocation().getTimeCommitInterval());
	}

	/**
	 * 发送提交消息
	 * 
	 * @param username
	 * @param passwordsrc
	 */
	@SuppressLint("HandlerLeak")
	public void sendCommit(FMLocation fmLocation) {
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == Consts.MSG_NETCALLBCAK) {
					recvCommit((FMResponse) msg.obj);
				}
				super.handleMessage(msg);
			}
		};
		FMCallBack fmCallBack=new FMCallBack(new Handler()) {
			
			@Override
			public void callback(Object data) {
				// TODO Auto-generated method stub
				recvCommit((FMResponse)data);
			}
		};
		new NetCommitPosition(fmCallBack, fmLocation).post();
	}

	/**
	 * 接收提交消息
	 * 
	 * @param response
	 */
	public void recvCommit(FMResponse response) {
		try {
			int errorCode = response.getErrorCode();
			if (errorCode == 0) {
				commitPosition();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}