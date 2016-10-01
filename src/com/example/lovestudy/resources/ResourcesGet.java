package com.example.lovestudy.resources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class ResourcesGet {

	/**
	 * @param ctx
	 * @param Id
	 * @return 获取字符串
	 */
	public static String getString(Context ctx, int Id) {
		return ctx.getResources().getString(Id);
	}

	/**
	 * @param ctx
	 * @param Id
	 * @return 获取Bitmap
	 */
	public static Bitmap getBitmap(Context ctx, int Id) {
		BitmapDrawable bitmapDrawable = (BitmapDrawable) ctx.getResources().getDrawable(Id);
		return bitmapDrawable.getBitmap();
	}
}
