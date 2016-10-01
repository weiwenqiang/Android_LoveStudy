package com.example.lovestudy.activity.function;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class BackgroundGradientActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_background_gradient);
		setHeadTitle(R.string.background_color_gradient);
		initView();
	}

	private void initView() {
		
	}
}
