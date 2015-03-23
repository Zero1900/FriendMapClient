package com.example.friendmap.map;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.friendmap.FMEvent;
import com.example.friendmap.R;
import com.example.friendmap.user.DataManager;

import de.greenrobot.event.EventBus;

/**
 * 此demo用来展示如何结合定位SDK实现定位，并使用MyLocationOverlay绘制定位位置 同时展示如何使用自定义图标绘制并点击时弹出泡泡
 * 
 */
public class MapActivity extends Activity {
	private MapView mMapView;
	private BaiduMap mBaiduMap;

	private List<Marker> listMakers=new ArrayList<Marker>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		if (intent.hasExtra("x") && intent.hasExtra("y")) {
			// 当用intent参数时，设置中心点为指定点
			Bundle b = intent.getExtras();
			LatLng p = new LatLng(b.getDouble("y"), b.getDouble("x"));
			mMapView = new MapView(this, new BaiduMapOptions().mapStatus(new MapStatus.Builder().target(p).build()));
		} else {
			mMapView = new MapView(this, new BaiduMapOptions());
		}
		setContentView(mMapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMyLocationEnabled(true);
		listMakers.clear();
	}

	

	@Override
	protected void onPause() {
		super.onPause();
		// activity 暂停时同时暂停地图控件
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// activity 恢复时同时恢复地图控件
		mMapView.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// activity 销毁时同时销毁地图控件
		mMapView.onDestroy();
		listMakers.clear();
	}

	protected void updateMyLocData(FMLocation fmLocation) 
	{if (fmLocation == null) {
			return;
		}
		MyLocationData locationData = new MyLocationData.Builder().latitude(fmLocation.getLatitude())
				.longitude(fmLocation.getLongitude()).build();
		mBaiduMap.setMyLocationData(locationData);
	}
	protected void updateOtherLocData(List<FMLocation>list){
		for(ListIterator<Marker> it=listMakers.listIterator();it.hasNext();){
			Marker marker=it.next();
			marker.remove();
		}
		listMakers.clear();
		for(ListIterator<FMLocation>it=list.listIterator();it.hasNext();){
			FMLocation fmLocation=it.next();
			//定义Maker坐标点  
			LatLng point = new LatLng(fmLocation.getLatitude(), fmLocation.getLongitude());  
			//构建Marker图标  
			BitmapDescriptor bitmap = BitmapDescriptorFactory  
			    .fromResource(R.drawable.map_icon_loc);  
			//构建MarkerOption，用于在地图上添加Marker  
			OverlayOptions option = new MarkerOptions()  
			    .position(point) 
			    .icon(bitmap);  
			//在地图上添加Marker，并显示  
			listMakers.add((Marker)mBaiduMap.addOverlay(option));
		}
	}
	public void animateTo(FMLocation fmLocation) {
		if(fmLocation==null){
			return;
		}
		LatLng ll = new LatLng(fmLocation.getLatitude(), fmLocation.getLongitude());
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
		mBaiduMap.animateMapStatus(u);
	}

}
