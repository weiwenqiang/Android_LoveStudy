package com.example.lovestudy.logic.function;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Bitmap;

public class QrCodeLogic {

	public static Bitmap createQRCode(String paramString, int paramInt) {
		try {
			if (paramString == null || "".equals(paramString) || paramString.length() < 1) {
				return null;
			}
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(paramString, BarcodeFormat.QR_CODE, paramInt, paramInt, hints);
			int[] pixels = new int[paramInt * paramInt];
			for (int y = 0; y < paramInt; y++) {
				for (int x = 0; x < paramInt; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * paramInt + x] = 0xff000000;
					} else {
						pixels[y * paramInt + x] = 0xffffffff;
					}
				}
			}
			Bitmap bitmap = Bitmap.createBitmap(paramInt, paramInt, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, paramInt, 0, 0, paramInt, paramInt);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
