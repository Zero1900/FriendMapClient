package com.example.friendmap.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.friendmap.R;

public class MainTabFind extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_find, container, false);

		((TextView) view.findViewById(R.id.ui_title_text)).setText(getResources().getString(R.string.ui_title_tab_find));

		return view;
	}

}
