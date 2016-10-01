package com.example.lovestudy.logic.function;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.example.lovestudy.base.BaseMethod;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class DataStoreFileLogic {

	static DataStoreFileLogic dataStoreFileLogic;
	static Context context;

	public static DataStoreFileLogic getInstent(Context ctx) {
		context = ctx;
		if (dataStoreFileLogic == null) {
			dataStoreFileLogic = new DataStoreFileLogic();
		}
		return dataStoreFileLogic;
	}

	/**
	 * @param fileName
	 * @param data
	 *            存储文字到指定文件
	 */
	public void setString(String filePath, String fileName, String data) {
		try {
			/** 创建文件目录 */
			String fileMainName = Environment.getExternalStorageDirectory() + filePath;
			File dirFile = new File(fileMainName);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			/** 创建文件 */
			File fileNew = new File(dirFile, fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(fileNew);
			fileOutputStream.write(data.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			BaseMethod.showToast(context, "文字存储成功！");
		} catch (Exception e) {
			e.printStackTrace();
			BaseMethod.showToast(context, "文字存储成功！");
		}
	}

	/**
	 * @param fileName
	 * @return 从指定文件中获取文本
	 */
	@SuppressWarnings("unused")
	public String getString(String filePath, String fileName) {
		String data = "";
		try {
			String fileMainName = Environment.getExternalStorageDirectory() + filePath + "/" + fileName;
			File file = new File(fileMainName);
			FileInputStream fileInputStream = new FileInputStream(file);
			StringBuffer stringBuffer = new StringBuffer();
			byte[] bs = new byte[1024];
			int length;
			while ((length = fileInputStream.read(bs)) != -1) {
				stringBuffer.append(new String(bs));
			}
			data = stringBuffer.toString();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * @param bm
	 * @param fileName
	 *            存储图片到指定文件
	 */
	public void setImage(Bitmap bm, String filePath, String fileName) {
		try {
			/** 创建文件目录 */
			String fileMainName = Environment.getExternalStorageDirectory() + filePath;
			File dirFile = new File(fileMainName);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			/** 创建文件 */
			File imageFile = new File(dirFile, fileName);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(imageFile));
			bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
			bos.flush();
			bos.close();
			BaseMethod.showToast(context, "图片存储成功！");
		} catch (Exception e) {
			BaseMethod.showToast(context, "图片存储失败！");
			e.printStackTrace();
		}
	}

	/**
	 * @return 从指定文件中获取图片
	 */
	public Bitmap getImage(String fileName) {
		Bitmap bitmap;
		bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + fileName);
		return bitmap;
	}

	public Bitmap getBitmap(int id) {
		return BitmapFactory.decodeStream(context.getResources().openRawResource(id));
	}
}
