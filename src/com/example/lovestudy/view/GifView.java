package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View {

	/**
	 * 默认为1秒
	 */
	private static final int DEFAULT_MOVIE_DURATION = 1000;
	
	/**
	 * gif资源ID
	 */
	private int resourceId;

	/**
	 * 专门用来显示gif图片的类
	 */
	private Movie movie;

	/**
	 * 起始时间
	 */
	private long startTime;

	private int mCurrentAnimationTime = 0;

	private float mLeft;

	private float mTop;

	/**
	 * 放大缩小比例
	 */
	private float mScale;

	private int mMeasuredMovieWidth;

	private int mMeasuredMovieHeight;

	private boolean mVisible = true;

	/**
	 * 是否暂停
	 */
	private volatile boolean paused = false;

	public GifView(Context context) {
		this(context, null);
	}

	public GifView(Context context, AttributeSet attrs) {
		this(context, attrs, R.styleable.CustomTheme_gifViewStyle);
	}

	public GifView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setViewAttributes(context, attrs, defStyle);
	}

	@SuppressLint("NewApi")
	private void setViewAttributes(Context context, AttributeSet attrs, int defStyle) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		// 从描述文件中读出gif的值，创建出Movie实例
		final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GifView, defStyle, R.style.AppBaseTheme);
		resourceId = array.getResourceId(R.styleable.GifView_gif, -1);
		paused = array.getBoolean(R.styleable.GifView_paused, false);
		array.recycle();
		if (resourceId != -1) {
			movie = Movie.decodeStream(getResources().openRawResource(resourceId));
		}
	}

	/**
	 * 设置gif图资源
	 */
	public void setMovieResource(int movieResId) {
		this.resourceId = movieResId;
		movie = Movie.decodeStream(getResources().openRawResource(resourceId));
		requestLayout();
	}

	/**
	 * 设置暂停
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
		if (!paused) {
			startTime = android.os.SystemClock.uptimeMillis() - mCurrentAnimationTime;
		}
		invalidate();
	}

	/**
	 * 判断gif图是否停止了
	 */
	public boolean isPaused() {
		return this.paused;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (movie != null) {
			int movieWidth = movie.width();
			int movieHeight = movie.height();
			int maximumWidth = MeasureSpec.getSize(widthMeasureSpec);
			float scaleW = (float) movieWidth / (float) maximumWidth;
			mScale = 1f / scaleW;
			mMeasuredMovieWidth = maximumWidth;
			mMeasuredMovieHeight = (int) (movieHeight * mScale);
			setMeasuredDimension(mMeasuredMovieWidth, mMeasuredMovieHeight);
		} else {
			setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		mLeft = (getWidth() - mMeasuredMovieWidth) / 2f;
		mTop = (getHeight() - mMeasuredMovieHeight) / 2f;
		mVisible = getVisibility() == View.VISIBLE;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (movie != null) {
			if (!paused) {
				updateAnimationTime();
				drawMovieFrame(canvas);
				invalidateView();
			} else {
				drawMovieFrame(canvas);
			}
		}
	}

	@SuppressLint("NewApi")
	private void invalidateView() {
		if (mVisible) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				postInvalidateOnAnimation();
			} else {
				invalidate();
			}
		}
	}

	private void updateAnimationTime() {
		long now = android.os.SystemClock.uptimeMillis();
		// 如果第一帧，记录起始时间
		if (startTime == 0) {
			startTime = now;
		}
		// 取出动画的时长
		int dur = movie.duration();
		if (dur == 0) {
			dur = DEFAULT_MOVIE_DURATION;
		}
		// 算出需要显示第几帧
		mCurrentAnimationTime = (int) ((now - startTime) % dur);
	}

	private void drawMovieFrame(Canvas canvas) {
		// 设置要显示的帧，绘制即可
		movie.setTime(mCurrentAnimationTime);
		canvas.save(Canvas.MATRIX_SAVE_FLAG);
		canvas.scale(mScale, mScale);
		movie.draw(canvas, mLeft / mScale, mTop / mScale);
		canvas.restore();
	}

	@SuppressLint("NewApi")
	@Override
	public void onScreenStateChanged(int screenState) {
		super.onScreenStateChanged(screenState);
		mVisible = screenState == SCREEN_STATE_ON;
		invalidateView();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		mVisible = visibility == View.VISIBLE;
		invalidateView();
	}

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);
		mVisible = visibility == View.VISIBLE;
		invalidateView();
	}

}