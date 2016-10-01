package com.example.lovestudy.activity.widget;

import java.util.Calendar;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class DatePickerWidgetActivity extends BaseActivity {

	private TextView dateTxt;
	private DatePicker datePicker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_datepicker_activity);
		setHeadTitle(R.string.DatePicker);
		initView();
		bindView();
	}

	private void initView() {
		dateTxt = (TextView) findViewById(R.id.dateTxt);
		datePicker = (DatePicker) findViewById(R.id.datePicker);
	}

	private void bindView() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				dateTxt.setText("您选择的日期是：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日。");
			}

		});
	}
}
