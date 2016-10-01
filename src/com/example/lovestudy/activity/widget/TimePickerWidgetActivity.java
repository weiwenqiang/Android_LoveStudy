package com.example.lovestudy.activity.widget;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class TimePickerWidgetActivity extends BaseActivity {

	private TextView timeTxt;
	private TimePicker timePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_timepicker_activity);
		setHeadTitle(R.string.TimePicker);
		initView();
	}

	private void initView() {
		timeTxt = (TextView) findViewById(R.id.timeTxt);
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				timeTxt.setText("您选择的时间是：" + hourOfDay + "时" + minute + "分。");
			}

		});
	}

}
