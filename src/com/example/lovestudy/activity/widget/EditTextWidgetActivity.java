package com.example.lovestudy.activity.widget;

import android.os.Bundle;
import android.widget.EditText;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.custom.MaxLengthWatcher;

public class EditTextWidgetActivity extends BaseActivity {

	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_edittext_widget);
		setHeadTitle(R.string.EditText);
		initView();
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.edit_max_length);
		editText.addTextChangedListener(new MaxLengthWatcher(10, editText));
	}

}
