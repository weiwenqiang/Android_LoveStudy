package com.example.lovestudy.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class AsynchLoadImgActivity extends BaseActivity {

	private Button btnHandler;
	private Button btnAsyncTask;
	private Button btnExecutorService;

	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_asynchloadimg);
		setHeadTitle(R.string.asynchload_img);
		initView();
	}
	
	private void initView() {
		btnHandler = (Button) findViewById(R.id.asynchload_handle);
		btnAsyncTask = (Button) findViewById(R.id.asynchload_asynchtask);
		btnExecutorService = (Button) findViewById(R.id.asynchload_executorservice);

		btnHandler.setOnClickListener(onClickListener);
		btnAsyncTask.setOnClickListener(onClickListener);
		btnExecutorService.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.asynchload_handle:
				intent.setClass(AsynchLoadImgActivity.this, AsynchLoadHandlerActivity.class);
				startActivity(intent);
				break;
			case R.id.asynchload_asynchtask:
				intent.setClass(AsynchLoadImgActivity.this, AsynchLoadAsyncTaskActivity.class);
				startActivity(intent);
				break;
			case R.id.asynchload_executorservice:
				intent.setClass(AsynchLoadImgActivity.this, AsynchLoadExecutorServiceActivity.class);
				startActivity(intent);
				break;
			}
		}
	};

}
