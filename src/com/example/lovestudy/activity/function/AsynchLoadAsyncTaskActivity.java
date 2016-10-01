package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.utils.asynchload.AsynchLoadAsyncTaskUtil;

public class AsynchLoadAsyncTaskActivity extends BaseActivity {

	private Button open;
	private BaseImageView imageView1;
	private BaseImageView imageView2;
	private BaseImageView imageView3;
	private BaseImageView imageView4;
	private BaseImageView imageView5;
	private AsynchLoadAsyncTaskUtil syncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_asynchload_asynchtask);
		setHeadTitle(R.string.asynchload_img_AsyncTask);
		initView();
	}
	
	private void initView() {
		open = (Button) findViewById(R.id.open);
		imageView1 = (BaseImageView) findViewById(R.id.imageview1);
		imageView2 = (BaseImageView) findViewById(R.id.imageview2);
		imageView3 = (BaseImageView) findViewById(R.id.imageview3);
		imageView4 = (BaseImageView) findViewById(R.id.imageview4);
		imageView5 = (BaseImageView) findViewById(R.id.imageview5);
		open.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			syncTask = new AsynchLoadAsyncTaskUtil(imageView1);
			syncTask.execute(Urls.AsynchLoadAsynchTaskUrl1);
			syncTask = new AsynchLoadAsyncTaskUtil(imageView2);
			syncTask.execute(Urls.AsynchLoadAsynchTaskUrl2);
			syncTask = new AsynchLoadAsyncTaskUtil(imageView3);
			syncTask.execute(Urls.AsynchLoadAsynchTaskUrl3);
			syncTask = new AsynchLoadAsyncTaskUtil(imageView4);
			syncTask.execute(Urls.AsynchLoadAsynchTaskUrl4);
			syncTask = new AsynchLoadAsyncTaskUtil(imageView5);
			syncTask.execute(Urls.AsynchLoadAsynchTaskUrl5);
		}
	};

}
