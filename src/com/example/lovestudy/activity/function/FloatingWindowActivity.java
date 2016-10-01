package com.example.lovestudy.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.service.FloatingWindowService;

public class FloatingWindowActivity extends BaseActivity {

	private TextView txtHide;
	private TextView txtShow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_floating_window);
		setHeadTitle(R.string.floating_window);
		initView();
	}

	private void initView() {
		txtShow = ((TextView) findViewById(R.id.txtShow));
		txtHide = ((TextView) findViewById(R.id.txtHide));
		txtShow.setOnClickListener(onClickListener);
		txtHide.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txtShow:
				Intent intent = new Intent(ctx, FloatingWindowService.class);
				intent.putExtra(FloatingWindowService.OPERATION, FloatingWindowService.OPERATION_SHOW);
				startService(intent);
				break;
			case R.id.txtHide:
				Intent intent2 = new Intent(ctx, FloatingWindowService.class);
				intent2.putExtra(FloatingWindowService.OPERATION, FloatingWindowService.OPERATION_HIDE);
				startService(intent2);
				break;
			}
		}
	};
}
