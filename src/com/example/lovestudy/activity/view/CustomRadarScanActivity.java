package com.example.lovestudy.activity.view;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class CustomRadarScanActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_custom_radar_scan);
		setHeadTitle(R.string.view_custom_radarscan);
		initView();
	}

	private void initView() {
		
	}
}
