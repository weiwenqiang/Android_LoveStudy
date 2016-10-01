package com.example.lovestudy.module.RightSlideFinish;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class SliderPanel extends FrameLayout {

    private static final int MIN_FLING_VELOCITY = 400; 
    private static final int MAX_DIM_ALPHA = 204;

    private int mScreenWidth;
    private int mScreenHeight;
    private View mDimView;
    private View mDecorView;
    private ViewDragHelper mDragHelper;
    private OnPanelSlideListener mListener;
    private boolean mIsLocked = false;
    private int mPosition;
    private SlidrConfig mConfig;

    public SliderPanel(Context context, View decorView) {
        super(context);
        mDecorView = decorView;
        mConfig = new SlidrConfig.Builder().build();
        init();
    }

    public SliderPanel(Context context, View decorView, SlidrConfig config){
        super(context);
        mDecorView = decorView;
        mConfig = config;
        init();
    }

    public void setOnPanelSlideListener(OnPanelSlideListener listener){
        mListener = listener;
    }

    private void init(){
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        final float density = getResources().getDisplayMetrics().density;
        final float minVel = MIN_FLING_VELOCITY * density;

        ViewDragHelper.Callback callback;
        switch (mConfig.getPosition()){
            case LEFT:
                callback = mLeftCallback;
                mPosition = ViewDragHelper.EDGE_LEFT;
                break;
            case RIGHT:
                callback = mRightCallback;
                mPosition = ViewDragHelper.EDGE_RIGHT;
                break;
            case TOP:
                callback = mTopCallback;
                mPosition = ViewDragHelper.EDGE_TOP;
                break;
            case BOTTOM:
                callback = mBottomCallback;
                mPosition = ViewDragHelper.EDGE_BOTTOM;
                break;
            default:
                callback = mLeftCallback;
                mPosition = ViewDragHelper.EDGE_LEFT;
        }

        mDragHelper = ViewDragHelper.create(this, mConfig.getSensitivity(), callback);
        mDragHelper.setMinVelocity(minVel);
        mDragHelper.setEdgeTrackingEnabled(mPosition);

        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);

        mDimView = new View(getContext());
        mDimView.setBackgroundColor(Color.BLACK);
        mDimView.getBackground().setAlpha(MAX_DIM_ALPHA);

        addView(mDimView);

        post(new Runnable() {
            @Override
            public void run() {
                mScreenHeight = getHeight();
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interceptForDrag = mDragHelper.shouldInterceptTouchEvent(ev);
        return interceptForDrag && !mIsLocked;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            mDragHelper.processTouchEvent(event);
        }catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void lock(){
        mDragHelper.cancel();
        mIsLocked = true;
    }

    public void unlock(){
        mDragHelper.cancel();
        mIsLocked = false;
    }

    private ViewDragHelper.Callback mLeftCallback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child.getId() == mDecorView.getId();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return clamp(left, 0, mScreenWidth);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mScreenWidth;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            final int width = getWidth();
            float offset = width - releasedChild.getLeft();
            int left = xvel < 0 || xvel == 0 && offset > 0.5f ? 0 : mScreenWidth;

            mDragHelper.settleCapturedViewAt(left, releasedChild.getTop());
            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            float percent = 1f - ((float)left / (float)mScreenWidth);

            if(mListener != null) mListener.onSlideChange(left);

            float alpha = percent * MAX_DIM_ALPHA;
            mDimView.getBackground().setAlpha((int)alpha);
            
            if (percent >= 1.0f){
            	mDimView.setVisibility(View.GONE);
            }else {
            	mDimView.setVisibility(View.VISIBLE);
			}
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if(mListener != null) mListener.onStateChanged(state);
            switch (state){
                case ViewDragHelper.STATE_IDLE:
                    if(mDecorView.getLeft() == 0){
                        if(mListener != null) mListener.onOpened();
                    }else{
                        if(mListener != null) mListener.onClosed();
                    }
                    break;
                case ViewDragHelper.STATE_DRAGGING:

                    break;
                case ViewDragHelper.STATE_SETTLING:

                    break;
            }
        }

    };

    private ViewDragHelper.Callback mRightCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mDragHelper.isEdgeTouched(mPosition, pointerId);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return clamp(left, -mScreenWidth, 0);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mScreenWidth;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            final int width = getWidth();
            float offset = width + releasedChild.getLeft();
            int left = xvel > 0 || xvel == 0 && offset < (mScreenWidth - 0.5f) ? 0 : -mScreenWidth;

            mDragHelper.settleCapturedViewAt(left, releasedChild.getTop());
            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            float percent = 1f - ((float)Math.abs(left) / (float)mScreenWidth);

            if(mListener != null) mListener.onSlideChange(left);

            float alpha = percent * MAX_DIM_ALPHA;
            mDimView.getBackground().setAlpha((int)alpha);
            
            if (percent >= 1.0f){
            	mDimView.setVisibility(View.GONE);
            }else {
            	mDimView.setVisibility(View.VISIBLE);
			}
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if(mListener != null) mListener.onStateChanged(state);
            switch (state){
                case ViewDragHelper.STATE_IDLE:
                    if(mDecorView.getLeft() == 0){
                        if(mListener != null) mListener.onOpened();
                    }else{
                        if(mListener != null) mListener.onClosed();
                    }
                    break;
                case ViewDragHelper.STATE_DRAGGING:

                    break;
                case ViewDragHelper.STATE_SETTLING:

                    break;
            }
        }
    };

    private ViewDragHelper.Callback mTopCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
        	return child.getId() == mDecorView.getId();
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return clamp(top, 0, mScreenHeight);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mScreenHeight;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            final int height = getHeight();
            float offset = height - releasedChild.getTop();
            int top = yvel < 0 || yvel == 0 && offset > 0.5f ? 0 : mScreenHeight;

            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            float percent = 1f - ((float)Math.abs(top) / (float)mScreenHeight);

            if(mListener != null) mListener.onSlideChange(top);

            float alpha = percent * MAX_DIM_ALPHA;
            mDimView.getBackground().setAlpha((int)alpha);
            
            if (percent >= 1.0f){
            	mDimView.setVisibility(View.GONE);
            }else {
            	mDimView.setVisibility(View.VISIBLE);
			}
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if(mListener != null) mListener.onStateChanged(state);
            switch (state){
                case ViewDragHelper.STATE_IDLE:
                    if(mDecorView.getTop() == 0){
                        if(mListener != null) mListener.onOpened();
                    }else{
                        if(mListener != null) mListener.onClosed();
                    }
                    break;
                case ViewDragHelper.STATE_DRAGGING:

                    break;
                case ViewDragHelper.STATE_SETTLING:

                    break;
            }
        }
    };

    private ViewDragHelper.Callback mBottomCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
        	return child.getId() == mDecorView.getId();
        	
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return clamp(top, -mScreenHeight, 0);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mScreenHeight;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);

            final int height = getHeight();
            float offset = height - releasedChild.getTop();
            int top = yvel > 0 || yvel == 0 && offset < (mScreenHeight-0.5f) ? 0 : -mScreenHeight;

            mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
            invalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            float percent = 1f - ((float)Math.abs(top) / (float)mScreenHeight);

            if(mListener != null) mListener.onSlideChange(top);

            float alpha = percent * MAX_DIM_ALPHA;
            mDimView.getBackground().setAlpha((int)alpha);
            
            if (percent >= 1.0f){
            	mDimView.setVisibility(View.GONE);
            }else {
            	mDimView.setVisibility(View.VISIBLE);
			}
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if(mListener != null) mListener.onStateChanged(state);
            switch (state){
                case ViewDragHelper.STATE_IDLE:
                    if(mDecorView.getTop() == 0){
                        if(mListener != null) mListener.onOpened();
                    }else{
                        if(mListener != null) mListener.onClosed();
                    }
                    break;
                case ViewDragHelper.STATE_DRAGGING:

                    break;
                case ViewDragHelper.STATE_SETTLING:

                    break;
            }
        }
    };

    public static int clamp(int value, int min, int max){
        return Math.max(min, Math.min(max, value));
    }

    public static interface OnPanelSlideListener{
        public void onStateChanged(int state);
        public void onClosed();
        public void onOpened();
        public void onSlideChange(int pos);
    }

}
