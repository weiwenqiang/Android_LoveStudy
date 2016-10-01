package com.example.lovestudy.activity.function;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class FontSetActivity extends BaseActivity {
	
	private TextView textViewCustom;
	private Typeface typefaceCustom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_fontset);
		setHeadTitle(R.string.font_set);
		initView();
		setTypeFace();
	}
	
	private void initView() {
		textViewCustom = (TextView) findViewById(R.id.textview_custom);
	}

	private void setTypeFace() {
		// 自定义字体
		typefaceCustom = Typeface.createFromAsset(getAssets(), "fonts/hangshanmaozuan.ttf");
		textViewCustom.setTypeface(typefaceCustom);
	}

}
