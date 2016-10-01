package com.example.lovestudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("我是闹钟，我要叫醒你...");
		Toast.makeText(context, "我是闹钟，我要叫醒你...", Toast.LENGTH_SHORT).show();
	}
}
