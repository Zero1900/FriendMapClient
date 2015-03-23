package com.example.friendmap.net;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.friendmap.FMEvent;
import com.example.friendmap.map.FMLocation;
import com.example.friendmap.utils.FMCallBack;

import de.greenrobot.event.EventBus;
import android.os.Handler;

public class NetPositionGetAll extends NetBase {

	public NetPositionGetAll(FMCallBack fmCallBack) {
		super(fmCallBack);
		// TODO Auto-generated constructor stub
		setUrl(encodeUrl("Client/PositionGetAll"));
	}
	public void onRecv(FMResponse fmResponse){
		try {
			JSONArray jsonPositions=fmResponse.getResult().getJSONArray("userPositions");
			List<FMLocation> list=new ArrayList<FMLocation>();
			for(int i=0;i<jsonPositions.length();i++){
				JSONObject jsonObject=(JSONObject) jsonPositions.get(i);
				FMLocation location=new FMLocation(jsonObject.getDouble("longitude"),jsonObject.getDouble("latitude"));
				list.add(location);
			}
			EventBus.getDefault().post(new FMEvent(FMEvent.Type.OtherLocation, list));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onRecv(fmResponse);
	}
}
