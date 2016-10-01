package com.example.lovestudy.logic.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class TextViewLogic {

	private static TextViewLogic TextViewLogic;
	private boolean isExtend = true;
	private boolean isRotate = true;

	public static TextViewLogic getInstance() {
		if (TextViewLogic == null) {
			TextViewLogic = new TextViewLogic();
		}
		return TextViewLogic;
	}

	/**
	 * 打开或关闭内容
	 */
	public void openOrShutContext(TextView view) {
		if (isExtend) {
			isExtend = false;
			view.setMaxLines(Integer.MAX_VALUE);
		} else {
			isExtend = true;
			view.setMaxLines(3);
		}
	}

	/**
	 * 设置控价旋转动画
	 */
	public void setRotateAnimation(View v) {
		RotateAnimation ra = null;
		if (isRotate) {
			isRotate = false;
			ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		} else {
			isRotate = true;
			ra = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		}
		ra.setFillAfter(true);
		ra.setDuration(200);
		v.startAnimation(ra);
	}
	
	/**
	 * 设置扩展和旋转状态
	 */
	public void setExtendAndRotateStatu(boolean isExtend, boolean isRotate){
		this.isExtend = isExtend;
		this.isRotate = isRotate;
	}

}
