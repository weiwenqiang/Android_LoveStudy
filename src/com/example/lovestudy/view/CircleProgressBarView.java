package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressBarView extends View {

	/**
	 * paint
	 */
	private Paint paint;

	/**
	 * Ring color
	 */
	private int roundColor;

	/**
	 * Ring Progress Color
	 */
	private int roundProgressColor;

	/**
	 * Progress Text Color
	 */
	private int textColor;

	/**
	 * Progress Text Size
	 */
	private float textSize;

	/**
	 * Ring Width
	 */
	private float roundWidth;

	/**
	 * Max Progress
	 */
	private int max;

	/**
	 * Current Progress
	 */
	private int progress;
	/**
	 * show the progress of the middle
	 */
	private boolean textIsDisplayable;

	/**
	 * progress style
	 */
	private int style;

	private boolean isDisplayProgressText = false;
	private String mDefaultProgressText = "��ͣ";

	public static final int STROKE = 0;
	public static final int FILL = 1;

	public CircleProgressBarView(Context context) {
		this(context, null);
	}

	public CircleProgressBarView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleProgressBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
		roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
		roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
		textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_txtColor, Color.GREEN);
		textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_txtSize, 15);
		roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
		max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
		textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
		style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);
		mTypedArray.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int centre = getWidth() / 2;
		int radius = (int) (centre - roundWidth / 2);
		paint.setColor(roundColor);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(roundWidth);
		paint.setAntiAlias(true);
		canvas.drawCircle(centre, centre, radius, paint);
		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		float textWidth = 0;
		if (isDisplayProgressText) {
			textWidth = paint.measureText(mDefaultProgressText);
			if (textIsDisplayable && style == STROKE) {
				canvas.drawText(mDefaultProgressText, centre - textWidth / 2, centre + textSize / 2, paint);
			}

		} else {
			int percent = (int) (((float) progress / (float) max) * 100);
			textWidth = paint.measureText(percent + "%");
			if (textIsDisplayable && percent != 0 && style == STROKE) {
				canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize / 2, paint);
			}
		}
		paint.setStrokeWidth(roundWidth);
		paint.setColor(roundProgressColor);
		RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

		switch (style) {
		case STROKE: {
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawArc(oval, 0, 360 * progress / max, false, paint);
			break;
		}
		case FILL: {
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			if (progress != 0)
				canvas.drawArc(oval, 0, 360 * progress / max, true, paint);
			break;
		}
		}
	}

	public synchronized int getMax() {
		return max;
	}

	public synchronized void setMax(int max) {
		if (max < 0) {
			throw new IllegalArgumentException("max not less than 0");
		}
		this.max = max;
	}

	public synchronized int getProgress() {
		return progress;
	}

	public synchronized void setProgress(int progress) {
		isDisplayProgressText = false;
		if (progress < 0) {
			throw new IllegalArgumentException("progress not less than 0");
		}
		if (progress > max) {
			progress = max;
		}
		if (progress <= max) {
			this.progress = progress;
			postInvalidate();
		}

	}

	public synchronized void setProgressText(String text) {
		this.mDefaultProgressText = text;
		isDisplayProgressText = true;
		postInvalidate();
	}

	public int getCricleColor() {
		return roundColor;
	}

	public void setCricleColor(int cricleColor) {
		this.roundColor = cricleColor;
	}

	public int getCricleProgressColor() {
		return roundProgressColor;
	}

	public void setCricleProgressColor(int cricleProgressColor) {
		this.roundProgressColor = cricleProgressColor;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}

}
