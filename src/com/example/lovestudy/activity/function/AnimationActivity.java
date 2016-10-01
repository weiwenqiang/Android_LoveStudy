package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.logic.function.ScreenSizeLogic;

public class AnimationActivity extends BaseActivity {

	private ImageView imgAlpha, imgScale, imgTranslate, imgRotate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_animation);
		setHeadTitle(R.string.animation);
		initView();
	}

	private void initView() {
		imgAlpha = (ImageView) findViewById(R.id.img_alpha);
		imgScale = (ImageView) findViewById(R.id.img_scale);
		imgTranslate = (ImageView) findViewById(R.id.img_translate);
		imgRotate = (ImageView) findViewById(R.id.img_rotate);

		imgAlpha.setAnimation(getAlphaAnimation());
		imgScale.setAnimation(getScaleAnimation());
		imgTranslate.setAnimation(getTranslateAnimation());
		imgRotate.setAnimation(getRotateAnimation());
	}

	private AlphaAnimation getAlphaAnimation() {
		AlphaAnimation mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
		mAlphaAnimation.setRepeatCount(Animation.INFINITE);
		mAlphaAnimation.setDuration(3000);
		mAlphaAnimation.setFillBefore(true);
		mAlphaAnimation.setInterpolator(new LinearInterpolator());
		mAlphaAnimation.setFillAfter(true);
		return mAlphaAnimation;
	}

	private ScaleAnimation getScaleAnimation() {
		ScaleAnimation mScaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setRepeatCount(Animation.INFINITE);
		mScaleAnimation.setDuration(3000);
		mScaleAnimation.setFillBefore(true);
		mScaleAnimation.setInterpolator(new LinearInterpolator());
		mScaleAnimation.setFillAfter(true);
		return mScaleAnimation;
	}

	private TranslateAnimation getTranslateAnimation() {
		int screenWidth = ScreenSizeLogic.getInstance(ctx).getScreenWidth();
		TranslateAnimation mTranslateAnimation = new TranslateAnimation(0, screenWidth, 0, 0);
		mTranslateAnimation.setRepeatCount(Animation.INFINITE);
		mTranslateAnimation.setDuration(3000);
		mTranslateAnimation.setFillBefore(true);
		mTranslateAnimation.setInterpolator(new LinearInterpolator());
		mTranslateAnimation.setFillAfter(true);
		return mTranslateAnimation;
	}

	private RotateAnimation getRotateAnimation() {
		RotateAnimation mRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateAnimation.setRepeatCount(Animation.INFINITE);
		mRotateAnimation.setDuration(3000);
		mRotateAnimation.setInterpolator(new LinearInterpolator());
		mRotateAnimation.setFillBefore(true);
		mRotateAnimation.setFillAfter(true);
		return mRotateAnimation;
	}
}
