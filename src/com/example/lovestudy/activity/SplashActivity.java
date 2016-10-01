package com.example.lovestudy.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import cn.jpush.android.api.JPushInterface;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.user.GuideActivity;
import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.utils.SharedPreferenceUtil;

public class SplashActivity extends Activity {

	private SharedPreferenceUtil sharedPreferenceUtil;
	private Thread mThread;
	private ImageView mImageView;
	private AnimationDrawable mDrawable;

	/**
	 * 创建的时候
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_splash);
		sharedPreferenceUtil = new SharedPreferenceUtil(this);
		initView();
	}

	/**
	 * 继续的时候
	 */
	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(getApplicationContext());
		JPushInterface.onPause(getApplicationContext());
	}

	/**
	 * 暂停的时候
	 */
	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onResume(getApplicationContext());
		JPushInterface.onPause(getApplicationContext());
	}

	private void initView() {
		mImageView = (ImageView) findViewById(R.id.SplashImageView);
		mImageView.setBackgroundResource(R.drawable.splash_drawable);
		mDrawable = (AnimationDrawable) mImageView.getBackground();
		mImageView.post(new Runnable() {
			@Override
			public void run() {
				mDrawable.start();
			}
		});
		mThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(1500);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Intent intent = new Intent();
				if (Integer.parseInt(sharedPreferenceUtil.getData(Constant.firstOpenApp)) == 0) {
					sharedPreferenceUtil.setData(Constant.firstOpenApp, "1");
					intent.setClass(SplashActivity.this, GuideActivity.class);
				} else {
					intent.setClass(SplashActivity.this, MainActivity.class);
				}
				startActivity(intent);
				finish();
			}
		};
		mThread.start();
	}
}
