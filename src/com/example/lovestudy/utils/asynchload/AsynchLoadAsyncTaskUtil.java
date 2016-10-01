package com.example.lovestudy.utils.asynchload;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.lovestudy.base.BaseImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class AsynchLoadAsyncTaskUtil extends AsyncTask<String, Integer, Bitmap> {

	private BaseImageView imageView;

	public AsynchLoadAsyncTaskUtil(BaseImageView imageView) {
		this.imageView = imageView;
	}

	/**
	 * 后台执行任务
	 */
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(params[0]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);// 以后就可以使用conn.getInputStream().read();
			InputStream inputStream = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 更新UI
	 */
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		imageView.setImageBitmap(result);
	}

	/**
	 * 进度更新
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

}
