package com.example.lovestudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PointView extends View {
	Paint paint;
	int MaxN;
	int currentPosition;

	int selectorColor = Color.WHITE;
	int unSelectorColor = Color.WHITE;

	int radiuEn = 8;
	int radiuUnEn = 5;
	int s = 20;
	public PointView(Context context) {
		super(context);
		init(0, 0);
	}

	public PointView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(0, 0);
	}

	public PointView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(0, 0);
	}

	public void init(int Position, int Max) {
		paint = new Paint();
		paint.setAntiAlias(true);
		if (Max != 0) {
			MaxN = Max;
			currentPosition = Position;
		} else {
			MaxN = Max;
			currentPosition = Position;
		}

	}

	/**
	 * 设置选中和未选中的颜色
	 */
	public void setColorResourceId(int selectorColorId, int unselectorColorId) {
		this.selectorColor = getResources().getColor(selectorColorId);
		this.unSelectorColor = getResources().getColor(unselectorColorId);
	}
	
	/**
	 * 设置选中和未选中的颜色
	 */
	public void setColor(int selectorColorId, int unselectorColorId) {
		this.selectorColor = selectorColorId;
		this.unSelectorColor = unselectorColorId;
	}

	/**
	 * 设置当前位置
	 */
	public void setPosition(int position) {
		currentPosition = position;
		invalidate();
	}

	/**
	 * 设置圆的半径
	 */
	public void setRadiu(int radiuEn, int radiuUnEn) {
		this.radiuEn = radiuEn;
		this.radiuUnEn = radiuUnEn;
	}

	/**
	 * 绘制的方法
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int x = 0;
		int y = radiuEn > radiuUnEn ? radiuEn + 5 : radiuUnEn + 5;

		for (int i = 0; i < MaxN; i++) {
			if (i == currentPosition) {
				paint.setColor(selectorColor);
				x = x + radiuEn + radiuUnEn + s;
				canvas.drawCircle(x, y, radiuEn, paint);

			} else {
				x = x + 2 * radiuUnEn + s;
				paint.setColor(unSelectorColor);
				canvas.drawCircle(x, y, radiuUnEn, paint);

			}
		}
	}

	/**
	 * 测量的方法
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = (2 * radiuUnEn + s) * MaxN+2* radiuEn-radiuUnEn;
		int heightTemp = radiuEn > radiuUnEn ? radiuEn : radiuUnEn;
		int height = 2 * heightTemp + 10;
		setMeasuredDimension(width, height);
	}
}
