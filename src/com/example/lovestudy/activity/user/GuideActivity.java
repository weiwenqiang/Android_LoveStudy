package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.lovestudy.activity.MainActivity;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseViewPageAdapter;

public class GuideActivity extends BaseActivity {

	private ViewPager viewPager;
	private View view1, view2, view3;
	private ArrayList<View> views;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_guide);
		setHeadTitle(R.string.guide);
		initView();
	}

	private void initView() {
		bindData();
		bindView();
	}

	@SuppressLint("InflateParams")
	private void bindData() {
		views = new ArrayList<View>();
		LayoutInflater layoutInflater = getLayoutInflater();
		view1 = layoutInflater.inflate(R.layout.layout_guide_one, null);
		view2 = layoutInflater.inflate(R.layout.layout_guide_two, null);
		view3 = layoutInflater.inflate(R.layout.layout_guide_three, null);
		view3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				gotoTargetActivity(MainActivity.class);
				finish();
			}
		});
		views.add(view1);
		views.add(view2);
		views.add(view3);
	}

	private void bindView() {
		viewPager = (ViewPager) findViewById(R.id.viewpage);
		viewPager.setAdapter(new BaseViewPageAdapter(views));
		viewPager.setCurrentItem(0);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			break;
		}
		return false;
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}
	
}
