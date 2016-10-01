package com.example.lovestudy.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextViewVertical extends TextView {

	public CustomTextViewVertical(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomTextViewVertical(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomTextViewVertical(Context context) {
		super(context);
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		if ("".equals(text) || text == null || text.length() == 0) {
			return;
		}
		int m = text.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < m; i++) {
			CharSequence index = text.toString().subSequence(i, i + 1);
			sb.append(index + "\n");
		}
		super.setText(sb, type);
	}
}
