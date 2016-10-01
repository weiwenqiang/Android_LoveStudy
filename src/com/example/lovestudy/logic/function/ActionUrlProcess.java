package com.example.lovestudy.logic.function;

import com.example.lovestudy.activity.view.AdviewActivity;
import com.example.lovestudy.activity.view.ViewPageCursorActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ActionUrlProcess {

	public static void process(Context context, Uri uri) {
		String host = "";
		if (uri.getScheme().equalsIgnoreCase("TestDemo")) {
			host = uri.getHost();
			try {
				if ("Demo1".equals(host)) {
					processDemo1(context);
				}else if("Demo2".equals(host)){
					processDemo2(context);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void addNewTask(Context context, Intent intent) {
		if (context instanceof Service) {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
	}
	
	private static void processDemo1(Context context) throws Exception {
		Intent intent = new Intent();
		intent.setClass(context, ViewPageCursorActivity.class);
		addNewTask(context, intent);
		context.startActivity(intent);
	}
	
	private static void processDemo2(Context context) throws Exception {
		Intent intent = new Intent();
		intent.setClass(context, AdviewActivity.class);
		addNewTask(context, intent);
		context.startActivity(intent);
	}

}
