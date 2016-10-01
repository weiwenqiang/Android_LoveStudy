package com.example.lovestudy.activity.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.utils.SharedPreferenceUtil;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 遮罩界面
 */
public class MaskActivity extends BaseActivity {

	private Button button;
	private SharedPreferenceUtil sharedPreferenceUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_mask);
		setHeadTitle(R.string.view_Mask);
		initView();
	}
	
	private void initView() {
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(onClickListener);
		sharedPreferenceUtil = new SharedPreferenceUtil(this);
		setNight(Integer.valueOf(sharedPreferenceUtil.getData(Constant.maskCode)));
		setHintText();
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			if ("0".equalsIgnoreCase(sharedPreferenceUtil.getData(Constant.maskCode))) {
				sharedPreferenceUtil.setData(Constant.maskCode, "1");
			} else if ("1".equalsIgnoreCase(sharedPreferenceUtil.getData(Constant.maskCode))) {
				sharedPreferenceUtil.setData(Constant.maskCode, "0");
			}
			setNight(Integer.valueOf(sharedPreferenceUtil.getData(Constant.maskCode)));
			setHintText();
		}
	};

	private void setHintText() {
		if ("0".equalsIgnoreCase(sharedPreferenceUtil.getData(Constant.maskCode))) {
			button.setText(getString(R.string.switch_night_mode));
		} else if ("1".equalsIgnoreCase(sharedPreferenceUtil.getData(Constant.maskCode))) {
			button.setText(getString(R.string.switch_day_mode));
		}
	}

}
