package com.example.lovestudy.activity.function;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.utils.asynchload.AsynchLoadHandlerUtil;

public class AsynchLoadHandlerActivity extends BaseActivity {
	
	private Button button;
	private BaseImageView imageView1;
	private BaseImageView imageView2;
	private BaseImageView imageView3;
	private BaseImageView imageView4;
	private BaseImageView imageView5;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case R.id.imageview1:
				imageView1.setImageBitmap((Bitmap) msg.obj);
				break;
			case R.id.imageview2:
				imageView2.setImageBitmap((Bitmap) msg.obj);
				break;
			case R.id.imageview3:
				imageView3.setImageBitmap((Bitmap) msg.obj);
				break;
			case R.id.imageview4:
				imageView4.setImageBitmap((Bitmap) msg.obj);
				break;
			case R.id.imageview5:
				imageView5.setImageBitmap((Bitmap) msg.obj);
				break;
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_asynchload_handler);
		setHeadTitle(R.string.asynchload_img_Handler);
		initView();
		initListener();
	}
	
	private void initView() {
		button = (Button) findViewById(R.id.open);
		imageView1 = (BaseImageView) findViewById(R.id.imageview1);
		imageView2 = (BaseImageView) findViewById(R.id.imageview2);
		imageView3 = (BaseImageView) findViewById(R.id.imageview3);
		imageView4 = (BaseImageView) findViewById(R.id.imageview4);
		imageView5 = (BaseImageView) findViewById(R.id.imageview5);
	}

	private void initListener() {
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AsynchLoadHandlerUtil.getInstance().DownLoadImage(handler, R.id.imageview1, imageView1, Urls.AsynchLoadHandlerUrl1);
				AsynchLoadHandlerUtil.getInstance().DownLoadImage(handler, R.id.imageview2, imageView2, Urls.AsynchLoadHandlerUrl2);
				AsynchLoadHandlerUtil.getInstance().DownLoadImage(handler, R.id.imageview3, imageView3, Urls.AsynchLoadHandlerUrl3);
				AsynchLoadHandlerUtil.getInstance().DownLoadImage(handler, R.id.imageview4, imageView4, Urls.AsynchLoadHandlerUrl4);
				AsynchLoadHandlerUtil.getInstance().DownLoadImage(handler, R.id.imageview5, imageView5, Urls.AsynchLoadHandlerUrl5);
			}
		});
	}
	
}
