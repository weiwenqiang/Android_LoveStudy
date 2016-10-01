package com.example.lovestudy.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class BaseGallery extends Gallery {

	public BaseGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BaseGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseGallery(Context context) {
		super(context);
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		/** velocityX/3把水平滑动的速度降低到原来的1/3 */
		return super.onFling(e1, e2, velocityX / 3, velocityY);
	}

}
