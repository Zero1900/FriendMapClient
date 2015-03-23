package com.example.friendmap.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.friendmap.R;

import android.R.color;
import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends BaseFragmentActivity {

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

	/** 再按一次退出游戏 */
	private long lTimeExit;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - lTimeExit) > 2000) {
				// 再按一次退出游戏
				Toast.makeText(getApplicationContext(),
						getResources().getString(R.string.ui_common_exit_one_more_time), Toast.LENGTH_SHORT).show();
				lTimeExit = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		initView();

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				return mFragments.get(arg0);
			}
		};

		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 选择页面
				onPageChangeCallBack(position);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// 滑动页面
				// Log.v("page scrolling",
				// "arg0:"+arg0+"   arg1:"+arg1+"   arg2:"+arg2);
				onPageScrollCallBack(arg0, arg1, arg0);
			}

		});

	}

	protected void resetTabBtn() {
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_chat)).setImageResource(R.drawable.ui_btn_chat_a);
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_find)).setImageResource(R.drawable.ui_btn_find_a);
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_func)).setImageResource(R.drawable.ui_btn_func_a);
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_user)).setImageResource(R.drawable.ui_btn_user_a);
	}

	// 初始化tab
	private void initView() {
		// tab页
		MainTabChat tab01 = new MainTabChat();
		MainTabFind tab02 = new MainTabFind();
		MainTabFunc tab03 = new MainTabFunc();
		MainTabUser tab04 = new MainTabUser();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		mFragments.add(tab04);

		// 底部按钮
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_chat)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onTabBtnClickCallBack(0);
			}
		});
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_find)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onTabBtnClickCallBack(1);
			}
		});
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_func)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onTabBtnClickCallBack(2);
			}
		});
		((ImageButton) findViewById(R.id.ui_bottom_tab_btn_user)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onTabBtnClickCallBack(3);
			}
		});
		onPageChangeCallBack(0);
	}

	/** 按钮点击回调 */
	private void onTabBtnClickCallBack(int position) {
		mViewPager.setCurrentItem(position);
		
	}

	/** 页面更改回调 */
	private void onPageChangeCallBack(int position) {
		resetTabBtn();
		
		switch (position) {
		case 0:
			((ImageButton) findViewById(R.id.ui_bottom_tab_btn_chat)).setImageResource(R.drawable.ui_btn_chat_b);
			break;
		case 1:
			((ImageButton) findViewById(R.id.ui_bottom_tab_btn_find)).setImageResource(R.drawable.ui_btn_find_b);
			break;
		case 2:
			((ImageButton) findViewById(R.id.ui_bottom_tab_btn_func)).setImageResource(R.drawable.ui_btn_func_b);
			break;
		case 3:
			((ImageButton) findViewById(R.id.ui_bottom_tab_btn_user)).setImageResource(R.drawable.ui_btn_user_b);
			break;
		}
	}

	/** 页面滑动回调 */
	private void onPageScrollCallBack(int nowPage, float percent, int distance) {

	}
}
