package com.example.lovestudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

import com.example.lovestudy.base.BaseActivity;

public class JpushShowActivity extends BaseActivity {

	private TextView JpushTitle, JpushContent;
	private Intent intent;
	private String title, content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jpush_show);
		setHeadTitle(R.string.jpush_show);
		initIntent();
		initView();
		bindView();
	}

	private void initIntent() {
		intent = getIntent();
		if (null != intent) {
			Bundle bundle = getIntent().getExtras();
			title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
			content = bundle.getString(JPushInterface.EXTRA_ALERT);
		}
	}

	private void initView() {
		JpushTitle = (TextView) findViewById(R.id.txt_jpush_title);
		JpushContent = (TextView) findViewById(R.id.txt_jpush_content);
	}

	private void bindView() {
		JpushTitle.setText("±êÌâ:" + title);
		JpushContent.setText("ÄÚÈÝ:\n" + content);
	}
}
