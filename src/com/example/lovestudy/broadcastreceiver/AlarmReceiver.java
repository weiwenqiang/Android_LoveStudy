package com.example.lovestudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("�������ӣ���Ҫ������...");
		Toast.makeText(context, "�������ӣ���Ҫ������...", Toast.LENGTH_SHORT).show();
	}
}
