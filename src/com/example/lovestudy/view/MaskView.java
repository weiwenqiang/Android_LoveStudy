package com.example.lovestudy.view;

import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.utils.SharedPreferenceUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class MaskView extends View {
	
	private SharedPreferenceUtil sharedPreferenceUtil;
	private String type;

	public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public MaskView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MaskView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		sharedPreferenceUtil = new SharedPreferenceUtil(context);
		type = sharedPreferenceUtil.getData(Constant.maskCode);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		try {
			if("1".equalsIgnoreCase(type)){
				canvas.drawColor(0xb0000000);
			}
		} catch (Exception e) {
		}
	}

}
