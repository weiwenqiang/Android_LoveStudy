package com.example.lovestudy.activity.user;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class HeadPortraitActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_head_portrait);
		setHeadTitle(R.string.head_portrait);
		initView();
	}

	private void initView() {
		
	}	
}
