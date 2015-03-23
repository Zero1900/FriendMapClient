package com.example.friendmap.ui;

import com.example.friendmap.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
	}
	
	public void showToast(String text){
		Toast.makeText(getApplicationContext(), text, 1000).show();
	}
}
