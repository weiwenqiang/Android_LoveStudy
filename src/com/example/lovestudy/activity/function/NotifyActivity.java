package com.example.lovestudy.activity.function;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.utils.NotifyUtils;

public class NotifyActivity extends BaseActivity {

	private Button button_system_notify_message;
	private Button button_custom_notify_message;
	private Button button_largeView_notify_message;
	private Button button_largeImg_notify_message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_notify);
		setHeadTitle(R.string.notify);
		initView();
	}
	
	private void initView() {
		button_system_notify_message = (Button) findViewById(R.id.button_system_notify_message);
		button_custom_notify_message = (Button) findViewById(R.id.button_custom_notify_message);
		button_largeView_notify_message = (Button) findViewById(R.id.button_largeView_notify_message);
		button_largeImg_notify_message = (Button) findViewById(R.id.button_largeImg_notify_message);

		button_system_notify_message.setOnClickListener(onClickListener);
		button_custom_notify_message.setOnClickListener(onClickListener);
		button_largeView_notify_message.setOnClickListener(onClickListener);
		button_largeImg_notify_message.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.button_system_notify_message:
				NotifyUtils.DefaultNotify(ctx, "标题", "内容", NotifyActivity.class);
				break;
			case R.id.button_custom_notify_message:
				NotifyUtils.CustomNotify(ctx, "标题:猜猜我是什么?", "内容:你就不是内容。", R.drawable.jiaju, NotifyActivity.class);
				break;
			case R.id.button_largeView_notify_message:
				NotifyUtils.LargeViewNotify(ctx, "标题", "内容", NotifyActivity.class);
				break;
			case R.id.button_largeImg_notify_message:
				NotifyUtils.LargeImgNotify(ctx, BitmapFactory.decodeResource(getResources(), R.drawable.jiaju), NotifyActivity.class);
				break;
			}
		}
	};

}
