package com.example.lovestudy.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

public class SDCardUtil {

	File path = Environment.getExternalStorageDirectory();
	StatFs stat = new StatFs(path.getPath());
	@SuppressWarnings("deprecation")
	long blockSize = stat.getBlockSize();
	@SuppressWarnings("deprecation")
	long totalBlocks = stat.getBlockCount();
	@SuppressWarnings("deprecation")
	long availableBlocks = stat.getAvailableBlocks();
	long totalSize = totalBlocks * blockSize;
	long availSize = availableBlocks * blockSize;
	Formatter formatter = new Formatter();

	public static SDCardUtil sdCardUtil;

	public static SDCardUtil getInstance() {
		if (sdCardUtil == null) {
			sdCardUtil = new SDCardUtil();
		}
		return sdCardUtil;
	}

	/**
	 * @param ctx
	 * @return 获取SD卡的总空间
	 */
	@SuppressWarnings("static-access")
	public String getTotalSize(Context ctx) {
		return formatter.formatFileSize(ctx, totalSize);
	}

	/**
	 * @param ctx
	 * @return 获取SD卡的当前可用空间
	 */
	@SuppressWarnings("static-access")
	public String getAvailSize(Context ctx) {
		return formatter.formatFileSize(ctx, availSize);
	}
}
