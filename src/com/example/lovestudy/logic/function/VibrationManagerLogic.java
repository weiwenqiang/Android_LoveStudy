package com.example.lovestudy.logic.function;

import android.app.Activity;
import android.app.Service;
import android.os.Vibrator;

public class VibrationManagerLogic {

	public static Vibrator vib;

	public static void Vibrate(final Activity activity, long milliseconds) {
		vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(milliseconds);
	}

	public static void Vibrate(final Activity activity, long[] pattern, boolean isRepeat) {
		vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(pattern, isRepeat ? 1 : -1);
	}

	public static void cancle(final Activity activity) {
		vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
		vib.cancel();
	}

}
