package com.example.lovestudy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {

	private Context context;
	private SharedPreferences preferences;
	private Editor editor;

	public SharedPreferenceUtil(Context context) {
		this.context = context;
		init();
	}

	@SuppressWarnings("static-access")
	private void init() {
		preferences = context.getSharedPreferences("Constant", context.MODE_PRIVATE);
		editor = preferences.edit();
	}

	/**
	 * 保存参数
	 */
	public void setData(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 获取参数
	 */
	public String getData(String key) {
		String value = preferences.getString(key, "0");
		return value;
	}

}
