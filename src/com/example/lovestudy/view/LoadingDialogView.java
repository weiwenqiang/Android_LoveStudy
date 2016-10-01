package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class LoadingDialogView extends AlertDialog {

	private TextView tips_loading_msg;
	private String message = null;

	public LoadingDialogView(Context context) {
		super(context);
		message = getContext().getResources().getString(R.string.loading);
	}

	public LoadingDialogView(Context context, String message) {
		super(context);
		this.message = message;
		this.setCancelable(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_view_loading);
		tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
		tips_loading_msg.setText(this.message);
		setCanceledOnTouchOutside(true);
	}
	
	/**
	 * 对话框点击消失
	 */
	@Override
	public void setCanceledOnTouchOutside(boolean cancel) {
		super.setCanceledOnTouchOutside(cancel);
	}

	/**
	 * 设置提示文字
	 */
	public void setText(String message) {
		this.message = message;
		tips_loading_msg.setText(this.message);
	}
	
	/**
	 * 通过资源ID设置提示文字
	 */
	public void setText(int resId) {
		setText(getContext().getResources().getString(resId));
	}
	
}
