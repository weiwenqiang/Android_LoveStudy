package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CheckBoxListView extends RelativeLayout {

	private boolean isCheck = false;
	private Context mContext;
	private LayoutInflater mInflater;
	private View mView;
	private ImageView checkImg;
	private ImageView unCheckImg;
	private Handler mHandler;

	public CheckBoxListView(Context context) {
		super(context);
		mContext = context;
		initView();
		this.addView(mView);
	}

	public CheckBoxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		this.addView(mView);
	}

	public CheckBoxListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
		this.addView(mView);
	}

	private void initView() {
		if (mContext != null && mInflater == null) {
			mInflater = LayoutInflater.from(mContext);
		}
		mView = (RelativeLayout) mInflater.inflate(R.layout.layout_view_checkbox_listview, null);
		checkImg = (ImageView) mView.findViewById(R.id.checkImg);
		unCheckImg = (ImageView) mView.findViewById(R.id.unCheckImg);
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean result = false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			setStatus();
			result = true;
			break;
		case MotionEvent.ACTION_DOWN:
			result = true;
			break;
		case MotionEvent.ACTION_CANCEL:
			result = true;
			break;
		case MotionEvent.ACTION_MOVE:
			result = false;
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void setStatus() {
		if (isCheck) {
			setUnCheck();
		} else {
			setChecked();
		}
	}

	public void setCheck(boolean isCheck) {
		if (isCheck) {
			setCheckedNoAnimation();
		} else {
			setUnCheckNoAnimation();
		}
	}

	public void setCheckAnimation(boolean isCheck) {
		if (isCheck) {
			setChecked();
		} else {
			setUnCheck();
		}
	}

	private void setCheckedNoAnimation() {
		if (isCheck) {
			return;
		}
		isCheck = true;
		checkImg.setVisibility(View.VISIBLE);
		unCheckImg.setVisibility(View.GONE);
	}

	private void setUnCheckNoAnimation() {
		if (!isCheck) {
			return;
		}
		isCheck = false;
		checkImg.setVisibility(View.GONE);
		unCheckImg.setVisibility(View.VISIBLE);
	}

	private void setChecked() {
		if (isCheck) {
			return;
		}
		isCheck = true;
		checkImg.setVisibility(View.VISIBLE);
		unCheckImg.setVisibility(View.GONE);
		startAnimation(checkImg);
	}

	private void setUnCheck() {
		if (!isCheck) {
			return;
		}
		isCheck = false;
		checkImg.setVisibility(View.GONE);
		unCheckImg.setVisibility(View.VISIBLE);
		startAnimation(unCheckImg);
	}

	public boolean isCheck() {
		return isCheck;
	}

	private void startAnimation(final ImageView i) {
		if (mHandler == null) {
			mHandler = new Handler();
		}
		final Animation scaleAnimation = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(200);
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				i.startAnimation(scaleAnimation);
			}
		});
	}

}
