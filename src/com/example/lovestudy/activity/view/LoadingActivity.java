package com.example.lovestudy.activity.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.LoadingDialogView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * º”‘ÿΩÁ√Ê
 */
public class LoadingActivity extends BaseActivity {

	private Button show;
	private LoadingDialogView dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_loading);
		setHeadTitle(R.string.view_Loading);
		initView();
		initEvent();
	}
	
	private void initView() {
		show = (Button) findViewById(R.id.show);
		dialog = new LoadingDialogView(this);
	}

	private void initEvent() {
		show.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.show:
				dialog.show();
				break;
			}
		}
	};

}
