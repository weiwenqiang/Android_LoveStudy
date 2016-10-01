package com.example.lovestudy.activity.user;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class ForgetPasswordActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_forget_password);
		setHeadTitle(R.string.forget_password);
		initView();
	}

	private void initView() {
		
	}
}
