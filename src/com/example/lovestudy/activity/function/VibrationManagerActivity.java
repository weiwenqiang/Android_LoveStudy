package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.logic.function.VibrationManagerLogic;

public class VibrationManagerActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_vibration_manager);
		setHeadTitle(R.string.vibration_manager);
		initView();
	}

	private void initView() {
		findViewById(R.id.txt_once).setOnClickListener(mOnClickListener);
		findViewById(R.id.txt_repeat).setOnClickListener(mOnClickListener);
		findViewById(R.id.txt_cancle).setOnClickListener(mOnClickListener);
	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_once:
				VibrationManagerLogic.Vibrate(ctx, 200);
				break;
			case R.id.txt_repeat:
				long[] pattern = { 100, 400, 100, 400 };
				VibrationManagerLogic.Vibrate(ctx, pattern, false);
				break;
			case R.id.txt_cancle:
				VibrationManagerLogic.cancle(ctx);
				break;
			}
		}
	};
}
