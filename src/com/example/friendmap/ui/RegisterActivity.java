package com.example.friendmap.ui;

import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.friendmap.Consts;
import com.example.friendmap.FMPreference;
import com.example.friendmap.HttpManager;
import com.example.friendmap.R;
import com.example.friendmap.R.layout;
import com.example.friendmap.net.FMResponse;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetRegister;
import com.example.friendmap.utils.FMCallBack;
import com.example.friendmap.utils.MD5;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	/**
	 * 点击注册
	 * 
	 * @param view
	 */
	public void onBtnClickRegister(View view) {
		String username = ((EditText) findViewById(R.id.ui_register_username_edittext)).getText().toString();
		String nickname = ((EditText) findViewById(R.id.ui_register_nickname_edittext)).getText().toString();
		String passwordsrc = ((EditText) findViewById(R.id.ui_register_password_edittext)).getText().toString();
		String passconfirm = ((EditText) findViewById(R.id.ui_register_passconfirm_edittext)).getText().toString();
		
		if(username.length()<6||username.length()>20){
			showToast(getResources().getString(R.string.ui_register_username_illegal));
			return;
		}
		if(passwordsrc.length()<6){
			showToast(getResources().getString(R.string.ui_register_password_illegal));
			return;
		}
		if (!passconfirm.equals(passwordsrc)) {
			showToast(getResources().getString(R.string.ui_register_password_check));
			return;
		}
		sendRegister(username, nickname, passwordsrc);
	}

	public void sendRegister(final String username, String nickname, final String passwordsrc) {
		FMCallBack fmCallBack=new FMCallBack(new Handler()) {
			
			@Override
			public void callback(Object data) {
				// TODO Auto-generated method stub
				recvRegister((FMResponse) data, username, passwordsrc);
			}
		};
		new NetRegister(fmCallBack, username, nickname, passwordsrc).post();
		
	}

	public void recvRegister(FMResponse response,String username,String passwordsrc) {
		int errorCode=response.getErrorCode();
		if (errorCode==202){
			showToast(getResources().getString(R.string.ui_register_username_exist));
			return;
		}
		if(errorCode==0){
			FMPreference userSets=new FMPreference();
			userSets.setLoginInfo(username, passwordsrc);
			
			setResult(1);
			finish();
		}
	}
}
