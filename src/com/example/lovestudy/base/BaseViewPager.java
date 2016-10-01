package com.example.lovestudy.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BaseViewPager extends ViewPager {

	BaseActivity ctx;

	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.ctx = (BaseActivity) context;
	}

	public BaseViewPager(Context context) {
		super(context);
		this.ctx = (BaseActivity) context;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
			int height = 0;
			for (int i = 0; i < getChildCount(); i++) {
				View child = getChildAt(i);
				child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
				int h = child.getMeasuredHeight();
				if (h > height)
					height = h;
			}

			heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onPageScrolled(int arg0, float arg1, int arg2) {
		super.onPageScrolled(arg0, arg1, arg2);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		ctx.disallowSlidrInterceptTouchEvent(true);
		return super.onInterceptTouchEvent(ev);
	}
	
}
