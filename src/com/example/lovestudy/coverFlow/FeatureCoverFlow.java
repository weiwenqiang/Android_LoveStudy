package com.example.lovestudy.coverFlow;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import com.example.lovestudy.activity.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;


public class FeatureCoverFlow extends EndlessLoopAdapterContainer implements ViewTreeObserver.OnPreDrawListener {
	public static final int DEFAULT_MAX_CACHE_SIZE = 32;
	private final Camera mCamera = new Camera();
	private float mSpacing = 0.5f;
	private int mReverseOrderIndex = -1;
	private int mLastCenterItemIndex = -1;
	private float mRotationThreshold = 0.3f;
	private float mScalingThreshold = 0.3f;
	private float mAdjustPositionThreshold = 0.1f;
	private float mAdjustPositionMultiplier = 1.0f;
	private float mMaxRotationAngle = 70.0f;
	private float mMaxScaleFactor = 1.2f;
	private float mRadius = 2f;
	private float mRadiusInMatrixSpace = 1000f;
	private float mReflectionHeight = 0.5f;
	private int mReflectionGap = 2;
	private int mReflectionOpacity = 0x70;
	private int mTuningWidgetSize = 1280;
	private int mAlignTime = 350;
	private int mReflectionBackgroundColor = Color.TRANSPARENT;
	protected final LinkedList<WeakReference<CoverFrame>> mRecycledCoverFrames = new LinkedList<WeakReference<CoverFrame>>();
	private OnScrollPositionListener mOnScrollPositionListener;
	private int mLastTouchState = -1;
	private int mlastCenterItemPosition = -1;
	private int mPaddingTop = 0;
	private int mPaddingBottom = 0;
	private int mCenterItemOffset;
	private final Scroller mAlignScroller = new Scroller(getContext(), new DecelerateInterpolator());
	private final MyCache mCachedFrames;
	private int mCoverWidth = 160;
	private int mCoverHeight = 240;
	private final Matrix mMatrix = new Matrix();
	private final Matrix mTemp = new Matrix();
	private final Matrix mTempHit = new Matrix();
	private final Rect mTempRect = new Rect();
	private final RectF mTouchRect = new RectF();
	private View mMotionTarget;
	private float mTargetLeft;
	private float mTargetTop;
	private final Matrix mReflectionMatrix = new Matrix();
	private final Paint mPaint = new Paint();
	private final Paint mReflectionPaint = new Paint();
	private final PorterDuffXfermode mXfermode = new PorterDuffXfermode(Mode.DST_IN);
	private final Canvas mReflectionCanvas = new Canvas();
	private int mScrollToPositionOnNextInvalidate = -1;
	private boolean mInvalidated = false;

	public interface OnScrollPositionListener {
		public void onScrolledToPosition(int position);

		public void onScrolling();
	}

	private class MyCache extends LruCache<Integer, CoverFrame> {
		public MyCache(int maxSize) {
			super(maxSize);
		}

		@Override
		protected void entryRemoved(boolean evicted, Integer key, CoverFrame oldValue, CoverFrame newValue) {
			if (evicted) {
				if (oldValue.getChildCount() == 1) {
					mCachedItemViews.addLast(new WeakReference<View>(oldValue.getChildAt(0)));
					recycleCoverFrame(oldValue);
				}
			}
		}

	}

	public FeatureCoverFlow(Context context, AttributeSet attrs, int defStyle, int cacheSize) {
		super(context, attrs, defStyle);
		if (cacheSize <= 0)
			cacheSize = DEFAULT_MAX_CACHE_SIZE;
		mCachedFrames = new MyCache(cacheSize);
		setChildrenDrawingOrderEnabled(true);
		setChildrenDrawingCacheEnabled(true);
		setChildrenDrawnWithCacheEnabled(true);
		mReflectionMatrix.preScale(1.0f, -1.0f);
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FeatureCoverFlow, defStyle, 0);
			mCoverWidth = a.getDimensionPixelSize(R.styleable.FeatureCoverFlow_coverWidth, mCoverWidth);
			if (mCoverWidth % 2 == 1)
				mCoverWidth--;
			mCoverHeight = a.getDimensionPixelSize(R.styleable.FeatureCoverFlow_coverHeight, mCoverHeight);
			mSpacing = a.getFloat(R.styleable.FeatureCoverFlow_spacing, mSpacing);
			mRotationThreshold = a.getFloat(R.styleable.FeatureCoverFlow_rotationThreshold, mRotationThreshold);
			mScalingThreshold = a.getFloat(R.styleable.FeatureCoverFlow_scalingThreshold, mScalingThreshold);
			mAdjustPositionThreshold = a.getFloat(R.styleable.FeatureCoverFlow_adjustPositionThreshold, mAdjustPositionThreshold);
			mAdjustPositionMultiplier = a.getFloat(R.styleable.FeatureCoverFlow_adjustPositionMultiplier, mAdjustPositionMultiplier);
			mMaxRotationAngle = a.getFloat(R.styleable.FeatureCoverFlow_maxRotationAngle, mMaxRotationAngle);
			mMaxScaleFactor = a.getFloat(R.styleable.FeatureCoverFlow_maxScaleFactor, mMaxScaleFactor);
			mRadius = a.getFloat(R.styleable.FeatureCoverFlow_circlePathRadius, mRadius);
			mRadiusInMatrixSpace = a.getFloat(R.styleable.FeatureCoverFlow_circlePathRadiusInMatrixSpace, mRadiusInMatrixSpace);
			mReflectionHeight = a.getFloat(R.styleable.FeatureCoverFlow_reflectionHeight, mReflectionHeight);
			mReflectionGap = a.getDimensionPixelSize(R.styleable.FeatureCoverFlow_reflectionGap, mReflectionGap);
			mReflectionOpacity = a.getInteger(R.styleable.FeatureCoverFlow_reflectionOpacity, mReflectionOpacity);
			mTuningWidgetSize = a.getDimensionPixelSize(R.styleable.FeatureCoverFlow_tunningWidgetSize, mTuningWidgetSize);
			mAlignTime = a.getInteger(R.styleable.FeatureCoverFlow_alignAnimationTime, mAlignTime);
			mPaddingTop = a.getDimensionPixelSize(R.styleable.FeatureCoverFlow_verticalPaddingTop, mPaddingTop);
			mPaddingBottom = a.getDimensionPixelSize(R.styleable.FeatureCoverFlow_verticalPaddingBottom, mPaddingBottom);
			mReflectionBackgroundColor = a.getColor(R.styleable.FeatureCoverFlow_reflectionBackroundColor, Color.TRANSPARENT);
			a.recycle();
		}
	}

	public FeatureCoverFlow(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FeatureCoverFlow(Context context) {
		this(context, null);
	}

	public FeatureCoverFlow(Context context, int cacheSize) {
		this(context, null, 0, cacheSize);
	}

	public FeatureCoverFlow(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs, defStyle, DEFAULT_MAX_CACHE_SIZE);
	}

	private class CoverFrame extends FrameLayout {
		private Bitmap mReflectionCache;
		private boolean mReflectionCacheInvalid = true;

		public CoverFrame(Context context, View cover) {
			super(context);
			setCover(cover);
		}

		public void setCover(View cover) {
			if (cover.getLayoutParams() != null)
				setLayoutParams(cover.getLayoutParams());
			final LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			lp.leftMargin = 1;
			lp.topMargin = 1;
			lp.rightMargin = 1;
			lp.bottomMargin = 1;
			if (cover.getParent() != null && cover.getParent() instanceof ViewGroup) {
				ViewGroup parent = (ViewGroup) cover.getParent();
				parent.removeView(cover);
			}
			cover.getViewTreeObserver().addOnPreDrawListener(FeatureCoverFlow.this);
			addView(cover, lp);
		}

		@Override
		protected void dispatchDraw(Canvas canvas) {
			super.dispatchDraw(canvas);
			mReflectionCacheInvalid = true;
		}

		@Override
		public Bitmap getDrawingCache(boolean autoScale) {
			final Bitmap b = super.getDrawingCache(autoScale);

			if (mReflectionCacheInvalid) {
				if ((mTouchState != TOUCH_STATE_FLING && mTouchState != TOUCH_STATE_ALIGN) || mReflectionCache == null) {
					try {
						mReflectionCache = createReflectionBitmap(b);
						mReflectionCacheInvalid = false;
					} catch (NullPointerException e) {
						Log.e(VIEW_LOG_TAG, "Null pointer in createReflectionBitmap. Bitmap b=" + b, e);
					}
				}
			}
			return b;
		}

		public void recycle() {
			if (mReflectionCache != null) {
				mReflectionCache.recycle();
				mReflectionCache = null;
			}
			mReflectionCacheInvalid = true;
			removeAllViewsInLayout();
		}

	}

	private float getWidgetSizeMultiplier() {
		return ((float) mTuningWidgetSize) / ((float) getWidth());
	}

	@SuppressLint("NewApi")
	@Override
	protected View addAndMeasureChildHorizontal(View child, int layoutMode) {
		final int index = layoutMode == LAYOUT_MODE_TO_BEFORE ? 0 : -1;
		final LoopLayoutParams lp = new LoopLayoutParams(mCoverWidth, mCoverHeight);

		if (child != null && child instanceof CoverFrame) {
			addViewInLayout(child, index, lp, true);
			measureChild(child);
			return child;
		}

		CoverFrame frame = getRecycledCoverFrame();
		if (frame == null) {
			frame = new CoverFrame(getContext(), child);
		} else {
			frame.setCover(child);
		}

		if (android.os.Build.VERSION.SDK_INT >= 11)
			frame.setLayerType(LAYER_TYPE_SOFTWARE, null);
		frame.setDrawingCacheEnabled(true);

		addViewInLayout(frame, index, lp, true);
		measureChild(frame);
		return frame;
	}

	@Override
	protected int layoutChildHorizontal(View v, int left, LoopLayoutParams lp) {
		int l, t, r, b;

		l = left;
		r = l + v.getMeasuredWidth();
		final int x = ((getHeight() - mPaddingTop - mPaddingBottom) - v.getMeasuredHeight()) / 2 + mPaddingTop; // -
																												// (int)((lp.actualHeight*mReflectionHeight)/2)
		t = x;
		b = t + v.getMeasuredHeight();

		v.layout(l, t, r, b);
		return l + (int) (v.getMeasuredWidth() * mSpacing);
	}

	protected int layoutChildHorizontalToBefore(View v, int right, LoopLayoutParams lp) {
		int left = right - v.getMeasuredWidth();
		left = layoutChildHorizontal(v, left, lp);
		return left;
	}

	private int getChildsCenter(View v) {
		final int w = v.getRight() - v.getLeft();
		return v.getLeft() + w / 2;
	}

	private int getChildsCenter(int i) {
		return getChildsCenter(getChildAt(i));
	}

	@Override
	protected int getChildDrawingOrder(int childCount, int i) {
		final int screenCenter = getWidth() / 2 + getScrollX();
		final int myCenter = getChildsCenter(i);
		final int d = myCenter - screenCenter;
		final View v = getChildAt(i);
		final int sz = (int) (mSpacing * v.getWidth() / 2f);
		if (mReverseOrderIndex == -1 && (Math.abs(d) < sz || d >= 0)) {
			mReverseOrderIndex = i;
			mCenterItemOffset = d;
			mLastCenterItemIndex = i;
			return childCount - 1;
		}

		if (mReverseOrderIndex == -1) {
			return i;
		} else {
			if (i == childCount - 1) {
				final int x = mReverseOrderIndex;
				mReverseOrderIndex = -1;
				return x;
			}
			return childCount - 1 - (i - mReverseOrderIndex);
		}
	}

	@Override
	protected void refillInternal(int lastItemPos, int firstItemPos) {
		super.refillInternal(lastItemPos, firstItemPos);
		final int c = getChildCount();
		for (int i = 0; i < c; i++) {
			getChildDrawingOrder(c, i); 
		}

	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		mInvalidated = false; 
		mReverseOrderIndex = -1;

		canvas.getClipBounds(mTempRect);
		mTempRect.top = 0;
		mTempRect.bottom = getHeight();
		canvas.clipRect(mTempRect);

		super.dispatchDraw(canvas);

		if (mScrollToPositionOnNextInvalidate != -1 && mAdapter != null && mAdapter.getCount() > 0) {
			final int lastCenterItemPosition = (mFirstItemPosition + mLastCenterItemIndex) % mAdapter.getCount();
			final int di = lastCenterItemPosition - mScrollToPositionOnNextInvalidate;
			mScrollToPositionOnNextInvalidate = -1;
			if (di != 0) {
				final int dst = (int) (di * mCoverWidth * mSpacing) - mCenterItemOffset;
				scrollBy(-dst, 0);
				shouldRepeat = true;
				postInvalidate();
				return;
			}
		}

		if (mTouchState == TOUCH_STATE_RESTING) {

			final int lastCenterItemPosition = (mFirstItemPosition + mLastCenterItemIndex) % mAdapter.getCount();
			if (mLastTouchState != TOUCH_STATE_RESTING || mlastCenterItemPosition != lastCenterItemPosition) {
				mLastTouchState = TOUCH_STATE_RESTING;
				mlastCenterItemPosition = lastCenterItemPosition;
				if (mOnScrollPositionListener != null)
					mOnScrollPositionListener.onScrolledToPosition(lastCenterItemPosition);
			}
		}

		if (mTouchState == TOUCH_STATE_SCROLLING && mLastTouchState != TOUCH_STATE_SCROLLING) {
			mLastTouchState = TOUCH_STATE_SCROLLING;
			if (mOnScrollPositionListener != null)
				mOnScrollPositionListener.onScrolling();
		}
		if (mTouchState == TOUCH_STATE_FLING && mLastTouchState != TOUCH_STATE_FLING) {
			mLastTouchState = TOUCH_STATE_FLING;
			if (mOnScrollPositionListener != null)
				mOnScrollPositionListener.onScrolling();
		}

		if (mTouchState == TOUCH_STATE_RESTING && mCenterItemOffset != 0) {
			scrollBy(mCenterItemOffset, 0);
			postInvalidate();
		}

		try {
			View v = getChildAt(mLastCenterItemIndex);
			if (v != null)
				v.requestFocus(FOCUS_FORWARD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			scroll((int) (-1 * mCoverWidth * mSpacing) - mCenterItemOffset);
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			scroll((int) (mCoverWidth * mSpacing) - mCenterItemOffset);
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void fillFirstTime(final int lastItemPos, final int firstItemPos) {
		final int leftScreenEdge = 0;
		final int rightScreenEdge = leftScreenEdge + getWidth();

		int right;
		int left;
		View child;

		boolean isRepeatingNow = false;

		isSrollingDisabled = false;

		mLastItemPosition = lastItemPos;
		mFirstItemPosition = firstItemPos;
		mLeftChildEdge = (int) (-mCoverWidth * mSpacing);
		right = 0;
		left = mLeftChildEdge;

		while (right < rightScreenEdge) {
			mLastItemPosition++;

			if (isRepeatingNow && mLastItemPosition >= firstItemPos)
				return;

			if (mLastItemPosition >= mAdapter.getCount()) {
				if (firstItemPos == 0 && shouldRepeat)
					mLastItemPosition = 0;
				else {
					if (firstItemPos > 0) {
						mLastItemPosition = 0;
						isRepeatingNow = true;
					} else if (!shouldRepeat) {
						mLastItemPosition--;
						isSrollingDisabled = true;
						final int w = right - mLeftChildEdge;
						final int dx = (getWidth() - w) / 2;
						scrollTo(-dx, 0);
						return;
					}

				}
			}

			if (mLastItemPosition >= mAdapter.getCount()) {
				Log.wtf("EndlessLoop", "mLastItemPosition > mAdapter.getCount()");
				return;
			}

			child = mAdapter.getView(mLastItemPosition, getCachedView(), this);
			Validate.notNull(child, "Your adapter has returned null from getView.");
			child = addAndMeasureChildHorizontal(child, LAYOUT_MODE_AFTER);
			left = layoutChildHorizontal(child, left, (LoopLayoutParams) child.getLayoutParams());
			right = child.getRight();

			if (mLastItemPosition == mSelectedPosition) {
				child.setSelected(true);
			}
		}

		if (mScrollPositionIfEndless > 0) {
			final int p = mScrollPositionIfEndless;
			mScrollPositionIfEndless = -1;
			removeAllViewsInLayout();
			refillOnChange(p);
		}
	}

	@Override
	protected void refillRight() {
		if (!shouldRepeat && isSrollingDisabled)
			return;
		if (getChildCount() == 0)
			return;
		final int leftScreenEdge = getScrollX();
		final int rightScreenEdge = leftScreenEdge + getWidth();
		View child = getChildAt(getChildCount() - 1);
		int currLayoutLeft = child.getLeft() + (int) (child.getWidth() * mSpacing);
		while (currLayoutLeft < rightScreenEdge) {
			mLastItemPosition++;
			if (mLastItemPosition >= mAdapter.getCount())
				mLastItemPosition = 0;
			child = getViewAtPosition(mLastItemPosition);
			child = addAndMeasureChildHorizontal(child, LAYOUT_MODE_AFTER);
			currLayoutLeft = layoutChildHorizontal(child, currLayoutLeft, (LoopLayoutParams) child.getLayoutParams());

			if (mLastItemPosition == mSelectedPosition) {
				child.setSelected(true);
			}
		}
	}

	private boolean containsView(View v) {
		for (int i = 0; i < getChildCount(); i++) {
			if (getChildAt(i) == v) {
				return true;
			}
		}
		return false;
	}

	private View getViewAtPosition(int position) {
		View v = mCachedFrames.remove(position);
		if (v == null) {
			v = mAdapter.getView(position, getCachedView(), this);
			Validate.notNull(v, "Your adapter has returned null from getView.");
			return v;
		}

		if (!containsView(v)) {
			return v;
		} else {
			v = mAdapter.getView(position, getCachedView(), this);
			Validate.notNull(v, "Your adapter has returned null from getView.");
			return v;
		}
	}

	@Override
	protected void refillLeft() {
		if (!shouldRepeat && isSrollingDisabled)
			return;
		if (getChildCount() == 0)
			return;
		final int leftScreenEdge = getScrollX();
		View child = getChildAt(0);
		int currLayoutRight = child.getRight() - (int) (child.getWidth() * mSpacing);
		while (currLayoutRight > leftScreenEdge) {
			mFirstItemPosition--;
			if (mFirstItemPosition < 0)
				mFirstItemPosition = mAdapter.getCount() - 1;
			child = getViewAtPosition(mFirstItemPosition);
			if (child == getChildAt(getChildCount() - 1)) {
				removeViewInLayout(child);
			}
			child = addAndMeasureChildHorizontal(child, LAYOUT_MODE_TO_BEFORE);
			currLayoutRight = layoutChildHorizontalToBefore(child, currLayoutRight, (LoopLayoutParams) child.getLayoutParams());

			mLeftChildEdge = child.getLeft();

			if (mFirstItemPosition == mSelectedPosition) {
				child.setSelected(true);
			}
		}
	}

	protected void removeNonVisibleViews() {
		if (getChildCount() == 0)
			return;

		final int leftScreenEdge = getScrollX();
		final int rightScreenEdge = leftScreenEdge + getWidth();

		View firstChild = getChildAt(0);
		final int leftedge = firstChild.getLeft();
		if (leftedge != mLeftChildEdge) {
			Log.e("feature component", "firstChild.getLeft() != mLeftChildEdge, leftedge:" + leftedge + " ftChildEdge:" + mLeftChildEdge);
			View v = getChildAt(0);
			removeAllViewsInLayout();
			addAndMeasureChildHorizontal(v, LAYOUT_MODE_TO_BEFORE);
			layoutChildHorizontal(v, mLeftChildEdge, (LoopLayoutParams) v.getLayoutParams());
			return;
		}
		while (firstChild != null && firstChild.getRight() < leftScreenEdge) {
			firstChild.setSelected(false);

			removeViewInLayout(firstChild);

			mCachedFrames.put(mFirstItemPosition, (CoverFrame) firstChild);

			mFirstItemPosition++;
			if (mFirstItemPosition >= mAdapter.getCount())
				mFirstItemPosition = 0;

			mLeftChildEdge = getChildAt(0).getLeft();

			if (getChildCount() > 1) {
				firstChild = getChildAt(0);
			} else {
				firstChild = null;
			}
		}

		View lastChild = getChildAt(getChildCount() - 1);
		while (lastChild != null && lastChild.getLeft() > rightScreenEdge) {
			lastChild.setSelected(false);

			removeViewInLayout(lastChild);

			mCachedFrames.put(mLastItemPosition, (CoverFrame) lastChild);

			mLastItemPosition--;
			if (mLastItemPosition < 0)
				mLastItemPosition = mAdapter.getCount() - 1;

			if (getChildCount() > 1) {
				lastChild = getChildAt(getChildCount() - 1);
			} else {
				lastChild = null;
			}
		}
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		canvas.save();
		setChildTransformation(child, mMatrix);
		Bitmap bitmap = child.getDrawingCache();
		canvas.translate(child.getLeft(), child.getTop());
		canvas.concat(mMatrix);
		final Bitmap rfCache = ((CoverFrame) child).mReflectionCache;
		if (mReflectionBackgroundColor != Color.TRANSPARENT) {
			final int top = bitmap.getHeight() + mReflectionGap - 2;
			final float frame = 1.0f;
			mReflectionPaint.setColor(mReflectionBackgroundColor);
			canvas.drawRect(frame, top + frame, rfCache.getWidth() - frame, top + rfCache.getHeight() - frame, mReflectionPaint);
		}
		mPaint.reset();
		mPaint.setAntiAlias(true);
		mPaint.setFilterBitmap(true);
		canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
		canvas.drawBitmap(rfCache, 0.0f, bitmap.getHeight() - 2 + mReflectionGap, mPaint);
		canvas.restore();
		return false;
	}

	private Bitmap createReflectionBitmap(Bitmap original) {
		final int w = original.getWidth();
		final int h = original.getHeight();
		final int rh = (int) (h * mReflectionHeight);
		final int gradientColor = Color.argb(mReflectionOpacity, 0xff, 0xff, 0xff);
		final Bitmap reflection = Bitmap.createBitmap(original, 0, rh, w, rh, mReflectionMatrix, false);
		final LinearGradient shader = new LinearGradient(0, 0, 0, reflection.getHeight(), gradientColor, 0x00ffffff, TileMode.CLAMP);
		mPaint.reset();
		mPaint.setShader(shader);
		mPaint.setXfermode(mXfermode);
		mReflectionCanvas.setBitmap(reflection);
		mReflectionCanvas.drawRect(0, 0, reflection.getWidth(), reflection.getHeight(), mPaint);
		return reflection;
	}

	protected void transformChildHitRectangle(View child, RectF outRect) {
		outRect.left = 0;
		outRect.top = 0;
		outRect.right = child.getWidth();
		outRect.bottom = child.getHeight();
		setChildTransformation(child, mTempHit);
		mTempHit.mapRect(outRect);
	}

	protected void transformChildHitRectangle(View child, RectF outRect, final Matrix transformation) {
		outRect.left = 0;
		outRect.top = 0;
		outRect.right = child.getWidth();
		outRect.bottom = child.getHeight();
		transformation.mapRect(outRect);
	}

	private void setChildTransformation(View child, Matrix m) {
		m.reset();
		addChildRotation(child, m);
		addChildScale(child, m);
		addChildCircularPathZOffset(child, m);
		addChildAdjustPosition(child, m);
		m.preTranslate(-child.getWidth() / 2f, -child.getHeight() / 2f);
		m.postTranslate(child.getWidth() / 2f, child.getHeight() / 2f);
	}

	private void addChildCircularPathZOffset(View child, Matrix m) {
		mCamera.save();
		final float v = getOffsetOnCircle(getChildsCenter(child));
		final float z = mRadiusInMatrixSpace * v;
		mCamera.translate(0.0f, 0.0f, z);
		mCamera.getMatrix(mTemp);
		m.postConcat(mTemp);
		mCamera.restore();
	}

	private void addChildScale(View v, Matrix m) {
		final float f = getScaleFactor(getChildsCenter(v));
		m.postScale(f, f);
	}

	private void addChildRotation(View v, Matrix m) {
		mCamera.save();
		final int c = getChildsCenter(v);
		mCamera.rotateY(getRotationAngle(c) - getAngleOnCircle(c));
		mCamera.getMatrix(mTemp);
		m.postConcat(mTemp);
		mCamera.restore();
	}

	private void addChildAdjustPosition(View child, Matrix m) {
		final int c = getChildsCenter(child);
		final float crp = getClampedRelativePosition(getRelativePosition(c), mAdjustPositionThreshold * getWidgetSizeMultiplier());
		final float d = mCoverWidth * mAdjustPositionMultiplier * mSpacing * crp * getSpacingMultiplierOnCirlce(c);
		m.postTranslate(d, 0f);
	}

	private float getRelativePosition(int pixexPos) {
		final int half = getWidth() / 2;
		final int centerPos = getScrollX() + half;
		return (pixexPos - centerPos) / ((float) half);
	}

	private float getClampedRelativePosition(float position, float threshold) {
		if (position < 0) {
			if (position < -threshold)
				return -1f;
			else
				return position / threshold;
		} else {
			if (position > threshold)
				return 1;
			else
				return position / threshold;
		}
	}

	private float getRotationAngle(int childCenter) {
		return -mMaxRotationAngle * getClampedRelativePosition(getRelativePosition(childCenter), mRotationThreshold * getWidgetSizeMultiplier());
	}

	private float getScaleFactor(int childCenter) {
		return 1 + (mMaxScaleFactor - 1) * (1 - Math.abs(getClampedRelativePosition(getRelativePosition(childCenter), mScalingThreshold * getWidgetSizeMultiplier())));
	}

	private float getOffsetOnCircle(int childCenter) {
		float x = getRelativePosition(childCenter) / mRadius;
		if (x < -1.0f)
			x = -1.0f;
		if (x > 1.0f)
			x = 1.0f;
		return (float) (1 - Math.sin(Math.acos(x)));
	}

	private float getAngleOnCircle(int childCenter) {
		float x = getRelativePosition(childCenter) / mRadius;
		if (x < -1.0f)
			x = -1.0f;
		if (x > 1.0f)
			x = 1.0f;
		return (float) (Math.acos(x) / Math.PI * 180.0f - 90.0f);
	}

	private float getSpacingMultiplierOnCirlce(int childCenter) {
		float x = getRelativePosition(childCenter) / mRadius;
		return (float) Math.sin(Math.acos(x));
	}

	@Override
	protected void handleClick(Point p) {
		final int c = getChildCount();
		View v;
		final RectF r = new RectF();
		final int[] childOrder = new int[c];

		for (int i = 0; i < c; i++) {
			childOrder[i] = getChildDrawingOrder(c, i);
		}

		for (int i = c - 1; i >= 0; i--) {
			v = getChildAt(childOrder[i]); 
			getScrolledTransformedChildRectangle(v, r);
			if (r.contains(p.x, p.y)) {
				final View old = getSelectedView();
				if (old != null)
					old.setSelected(false);

				int position = mFirstItemPosition + childOrder[i];
				if (position >= mAdapter.getCount())
					position = position - mAdapter.getCount();

				mSelectedPosition = position;
				v.setSelected(true);

				if (mOnItemClickListener != null)
					mOnItemClickListener.onItemClick(this, v, position, getItemIdAtPosition(position));
				if (mOnItemSelectedListener != null)
					mOnItemSelectedListener.onItemSelected(this, v, position, getItemIdAtPosition(position));

				break;
			}
		}
	}

	@Override
	public void computeScroll() {
		if (mAdapter == null) {
			return;
		}
		if (mAdapter.getCount() == 0) {
			return;
		}

		if (getChildCount() == 0) {
			requestLayout();
		}

		if (mTouchState == TOUCH_STATE_ALIGN) {
			if (mAlignScroller.computeScrollOffset()) {
				if (mAlignScroller.getFinalX() == mAlignScroller.getCurrX()) {
					mAlignScroller.abortAnimation();
					mTouchState = TOUCH_STATE_RESTING;
					clearChildrenCache();
					return;
				}

				int x = mAlignScroller.getCurrX();
				scrollTo(x, 0);

				postInvalidate();
				return;
			} else {
				mTouchState = TOUCH_STATE_RESTING;
				clearChildrenCache();
				return;
			}
		}

		super.computeScroll();
	}

	@Override
	protected boolean checkScrollPosition() {
		if (mCenterItemOffset != 0) {
			mAlignScroller.startScroll(getScrollX(), 0, mCenterItemOffset, 0, mAlignTime);
			mTouchState = TOUCH_STATE_ALIGN;
			invalidate();
			return true;
		}
		return false;
	}

	private void getScrolledTransformedChildRectangle(View child, RectF r) {
		transformChildHitRectangle(child, r);
		final int offset = child.getLeft() - getScrollX();
		r.offset(offset, child.getTop());
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		final float xf = ev.getX();
		final float yf = ev.getY();
		final RectF frame = mTouchRect;

		if (action == MotionEvent.ACTION_DOWN) {
			if (mMotionTarget != null) {
				mMotionTarget = null;
			}
			if (!onInterceptTouchEvent(ev)) {
				ev.setAction(MotionEvent.ACTION_DOWN);
				final int count = getChildCount();
				final int[] childOrder = new int[count];
				for (int i = 0; i < count; i++) {
					childOrder[i] = getChildDrawingOrder(count, i);
				}
				for (int i = count - 1; i >= 0; i--) {
					final View child = getChildAt(childOrder[i]);
					if (child.getVisibility() == VISIBLE || child.getAnimation() != null) {
						getScrolledTransformedChildRectangle(child, frame);
						if (frame.contains(xf, yf)) {
							final float xc = xf - frame.left;
							final float yc = yf - frame.top;
							ev.setLocation(xc, yc);
							if (child.dispatchTouchEvent(ev)) {
								mMotionTarget = child;
								mTargetTop = frame.top;
								mTargetLeft = frame.left;
								return true;
							}
							break;
						}
					}
				}
			}
		}

		boolean isUpOrCancel = (action == MotionEvent.ACTION_UP) || (action == MotionEvent.ACTION_CANCEL);
		final View target = mMotionTarget;
		if (target == null) {
			ev.setLocation(xf, yf);
			return onTouchEvent(ev);
		}
		if (onInterceptTouchEvent(ev)) {
			final float xc = xf - mTargetLeft;
			final float yc = yf - mTargetTop;
			ev.setAction(MotionEvent.ACTION_CANCEL);
			ev.setLocation(xc, yc);
			if (!target.dispatchTouchEvent(ev)) {
			}
			mMotionTarget = null;
			return true;
		}

		if (isUpOrCancel) {
			mMotionTarget = null;
			mTargetTop = -1;
			mTargetLeft = -1;
		}

		final float xc = xf - mTargetLeft;
		final float yc = yf - mTargetTop;
		ev.setLocation(xc, yc);

		return target.dispatchTouchEvent(ev);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
		final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
		final int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
		final int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

		int h, w;
		if (heightSpecMode == MeasureSpec.EXACTLY)
			h = heightSpecSize;
		else {
			h = (int) ((mCoverHeight + mCoverHeight * mReflectionHeight + mReflectionGap) * mMaxScaleFactor + mPaddingTop + mPaddingBottom);
			h = resolveSize(h, heightMeasureSpec);
		}

		if (widthSpecMode == MeasureSpec.EXACTLY)
			w = widthSpecSize;
		else {
			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			w = display.getWidth();
			w = resolveSize(w, widthMeasureSpec);
		}

		setMeasuredDimension(w, h);
	}

	@Override
	protected void enableChildrenCache() {
	}

	@Override
	protected void clearChildrenCache() {
	}

	public void trimChacheSize(int size) {
		mCachedFrames.trimToSize(size);
	}

	public void clearCache() {
		mCachedFrames.evictAll();
	}

	public float getSpacing() {
		return mSpacing;
	}

	public void setSpacing(float spacing) {
		this.mSpacing = spacing;
	}

	public int getCoverWidth() {
		return mCoverWidth;
	}

	public void setCoverWidth(int coverWidth) {
		if (coverWidth % 2 == 1)
			coverWidth--;
		this.mCoverWidth = coverWidth;
	}

	public int getCoverHeight() {
		return mCoverHeight;
	}

	public void setCoverHeight(int coverHeight) {
		this.mCoverHeight = coverHeight;
	}

	public void setRotationTreshold(float rotationThreshold) {
		this.mRotationThreshold = rotationThreshold;
	}

	public void setScalingThreshold(float scalingThreshold) {
		this.mScalingThreshold = scalingThreshold;
	}

	public void setAdjustPositionThreshold(float adjustPositionThreshold) {
		this.mAdjustPositionThreshold = adjustPositionThreshold;
	}

	public void setAdjustPositionMultiplier(float adjustPositionMultiplier) {
		this.mAdjustPositionMultiplier = adjustPositionMultiplier;
	}

	public void setMaxRotationAngle(float maxRotationAngle) {
		this.mMaxRotationAngle = maxRotationAngle;
	}

	public void setMaxScaleFactor(float maxScaleFactor) {
		this.mMaxScaleFactor = maxScaleFactor;
	}

	public void setRadius(float radius) {
		this.mRadius = radius;
	}

	public void setRadiusInMatrixSpace(float radiusInMatrixSpace) {
		this.mRadiusInMatrixSpace = radiusInMatrixSpace;
	}

	public void setReflectionHeight(float reflectionHeight) {
		this.mReflectionHeight = reflectionHeight;
	}

	public void setReflectionGap(int reflectionGap) {
		this.mReflectionGap = reflectionGap;
	}

	public void setReflectionOpacity(int reflectionOpacity) {
		this.mReflectionOpacity = reflectionOpacity;
	}

	public void setTuningWidgetSize(int size) {
		this.mTuningWidgetSize = size;
	}

	public void setAlignTime(int alignTime) {
		this.mAlignTime = alignTime;
	}

	public void setVerticalPaddingTop(int paddingTop) {
		this.mPaddingTop = paddingTop;
	}

	public void setVerticalPaddingBottom(int paddingBottom) {
		this.mPaddingBottom = paddingBottom;
	}

	public void setReflectionBackgroundColor(int reflectionBackgroundColor) {
		this.mReflectionBackgroundColor = reflectionBackgroundColor;
	}

	@Override
	public int getScrollPosition() {
		if (mAdapter == null || mAdapter.getCount() == 0)
			return -1;

		if (mLastCenterItemIndex != -1) {
			return (mFirstItemPosition + mLastCenterItemIndex) % mAdapter.getCount();
		} else
			return (mFirstItemPosition + (getWidth() / ((int) (mCoverWidth * mSpacing))) / 2) % mAdapter.getCount();
	}

	@Override
	public void scrollToPosition(int position) {
		if (mAdapter == null || mAdapter.getCount() == 0)
			throw new IllegalStateException("You are trying to scroll container with no adapter set. Set adapter first.");

		if (mLastCenterItemIndex != -1) {
			final int lastCenterItemPosition = (mFirstItemPosition + mLastCenterItemIndex) % mAdapter.getCount();
			final int di = lastCenterItemPosition - position;
			final int dst = (int) (di * mCoverWidth * mSpacing);
			mScrollToPositionOnNextInvalidate = -1;
			scrollBy(-dst, 0);
		} else {
			mScrollToPositionOnNextInvalidate = position;
		}
		invalidate();
	}

	public void setOnScrollPositionListener(OnScrollPositionListener onScrollPositionListener) {
		mOnScrollPositionListener = onScrollPositionListener;
	}

	private void recycleCoverFrame(CoverFrame cf) {
		cf.recycle();
		WeakReference<CoverFrame> ref = new WeakReference<CoverFrame>(cf);
		mRecycledCoverFrames.addLast(ref);
	}

	protected CoverFrame getRecycledCoverFrame() {
		if (!mRecycledCoverFrames.isEmpty()) {
			CoverFrame v;
			do {
				v = mRecycledCoverFrames.removeFirst().get();
			} while (v == null && !mRecycledCoverFrames.isEmpty());
			return v;
		}
		return null;
	}

	public void releaseAllMemoryResources() {
		mLastItemPosition = mFirstItemPosition;
		mLastItemPosition--;
		final int w = (int) (mCoverWidth * mSpacing);
		int sp = getScrollX() % w;
		if (sp < 0)
			sp = sp + w;
		scrollTo(sp, 0);
		removeAllViewsInLayout();
		clearCache();
	}

	@Override
	public boolean onPreDraw() { 
		if (!mInvalidated) { 
			mInvalidated = true;
			invalidate();
			return false;
		}
		return true;
	}
}
