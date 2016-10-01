package com.example.lovestudy.activity.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.custom.CustomProgressBarView;
import com.example.lovestudy.view.custom.CustomProgressBarView.Mode;

public class ProgressWidgetActivity extends BaseActivity {

	private CustomProgressBarView progressBarView1, progressBarView2;
	private TextView txtStart, txtStop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_progress_widget);
		setHeadTitle(R.string.Progress);
		initView();
	}

	private void initView() {
		progressBarView1 = (CustomProgressBarView) findViewById(R.id.progressBarView1);
		progressBarView2 = (CustomProgressBarView) findViewById(R.id.progressBarView2);
		txtStart = (TextView) findViewById(R.id.txtStart);
		txtStop = (TextView) findViewById(R.id.txtStop);
		txtStart.setOnClickListener(onClickListener);
		txtStop.setOnClickListener(onClickListener);

		progressBarView1.animation_config(2, 10);
		int[] colors1 = { Color.RED, Color.TRANSPARENT };
		progressBarView1.bar_config(0, 5, 0, Color.GRAY, colors1);

		progressBarView2.animation_config(2, 10);
		int[] colors2 = { Color.MAGENTA, Color.CYAN };
		progressBarView2.bar_config(0, 10, 10, Color.GRAY, colors2);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txtStart:
				progressBarView1.animation_start(Mode.INDETERMINATE);
				progressBarView2.animation_start(Mode.ONESHOT);
				break;
			case R.id.txtStop:
				progressBarView1.animation_stop();
				progressBarView2.animation_stop();
				break;
			}
		}
	};
}
