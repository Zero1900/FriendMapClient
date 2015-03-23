package com.example.friendmap;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.example.friendmap.map.FMLocation;
import com.example.friendmap.user.DataManager;

import de.greenrobot.event.EventBus;
import android.app.Application;

public class FMApplication extends Application {
	private static FMApplication instance;
	
	private FMTimerManager timerManager;

	private LocationClient mLocationClient;
	private GeofenceClient mGeofenceClient;
	private MyLocationListener mMyLocationListener;

	public static FMApplication getInstance() {
		return instance;
	}

	public LocationClient getLocationClient() {
		return mLocationClient;
	}
	public FMTimerManager getTimerManager(){
		return timerManager;
	}
	public void onCreate() {
		super.onCreate();
		instance = this;
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(getApplicationContext());
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
		
		timerManager=new FMTimerManager();

	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			DataManager.getInstance().getDataLocation().appendLocation(location);
			FMLocation fmLocation=DataManager.getInstance().getDataLocation().getLastLocation();
			EventBus.getDefault().post(new FMEvent(FMEvent.Type.Location,fmLocation));
		}
	}

}
