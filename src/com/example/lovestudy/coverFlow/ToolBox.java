package com.example.lovestudy.coverFlow;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.TypedValue;
import android.view.View;

public abstract class ToolBox {
	
	@SuppressLint("FloatMath")
	public static float getLineLength(PointF a, PointF b) {
		float vx = b.x - a.x;
		float vy = b.y - a.y;
		return FloatMath.sqrt(vx * vx + vy * vy);
	}

	@SuppressLint("FloatMath")
	public static float getLineLength(float ax, float ay, float bx, float by) {
		float vx = bx - ax;
		float vy = by - ay;
		return FloatMath.sqrt(vx * vx + vy * vy);
	}

	@SuppressLint("FloatMath")
	public static float getVectorLength(float vx, float vy) {
		return FloatMath.sqrt(vx * vx + vy * vy);
	}

	@SuppressLint("FloatMath")
	public static float getVectorLength(PointF v) {
		return FloatMath.sqrt(v.x * v.x + v.y * v.y);
	}

	public static PointF getLinesIntersection(PointF a1, PointF a2, PointF b1, PointF b2) {
		return getLinesIntersection(a1.x, a1.y, a2.x, a2.y, b1.x, b1.y, b2.x, b2.y);
	}

	public static PointF getLinesIntersection(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		float a1 = y2 - y1;
		float b1 = x1 - x2;
		float c1 = x2 * y1 - x1 * y2;

		float a2 = y4 - y3;
		float b2 = x3 - x4;
		float c2 = x4 * y3 - x3 * y4;

		float d = a1 * b2 - a2 * b1;
		if (d == 0) {
			throw new InvalidParameterException("Intersection cant be found, lines are paralel.");
		}

		float x = (b1 * c2 - b2 * c1) / d;
		float y = (a2 * c1 - a1 * c2) / d;
		return new PointF(x, y);
	}

	public static int roundToInt(float num) {
		if ((num - Math.floor(num)) > 0.5f) {
			return (int) Math.ceil(num);
		} else {
			return (int) Math.floor(num);
		}
	}

	public static Bitmap getViewBitmap(View v) {
		v.clearFocus();
		v.setPressed(false);

		boolean willNotCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);

		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);

		if (color != 0) {
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();
		Bitmap cacheBitmap = v.getDrawingCache();
		if (cacheBitmap == null) {
			return null;
		}

		Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willNotCache);
		v.setDrawingCacheBackgroundColor(color);

		return bitmap;
	}

	public static Bitmap getViewBitmapNoCache(View v, int w, int h) {
		Bitmap b = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		v.layout(0, 0, w, h);
		v.draw(c);
		return b;
	}

	public static Bitmap doInvert(Bitmap src) {
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		int A, R, G, B;
		int pixelColor;
		int height = src.getHeight();
		int width = src.getWidth();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixelColor = src.getPixel(x, y);
				A = Color.alpha(pixelColor);
				R = 255 - Color.red(pixelColor);
				G = 255 - Color.green(pixelColor);
				B = 255 - Color.blue(pixelColor);
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}
		return bmOut;
	}

	public static float dpToPixels(int dp, Context c) {
		final Resources r = c.getResources();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
	}

	@SuppressLint("FloatMath")
	public static PointF getCircleIntersection(PointF center, float radius, PointF touchPoint) {
		final float r = radius;

		final float x2 = touchPoint.x - center.x;
		final float y2 = touchPoint.y - center.y;

		final float dx = x2;
		final float dy = y2;

		final float dr = FloatMath.sqrt(dx * dx + dy * dy);

		final PointF res1 = new PointF();
		final PointF res2 = new PointF();

		res1.x = (sgn(dy) * dx * r * dr) / (dr * dr);
		res2.x = (-1 * sgn(dy) * dx * r * dr) / (dr * dr);

		res1.y = (Math.abs(dy) * r * dr) / (dr * dr);
		res2.y = (-1 * Math.abs(dy) * r * dr) / (dr * dr);

		res1.x = res1.x + center.x;
		res1.y = res1.y + center.y;
		res2.x = res2.x + center.x;
		res2.y = res2.y + center.y;

		if (getLineLength(res1, touchPoint) < getLineLength(res2, touchPoint)) {
			return res1;
		} else {
			return res2;
		}
	}

	public static PointF getCircleIntersection(PointF center, float radius, float x, float y) {
		return getCircleIntersection(center, radius, new PointF(x, y));
	}

	public static boolean isInsideCircle(PointF center, float radius, float x, float y) {
		final float dx = x - center.x;
		final float dy = y - center.y;

		if (dx * dx + dy * dy < radius * radius) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isOutsideCircle(PointF center, float radius, float x, float y) {
		final float dx = x - center.x;
		final float dy = y - center.y;

		if (dx * dx + dy * dy > radius * radius) {
			return true;
		} else {
			return false;
		}
	}

	private static float sgn(float x) {
		if (x < 0)
			return -1;
		else
			return 1;
	}

	public static PointF getNormalizedVector(PointF v) {
		float l = getVectorLength(v);
		final PointF r = new PointF();
		r.x = v.x / l;
		r.y = v.y / l;
		return r;
	}

	public static float dotProduct(PointF a, PointF b) {
		return a.x * b.x + a.y * b.y;
	}

	public static float getVectorAngle(float x, float y) {
		final float l = getVectorLength(x, y);
		final float cos = x / l;
		final float sin = y / l;
		final float as = (float) Math.asin(sin);

		if (cos > 0 && sin >= 0) { // quadrant I
			return as;
		} else if (sin > 0 && cos <= 0) { // quadrant II
			return (float) (Math.PI - as);
		} else if (sin <= 0 && cos < 0) { // quadrant III
			return (float) (Math.PI - as);
		} else if (sin < 0 && cos >= 0) {// quadrant IV
			return (float) (2 * Math.PI + as);
		}
		return as;
	}

	public static void rgbToHsl(int rgb, float[] hsl) {
		float r = ((0x00ff0000 & rgb) >> 16) / 255.f;
		float g = ((0x0000ff00 & rgb) >> 8) / 255.f;
		float b = ((0x000000ff & rgb)) / 255.f;
		float max = Math.max(Math.max(r, g), b);
		float min = Math.min(Math.min(r, g), b);
		float c = max - min;

		float h_ = 0.f;
		if (c == 0) {
			h_ = 0;
		} else if (max == r) {
			h_ = (float) (g - b) / c;
			if (h_ < 0)
				h_ += 6.f;
		} else if (max == g) {
			h_ = (float) (b - r) / c + 2.f;
		} else if (max == b) {
			h_ = (float) (r - g) / c + 4.f;
		}
		float h = 60.f * h_;

		float l = (max + min) * 0.5f;

		float s;
		if (c == 0) {
			s = 0.f;
		} else {
			s = c / (1 - Math.abs(2.f * l - 1.f));
		}

		hsl[0] = h;
		hsl[1] = s;
		hsl[2] = l;
	}

	public static int hslToRgb(float[] hsl) {
		float h = hsl[0];
		float s = hsl[1];
		float l = hsl[2];

		float c = (1 - Math.abs(2.f * l - 1.f)) * s;
		float h_ = h / 60.f;
		float h_mod2 = h_;
		if (h_mod2 >= 4.f)
			h_mod2 -= 4.f;
		else if (h_mod2 >= 2.f)
			h_mod2 -= 2.f;

		float x = c * (1 - Math.abs(h_mod2 - 1));
		float r_, g_, b_;
		if (h_ < 1) {
			r_ = c;
			g_ = x;
			b_ = 0;
		} else if (h_ < 2) {
			r_ = x;
			g_ = c;
			b_ = 0;
		} else if (h_ < 3) {
			r_ = 0;
			g_ = c;
			b_ = x;
		} else if (h_ < 4) {
			r_ = 0;
			g_ = x;
			b_ = c;
		} else if (h_ < 5) {
			r_ = x;
			g_ = 0;
			b_ = c;
		} else {
			r_ = c;
			g_ = 0;
			b_ = x;
		}

		float m = l - (0.5f * c);
		int r = (int) ((r_ + m) * (255.f) + 0.5f);
		int g = (int) ((g_ + m) * (255.f) + 0.5f);
		int b = (int) ((b_ + m) * (255.f) + 0.5f);
		return r << 16 | g << 8 | b;
	}

	public static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format(Locale.US, "%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public static int getAppVersion(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	@SuppressWarnings("resource")
	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
		String r = s.hasNext() ? s.next() : "";
		s.close();
		return r;
	}

	public static <T> ArrayList<T> union(List<T> list1, List<T> list2) {
		Set<T> set = new HashSet<T>();

		set.addAll(list1);
		set.addAll(list2);

		return new ArrayList<T>(set);
	}

	public static <T> ArrayList<T> intersection(List<T> list1, List<T> list2) {
		ArrayList<T> list = new ArrayList<T>();

		for (T t : list1) {
			if (list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

	public static <T> T[] concatenateArray(T[] A, T[] B) {
		int aLen = A.length;
		int bLen = B.length;

		@SuppressWarnings("unchecked")
		T[] C = (T[]) Array.newInstance(A.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);

		return C;
	}

	public static class ViewCache<T extends View> {
		private final LinkedList<WeakReference<T>> mCachedItemViews = new LinkedList<WeakReference<T>>();

		public T getCachedView() {
			if (mCachedItemViews.size() != 0) {
				T v;
				do {
					v = mCachedItemViews.removeFirst().get();
				} while (v == null && mCachedItemViews.size() != 0);
				return v;
			}
			return null;
		}

		public void cacheView(T v) {
			WeakReference<T> ref = new WeakReference<T>(v);
			mCachedItemViews.addLast(ref);
		}
	}

	public static byte[] MACtobyteConverter(String MAC) {
		MAC = MAC.replaceAll(":", "");
		int len = MAC.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(MAC.charAt(i), 16) << 4) + Character.digit(MAC.charAt(i + 1), 16));
		}
		return data;
	}

	public static byte[] IMEItobyteConverter(String IMEI) {
		long imeiInLong;
		try {
			imeiInLong = Long.parseLong(IMEI);
		} catch (NumberFormatException e) {
			return null;
		}
		byte[] data = ByteBuffer.allocate(8).putLong(imeiInLong).array();
		return data;
	}

	public static String formatISODate(String isoDate) {
		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
		Date dtIn;
		try {
			dtIn = inFormat.parse(isoDate);
			SimpleDateFormat outFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
			return outFormat.format(dtIn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isoDate;
	}
}
