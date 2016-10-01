package com.example.lovestudy.activity.function;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.WebJavaInteractionView;

public class WebJavaInteractionActivity extends BaseActivity {
	
	private WebJavaInteractionView webJavaInteractionView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_webjava_interaction);
		setHeadTitle(R.string.java_js);
		initView();
	}
	
	private void initView() {
		webJavaInteractionView = (WebJavaInteractionView) findViewById(R.id.webjava_interaction_view);
		webJavaInteractionView.setUrl("file:///android_asset/demo.html");
	}
	
}
