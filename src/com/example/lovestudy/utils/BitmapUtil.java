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
	 * @return ��ȡԲ��ͼƬ�ķ���
	 */
	public static Bitmap getCircular(Bitmap bitmap) {
		Bitmap roundShapePhoto = null;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, left_2, top_2, right_2, bottom_2;
		if (width <= height) {
			// �������ҡ�����ǰ��
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			// �������ҡ�������
			height = width;
			left_2 = 0;
			top_2 = 0;
			right_2 = width;
			bottom_2 = width;
		} else {
			float clip = (width - height) / 2;
			// �������ҡ�����ǰ��
			roundPx = height / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			// �������ҡ�������
			left_2 = 0;
			top_2 = 0;
			right_2 = height;
			bottom_2 = height;
		}

		/** ����bitmap */
		roundShapePhoto = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		/** ���� */
		Canvas canvas = new Canvas(roundShapePhoto);
		/** ������ɫ����ɫ�� */
		canvas.drawARGB(0, 0, 0, 0);
		/** ���ᣨ���ʣ� */
		final Paint paint = new Paint();
		final int color = 0xff424242;
		/** ������ɫ */
		paint.setColor(color);
		/** ���þ�� */
		paint.setAntiAlias(true);
		/** ���Σ���״��---------(src:��ʾ�ϲ�ͼƬ)-------(dst:��ʾ�²�ͼƬ) */
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) left_2, (int) top_2, (int) right_2, (int) bottom_2);
		final RectF rect3 = new RectF(dst);
		/** �ڻ����ϻ���״ */
		canvas.drawRoundRect(rect3, roundPx, roundPx, paint);
		/** ����ת��ģʽ���ز����٣� */
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		/** �ڻ����ϻ�ͼ�� */
		canvas.drawBitmap(bitmap, src, dst, paint);
		return roundShapePhoto;
	}

	/**
	 * @param bitmap
	 *            ��ͼƬ��
	 * @param angle
	 *            ���Ƕȣ�
	 * @return ����ͨͼƬ����Բ��ͼƬ
	 */
	public static Bitmap getRoundCorner(Bitmap bitmap, int angle) {
		Bitmap picture = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		/** ���� */
		Canvas canvas = new Canvas(picture);
		canvas.drawARGB(0, 0, 0, 0);
		/** ���ᣨ���ʣ� */
		final Paint paint = new Paint();
		final int color = 0xff424242;
		paint.setColor(color);
		paint.setAntiAlias(true);
		/** ���Σ���״�� */
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		/** ����Բ�� */
		final float roundPx = angle;
		/** �ڻ����ϻ���״ */
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		/** ����ת��ģʽ */
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		/** �ڻ����ϻ�ͼƬ */
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return picture;
	}

	/**
	 * @param intent
	 * @return ��ȡ�ü����ͼƬ
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
	 * @return ��ȡ��������е�һ��ͼƬ
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
