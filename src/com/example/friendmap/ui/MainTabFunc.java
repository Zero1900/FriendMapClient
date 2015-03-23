package com.example.friendmap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.friendmap.FMApplication;
import com.example.friendmap.FMTimerManager;
import com.example.friendmap.HttpManager;
import com.example.friendmap.R;
import com.example.friendmap.map.MapActivity;
import com.example.friendmap.map.MapPosAllActivity;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetPositionGetAll;
import com.example.friendmap.ui.utils.RowButton;
import com.example.friendmap.utils.FMCallBack;
import com.example.friendmap.utils.FMTimer;

import de.greenrobot.event.EventBus;

public class MainTabFunc extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_func, container, false);

		((TextView) view.findViewById(R.id.ui_title_text))
				.setText(getResources().getString(R.string.ui_title_tab_func));

		RowButton btnMapAll = ((RowButton) view.findViewById(R.id.ui_tab_func_mapall_rowbutton));
		btnMapAll.setOnBtnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onClickMapAll();
			}
		});

		return view;
	}

	public void onClickMapAll() {
		Intent intent = new Intent(getActivity(), MapPosAllActivity.class);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.hold);
	}
}
