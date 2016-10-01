package com.example.lovestudy.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

public class BrightnessUtil {
	public static int getScreenBrightness(Activity paramActivity) {
		ContentResolver localContentResolver = paramActivity.getContentResolver();
		try {
			int i = Settings.System.getInt(localContentResolver, "screen_brightness");
			return i;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}

	public static boolean isAutoBrightness(ContentResolver paramContentResolver) {
		try {
			int i = Settings.System.getInt(paramContentResolver, "screen_brightness_mode");
			return i == 1;
		} catch (Settings.SettingNotFoundException localSettingNotFoundException) {
			localSettingNotFoundException.printStackTrace();
		}
		return false;
	}

	public static void saveBrightness(ContentResolver paramContentResolver, int paramInt) {
		Uri localUri = Settings.System.getUriFor("screen_brightness");
		Settings.System.putInt(paramContentResolver, "screen_brightness", paramInt);
		paramContentResolver.notifyChange(localUri, null);
	}

	public static void setBrightness(Activity paramActivity, int paramInt) {
		WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
		localLayoutParams.screenBrightness = (0.003921569F * Float.valueOf(paramInt).floatValue());
		paramActivity.getWindow().setAttributes(localLayoutParams);
	}

	public static void startAutoBrightness(Activity paramActivity) {
		Settings.System.putInt(paramActivity.getContentResolver(), "screen_brightness_mode", 1);
	}

	public static void stopAutoBrightness(Activity paramActivity) {
		Settings.System.putInt(paramActivity.getContentResolver(), "screen_brightness_mode", 0);
	}
}
