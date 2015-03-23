package com.example.friendmap.user;

public class DataManager {
	private DataLogin dataLogin=new DataLogin();
	private DataUserInfo dataUserInfo=new DataUserInfo();
	private DataLocation dataLocation=new DataLocation();
	
	private static DataManager instance=null;
	public static DataManager getInstance(){
		if(instance == null ){
			instance=new DataManager();
		}
		return instance;
	}
	
	public DataLogin getDataLogin(){
		return dataLogin;
	}
	public DataUserInfo getDataUserInfo(){
		return dataUserInfo;
	}
	public DataLocation getDataLocation(){
		return dataLocation;
	}
}
