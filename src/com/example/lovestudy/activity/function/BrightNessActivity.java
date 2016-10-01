package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.utils.BrightnessUtil;

public class BrightNessActivity extends BaseActivity {

	private RadioButton rdbClose;
	private RadioButton rdbOpen;
	private TextView txtProgress;
	private SeekBar seekBar;
	private int state = 1;
	private int savedProgress;
	private int currentProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_bright_ness);
		setHeadTitle(R.string.bright_ness);
		initView();
	}

	private void initView() {
		rdbOpen = ((RadioButton) findViewById(R.id.rdbOpen));
		rdbClose = ((RadioButton) findViewById(R.id.rdbClose));
		txtProgress = ((TextView) findViewById(R.id.txtProgress));
		seekBar = ((SeekBar) findViewById(R.id.seekBar));
		rdbOpen.setOnClickListener(onClickListener);
		rdbClose.setOnClickListener(onClickListener);
		seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
		savedProgress = BrightnessUtil.getScreenBrightness(ctx);
		seekBar.setProgress(savedProgress);
		txtProgress.setText(String.format(getResources().getString(R.string.progress), savedProgress));
		seekBar.setMax(255);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.rdbOpen:
				state = 0;
				break;
			case R.id.rdbClose:
				state = 1;
				break;
			}
		}
	};

	private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {

		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {

		}

		@Override
		public void onProgressChanged(SeekBar paramAnonymousSeekBar, int arg1, boolean paramAnonymousBoolean) {
			if ((paramAnonymousBoolean) && (state == 0)) {
				currentProgress = paramAnonymousSeekBar.getProgress();
				BrightnessUtil.setBrightness(ctx, currentProgress);
				TextView localTextView = txtProgress;
				localTextView.setText(String.format(getResources().getString(R.string.progress), currentProgress));
			}
		}
	};
}
