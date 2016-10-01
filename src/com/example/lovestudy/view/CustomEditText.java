package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class CustomEditText extends EditText {

	/** 搜索按钮的引用*/ 
	private Drawable DrawableLeft;
	/** 删除按钮的引用*/ 
	private Drawable DrawableRight;

	public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomEditText(Context context) {
		super(context);
		init();
	}

	private void init() {
		/**
		 * 搜索按钮的引用
		 */
		if (DrawableLeft == null) {
			DrawableLeft = getResources().getDrawable(R.drawable.icon_search);
		}
		DrawableLeft.setBounds(0, 0, DrawableLeft.getIntrinsicWidth(), DrawableLeft.getIntrinsicHeight());
		/**
		 * 删除按钮的引用
		 */
		if (DrawableRight == null) {
			DrawableRight = getResources().getDrawable(R.drawable.icon_clear);
		}
		DrawableRight.setBounds(0, 0, DrawableRight.getIntrinsicWidth(), DrawableRight.getIntrinsicHeight());
		/**
		 * 设置焦点改变的监听
		 */
		setOnFocusChangeListener(onFocusChangeListener);
		/**
		 * 设置输入框内容改变的监听
		 */
		addTextChangedListener(textWatcher);
	}

	/**
	 * 当我们按下的位置 在 EditText的宽度-图标到控件右边的间距-图标的宽度 和
	 * EditText的宽度-图标到控件右边的间距之间我们就算点击了图标
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (DrawableRight != null) {
				boolean touchable = event.getX() > (getWidth() - getPaddingRight() - DrawableRight.getIntrinsicWidth()) && (event.getX() < ((getWidth() - getPaddingRight())));
				if (touchable) {
					this.setText("");
				}
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 设置清除图标的显示与隐藏
	 */
	protected void setClearIconVisible(boolean visible) {
		Drawable right = visible ? DrawableRight : null;
		Drawable left = DrawableLeft;
		setCompoundDrawables(left, null, right, null);
	}

	/**
	 * 焦点改变的监听
	 */
	OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {

		@Override
		public void onFocusChange(View arg0, boolean hasFocus) {
			if (hasFocus) {
				setClearIconVisible(getText().length() > 0);
			} else {
				setClearIconVisible(false);
			}
		}
	};

	/**
	 * 输入框内容改变的监听
	 */
	TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
			setClearIconVisible(text.length() > 0);
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

		}

		@Override
		public void afterTextChanged(Editable arg0) {

		}
	};
}
