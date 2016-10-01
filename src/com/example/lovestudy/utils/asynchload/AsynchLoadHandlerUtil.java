package com.example.lovestudy.utils.asynchload;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.lovestudy.base.BaseImageView;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class AsynchLoadHandlerUtil {
	
	Bitmap bitmap;
	
	public static AsynchLoadHandlerUtil getInstance(){
		return new AsynchLoadHandlerUtil();
	}
	
	@SuppressLint("HandlerLeak")
	public void DownLoadImage(final Handler handler,final int what,final BaseImageView imageView,final String urlStr){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					URL url = new URL(urlStr);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setDoInput(true);// 以后就可以使用conn.getInputStream().read();
					conn.connect();
					InputStream inputStream = conn.getInputStream();
					bitmap = BitmapFactory.decodeStream(inputStream);
					inputStream.close();
					Message msg = new Message();
					msg.what = what;
					msg.obj = bitmap;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
