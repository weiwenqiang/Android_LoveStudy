package com.example.lovestudy.activity.user;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class WorkActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_work);
		setHeadTitle(R.string.work);
		initView();
	}

	private void initView() {
		
	}
}
