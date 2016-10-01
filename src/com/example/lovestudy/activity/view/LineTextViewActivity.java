package com.example.lovestudy.activity.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.LineTextView;

import android.os.Bundle;

public class LineTextViewActivity extends BaseActivity {
	
	private LineTextView textViewDown;
	private LineTextView textViewCenter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_line_textview);
		setHeadTitle(R.string.view_LineTextView);
		initView();
	}

	private void initView() {
		textViewDown = (LineTextView) findViewById(R.id.text_down);
		textViewCenter = (LineTextView) findViewById(R.id.text_center);
		textViewDown.setBottomLine();
		textViewCenter.setCeterLine();
	}
	
}
