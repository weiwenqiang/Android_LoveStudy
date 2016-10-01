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
		/** ����һ��LainearLayout���� */
		LinearLayout layout = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(params);
		/** ����һ���ı��༭�� */
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
		/** ����һ����ť */
		button = new Button(context);
		LayoutParams btpParams = new LayoutParams(LayoutParams.MATCH_PARENT, ConversionUtil.dip2px(context, 40));
		button.setLayoutParams(btpParams);
		button.setGravity(Gravity.CENTER);
		button.setTextColor(Color.WHITE);
		button.setText("�����ȡ");
		button.setEnabled(false);
		button.setBackgroundResource(R.drawable.red_btn_selector);
		/** ���ð�ť�ĵ���¼� */
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/** ��������Զ���ؼ��м������ֵ��ʹ�ûص�ʵ�� */
				callbackFunction.onWidgetClick(edit.getText().toString());
				edit.setText("");
			}
		});
		/** �ı��༭��Ͱ�ť��ӵ�layout���� */
		layout.addView(edit);
		layout.addView(button);
		return layout;
	}

}
