package com.example.friendmap;




import com.example.friendmap.utils.AESUtil;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class FMPreference {
	public FMPreference() {
	}
	private SharedPreferences getSharedPreference(){
		return FMApplication.getInstance().getSharedPreferences("UserSets",0);
	}
	public void setLoginInfo(String username,String passwordsrc){
		try {
			SharedPreferences sp=getSharedPreference();
			Editor editor=sp.edit();
			editor.putString("login_username", username);
			String passwordaes=AESUtil.encrypt("a",passwordsrc);
			editor.putString("login_password", passwordaes);
			editor.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getLoginUserName(){
		String username=getSharedPreference().getString("login_username", null);
		return username;
	}
	public String getLoginPassWord(){
		String username=getLoginUserName();
		String passwordaes=getSharedPreference().getString("login_password", null);
		String password=null;
		try {
			password=AESUtil.decrypt("a",passwordaes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
}
