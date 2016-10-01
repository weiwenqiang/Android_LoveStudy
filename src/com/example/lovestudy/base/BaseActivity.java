package com.example.lovestudy.base;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.logic.function.ScreenSwitchLogic;
import com.example.lovestudy.manager.SystemBarTintManager;
import com.example.lovestudy.module.RightSlideFinish.Slidr;
import com.example.lovestudy.module.RightSlideFinish.SlidrConfig;
import com.example.lovestudy.module.RightSlideFinish.SlidrInterface;
import com.example.lovestudy.module.RightSlideFinish.SlidrPosition;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.ConversionUtil;
import com.example.lovestudy.utils.SharedPreferenceUtil;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseActivity extends Activity {

	protected BaseActivity ctx;
	private View nightView;
	private SharedPreferenceUtil sharedPreferenceUtil;
	SlidrInterface slidrInterface;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		if (sharedPreferenceUtil == null) {
			sharedPreferenceUtil = new SharedPreferenceUtil(ctx);
		}
		/** 右滑退出 */
		setFlingBackFeature();
		/** 设置状态栏背景色 */
		setStatusBarColor();
	}

	/**
	 * @param cls
	 *            跳转到目标界面
	 */
	public void gotoTargetActivity(Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		startActivity(intent);
	}

	/**
	 * @param theme
	 *            夜间模式切换
	 */
	protected void setNight(int theme) {
		ViewGroup vg = (ViewGroup) getWindow().getDecorView();
		if (theme == 1) {
			if (nightView == null) {
				nightView = new View(this);
				nightView.setBackgroundColor(0xb6000000);
			}
			if (nightView.getParent() == null)
				vg.addView(nightView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		} else {
			if (nightView != null)
				vg.removeView(nightView);
		}
	}

	/**
	 * @param b
	 * @return 右滑退出开关
	 */
	protected boolean getFlingBackFeature(boolean b) {
		return b;
	}

	/**
	 * 右滑退出核心方法()
	 */
	protected void setFlingBackFeature() {
		if (!getFlingBackFeature(true)) {
			return;
		}
		SlidrConfig config = new SlidrConfig.Builder().position(SlidrPosition.LEFT).sensitivity(0.8f).touchSize(ConversionUtil.dip2px(this, 32)).build();
		slidrInterface = Slidr.attach(this, config);
	}

	/**
	 * @param disallowIntercept
	 *            解决ViewPager左右滑动和右滑退出activity的冲突
	 */
	protected void disallowSlidrInterceptTouchEvent(boolean disallowIntercept) {
		if (slidrInterface != null) {
			slidrInterface.requestDisallowInterceptTouchEvent(disallowIntercept);
		}
	}

	/**
	 * 判断手机号的移动运营商
	 */
	protected void JudgeTelephoneBuisness() {

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String subscriterId = telephonyManager.getSubscriberId();
		if (subscriterId != null) {
			if (subscriterId.startsWith("46000") || subscriterId.startsWith("46002")) {
				BaseMethod.showToast(ctx, subscriterId + ":中国移动");
			} else if (subscriterId.startsWith("46001")) {
				BaseMethod.showToast(ctx, subscriterId + ":中国联通");
			} else if (subscriterId.startsWith("46003")) {
				BaseMethod.showToast(ctx, subscriterId + ":中国电信");
			} else {
				BaseMethod.showToast(ctx, subscriterId + ":无用户");
			}
		} else {
			BaseMethod.showToast(ctx, "用户id为空==" + subscriterId);
		}
	}

	/**
	 * 设置状态栏的颜色(注意：setStatusBarTintResource（这里的颜色必须写在color.xml文件中，不能使用Color.
	 * praseColor()）)
	 */
	protected void setStatusBarColor() {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(R.color.StatusBarColor);
		}
	}

	/**
	 * 设置状态栏的默认颜色
	 */
	protected void setStatusBarDefaultColor() {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(R.color.black);
		}
	}

	/**
	 * @param on
	 *            设置半透明的状态
	 */
	@TargetApi(19)
	protected void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * @param id
	 *            设置界面标题
	 */
	protected void setHeadTitle(int id) {
		setHeadTitle(id, false, false, null, 0);
	}

	/**
	 * @param titleStr
	 *            设置界面标题
	 */
	protected void setHeadTitle(String titleStr) {
		setHeadTitle(titleStr, false, false, null, 0);
	}

	/**
	 * @param id
	 * @param isShowBack
	 * @param isShowMore
	 * @param listenerBack
	 * @param listenerMore
	 *            设置界面标题
	 */
	protected void setHeadTitle(int id, boolean isShowBack, boolean isShowMore, OnClickListener listenerMore, int moreIconId) {
		View layout = findViewById(R.id.title);
		ImageView back = (ImageView) findViewById(R.id.img_back);
		TextView title = (TextView) layout.findViewById(R.id.txt_title);
		ImageView more = (ImageView) layout.findViewById(R.id.img_more);
		title.setText(ResourcesGet.getString(ctx, id));
		if (Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)) == 1) {
			layout.setPadding(0, BaseMethod.getStatusBarHeight(ctx), 0, 0);
		} else {
			layout.setPadding(0, 0, 0, 0);
		}
		if (isShowBack) {
			back.setVisibility(View.VISIBLE);
		}
		if (isShowMore) {
			more.setVisibility(View.VISIBLE);
		}
		if (moreIconId > 0) {
			more.setImageResource(moreIconId);
		}
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		more.setOnClickListener(listenerMore);
	}

	/**
	 * @param titleStr
	 * @param isShowBack
	 * @param isShowMore
	 * @param listenerMore
	 * @param moreIconId
	 *            设置界面标题
	 */
	protected void setHeadTitle(String titleStr, boolean isShowBack, boolean isShowMore, OnClickListener listenerMore, int moreIconId) {
		View layout = findViewById(R.id.title);
		ImageView back = (ImageView) findViewById(R.id.img_back);
		TextView title = (TextView) layout.findViewById(R.id.txt_title);
		ImageView more = (ImageView) layout.findViewById(R.id.img_more);
		title.setText(titleStr);
		if (Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)) == 1) {
			layout.setPadding(0, BaseMethod.getStatusBarHeight(ctx), 0, 0);
		} else {
			layout.setPadding(0, 0, 0, 0);
		}
		if (isShowBack) {
			back.setVisibility(View.VISIBLE);
		}
		if (isShowMore) {
			more.setVisibility(View.VISIBLE);
		}
		if (moreIconId > 0) {
			more.setImageResource(moreIconId);
		}
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		more.setOnClickListener(listenerMore);
	}

	/**
	 * 显示App被分配的内存
	 */
	protected void showHeap() {
		final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		int normalHeap = activityManager.getMemoryClass();
		int largeHeap = activityManager.getLargeMemoryClass();
		System.out.println("APP分配到的内存(正常)：" + normalHeap);
		System.out.println("APP分配到的内存(大的)：" + largeHeap);
		BaseMethod.showToast(ctx, "内存分配(正常)：" + normalHeap + "M    " + "内存分配(大的)：" + largeHeap + "M");
	}

	@Override
	protected void onResume() {
		super.onResume();
		setNight(Integer.parseInt(sharedPreferenceUtil.getData(Constant.maskCode)));
		ScreenSwitchLogic.getInstance(this).setScreenOrientation(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenOrientationCode)));
		ScreenSwitchLogic.getInstance(this).setScreenFull(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)));
	}

}
