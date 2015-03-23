package com.example.friendmap.user;

public class DataUserInfo {
	private String username="";
	private String nickname="";
	public void setUser(String username,String nickname){
		this.username=new String(username);
		this.nickname=new String(nickname);
	}
	public String getNickName(){
		return nickname;
	}
	public String getUserName() {
		// TODO Auto-generated method stub
		return username;
	}
	
}
