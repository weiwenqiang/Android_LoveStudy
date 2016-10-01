package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.utils.ConversionUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class CallbackFunctionView extends LinearLayout {

	CallbackFunction callbackFunction;

	Button button;

	public CallbackFunctionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CallbackFunctionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		addView(createView(context));
	}

	public CallbackFunctionView(Context context) {
		super(context);
	}

	public interface CallbackFunction {
		public void onWidgetClick(String string);
	}

	public void setCallbackFunction(CallbackFunction callbackFunction) {
		this.callbackFunction = callbackFunction;
	}

	private View createView(Context context) {
		/** 创建一个LainearLayout布局 */
		LinearLayout layout = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(params);
		/** 创建一个文本编辑框 */
		final EditText edit = new EditText(context);
		LayoutParams editParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		edit.setLayoutParams(editParams);
		edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence charSequence, int arg1, int arg2, int arg3) {
				if (charSequence != null && charSequence.length() > 0) {
					button.setEnabled(true);
				} else {
					button.setEnabled(false);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {

			}
		});
		/** 创建一个按钮 */
		button = new Button(context);
		LayoutParams btpParams = new LayoutParams(LayoutParams.MATCH_PARENT, ConversionUtil.dip2px(context, 40));
		button.setLayoutParams(btpParams);
		button.setGravity(Gravity.CENTER);
		button.setTextColor(Color.WHITE);
		button.setText("点击获取");
		button.setEnabled(false);
		button.setBackgroundResource(R.drawable.red_btn_selector);
		/** 设置按钮的点击事件 */
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/** 返回这个自定义控件中计算出的值，使用回调实现 */
				callbackFunction.onWidgetClick(edit.getText().toString());
				edit.setText("");
			}
		});
		/** 文本编辑框和按钮添加到layout布局 */
		layout.addView(edit);
		layout.addView(button);
		return layout;
	}

}
