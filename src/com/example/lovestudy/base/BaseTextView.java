package com.example.lovestudy.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class BaseTextView extends TextView {

	public BaseTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BaseTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseTextView(Context context) {
		super(context);
	}
	
}
