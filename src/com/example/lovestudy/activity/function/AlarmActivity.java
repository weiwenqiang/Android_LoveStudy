package com.example.lovestudy.activity.function;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class AlarmActivity extends BaseActivity {

	private AlarmManager alarmManager;
	private PendingIntent mPendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_alarm);
		setHeadTitle(R.string.alarm);
		initView();
	}

	private void initView() {
		findViewById(R.id.clock).setOnClickListener(mOnClickListener);
		findViewById(R.id.repeating_clock).setOnClickListener(mOnClickListener);
		findViewById(R.id.cancel_clock).setOnClickListener(mOnClickListener);

		/** ��ȡAlarmManager���� */
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		/** ����Intent���� */
		Intent intent = new Intent();
		intent.setAction("android.intent.action.ALARM_RECEIVER");
		mPendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.clock:
				/** ����һ�������� */
				alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10000, mPendingIntent);
				break;
			case R.id.repeating_clock:
				/** �����ظ����� */
				alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, 10000, mPendingIntent);
				break;
			case R.id.cancel_clock:
				/** ȡ������ */
				alarmManager.cancel(mPendingIntent);
				break;
			}
		}
	};
}
