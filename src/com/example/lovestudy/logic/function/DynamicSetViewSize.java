package com.example.lovestudy.logic.function;

import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

/**
 * @author Administrator ��̬���ÿؼ��Ĵ�С���߶�/��ȣ�
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
	 * ��ȡ���úõ�ImageView�Ĵ�С
	 */
	public LayoutParams getLayoutParamsImageView(ImageView imageView, int width, int height) {
		LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
		layoutParams.width = width;
		layoutParams.height = height;
		return layoutParams;
	}

}
