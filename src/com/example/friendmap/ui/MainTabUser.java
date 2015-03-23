package com.example.friendmap.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.friendmap.FMApplication;
import com.example.friendmap.R;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.user.DataUserInfo;

public class MainTabUser extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_user, container, false);

		((TextView) view.findViewById(R.id.ui_title_text)).setText(getResources().getString(R.string.ui_title_tab_user));

		DataUserInfo dataUserInfo=DataManager.getInstance().getDataUserInfo();
		String username=dataUserInfo.getUserName();
		String nickname=dataUserInfo.getNickName();
		
		((TextView) view.findViewById(R.id.ui_tab_user_info_username_textview)).setText(username);
		((TextView) view.findViewById(R.id.ui_tab_user_info_nickname_textview)).setText(nickname);
		
		return view;
	}
	
	
	
	/**
	 * 点击按钮
	 * 
	 * @param view
	 */
	public void onClickRowButton(View view) {
		Log.v("点击按钮", "");
	}

}
