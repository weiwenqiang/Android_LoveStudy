package com.example.lovestudy.logic.function;

import com.example.lovestudy.callback.AsyncImageLoaderCallBack;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

/**
 * 自定义Handler机制（用于子线程与主线程的界面交互）
 */

public class ImageHandler extends Handler {

	private AsyncImageLoaderCallBack callBack;

	public ImageHandler(AsyncImageLoaderCallBack callBack) {
		this.callBack = callBack;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		callBack.onImageLoader((Bitmap) msg.obj);
	}

}
