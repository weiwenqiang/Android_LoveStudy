package com.example.lovestudy.activity.widget;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class ButtonWidgetActivity extends BaseActivity {

	private Button btnEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_button_widget);
		setHeadTitle(R.string.Button);
		initView();
	}

	private void initView() {
		btnEdit = (Button) findViewById(R.id.btn_edit);
		btnEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				JudgeTelephoneBuisness();
			}
		});
	}
}
