package com.example.lovestudy.broadcastreceiver;

import com.example.lovestudy.base.BaseMethod;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StaticBroadCastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("com.example.lovestudy.activity.user.BroadCastStatic")) {
			BaseMethod.showToast(context, intent.getStringExtra("Content"));
		}
	}

}
