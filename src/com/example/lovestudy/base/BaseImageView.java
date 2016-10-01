package com.example.lovestudy.base;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.utils.BitmapUtil;
import com.example.lovestudy.utils.DownLoadUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("HandlerLeak")
public class BaseImageView extends ImageView {

	public static final int PUBLIC_LOADING_WHAT = 101;
	public Bitmap bitmap;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case PUBLIC_LOADING_WHAT:
				setImageBitmap((Bitmap) msg.obj);
				break;
			}
		}
	};

	public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BaseImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseImageView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setImageDrawable(getResources().getDrawable(R.drawable.empty_photo));
	}

	public void setImageUrl(final String url) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					bitmap = DownLoadUtil.downLoadBitmapClient(url);
					Message message = new Message();
					message.what = PUBLIC_LOADING_WHAT;
					message.obj = bitmap;
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void setCircularImageUrl(final String url) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					bitmap = BitmapUtil.getCircular(DownLoadUtil.downLoadBitmapClient(url));
					Message message = new Message();
					message.what = PUBLIC_LOADING_WHAT;
					message.obj = bitmap;
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void isSetDefaultImage(boolean isShow) {
		if (isShow) {
			setImageDrawable(getResources().getDrawable(R.drawable.empty_photo));
		} else {
			setImageDrawable(null);
		}
	}

	public void isSetDefaultImage(int Id) {
		if (Id == 0) {
			setImageDrawable(getResources().getDrawable(R.drawable.empty_photo));
		} else {
			setImageDrawable(getResources().getDrawable(Id));
		}
	}
}
