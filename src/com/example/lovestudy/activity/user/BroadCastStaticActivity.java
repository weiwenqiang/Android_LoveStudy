package com.example.lovestudy.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class BroadCastStaticActivity extends BaseActivity {

	private TextView txt_send_broadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_broadcast_static);
		setHeadTitle(R.string.static_broadcast);
		initView();
	}

	private void initView() {
		txt_send_broadcast = (TextView) findViewById(R.id.txt_send_broadcast);
		txt_send_broadcast.setOnClickListener(mOnClickListener);
	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_send_broadcast:
				sendBroadCast();
				break;
			}
		}
	};

	private void sendBroadCast() {
		Intent intent = new Intent();
		intent.setAction("com.example.lovestudy.activity.user.BroadCastStatic");
		intent.putExtra("Content", "这就是通知的内容了.......");
		sendBroadcast(intent);
	}

}
