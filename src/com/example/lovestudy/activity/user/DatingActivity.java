package com.example.lovestudy.activity.user;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class DatingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_dating);
		setHeadTitle(R.string.dating);
		initView();
	}

	private void initView() {
		
	}
}
