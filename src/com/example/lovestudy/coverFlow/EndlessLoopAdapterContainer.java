package com.example.lovestudy.coverFlow;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import com.example.lovestudy.activity.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Scroller;

public class EndlessLoopAdapterContainer extends AdapterView<Adapter> {

	public interface IViewObserver {
		void onViewRemovedFromParent(View v, int position);
	}

	protected static final int LAYOUT_MODE_AFTER = 0;

	protected static final int LAYOUT_MODE_TO_BEFORE = 1;

	protected static final int SCROLLING_DURATION = 500;

	protected Adapter mAdapter;

	protected int mFirstItemPosition;

	protected int mLastItemPosition;

	protected int mSelectedPosition = INVALID_POSITION;

	protected int mLeftChildEdge;

	protected static final int TOUCH_STATE_RESTING = 1;

	protected static final int TOUCH_STATE_SCROLLING = 2;

	protected static final int TOUCH_STATE_FLING = 3;

	protected static final int TOUCH_STATE_ALIGN = 4;

	protected static final int TOUCH_STATE_DISTANCE_SCROLL = 5;

	protected final LinkedList<WeakReference<View>> mCachedItemViews = new LinkedList<WeakReference<View>>();

	protected boolean isSrollingDisabled = false;

	protected boolean shouldRepeat = true;

	protected int mScrollPositionIfEndless = -1;

	private IViewObserver mViewObserver;

	protected int mTouchState = TOUCH_STATE_RESTING;

	protected final Scroller mScroller = new Scroller(getContext());
	private VelocityTracker mVelocityTracker;
	private boolean mDataChanged;

	private int mTouchSlop;
	private int mMinimumVelocity;
	private int mMaximumVelocity;

	private boolean mAllowLongPress;
	private float mLastMotionX;
	private float mLastMotionY;

	private final Point mDown = new Point();
	private boolean mHandleSelectionOnActionUp = false;
	private boolean mInterceptTouchEvents;

	protected OnItemClickListener mOnItemClickListener;
	protected OnItemSelectedListener mOnItemSelectedListener;

	public EndlessLoopAdapterContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		final ViewConfiguration configuration = ViewConfiguration.get(context);
		mTouchSlop = configuration.getScaledTouchSlop();
		mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
		mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();

		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EndlessLoopAdapterContainer, defStyle, 0);
			shouldRepeat = a.getBoolean(R.styleable.EndlessLoopAdapterContainer_shouldRepeat, true);
			a.recycle();
		}
	}

	public EndlessLoopAdapterContainer(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public EndlessLoopAdapterContainer(Context context) {
		this(context, null);
	}

	private final DataSetObserver fDataObserver = new DataSetObserver() {

		@Override
		public void onChanged() {
			synchronized (this) {
				mDataChanged = true;
			}
			invalidate();
		}

		@Override
		public void onInvalidated() {
			mAdapter = null;
		}
	};

	public static class LoopLayoutParams extends MarginLayoutParams {
		public static final int TOP = 0;
		public static final int CENTER = 1;
		public static final int BOTTOM = 2;
		public static final int LEFT = 3;
		public static final int RIGHT = 4;
		public int position;

		public LoopLayoutParams(int w, int h) {
			super(w, h);
			position = CENTER;
		}

		public LoopLayoutParams(int w, int h, int pos) {
			super(w, h);
			position = pos;
		}

		public LoopLayoutParams(LayoutParams lp) {
			super(lp);

			if (lp != null && lp instanceof MarginLayoutParams) {
				MarginLayoutParams mp = (MarginLayoutParams) lp;
				leftMargin = mp.leftMargin;
				rightMargin = mp.rightMargin;
				topMargin = mp.topMargin;
				bottomMargin = mp.bottomMargin;
			}
			position = CENTER;
		}

	}

	protected LoopLayoutParams createLayoutParams(int w, int h) {
		return new LoopLayoutParams(w, h);
	}

	protected LoopLayoutParams createLayoutParams(int w, int h, int pos) {
		return new LoopLayoutParams(w, h, pos);
	}

	protected LoopLayoutParams createLayoutParams(LayoutParams lp) {
		return new LoopLayoutParams(lp);
	}

	public boolean isRepeatable() {
		return shouldRepeat;
	}

	public boolean isEndlessRightNow() {
		return !isSrollingDisabled;
	}

	public void setShouldRepeat(boolean shouldRepeat) {
		this.shouldRepeat = shouldRepeat;
	}

	public void scrollToPosition(int position) {
		if (position < 0 || position >= mAdapter.getCount())
			throw new IndexOutOfBoundsException("Position must be in bounds of adapter values count");

		reset();
		refillInternal(position - 1, position);
		invalidate();
	}

	public void scrollToPositionIfEndless(int position) {
		if (position < 0 || position >= mAdapter.getCount())
			throw new IndexOutOfBoundsException("Position must be in bounds of adapter values count");

		if (isEndlessRightNow() && getChildCount() != 0) {
			scrollToPosition(position);
		} else {
			mScrollPositionIfEndless = position;
		}
	}

	public int getScrollPositionIfEndless() {
		return mScrollPositionIfEndless;
	}

	public int getScrollPosition() {
		return mFirstItemPosition;
	}

	public int getFirstItemOffset() {
		if (isSrollingDisabled)
			return 0;
		else
			return getScrollX() - mLeftChildEdge;
	}

	public void setFirstItemOffset(int offset) {
		scrollTo(offset, 0);
	}

	@Override
	public Adapter getAdapter() {
		return mAdapter;
	}

	@Override
	public void setAdapter(Adapter adapter) {
		if (mAdapter != null) {
			mAdapter.unregisterDataSetObserver(fDataObserver);
		}
		mAdapter = adapter;
		mAdapter.registerDataSetObserver(fDataObserver);

		if (adapter instanceof IViewObserver) {
			setViewObserver((IViewObserver) adapter);
		}
		reset();
		refill();
		invalidate();
	}

	@Override
	public View getSelectedView() {
		if (mSelectedPosition == INVALID_POSITION)
			return null;

		final int index;
		if (mFirstItemPosition > mSelectedPosition) {
			index = mSelectedPosition + mAdapter.getCount() - mFirstItemPosition;
		} else {
			index = mSelectedPosition - mFirstItemPosition;
		}
		if (index < 0 || index >= getChildCount())
			return null;

		return getChildAt(index);
	}

	@Override
	public void setSelection(int position) {
		if (mAdapter == null)
			throw new IllegalStateException("You are trying to set selection on widget without adapter");
		if (mAdapter.getCount() == 0 && position == 0)
			position = -1;
		if (position < -1 || position > mAdapter.getCount() - 1)
			throw new IllegalArgumentException("Position index must be in range of adapter values (0 - getCount()-1) or -1 to unselect");

		View v = getSelectedView();
		if (v != null)
			v.setSelected(false);

		final int oldPos = mSelectedPosition;
		mSelectedPosition = position;

		if (position == -1) {
			if (mOnItemSelectedListener != null)
				mOnItemSelectedListener.onNothingSelected(this);
			return;
		}

		v = getSelectedView();
		if (v != null)
			v.setSelected(true);

		if (oldPos != mSelectedPosition && mOnItemSelectedListener != null)
			mOnItemSelectedListener.onItemSelected(this, v, mSelectedPosition, getSelectedItemId());
	}

	private void reset() {
		scrollTo(0, 0);
		removeAllViewsInLayout();
		mFirstItemPosition = 0;
		mLastItemPosition = -1;
		mLeftChildEdge = 0;
	}

	@Override
	public void computeScroll() {
		if (mAdapter == null) {
			return;
		}
		if (mAdapter.getCount() == 0) {
			return;
		}
		if (mScroller.computeScrollOffset()) {
			if (mScroller.getFinalX() == mScroller.getCurrX()) {
				mScroller.abortAnimation();
				mTouchState = TOUCH_STATE_RESTING;
				if (!checkScrollPosition())
					clearChildrenCache();
				return;
			}

			int x = mScroller.getCurrX();
			scrollTo(x, 0);

			postInvalidate();
		} else if (mTouchState == TOUCH_STATE_FLING || mTouchState == TOUCH_STATE_DISTANCE_SCROLL) {
			mTouchState = TOUCH_STATE_RESTING;
			if (!checkScrollPosition())
				clearChildrenCache();
		}
		if (mDataChanged) {
			removeAllViewsInLayout();
			refillOnChange(mFirstItemPosition);
			return;
		}
		relayout();
		removeNonVisibleViews();
		refillRight();
		refillLeft();

	}

	public void fling(int velocityX, int velocityY) {
		mTouchState = TOUCH_STATE_FLING;
		final int x = getScrollX();
		final int y = getScrollY();

		mScroller.fling(x, y, velocityX, velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);

		invalidate();
	}

	public void scroll(int dx) {
		mScroller.startScroll(getScrollX(), 0, dx, 0, SCROLLING_DURATION);
		mTouchState = TOUCH_STATE_DISTANCE_SCROLL;
		invalidate();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mAdapter == null) {
			return;
		}
		refillInternal(mLastItemPosition, mFirstItemPosition);
	}

	protected void refillOnChange(int firstItemPosition) {
		refillInternal(firstItemPosition - 1, firstItemPosition);
	}

	protected void refillInternal(final int lastItemPos, final int firstItemPos) {
		if (mAdapter == null) {
			return;
		}
		if (mAdapter.getCount() == 0) {
			return;
		}

		if (getChildCount() == 0) {
			fillFirstTime(lastItemPos, firstItemPos);
		} else {
			relayout();
			removeNonVisibleViews();
			refillRight();
			refillLeft();
		}
	}

	private void refill() {
		scrollTo(0, 0);
		refillInternal(-1, 0);
	}

	protected void measureChild(View child) {
		final int pwms = MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY);
		final int phms = MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY);
		measureChild(child, pwms, phms);
	}

	private void relayout() {
		final int c = getChildCount();
		int left = mLeftChildEdge;
		View child;
		LoopLayoutParams lp;
		for (int i = 0; i < c; i++) {
			child = getChildAt(i);
			lp = (LoopLayoutParams) child.getLayoutParams();
			measureChild(child);
			left = layoutChildHorizontal(child, left, lp);
		}
	}

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
		mLeftChildEdge = 0;
		right = mLeftChildEdge;
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

	protected void refillRight() {
		if (!shouldRepeat && isSrollingDisabled)
			return;
		if (getChildCount() == 0)
			return;

		final int leftScreenEdge = getScrollX();
		final int rightScreenEdge = leftScreenEdge + getWidth();

		View child = getChildAt(getChildCount() - 1);
		int right = child.getRight();
		int currLayoutLeft = right + ((LoopLayoutParams) child.getLayoutParams()).rightMargin;
		while (right < rightScreenEdge) {
			mLastItemPosition++;
			if (mLastItemPosition >= mAdapter.getCount())
				mLastItemPosition = 0;

			child = mAdapter.getView(mLastItemPosition, getCachedView(), this);
			Validate.notNull(child, "Your adapter has returned null from getView.");
			child = addAndMeasureChildHorizontal(child, LAYOUT_MODE_AFTER);
			currLayoutLeft = layoutChildHorizontal(child, currLayoutLeft, (LoopLayoutParams) child.getLayoutParams());
			right = child.getRight();

			if (mLastItemPosition == mSelectedPosition) {
				child.setSelected(true);
			}
		}
	}

	protected void refillLeft() {
		if (!shouldRepeat && isSrollingDisabled)
			return;
		if (getChildCount() == 0)
			return;

		final int leftScreenEdge = getScrollX();

		View child = getChildAt(0);
		int childLeft = child.getLeft();
		int currLayoutRight = childLeft - ((LoopLayoutParams) child.getLayoutParams()).leftMargin;
		while (currLayoutRight > leftScreenEdge) {
			mFirstItemPosition--;
			if (mFirstItemPosition < 0)
				mFirstItemPosition = mAdapter.getCount() - 1;

			child = mAdapter.getView(mFirstItemPosition, getCachedView(), this);
			Validate.notNull(child, "Your adapter has returned null from getView.");
			child = addAndMeasureChildHorizontal(child, LAYOUT_MODE_TO_BEFORE);
			currLayoutRight = layoutChildHorizontalToBefore(child, currLayoutRight, (LoopLayoutParams) child.getLayoutParams());
			childLeft = child.getLeft() - ((LoopLayoutParams) child.getLayoutParams()).leftMargin;
			mLeftChildEdge = childLeft;

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
		final int leftedge = firstChild.getLeft() - ((LoopLayoutParams) firstChild.getLayoutParams()).leftMargin;
		if (leftedge != mLeftChildEdge)
			throw new IllegalStateException("firstChild.getLeft() != mLeftChildEdge");
		while (firstChild != null && firstChild.getRight() + ((LoopLayoutParams) firstChild.getLayoutParams()).rightMargin < leftScreenEdge) {
			firstChild.setSelected(false);
			removeViewInLayout(firstChild);
			if (mViewObserver != null)
				mViewObserver.onViewRemovedFromParent(firstChild, mFirstItemPosition);
			WeakReference<View> ref = new WeakReference<View>(firstChild);
			mCachedItemViews.addLast(ref);
			mFirstItemPosition++;
			if (mFirstItemPosition >= mAdapter.getCount())
				mFirstItemPosition = 0;
			mLeftChildEdge = getChildAt(0).getLeft() - ((LoopLayoutParams) getChildAt(0).getLayoutParams()).leftMargin;
			if (getChildCount() > 1) {
				firstChild = getChildAt(0);
			} else {
				firstChild = null;
			}
		}

		View lastChild = getChildAt(getChildCount() - 1);
		while (lastChild != null && firstChild != null && lastChild.getLeft() - ((LoopLayoutParams) firstChild.getLayoutParams()).leftMargin > rightScreenEdge) {
			lastChild.setSelected(false);
			removeViewInLayout(lastChild);

			if (mViewObserver != null)
				mViewObserver.onViewRemovedFromParent(lastChild, mLastItemPosition);
			WeakReference<View> ref = new WeakReference<View>(lastChild);
			mCachedItemViews.addLast(ref);

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

	protected View addAndMeasureChildHorizontal(final View child, final int layoutMode) {
		LayoutParams lp = child.getLayoutParams();
		LoopLayoutParams params;
		if (lp == null) {
			params = createLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		} else {
			if (lp != null && lp instanceof LoopLayoutParams)
				params = (LoopLayoutParams) lp;
			else
				params = createLayoutParams(lp);
		}
		final int index = layoutMode == LAYOUT_MODE_TO_BEFORE ? 0 : -1;
		addViewInLayout(child, index, params, true);

		measureChild(child);
		child.setDrawingCacheEnabled(true);
		return child;
	}

	protected int layoutChildHorizontal(View v, int left, LoopLayoutParams lp) {
		int l, t, r, b;

		switch (lp.position) {
		case LoopLayoutParams.TOP:
			l = left + lp.leftMargin;
			t = lp.topMargin;
			r = l + v.getMeasuredWidth();
			b = t + v.getMeasuredHeight();
			break;
		case LoopLayoutParams.BOTTOM:
			b = getHeight() - lp.bottomMargin;
			t = b - v.getMeasuredHeight();
			l = left + lp.leftMargin;
			r = l + v.getMeasuredWidth();
			break;
		case LoopLayoutParams.CENTER:
			l = left + lp.leftMargin;
			r = l + v.getMeasuredWidth();
			final int x = (getHeight() - v.getMeasuredHeight()) / 2;
			t = x;
			b = t + v.getMeasuredHeight();
			break;
		default:
			throw new RuntimeException("Only TOP,BOTTOM,CENTER are alowed in horizontal orientation");
		}

		v.layout(l, t, r, b);
		return r + lp.rightMargin;
	}

	protected int layoutChildHorizontalToBefore(View v, int right, LoopLayoutParams lp) {
		final int left = right - v.getMeasuredWidth() - lp.leftMargin - lp.rightMargin;
		layoutChildHorizontal(v, left, lp);
		return left;
	}

	protected boolean checkScrollPosition() {
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE) && (mTouchState == TOUCH_STATE_SCROLLING)) {
			return true;
		}

		final float x = ev.getX();
		final float y = ev.getY();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			if (!shouldRepeat && isSrollingDisabled)
				return false;
			final int xDiff = (int) Math.abs(x - mLastMotionX);
			final int yDiff = (int) Math.abs(y - mLastMotionY);

			final int touchSlop = mTouchSlop;
			final boolean xMoved = xDiff > touchSlop;
			final boolean yMoved = yDiff > touchSlop;

			if (xMoved) {
				mTouchState = TOUCH_STATE_SCROLLING;
				mHandleSelectionOnActionUp = false;
				enableChildrenCache();
				if (mAllowLongPress) {
					mAllowLongPress = false;
					cancelLongPress();
				}
			}
			if (yMoved) {
				mHandleSelectionOnActionUp = false;
				if (mAllowLongPress) {
					mAllowLongPress = false;
					cancelLongPress();
				}
			}
			break;

		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mLastMotionY = y;
			mAllowLongPress = true;

			mDown.x = (int) x;
			mDown.y = (int) y;

			mTouchState = mScroller.isFinished() ? TOUCH_STATE_RESTING : TOUCH_STATE_SCROLLING;
			if (mTouchState == TOUCH_STATE_RESTING) {
				mHandleSelectionOnActionUp = true;
			}
			break;

		case MotionEvent.ACTION_CANCEL:
			mDown.x = -1;
			mDown.y = -1;
			break;
		case MotionEvent.ACTION_UP:
			if (mHandleSelectionOnActionUp && mTouchState == TOUCH_STATE_RESTING) {
				final float d = ToolBox.getLineLength(mDown.x, mDown.y, x, y);
				if ((ev.getEventTime() - ev.getDownTime()) < ViewConfiguration.getLongPressTimeout() && d < mTouchSlop)
					handleClick(mDown);
			}
			mAllowLongPress = false;
			mHandleSelectionOnActionUp = false;
			mDown.x = -1;
			mDown.y = -1;
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				if (checkScrollPosition()) {
					break;
				}
			}
			mTouchState = TOUCH_STATE_RESTING;
			clearChildrenCache();
			break;
		}

		mInterceptTouchEvents = mTouchState == TOUCH_STATE_SCROLLING;
		return mInterceptTouchEvents;

	}

	protected void handleClick(Point p) {
		final int c = getChildCount();
		View v;
		final Rect r = new Rect();
		for (int i = 0; i < c; i++) {
			v = getChildAt(i);
			v.getHitRect(r);
			if (r.contains(getScrollX() + p.x, getScrollY() + p.y)) {
				final View old = getSelectedView();
				if (old != null)
					old.setSelected(false);

				int position = mFirstItemPosition + i;
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

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mAdapter == null) {
			return false;
		}

		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);

		final int action = event.getAction();
		final float x = event.getX();
		final float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			super.onTouchEvent(event);
			if (!mScroller.isFinished()) {
				mScroller.forceFinished(true);
			}
			mLastMotionX = x;
			mLastMotionY = y;

			break;
		case MotionEvent.ACTION_MOVE:
			if (!shouldRepeat && isSrollingDisabled)
				return false;
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				final int deltaX = (int) (mLastMotionX - x);
				mLastMotionX = x;
				mLastMotionY = y;
				int sx = getScrollX() + deltaX;
				scrollTo(sx, 0);
			} else {
				final int xDiff = (int) Math.abs(x - mLastMotionX);
				final int touchSlop = mTouchSlop;
				final boolean xMoved = xDiff > touchSlop;
				if (xMoved) {
					mTouchState = TOUCH_STATE_SCROLLING;
					enableChildrenCache();
					if (mAllowLongPress) {
						mAllowLongPress = false;
						cancelLongPress();
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mHandleSelectionOnActionUp && mTouchState == TOUCH_STATE_RESTING) {
				final float d = ToolBox.getLineLength(mDown.x, mDown.y, x, y);
				if ((event.getEventTime() - event.getDownTime()) < ViewConfiguration.getLongPressTimeout() && d < mTouchSlop)
					handleClick(mDown);
				mHandleSelectionOnActionUp = false;
			}

			if (mTouchState == TOUCH_STATE_SCROLLING) {
				mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
				int initialXVelocity = (int) mVelocityTracker.getXVelocity();
				int initialYVelocity = (int) mVelocityTracker.getYVelocity();
				if (Math.abs(initialXVelocity) + Math.abs(initialYVelocity) > mMinimumVelocity) {
					fling(-initialXVelocity, -initialYVelocity);
				} else {
					clearChildrenCache();
					mTouchState = TOUCH_STATE_RESTING;
					checkScrollPosition();
					mAllowLongPress = false;
					mDown.x = -1;
					mDown.y = -1;
				}
				if (mVelocityTracker != null) {
					mVelocityTracker.recycle();
					mVelocityTracker = null;
				}
				break;
			}

			clearChildrenCache();
			mTouchState = TOUCH_STATE_RESTING;
			mAllowLongPress = false;
			mDown.x = -1;
			mDown.y = -1;
			break;
		case MotionEvent.ACTION_CANCEL:
			mAllowLongPress = false;
			mDown.x = -1;
			mDown.y = -1;
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				if (checkScrollPosition()) {
					break;
				}
			}
			mTouchState = TOUCH_STATE_RESTING;
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			checkScrollFocusLeft();
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			checkScrollFocusRight();
			break;
		default:
			break;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void checkScrollFocusLeft() {
		final View focused = getFocusedChild();
		if (getChildCount() >= 2) {
			View second = getChildAt(1);
			View first = getChildAt(0);
			if (focused == second) {
				scroll(-first.getWidth());
			}
		}
	}

	private void checkScrollFocusRight() {
		final View focused = getFocusedChild();
		if (getChildCount() >= 2) {
			View last = getChildAt(getChildCount() - 1);
			View lastButOne = getChildAt(getChildCount() - 2);
			if (focused == lastButOne) {
				scroll(last.getWidth());
			}
		}
	}

	protected View getCachedView() {
		if (mCachedItemViews.size() != 0) {
			View v;
			do {
				v = mCachedItemViews.removeFirst().get();
			} while (v == null && mCachedItemViews.size() != 0);
			return v;
		}
		return null;
	}

	protected void enableChildrenCache() {
		setChildrenDrawnWithCacheEnabled(true);
		setChildrenDrawingCacheEnabled(true);
	}

	protected void clearChildrenCache() {
		setChildrenDrawnWithCacheEnabled(false);
	}

	@Override
	public void setOnItemClickListener(OnItemClickListener listener) {
		mOnItemClickListener = listener;
	}

	@Override
	public void setOnItemSelectedListener(OnItemSelectedListener listener) {
		mOnItemSelectedListener = listener;
	}

	@Override
	@CapturedViewProperty
	public int getSelectedItemPosition() {
		return mSelectedPosition;
	}

	public void setSeletedItemPosition(int position) {
		if (mAdapter.getCount() == 0 && position == 0)
			position = -1;
		if (position < -1 || position > mAdapter.getCount() - 1)
			throw new IllegalArgumentException("Position index must be in range of adapter values (0 - getCount()-1) or -1 to unselect");
		mSelectedPosition = position;
	}

	@Override
	@CapturedViewProperty
	public long getSelectedItemId() {
		return mAdapter.getItemId(mSelectedPosition);
	}

	@Override
	public Object getSelectedItem() {
		return getSelectedView();
	}

	@Override
	@CapturedViewProperty
	public int getCount() {
		if (mAdapter != null)
			return mAdapter.getCount();
		else
			return 0;
	}

	@Override
	public int getPositionForView(View view) {
		final int c = getChildCount();
		View v;
		for (int i = 0; i < c; i++) {
			v = getChildAt(i);
			if (v == view)
				return mFirstItemPosition + i;
		}
		return INVALID_POSITION;
	}

	@Override
	public int getFirstVisiblePosition() {
		return mFirstItemPosition;
	}

	@Override
	public int getLastVisiblePosition() {
		return mLastItemPosition;
	}

	@Override
	public Object getItemAtPosition(int position) {
		final int index;
		if (mFirstItemPosition > position) {
			index = position + mAdapter.getCount() - mFirstItemPosition;
		} else {
			index = position - mFirstItemPosition;
		}
		if (index < 0 || index >= getChildCount())
			return null;

		return getChildAt(index);
	}

	@Override
	public long getItemIdAtPosition(int position) {
		return mAdapter.getItemId(position);
	}

	@Override
	public boolean performItemClick(View view, int position, long id) {
		throw new UnsupportedOperationException();
	}

	public void setViewObserver(IViewObserver viewObserver) {
		this.mViewObserver = viewObserver;
	}

}
