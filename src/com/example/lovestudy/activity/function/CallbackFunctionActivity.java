package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.CallbackFunctionView;
import com.example.lovestudy.view.CallbackFunctionView.CallbackFunction;

public class CallbackFunctionActivity extends BaseActivity {

	private CallbackFunctionView functionView;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_callback_function);
		setHeadTitle(R.string.callback_function);
		initView();
	}

	private void initView() {
		functionView = (CallbackFunctionView) findViewById(R.id.callback_view);
		textView = (TextView) findViewById(R.id.show_content);
		functionView.setCallbackFunction(callbackFunction);
	}
	
	private CallbackFunction callbackFunction = new CallbackFunction() {
		
		@Override
		public void onWidgetClick(String string) {
			textView.setText(string);
		}
	};
}
