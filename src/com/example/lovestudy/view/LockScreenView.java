package com.example.lovestudy.view;

import java.util.ArrayList;
import java.util.List;
import com.example.lovestudy.activity.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("Recycle")
public class LockScreenView extends View {
	private Paint paint = new Paint();
	private RectF oval = new RectF();
	private OnSelectedListener onSelectedListener;
	/**
	 * 属性
	 */
	private int radius;// 圆半径
	private int pointRadius;// 中心的圆点半径
	private int border;// 圆边框
	private int columnNum;// 列数
	private int rowNum;// 行数
	private int lineWidth;// 直线的宽度
	/**
	 * 运行时参数
	 */
	private List<CircleWrapper> wrappers = null;// 所有的
	private List<CircleWrapper> selectedWrappers = null;// 已经选中的
	/**
	 * 触碰点实时移动位置
	 */
	private int moveX = 0;
	private int moveY = 0;

	public LockScreenView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LockScreenView);
		radius = (int) a.getDimension(R.styleable.LockScreenView_radius, dip2px(this.getContext(), 30));
		pointRadius = (int) a.getDimension(R.styleable.LockScreenView_pointRadius, dip2px(this.getContext(), 10));
		border = (int) a.getDimension(R.styleable.LockScreenView_border, dip2px(this.getContext(), 3));
		columnNum = (int) a.getDimension(R.styleable.LockScreenView_columnNum, 3);
		rowNum = (int) a.getDimension(R.styleable.LockScreenView_rowNum, 3);
		lineWidth = (int) a.getDimension(R.styleable.LockScreenView_lineWidth, dip2px(this.getContext(), 5));
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
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
		/** 圆间距 */
		int spacing = dip2px(this.getContext(), 70);
		int circleWidth = getCircleWidth();
		int defaultWidth = this.columnNum * circleWidth + (this.columnNum - 1) + spacing;
		return defaultWidth;
	}

	/**
	 * 计算默认宽度
	 */
	private int getDefaultHeight() {
		/** 圆间距 */
		int spacing = dip2px(this.getContext(), 30);
		int circleHeight = getCircleWidth();
		int defaultHeight = this.rowNum * circleHeight + (this.rowNum - 1) + spacing;
		return defaultHeight;
	}

	/**
	 * 获得圆宽度
	 */
	private int getCircleWidth() {
		return this.radius * 2 + this.border * 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		createWrappers();
		if (this.wrappers == null || this.wrappers.size() == 0)
			return;
		for (CircleWrapper wrapper : this.wrappers) {
			drawCircle(canvas, wrapper);
		}
		drawLine(canvas);
	}

	/**
	 * 创建wrappers
	 */
	private void createWrappers() {
		if (this.wrappers != null)
			return;
		if (this.columnNum == 0 || this.rowNum == 0)
			return;
		this.wrappers = new ArrayList<CircleWrapper>();
		int columnWidth = this.getWidth() / this.columnNum;
		int rowHeight = this.getHeight() / this.rowNum;

		int index = 0;
		for (int i = 0; i < this.rowNum; i++) {
			for (int j = 0; j < this.columnNum; j++) {
				CircleWrapper wrapper = new CircleWrapper();
				this.wrappers.add(wrapper);
				int centerX = columnWidth * j + columnWidth / 2;
				int centerY = rowHeight * i + rowHeight / 2;
				wrapper.centerX = centerX;
				wrapper.centerY = centerY;
				wrapper.selected = false;
				wrapper.value = index;
				index++;
			}
		}
	}

	/**
	 * 画单个圆
	 */
	private void drawCircle(Canvas canvas, CircleWrapper wrapper) {
		if (wrapper.selected) {
			// 画背景
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(0x45FFFFFF);
			oval.left = wrapper.centerX - this.radius;
			oval.top = wrapper.centerY - this.radius;
			oval.right = wrapper.centerX + this.radius;
			oval.bottom = wrapper.centerY + this.radius;
			canvas.drawArc(oval, 0, 360, false, paint);
		}
		// 画圆环
		int borderColor = wrapper.selected ? 0xFFFFFFFF : 0xFF7D7B7B;
		paint.setColor(borderColor);
		paint.setAntiAlias(true);// 定义画笔无锯齿
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth((float) this.border);
		oval.left = wrapper.centerX - this.radius;
		oval.top = wrapper.centerY - this.radius;
		oval.right = wrapper.centerX + this.radius;
		oval.bottom = wrapper.centerY + this.radius;
		canvas.drawArc(oval, 0, 360, false, paint);
		// 如果被选中需要画中心的圆点
		if (!wrapper.selected)
			return;
		paint.setStyle(Paint.Style.FILL);
		oval.left = wrapper.centerX - pointRadius;
		oval.top = wrapper.centerY - pointRadius;
		oval.right = wrapper.centerX + pointRadius;
		oval.bottom = wrapper.centerY + pointRadius;
		canvas.drawArc(oval, 0, 360, false, paint);
	}

	/**
	 * 画线
	 */
	private void drawLine(Canvas canvas) {
		paint.setColor(0xFFFFFFFF);
		paint.setStrokeWidth((float) lineWidth);
		if (this.selectedWrappers != null && this.selectedWrappers.size() >= 2) {
			for (int i = 1; i < this.selectedWrappers.size(); i++) {
				CircleWrapper lastWrapper = this.selectedWrappers.get(i - 1);
				CircleWrapper wrapper = this.selectedWrappers.get(i);
				canvas.drawLine(lastWrapper.centerX, lastWrapper.centerY, wrapper.centerX, wrapper.centerY, paint);
			}
		}
		// 画实时变化的直线
		if (this.selectedWrappers != null && this.selectedWrappers.size() > 0) {
			CircleWrapper lastWrapper = this.selectedWrappers.get(this.selectedWrappers.size() - 1);
			canvas.drawLine(lastWrapper.centerX, lastWrapper.centerY, this.moveX, this.moveY, paint);
		}
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 判断坐标是否在某个圆内 如果在，则返回这个圆
	 */
	private CircleWrapper inCircle(int x, int y) {
		for (CircleWrapper wrapper : this.wrappers) {
			double juli = Math.sqrt(Math.abs(x - wrapper.centerX) * Math.abs(x - wrapper.centerX) + Math.abs(y - wrapper.centerY) * Math.abs(y - wrapper.centerY));// 计算与圆心的距离
			if (juli <= this.radius)
				return wrapper;
		}
		return null;
	}

	/**
	 * 添加到selectedWrappers
	 */
	private void addToSelectedWrappers(CircleWrapper selectedWrapper) {
		if (this.selectedWrappers == null)
			this.selectedWrappers = new ArrayList<CircleWrapper>();
		if (!selectedWrapper.selected) {
			selectedWrapper.selected = true;
			this.selectedWrappers.add(selectedWrapper);
		}
	}

	/**
	 * 触碰事件
	 */
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.wrappers == null || this.wrappers.size() == 0)
			return false;
		try {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				downEventHandler((int) event.getX(), (int) event.getY());
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				moveEventHandler((int) event.getX(), (int) event.getY());
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				upEventHandler((int) event.getX(), (int) event.getY());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 按下事件
	 */
	private void downEventHandler(int x, int y) {
		CircleWrapper selectedWrapper = inCircle(x, y);
		if (selectedWrapper == null)
			return;
		addToSelectedWrappers(selectedWrapper);
		this.invalidate();
	}

	/**
	 * 移动事件
	 */
	private void moveEventHandler(int x, int y) {
		CircleWrapper selectedWrapper = inCircle(x, y);
		if (selectedWrapper != null) {
			addToSelectedWrappers(selectedWrapper);
		}
		this.moveX = x;
		this.moveY = y;
		this.invalidate();
	}

	/**
	 * 抬起事件
	 */
	private void upEventHandler(int x, int y) {
		int[] results = createResults();
		reset();
		this.invalidate();
		if (this.onSelectedListener != null)
			this.onSelectedListener.onSelected(this, results);
	}

	/**
	 * 重置
	 */
	private void reset() {
		for (CircleWrapper wrapper : this.wrappers) {
			wrapper.selected = false;
		}
		if (this.selectedWrappers != null)
			this.selectedWrappers.clear();
		this.moveX = 0;
		this.moveY = 0;
	}

	/**
	 * 执行监听器
	 */
	private int[] createResults() {
		if (this.selectedWrappers == null)
			return new int[0];
		int[] values = new int[this.selectedWrappers.size()];
		for (int i = 0; i < this.selectedWrappers.size(); i++) {
			values[i] = this.selectedWrappers.get(i).value;
		}
		return values;
	}

	/**
	 * 内部封装类
	 */
	private class CircleWrapper {
		// 圆心的x、y坐标
		int centerX;
		int centerY;
		int value;
		boolean selected;// 是否被选中
	}

	/**
	 * 定义监听器
	 * 
	 * @author pc
	 * 
	 */
	public interface OnSelectedListener {
		public void onSelected(View view, int[] values);
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPointRadius() {
		return pointRadius;
	}

	public void setPointRadius(int pointRadius) {
		this.pointRadius = pointRadius;
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
		this.onSelectedListener = onSelectedListener;
	}
}
