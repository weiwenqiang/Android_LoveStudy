package com.example.lovestudy.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CustomTextView extends TextView {

	private Activity ctx;

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.ctx = (Activity) context;
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.ctx = (Activity) context;
	}

	public CustomTextView(Context context) {
		super(context);
		this.ctx = (Activity) context;
	}

	public int setWidgetWidth(int size) {
		System.out.println("getScreenWidth() - size==:" + (getScreenWidth() - size));
		return getScreenWidth() - size;
	}

	private int getScreenWidth() {
		DisplayMetrics metric = new DisplayMetrics();
		ctx.getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;
		System.out.println("getScreenWidth()==:" + width);
		return width;
	}

}
