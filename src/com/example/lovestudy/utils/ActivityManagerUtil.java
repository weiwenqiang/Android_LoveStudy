package com.example.lovestudy.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityManagerUtil {

	public static List<Activity> activityList = new ArrayList<Activity>();

	public static void exitActivity() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
		activityList.clear();
	}
}
