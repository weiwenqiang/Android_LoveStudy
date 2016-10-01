package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.utils.asynchload.AsynchLoadThreadPoolUtil;

public class AsynchLoadExecutorServiceActivity extends BaseActivity {
	
	private Button button;
	private BaseImageView imageView1;
	private BaseImageView imageView2;
	private BaseImageView imageView3;
	private BaseImageView imageView4;
	private BaseImageView imageView5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_asynchload_executorservice);
		setHeadTitle(R.string.asynchload_img_ExecutorService);
		initView();
	}
	
	private void initView() {
		button = (Button) findViewById(R.id.open);
		imageView1 = (BaseImageView) findViewById(R.id.imageview1);
		imageView2 = (BaseImageView) findViewById(R.id.imageview2);
		imageView3 = (BaseImageView) findViewById(R.id.imageview3);
		imageView4 = (BaseImageView) findViewById(R.id.imageview4);
		imageView5 = (BaseImageView) findViewById(R.id.imageview5);
		button.setOnClickListener(onClickListener);
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			AsynchLoadThreadPoolUtil.getInstance().DownLoadImage(imageView1, Urls.AsynchLoadExecutorServiceUrl1);
			AsynchLoadThreadPoolUtil.getInstance().DownLoadImage(imageView2, Urls.AsynchLoadExecutorServiceUrl2);
			AsynchLoadThreadPoolUtil.getInstance().DownLoadImage(imageView3, Urls.AsynchLoadExecutorServiceUrl3);
			AsynchLoadThreadPoolUtil.getInstance().DownLoadImage(imageView4, Urls.AsynchLoadExecutorServiceUrl4);
			AsynchLoadThreadPoolUtil.getInstance().DownLoadImage(imageView5, Urls.AsynchLoadExecutorServiceUrl5);
		}
	};
	
}
