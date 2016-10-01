package com.example.lovestudy.utils.asynchload;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.lovestudy.base.BaseImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

public class AsynchLoadThreadPoolUtil {

	private ExecutorService executorService = Executors.newFixedThreadPool(1);// 同时最多启动1个线程
	private Bitmap bitmap;

	public static AsynchLoadThreadPoolUtil getInstance() {
		return new AsynchLoadThreadPoolUtil();
	}

	public void DownLoadImage(final BaseImageView imageView, final String url) {
		final Handler handler = new Handler();
		executorService.submit(new Runnable() {

			@Override
			public void run() {

				try {
					URL newurl = new URL(url);
					HttpURLConnection conn = (HttpURLConnection) newurl.openConnection();
					conn.setDoInput(true);
					conn.connect();
					InputStream inputStream = conn.getInputStream();
					bitmap = BitmapFactory.decodeStream(inputStream);
					handler.post(new Runnable() {
						@Override
						public void run() {
							imageView.setImageBitmap(bitmap);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
