package com.example.lovestudy.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LineTextView extends TextView {

	public LineTextView(Context context) {
		super(context);
	}

	public LineTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LineTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置下划线
	 */
	public void setBottomLine() {
		this.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
	}

	/**
	 * 设置中划线
	 */
	public void setCeterLine() {
		this.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
	}

	/**
	 * 取消划线
	 */
	public void cancelLine() {
		this.getPaint().setFlags(0);
	}

}
