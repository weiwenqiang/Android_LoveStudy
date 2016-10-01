package com.example.lovestudy.utils;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownLoadUtil {

	/**
	 * 下载图片
	 */
	public static Bitmap downLoadBitmapClient(String stringUrl) throws Exception {
		HttpGet httpGet = new HttpGet(stringUrl);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpGet);
		Bitmap bitmap = null;
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream inputStream = httpEntity.getContent();
			bitmap = BitmapFactory.decodeStream(inputStream);
			return bitmap;
		}
		return bitmap;
	}
	
	/**
	 * 下载--获取HttpEntity对象
	 */
	public static HttpEntity getHttpEntity(String stringUrl) throws Exception {
		HttpGet httpGet = new HttpGet(stringUrl);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = null;
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			httpEntity = httpResponse.getEntity();
		}
		return httpEntity;
	}
	
}
