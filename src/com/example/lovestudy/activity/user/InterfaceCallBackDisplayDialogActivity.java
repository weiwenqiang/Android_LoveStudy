package com.example.lovestudy.activity.user;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class InterfaceCallBackDisplayDialogActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_interface_callback_display_dialog);
		setHeadTitle(R.string.interface_callback_display_dialog);
		initView();
	}

	private void initView() {

	}
}
