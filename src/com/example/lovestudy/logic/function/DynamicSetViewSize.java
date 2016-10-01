package com.example.lovestudy.logic.function;

import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author Administrator 动态设置控件的大小（高度/宽度）
 */
public class DynamicSetViewSize {

	static DynamicSetViewSize dynamicSetViewSize;

	public static DynamicSetViewSize getInstance() {
		if (dynamicSetViewSize == null) {
			dynamicSetViewSize = new DynamicSetViewSize();
		}
		return dynamicSetViewSize;
	}

	/**
	 * 获取设置好的ImageView的大小
	 */
	public LayoutParams getLayoutParamsImageView(ImageView imageView, int width, int height) {
		LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
		layoutParams.width = width;
		layoutParams.height = height;
		return layoutParams;
	}

}
