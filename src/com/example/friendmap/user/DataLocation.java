package com.example.friendmap.user;

import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.example.friendmap.map.FMLocation;

public class DataLocation {
	private List<FMLocation> listLocations;
	
	private long timeLocaleInterval = 30000;
	private long timeCommitInterval = 30000;
	public DataLocation() {
		// TODO Auto-generated constructor stub
		listLocations = new ArrayList<FMLocation>();
	}
	public void setTimeLocaleInterval(long time){
		timeLocaleInterval=time;
	}
	public long getTimeLocaleInterval(){
		return timeLocaleInterval;
	}
	public void setTimeCommitInterval(long time){
		timeCommitInterval=time;
	}
	public long getTimeCommitInterval(){
		return timeCommitInterval;
	}
	public void appendLocation(BDLocation bdlocation) {
		if (listLocations.size() > 100) {
			listLocations.clear();
		}
		listLocations.add(new FMLocation(bdlocation));
	}

	public List<FMLocation> getLocations() {
		return listLocations;
	}

	public List<FMLocation> getNotCommitedLocations() {
		return listLocations;
	}
	public FMLocation getLastLocation() {
		if (listLocations.size() > 0) {
			return listLocations.get(listLocations.size() - 1);
		} else {
			return null;
		}
	}
}
