package com.example.lovestudy.base;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.widget.Toast;

public class BaseMethod {

	/**
	 * @param ctx
	 *            打开本地相册的方法
	 */
	public static void openPhotoAlbum(BaseActivity ctx) {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		ctx.startActivityForResult(intent, 0);
	}

	/**
	 * @param ctx
	 * @param fileName
	 *            打开相机的方法
	 */
	public static void openCamera(BaseActivity ctx, String fileName) {
		Intent intent = new Intent();
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName)));
		ctx.startActivityForResult(intent, 1);
	}

	/**
	 * @param ctx
	 * @param uri
	 *            裁剪图片的方法
	 */
	public static void cutPicture(BaseActivity ctx, Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		/** 设置裁剪 */
		intent.putExtra("crop", "true");
		/** 设置宽高的比例 */
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		/** 设置裁剪图片的宽高 */
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		/** 设置返回数据 */
		intent.putExtra("return-data", true);
		ctx.startActivityForResult(intent, 2);
	}

	/**
	 * @param context
	 * @param num
	 *            拨打电话（不直接拨出去）
	 */
	public static void callPhoneUnNow(Context context, String num) {
		Uri uri = Uri.parse("tel:" + num);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		context.startActivity(intent);
	}

	/**
	 * @param context
	 * @param num
	 *            拨打电话（直接拨出去）
	 */
	public static void callPhoneNow(Context context, String num) {
		Uri uri = Uri.parse("tel:" + num);
		Intent intent = new Intent(Intent.ACTION_CALL, uri);
		context.startActivity(intent);
	}

	/**
	 * @param context
	 * @param address
	 * @param body
	 *            发送短信
	 */
	public static void sentSms(Context context, String address, String body) {

		PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, new Intent("SENT_SMS_ACTION"), 0);
		PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0, new Intent("DELIVERED_SMS_ACTION"), 0);

		SmsManager smsManager = SmsManager.getDefault();
		if (body.length() > 140) {
			List<String> contents = smsManager.divideMessage(body);
			for (String sms : contents) {
				smsManager.sendTextMessage(address, null, sms, sentPI, deliverPI);
			}
		} else {
			smsManager.sendTextMessage(address, null, body, sentPI, deliverPI);
		}
	}

	/**
	 * @param oldString
	 * @return 从字符串中提取数字
	 */
	public static String getNumFromString(String oldString) {
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < oldString.length(); i++) {
			if (oldString.charAt(i) >= 48 && oldString.charAt(i) <= 57) {
				newString.append(oldString.charAt(i));
			}
		}
		return newString.toString();
	}

	/**
	 * @param context
	 * @param fileName
	 * @return
	 * @throws Exception
	 *             将Json格式的文件转成Json对象
	 */
	public static JSONObject file2json(Context context, String fileName) throws Exception {
		JSONObject jsonObject = new JSONObject(inputStream2String(file2InputStream(context, fileName)));
		return jsonObject;
	}

	/**
	 * @param in
	 * @return
	 * @throws Exception
	 *             将字节流转换为字符串
	 */
	public static String inputStream2String(InputStream in) throws Exception {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[1024];
		int n;
		while ((n = in.read(b)) != -1) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	/**
	 * @param context
	 * @param fileName
	 * @return
	 * @throws Exception
	 *             将文件转成字节流
	 */
	public static InputStream file2InputStream(Context context, String fileName) throws Exception {
		InputStream inputStream;
		inputStream = context.getResources().getAssets().open(fileName);
		return inputStream;
	}

	/**
	 * @param string
	 * @return 判断字符串是否全由数字组成
	 */
	public static boolean isNumber(String string) {
		Pattern mPattern = Pattern.compile("[0-9]*");
		Matcher mMatcher = mPattern.matcher(string);
		if (mMatcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * @param ctx
	 * @param str
	 *            Toast提醒
	 */
	@SuppressLint("ShowToast")
	public static void showToast(Context ctx, String str) {
		Toast mToast = Toast.makeText(ctx, str, Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.BOTTOM, 0, 80);
		mToast.show();
	}

	/**
	 * @param ctx
	 * @param resID
	 *            Toast提醒
	 */
	@SuppressLint("ShowToast")
	public static void showToast(Context ctx, int resID) {
		Toast mToast = Toast.makeText(ctx, resID, Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.BOTTOM, 0, 80);
		mToast.show();
	}

	/**
	 * @param ctx
	 * @return 获取状态栏的高度
	 */
	public static int getStatusBarHeight(Activity ctx) {
		Resources res = ctx.getResources();
		int result = 0;
		int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = res.getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
