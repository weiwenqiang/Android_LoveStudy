package com.example.lovestudy.utils;

import java.io.FileNotFoundException;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Bundle;

public class BitmapUtil {
	/**
	 * @param bitmap
	 * @return 获取圆形图片的方法
	 */
	public static Bitmap getCircular(Bitmap bitmap) {
		Bitmap roundShapePhoto = null;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, left_2, top_2, right_2, bottom_2;
		if (width <= height) {
			// 上下左右——（前）
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			// 上下左右——（后）
			height = width;
			left_2 = 0;
			top_2 = 0;
			right_2 = width;
			bottom_2 = width;
		} else {
			float clip = (width - height) / 2;
			// 上下左右——（前）
			roundPx = height / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			// 上下左右——（后）
			left_2 = 0;
			top_2 = 0;
			right_2 = height;
			bottom_2 = height;
		}

		/** 创建bitmap */
		roundShapePhoto = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		/** 画布 */
		Canvas canvas = new Canvas(roundShapePhoto);
		/** 画布颜色（无色） */
		canvas.drawARGB(0, 0, 0, 0);
		/** 油漆（画笔） */
		final Paint paint = new Paint();
		final int color = 0xff424242;
		/** 油漆颜色 */
		paint.setColor(color);
		/** 设置锯齿 */
		paint.setAntiAlias(true);
		/** 矩形（形状）---------(src:表示上层图片)-------(dst:表示下层图片) */
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) left_2, (int) top_2, (int) right_2, (int) bottom_2);
		final RectF rect3 = new RectF(dst);
		/** 在画布上画形状 */
		canvas.drawRoundRect(rect3, roundPx, roundPx, paint);
		/** 设置转移模式（必不可少） */
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		/** 在画布上画图形 */
		canvas.drawBitmap(bitmap, src, dst, paint);
		return roundShapePhoto;
	}

	/**
	 * @param bitmap
	 *            （图片）
	 * @param angle
	 *            （角度）
	 * @return 将普通图片做成圆角图片
	 */
	public static Bitmap getRoundCorner(Bitmap bitmap, int angle) {
		Bitmap picture = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		/** 画布 */
		Canvas canvas = new Canvas(picture);
		canvas.drawARGB(0, 0, 0, 0);
		/** 油漆（画笔） */
		final Paint paint = new Paint();
		final int color = 0xff424242;
		paint.setColor(color);
		paint.setAntiAlias(true);
		/** 矩形（形状） */
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		/** 设置圆角 */
		final float roundPx = angle;
		/** 在画布上画形状 */
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		/** 设置转移模式 */
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		/** 在画布上画图片 */
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return picture;
	}

	/**
	 * @param intent
	 * @return 获取裁剪后的图片
	 */
	public static Bitmap getTailorBitmap(Intent data) {
		Bitmap bitmap = null;
		Bundle extras = data.getExtras();
		if (extras != null) {
			bitmap = extras.getParcelable("data");
		}
		return bitmap;
	}

	/**
	 * @param data
	 * @return 获取本地相册中的一张图片
	 */
	public static Bitmap getBitmapFromPhotoAlbum(Context ctx, Intent data) {
		Bitmap bitmap = null;
		Uri uri = data.getData();
		ContentResolver contentResolver = ctx.getContentResolver();
		try {
			bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
