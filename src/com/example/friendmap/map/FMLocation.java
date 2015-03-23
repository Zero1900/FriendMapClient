package com.example.friendmap.map;


import com.baidu.location.BDLocation;

public class FMLocation {
	private double longitude,latitude;
	public FMLocation(BDLocation bdLocation){
		longitude=bdLocation.getLongitude();
		latitude=bdLocation.getLatitude();
	}
	public FMLocation(double longitude,double latitude){
		this.longitude=longitude;
		this.latitude=latitude;
	}
	public double getLongitude(){
		return longitude;
	}
	public double getLatitude(){
		return latitude;
	}
}
