package com.example.lovestudy.logic.function;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.lovestudy.callback.AsyncImageLoaderCallBack;
import com.example.lovestudy.utils.FileUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.v4.util.LruCache;

/**
 * 图片下载类
 */

public class ImageDownLoader {

	/** 缓存类 */
	private LruCache<String, Bitmap> lruCache;
	/** 线程池 */
	private ExecutorService threadPool;
	/** 缓存文件目录 （如无SD卡，则data目录下） */
	private File cacheFileDir;
	/** 缓存文件夹 */
	private static final String DIR_CACHE = "爱学习/缓存图片";
	/** 缓存文件夹最大容量限制（10M） */
	private static final long DIR_CACHE_LIMIT = 10 * 1024 * 1024;

	public ImageDownLoader(Context context) {
		/** 获取系统分配给每个应用程序的最大内存 */
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		/** 给LruCache分配最大内存的1/8 */
		lruCache = new LruCache<String, Bitmap>(maxMemory / 8) {
			/** 必须重写此方法，来测量Bitmap的大小 */
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight() / 1024;
			}
		};
		/** 创建线程数 */
		threadPool = Executors.newFixedThreadPool(10);
		/** 创建缓存图片的文件目录 */
		cacheFileDir = FileUtils.createFileDir(context, DIR_CACHE);
	}

	/**
	 * 从缓存中获取图片
	 */
	public Bitmap getBitmapCache(String url) {
		/** 缓存文件名称（ 替换URL中非字母和非数字的字符，防止系统误认为文件路径） */
		String urlKey = url.replaceAll("[^\\w]", "");
		
		/** 1,从内存缓存中获取 */
		if (getBitmapFromMemCache(url) != null) {
			return getBitmapFromMemCache(url);
		}

		/** 2,从文件缓存中获取 */
		else if (FileUtils.isFileExists(cacheFileDir, urlKey) && FileUtils.getFileSize(new File(cacheFileDir, urlKey)) > 0) {
			/** 从文件缓存中获取Bitmap */
			Bitmap bitmap = BitmapFactory.decodeFile(cacheFileDir.getPath() + File.separator + urlKey);
			/** 将Bitmap 加入内存缓存 */
			addLruCache(url, bitmap);
			return bitmap;
		}
		return null;
	}

	/**
	 * 从内存缓存中获取
	 */
	private Bitmap getBitmapFromMemCache(String key) {
		return lruCache.get(key);
	}

	/**
	 * 添加到內存缓存中
	 */
	private void addLruCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null && bitmap != null) {
			lruCache.put(key, bitmap);
		}
	}

	/**
	 * 下载图片
	 */
	private Bitmap downloadImage(String url) {
		try {
			Bitmap bitmap = null;
			HttpGet httpGet = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse;
			httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream inputStream = httpEntity.getContent();
				bitmap = BitmapFactory.decodeStream(inputStream);
				return bitmap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 异步下载图片
	 */
	public void loadImage(final String url, AsyncImageLoaderCallBack callBack) {
		final ImageHandler handler = new ImageHandler(callBack);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Bitmap bitmap = downloadImage(url);
				Message msg = handler.obtainMessage();
				msg.obj = bitmap;
				handler.sendMessage(msg);

				/** 1,添加到內存缓存中 */
				addLruCache(url, bitmap);

				/** 2,添加到文件缓存中(加入文件缓存前，需判断缓存目录大小是否超过限制，超过则清空缓存再加入) */
				long cacheFileSize = FileUtils.getFileSize(cacheFileDir);
				if (cacheFileSize > DIR_CACHE_LIMIT) {
					/** 先删除文件 */
					FileUtils.delFile(cacheFileDir, false);
				}
				/** 缓存文件名称（ 替换URL中非字母和非数字的字符，防止系统误认为文件路径） */
				String urlKey = url.replaceAll("[^\\w]", "");
				/** 添加到文件缓存中 */
				FileUtils.savaBitmap(cacheFileDir, urlKey, bitmap);
			}
		};
		/** 执行线程池 */
		threadPool.execute(runnable);
	}

	/**
	 * 取消正在下载的任务
	 */
	public synchronized void cancelTasks() {
		if (threadPool != null) {
			threadPool.shutdownNow();
			threadPool = null;
		}
	}
}
