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

	private final String ACTION_NAME = "���͹㲥";
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
	 * ʵ����һ���㲥�����߶��󣨲��������ݣ�
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
	 * ע��㲥
	 */
	private void registerBoradcastReceiver() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_NAME);
		registerReceiver(mBroadcastReceiver, intentFilter);
	}

	/**
	 * ȡ���㲥��ע��
	 */
	private void unregisterBroadcastReceiver() {
		unregisterReceiver(mBroadcastReceiver);
	}

	/**
	 * ���͹㲥
	 */
	private void sendBroadcastReceiver() {
		Intent mIntent = new Intent(ACTION_NAME);
		mIntent.putExtra("yaner", "���͹㲥���൱�������ﴫ������");
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
