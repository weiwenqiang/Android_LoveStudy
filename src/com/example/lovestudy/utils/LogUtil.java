package com.example.lovestudy.utils;

import android.util.Log;

public class LogUtil {
	private static String className;
	private static String methodName;
	private static int lineNumber;
	private static boolean isLogEnable = true;

	/**
	 * 打开Log信息（默认打开）
	 */
	public static void enableLog() {
		isLogEnable = true;
	}

	/**
	 * 关闭Log信息
	 */
	public static void disableLog() {
		isLogEnable = false;
	}

	/**
	 * @param log
	 * @return 拼接好的Log字符串
	 */
	private static String createLog(String log) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(methodName);
		buffer.append(":");
		buffer.append(lineNumber);
		buffer.append("]");
		buffer.append(log);

		return buffer.toString();
	}

	/**
	 * @param sElements
	 *            获取方法名
	 */
	private static void getMethodNames(StackTraceElement[] sElements) {
		className = sElements[1].getFileName();
		methodName = sElements[1].getMethodName();
		lineNumber = sElements[1].getLineNumber();
	}

	/**
	 * @param message
	 *            显示error信息
	 */
	public static void e(String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.e(className, createLog(message));
		}
	}

	/**
	 * @param tag
	 * @param message
	 *            显示error信息
	 */
	public static void e(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.e(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            显示info信息
	 */
	public static void i(String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.i(className, createLog(message));
		}
	}

	/**
	 * @param tag
	 * @param message
	 *            显示info信息
	 */
	public static void i(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.i(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            显示debug信息
	 */
	public static void d(String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.d(className, createLog(message));
		}
	}

	/**
	 * @param tag
	 * @param message
	 *            显示debug信息
	 */
	public static void d(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.d(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            显示verbose信息
	 */
	public static void v(String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.v(className, createLog(message));
		}
	}

	/**
	 * @param tag
	 * @param message
	 *            显示verbose信息
	 */
	public static void v(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.v(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            显示warn信息
	 */
	public static void w(String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.w(className, createLog(message));
		}
	}

	/**
	 * @param tag
	 * @param message
	 *            显示warn信息
	 */
	public static void w(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.w(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            显示wtf信息
	 */
	public static void wtf(String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.wtf(className, createLog(message));
		}
	}

	/**
	 * @param tag
	 * @param message
	 *            显示wtf信息
	 */
	public static void wft(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.wtf(tag, createLog(message));
		}
	}
}
