package com.example.friendmap.map;

import java.util.List;

import com.example.friendmap.Consts;
import com.example.friendmap.FMEvent;
import com.example.friendmap.HttpManager;
import com.example.friendmap.net.FMResponse;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetGetUserInfo;
import com.example.friendmap.net.NetPositionGetAll;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.utils.FMCallBack;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MapPosAllActivity extends MapActivity {
	public boolean bRun=false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FMLocation fmLocation=DataManager.getInstance().getDataLocation().getLastLocation();
		animateTo(fmLocation);
		updateMyLocData(fmLocation);
		
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		EventBus.getDefault().register(this);
		bRun=true;
		sendPositionGetAll();
	};
	@Override
	protected void onPause(){
		super.onPause();
		EventBus.getDefault().unregister(this);
		bRun=false;
	};
	
	public void onEvent(FMEvent event) {
		switch (event.getType()) {
		case Location:
			updateMyLocData((FMLocation)event.getData());
			break;
		case OtherLocation:
			updateOtherLocData((List<FMLocation>)event.getData());
			break;
		default:
			break;
		}
	}
	public void sendPositionGetAll(){
		if(!bRun){
			return;
		}
		FMCallBack fmCallBack=new FMCallBack(new Handler()) {
			
			@Override
			public void callback(Object data) {
				// TODO Auto-generated method stub
				recvPositionGetAll((FMResponse)data);
			}
		};
		new NetPositionGetAll(fmCallBack).post();;
	}
	public void recvPositionGetAll(FMResponse fmResponse){
		Handler handler=new Handler();
		Runnable runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sendPositionGetAll();
			}
		};
		handler.postDelayed(runnable, 10000);
	}
}
