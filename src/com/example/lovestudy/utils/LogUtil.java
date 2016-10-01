package com.example.lovestudy.utils;

import android.util.Log;

public class LogUtil {
	private static String className;
	private static String methodName;
	private static int lineNumber;
	private static boolean isLogEnable = true;

	/**
	 * ��Log��Ϣ��Ĭ�ϴ򿪣�
	 */
	public static void enableLog() {
		isLogEnable = true;
	}

	/**
	 * �ر�Log��Ϣ
	 */
	public static void disableLog() {
		isLogEnable = false;
	}

	/**
	 * @param log
	 * @return ƴ�Ӻõ�Log�ַ���
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
	 *            ��ȡ������
	 */
	private static void getMethodNames(StackTraceElement[] sElements) {
		className = sElements[1].getFileName();
		methodName = sElements[1].getMethodName();
		lineNumber = sElements[1].getLineNumber();
	}

	/**
	 * @param message
	 *            ��ʾerror��Ϣ
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
	 *            ��ʾerror��Ϣ
	 */
	public static void e(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.e(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            ��ʾinfo��Ϣ
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
	 *            ��ʾinfo��Ϣ
	 */
	public static void i(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.i(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            ��ʾdebug��Ϣ
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
	 *            ��ʾdebug��Ϣ
	 */
	public static void d(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.d(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            ��ʾverbose��Ϣ
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
	 *            ��ʾverbose��Ϣ
	 */
	public static void v(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.v(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            ��ʾwarn��Ϣ
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
	 *            ��ʾwarn��Ϣ
	 */
	public static void w(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.w(tag, createLog(message));
		}
	}

	/**
	 * @param message
	 *            ��ʾwtf��Ϣ
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
	 *            ��ʾwtf��Ϣ
	 */
	public static void wft(String tag, String message) {
		if (isLogEnable) {
			getMethodNames(new Throwable().getStackTrace());
			Log.wtf(tag, createLog(message));
		}
	}
}
