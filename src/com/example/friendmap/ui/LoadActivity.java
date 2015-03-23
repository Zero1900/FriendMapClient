package com.example.friendmap.ui;

import javax.security.auth.callback.Callback;

import com.example.friendmap.Consts;
import com.example.friendmap.Global;
import com.example.friendmap.HttpManager;
import com.example.friendmap.R;
import com.example.friendmap.net.FMResponse;
import com.example.friendmap.net.NetBase;
import com.example.friendmap.net.NetCheckVersion;
import com.example.friendmap.utils.FMCallBack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// ʱ��
		long delayMillis = 1000;
		// ͼ�국�붯��
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(delayMillis);

		ImageView imageView = (ImageView) findViewById(R.id.imageview_main_icon);
		imageView.startAnimation(alphaAnimation);
		// ���ַ��붯��

		AlphaAnimation animTitleAlpha = new AlphaAnimation(0, 1);
		animTitleAlpha.setDuration(delayMillis);

		TranslateAnimation animTitleTrans = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f);
		animTitleTrans.setDuration(delayMillis);

		AnimationSet animTitleSet = new AnimationSet(true);
		animTitleSet.addAnimation(animTitleAlpha);
		animTitleSet.addAnimation(animTitleTrans);

		TextView textView = (TextView) findViewById(R.id.textview_main_title);
		textView.startAnimation(animTitleSet);
		// �ȴ�ʱ��
		Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				sendCheckVersion();
			}
		};
		handler.postDelayed(runnable, delayMillis);
	}

	public void sendCheckVersion() {
		FMCallBack fmCallBack=new FMCallBack(new Handler()) {
			
			@Override
			public void callback(Object data) {
				// TODO Auto-generated method stub
				recvCheckVersion((FMResponse)data);
			}
		};
		new NetCheckVersion(fmCallBack).post();
	}

	public void recvCheckVersion(FMResponse response) {
		onNextActivity();
	}

	public void onNextActivity() {
		Intent intent = new Intent(LoadActivity.this, LoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.abc_fade_in, R.anim.hold);
		finish();
	}
}
