package com.example.friendmap.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.friendmap.map.FMLocation;
import com.example.friendmap.map.LocalService;
import com.example.friendmap.user.DataLocation;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.utils.FMCallBack;

import android.os.Handler;

public class NetCommitPosition extends NetBase {

	public NetCommitPosition(FMCallBack fmCallBack, FMLocation location) {
		super(fmCallBack);
		// TODO Auto-generated constructor stub
		setUrl(encodeUrl("Client/CommitPosition"));
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("longitude", location.getLongitude());
			jsonObject.put("latitude", location.getLatitude());
			setRequest(jsonObject.toString());
		} catch (JSONException e) {
			// TODO: handle exception
		}

	}

	public void onRecv(FMResponse response) {
		try {
			JSONObject jsonObject = response.getResult().getJSONObject("locationSetting");
			long timeCommitInterval = jsonObject.getLong("timeCommitInterval");
			long timeLocaleInterval = jsonObject.getLong("timeLocaleInterval");
			DataLocation dataLocation=DataManager.getInstance().getDataLocation();
			dataLocation.setTimeCommitInterval(timeCommitInterval);
			dataLocation.setTimeLocaleInterval(timeLocaleInterval);
		} catch (Exception e) {
			e.printStackTrace();
		}

		super.onRecv(response);
	}
}
