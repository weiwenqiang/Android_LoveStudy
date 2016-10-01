package com.example.lovestudy.utils;

import android.annotation.SuppressLint;
import java.security.MessageDigest;

public class MD5Util {
	
	@SuppressLint("DefaultLocale")
	public static String getMD5Str(String paramString) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(paramString.getBytes("UTF-8"));
		} catch (Exception e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.substring(8, 24).toString().toUpperCase();
	}
}
