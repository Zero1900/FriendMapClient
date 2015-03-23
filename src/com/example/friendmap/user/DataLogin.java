package com.example.friendmap.user;

public class DataLogin {
	private String strSession=null;
	public void login(String session){
		strSession=new String(session);
	}
	public String getSession(){
		return strSession;
	}
}
