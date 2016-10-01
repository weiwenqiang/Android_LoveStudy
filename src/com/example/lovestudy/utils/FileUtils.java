package com.example.lovestudy.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

/**
 * 文件工具�?
 */

public class FileUtils {

	/**
	 * 1,�?查是否存在SD�?
	 * 
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 2,创建目录
	 * 
	 */
	public static File createFileDir(Context context, String dirName) {
		String filePath;
		/** 如SD卡已存在，则存储；反之存在data目录�? */
		if (hasSdcard()) {
			/** SD卡路�? */
			filePath = Environment.getExternalStorageDirectory() + File.separator + dirName;
		} else {
			/** data目录路径 */
			filePath = context.getCacheDir().getPath() + File.separator + dirName;
		}
		File destDir = new File(filePath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return destDir;
	}

	/**
	 * 3,判断某文件是否存�?
	 */
	public static boolean isFileExists(File dir, String fileName) {
		return new File(dir, fileName).exists();
	}

	/**
	 * 4,获取某文件的大小（�?�归�?
	 */
	public static long getFileSize(File file) {
		long size = 0;
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] subFiles = file.listFiles();
				if (subFiles != null) {
					int num = subFiles.length;
					for (int i = 0; i < num; i++) {
						size += getFileSize(subFiles[i]);
					}
				}
			} else {
				size += file.length();
			}
		}
		return size;
	}

	/**
	 * 5,保存文件到指定目�?
	 */
	public static void savaBitmap(File dir, String fileName, Bitmap bitmap) {
		if (bitmap == null) {
			return;
		}
		File file = new File(dir, fileName);
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 6,删除文件(递归)
	 */
	public static void delFile(File file, boolean delThisPath) {
		if (!file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (subFiles != null) {
				int num = subFiles.length;
				for (int i = 0; i < num; i++) {
					delFile(subFiles[i], true);
				}
			}
		}
		if (delThisPath) {
			file.delete();
		}
	}
}
