package com.example.friendmap.net;

import org.json.JSONException;
import org.json.JSONObject;

public class FMResponse {
	private JSONObject jsonResponse;

	public FMResponse(String response) throws JSONException {
		// TODO Auto-generated constructor stub
		jsonResponse = new JSONObject(response);
	}
	public int getErrorCode(){
		int errorCode=-1;
		try {
			errorCode =jsonResponse.getInt("errorCode");
		} catch (Exception e) {
			// TODO: handle exception
			errorCode=-1;
		} 
		return errorCode;
	}
	public JSONObject getResult() {
		// TODO Auto-generated method stub
		try {
			return jsonResponse.getJSONObject("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
