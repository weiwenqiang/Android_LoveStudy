package com.example.lovestudy.activity.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;

public class BroadCastDynamicActivity extends BaseActivity {

	private final String ACTION_NAME = "发送广播";
	private TextView txt_register_broadcast;
	private TextView txt_unregister_broadcast;
	private TextView txt_send_broadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_broadcast_dynamic);
		setHeadTitle(R.string.dynamic_broadcast);
		initView();
	}

	private void initView() {
		txt_register_broadcast = (TextView) findViewById(R.id.txt_register_broadcast);
		txt_unregister_broadcast = (TextView) findViewById(R.id.txt_unregister_broadcast);
		txt_send_broadcast = (TextView) findViewById(R.id.txt_send_broadcast);

		txt_register_broadcast.setOnClickListener(mOnClickListener);
		txt_unregister_broadcast.setOnClickListener(mOnClickListener);
		txt_send_broadcast.setOnClickListener(mOnClickListener);
	}

	/**
	 * 实例化一个广播接受者对象（并处理数据）
	 */
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ACTION_NAME)) {
				BaseMethod.showToast(ctx, intent.getStringExtra("yaner"));
			}
		}
	};

	/**
	 * 注册广播
	 */
	private void registerBoradcastReceiver() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_NAME);
		registerReceiver(mBroadcastReceiver, intentFilter);
	}

	/**
	 * 取消广播的注册
	 */
	private void unregisterBroadcastReceiver() {
		unregisterReceiver(mBroadcastReceiver);
	}

	/**
	 * 发送广播
	 */
	private void sendBroadcastReceiver() {
		Intent mIntent = new Intent(ACTION_NAME);
		mIntent.putExtra("yaner", "发送广播，相当于在这里传送数据");
		sendBroadcast(mIntent);
	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_register_broadcast:
				registerBoradcastReceiver();
				break;
			case R.id.txt_unregister_broadcast:
				unregisterBroadcastReceiver();
				break;
			case R.id.txt_send_broadcast:
				sendBroadcastReceiver();
				break;
			}
		}
	};

}
