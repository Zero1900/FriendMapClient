package com.example.friendmap.ui;

import org.json.JSONObject;

import com.example.friendmap.Consts;
import com.example.friendmap.FMPreference;
import com.example.friendmap.HttpManager;
import com.example.friendmap.R;
import com.example.friendmap.map.LocalService;
import com.example.friendmap.net.FMResponse;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetGetUserInfo;
import com.example.friendmap.net.NetLogin;
import com.example.friendmap.utils.FMCallBack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loadLoginInfo();
	}

	/**
	 * 点击登录
	 * 
	 * @param v
	 */
	public void onBtnClickLogin(View v) {
		Log.v("点击登录", "");
		String username = ((EditText) findViewById(R.id.ui_login_username_edittext)).getText().toString();
		String passwordsrc = ((EditText) findViewById(R.id.ui_login_password_edittext)).getText().toString();
		if (username.length() < 6 || username.length() > 20) {
			showToast(getResources().getString(R.string.ui_register_username_illegal));
			return;
		}
		if (passwordsrc.length() < 6) {
			showToast(getResources().getString(R.string.ui_register_password_illegal));
			return;
		}
		sendLogin(username, passwordsrc);
		saveLoginInfo();
	}

	/**
	 * 点击注册
	 * 
	 * @param v
	 */
	public void onBtnClickRegister(View v) {
		Log.v("点击注册", "");
		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivityForResult(intent, 0);
		// finish();
		overridePendingTransition(R.anim.abc_fade_in, R.anim.hold);
	}

	/**
	 * 发送登录请求
	 * @param username
	 * @param passwordsrc
	 */
	@SuppressLint("HandlerLeak")
	public void sendLogin(String username, String passwordsrc) {
		FMCallBack fmCallBack=new FMCallBack(new Handler()) {
			
			@Override
			public void callback(Object data) {
				// TODO Auto-generated method stub
				recvLogin((FMResponse) data);
			}
		};
		new NetLogin(fmCallBack, username, passwordsrc).post();;
	}
	/**
	 * 接收登录消息
	 * @param response
	 */
	public void recvLogin(FMResponse response) {
		try {
			int errorCode = response.getErrorCode();
			if (errorCode == 210 || errorCode == 211) {
				showToast(getResources().getString(R.string.ui_login_check));
			}
			if (errorCode == 0) {
				sendGetUserInfo();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	/**
	 * 发送获取用户信息的消息
	 */
	public void sendGetUserInfo() {

		FMCallBack fmCallBack=new FMCallBack(new Handler()) {
			
			@Override
			public void callback(Object data) {
				// TODO Auto-generated method stub
				recvGetUserInfo((FMResponse) data);
			}
		};
		new NetGetUserInfo(fmCallBack).post();
	}
	/**
	 * 接收获取用户信息的消息
	 */
	public void recvGetUserInfo(FMResponse response) {
		try {
			int errorCode = response.getErrorCode();
			if (errorCode == 210 || errorCode == 211) {
				showToast(getResources().getString(R.string.ui_login_check));
			}
			if (errorCode == 0) {
				//启动定位服务
				Intent intentService=new Intent(this,LocalService.class);
		        startService(intentService);
				
				//切换至下一activity
				Intent intentActivity = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intentActivity);
				finish();
				overridePendingTransition(R.anim.abc_fade_in, R.anim.hold);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	/**
	 * 返回数据 requestCode 0 resultCode 0:普通返回 1:注册成功
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			FMPreference preference = new FMPreference();
			String username = preference.getLoginUserName();
			String passwordsrc = preference.getLoginPassWord();
			sendLogin(username, passwordsrc);
		}
	}

	/**
	 * 读取保存的用户名、密码
	 */
	private void loadLoginInfo() {
		FMPreference userSets = new FMPreference();
		String username = userSets.getLoginUserName();
		if (username!=null) {
			String password = userSets.getLoginPassWord();
			((EditText) findViewById(R.id.ui_login_username_edittext)).setText(username);
			((EditText) findViewById(R.id.ui_login_password_edittext)).setText(password);
		}
	}

	/**
	 * 存储用户名密码
	 */
	private void saveLoginInfo() {
		String username = ((EditText) findViewById(R.id.ui_login_username_edittext)).getText().toString();
		String passwordsrc = ((EditText) findViewById(R.id.ui_login_password_edittext)).getText().toString();
		FMPreference preference = new FMPreference();
		preference.setLoginInfo(username, passwordsrc);

	}
}
