package com.example.lovestudy.view;

import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.utils.ConversionUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class ViewPageCursorView extends ImageView {

	BaseActivity ctx;
	
	DisplayMetrics metrics;
	ViewGroup.MarginLayoutParams params;
	/** 屏幕分辨率宽度*/
	int screenW;
	/** 滑动线条的宽度 */
	int imgWidth;
	/** 切换动画*/
	Animation animation;
	/** 滑动线条离屏幕左边的距离*/
	int left;
	

	public ViewPageCursorView(Context context) {
		super(context);
		this.ctx = (BaseActivity) context;
	}
	
	public ViewPageCursorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.ctx = (BaseActivity) context;
	}
	
	public ViewPageCursorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.ctx = (BaseActivity) context;
	}

	public void initView(int viewCount, int position) {
		/** 1,显示指标 */
		if (metrics == null)
			metrics = new DisplayMetrics();
		ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenW = metrics.widthPixels;
		imgWidth = screenW / viewCount;
		
		/** 2,布局参数 */
		ViewGroup.MarginLayoutParams params = null;
		if (params == null)
			params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
		params.height = ConversionUtil.dip2px(ctx, 2);
		params.width = imgWidth;
		int left = params.leftMargin;
		params.leftMargin = imgWidth * position;
		this.setLayoutParams(params);

		/** 3,切换动画 */
		animation = new TranslateAnimation(left - params.leftMargin, 0, 0, 0);
		animation.setFillEnabled(true);
		animation.setDuration(300);
		animation.setFillAfter(true);
		this.startAnimation(animation);
	}

}
