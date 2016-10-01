package com.example.lovestudy.broadcastreceiver;

import com.example.lovestudy.activity.JpushShowActivity;
import cn.jpush.android.api.JPushInterface;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class JPushCustomReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
			Intent intent2 = new Intent(context, JpushShowActivity.class);
			intent2.putExtras(bundle);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
			context.startActivity(intent2);
		}
	}
}
