package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.utils.ConversionUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SwitchView extends View {

	private OnSwitchChangedListener onSwitchChangedListener;
	private Paint paint = new Paint();
	public static int OFF = 0;
	public static int ON = 1;
	private int value = OFF;// 当前状态默认是关状态

	// 属性
	private int paddingLeft;
	private int paddingTop;
	private int paddingRight;
	private int paddingBottom;

	private int thumbMarginLeft;
	private int thumbMarginTop;
	private int thumbMarginRight;
	private int thumbMarginBottom;
	private int cornerRadius;
	private int onColor;

	// 运行时状态
	private int thumbLeft = this.paddingLeft + this.thumbMarginLeft;// 滑块的位置
	private boolean scrolling = false;// 是否正在拖动
	
	/**
	 * 回调接口
	 */
	public interface OnSwitchChangedListener {
		public void onSwitchChanged(View view, int value);
	}

	public SwitchView(Context context) {
		super(context);
	}

	public SwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttribute(context, attrs);
	}

	public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttribute(context, attrs);
	}

	/**
	 * 初始化属性
	 */
	private void initAttribute(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.switchview);
		paddingLeft = (int) a.getDimension(R.styleable.switchview_paddingLeft, 0);
		paddingTop = (int) a.getDimension(R.styleable.switchview_paddingTop, 0);
		paddingRight = (int) a.getDimension(R.styleable.switchview_paddingRight, 0);
		paddingBottom = (int) a.getDimension(R.styleable.switchview_paddingBottom, 0);
		thumbMarginLeft = (int) a.getDimension(R.styleable.switchview_thumbMarginLeft, ConversionUtil.dip2px(this.getContext(), 2));
		thumbMarginTop = (int) a.getDimension(R.styleable.switchview_thumbMarginTop, ConversionUtil.dip2px(this.getContext(), 1));
		thumbMarginRight = (int) a.getDimension(R.styleable.switchview_thumbMarginRight, ConversionUtil.dip2px(this.getContext(), 2));
		thumbMarginBottom = (int) a.getDimension(R.styleable.switchview_thumbMarginBottom, ConversionUtil.dip2px(this.getContext(), 1));
		cornerRadius = (int) a.getDimension(R.styleable.switchview_cornerRadius, ConversionUtil.dip2px(this.getContext(), 4));
		onColor = (int) a.getColor(R.styleable.switchview_onColor, 0xFFD9434B);
	}

	/**
	 * 绘制的方法
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBackground(canvas);
		drawThumb(canvas);
	}

	/**
	 * 测量的方法
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}
	
	/**
	 * 触碰事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				upEventHandler((int) event.getX(), (int) event.getY());
			}
			clickEventHandler((int) event.getX(), (int) event.getY());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 画背景
	 */
	private void drawBackground(Canvas canvas) {
		int backgroundColor = 0xFF989898;
		if (this.value == ON) {
			backgroundColor = onColor;
		}
		RectF rectF = new RectF();
		rectF.left = this.paddingLeft;
		rectF.top = this.paddingTop;
		rectF.right = this.getWidth() - this.paddingRight;
		rectF.bottom = this.getHeight() - this.paddingBottom;
		paint.setAntiAlias(true);
		paint.setColor(backgroundColor);
		canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);
	}

	/**
	 * 画滑块
	 */
	private void drawThumb(Canvas canvas) {
		paint.setColor(0xFFFFFFFF);
		RectF rectF = new RectF();
		if (scrolling)
			rectF.left = this.thumbLeft;
		else {
			rectF.left = getMinThumbLeft();
			if (this.value == ON) {
				rectF.left = getMaxThumbLeft();
			}
		}
		rectF.top = this.paddingTop + this.thumbMarginTop;
		rectF.right = rectF.left + geThumbWidth();
		rectF.bottom = this.getHeight() - this.paddingBottom - this.thumbMarginBottom;
		paint.setAntiAlias(true);
		canvas.drawRoundRect(rectF, this.cornerRadius, this.cornerRadius, paint);
	}

	/**
	 * 计算组件宽度
	 */
	private int measureWidth(int measureSpec) {
		int result;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = getDefaultWidth();
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * 计算组件高度
	 */
	private int measureHeight(int measureSpec) {
		int result;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = getDefaultHeight();
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	/**
	 * 计算默认宽度
	 */
	private int getDefaultWidth() {
		return ConversionUtil.dip2px(this.getContext(), 90);
	}

	/**
	 * 计算默认宽度
	 */
	private int getDefaultHeight() {
		return ConversionUtil.dip2px(this.getContext(), 30);
	}

	/**
	 * 获得minThumbLeft
	 */
	private int getMinThumbLeft() {
		return this.paddingLeft + this.thumbMarginLeft;
	}

	/**
	 * 获得maxThumbLeft
	 */
	private int getMaxThumbLeft() {
		return this.getWidth() - this.paddingRight - this.thumbMarginRight - geThumbWidth();
	}
	
	/**
	 * 获得滑块宽度
	 */
	private int geThumbWidth() {
		return (this.getWidth() - (this.paddingLeft + this.paddingRight + this.thumbMarginLeft + this.thumbMarginRight)) / 2;
	}
	
	/**
	 * up事件处理
	 */
	private void upEventHandler(int x, int y) {
		this.scrolling = false;
		int minThumbLeft = getMinThumbLeft();
		int thumbWidth = geThumbWidth();
		int value = ON;
		if (this.thumbLeft <= (minThumbLeft + thumbWidth - thumbWidth / 2))
			value = OFF;
		int oldValue = this.value;
		this.setValue(value);
		if (this.onSwitchChangedListener != null && oldValue != value)
			this.onSwitchChangedListener.onSwitchChanged(this, value);
	}
	
	/**
	 * 单击事件处理
	 */
	private void clickEventHandler(int x, int y) {
		this.thumbLeft = getMaxThumbLeft();
		int minThumbLeft = getMinThumbLeft();
		int thumbWidth = geThumbWidth();
		if (x <= (minThumbLeft + thumbWidth))
			this.thumbLeft = getMinThumbLeft();
	}
	
	/**
	 * 设置状态，或值
	 */
	public void setValue(int value) {
		if (value != OFF && value != ON)
			return;
		this.value = value;
		this.invalidate();
	}
	
	/**
	 * 设置开关状态改变的回调对象
	 */
	public void setOnSwitchChangedListener(OnSwitchChangedListener onSwitchChangedListener) {
		this.onSwitchChangedListener = onSwitchChangedListener;
	}
	
}
