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
		/** �һ��˳� */
		setFlingBackFeature();
		/** ����״̬������ɫ */
		setStatusBarColor();
	}

	/**
	 * @param cls
	 *            ��ת��Ŀ�����
	 */
	public void gotoTargetActivity(Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		startActivity(intent);
	}

	/**
	 * @param theme
	 *            ҹ��ģʽ�л�
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
	 * @return �һ��˳�����
	 */
	protected boolean getFlingBackFeature(boolean b) {
		return b;
	}

	/**
	 * �һ��˳����ķ���()
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
	 *            ���ViewPager���һ������һ��˳�activity�ĳ�ͻ
	 */
	protected void disallowSlidrInterceptTouchEvent(boolean disallowIntercept) {
		if (slidrInterface != null) {
			slidrInterface.requestDisallowInterceptTouchEvent(disallowIntercept);
		}
	}

	/**
	 * �ж��ֻ��ŵ��ƶ���Ӫ��
	 */
	protected void JudgeTelephoneBuisness() {

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String subscriterId = telephonyManager.getSubscriberId();
		if (subscriterId != null) {
			if (subscriterId.startsWith("46000") || subscriterId.startsWith("46002")) {
				BaseMethod.showToast(ctx, subscriterId + ":�й��ƶ�");
			} else if (subscriterId.startsWith("46001")) {
				BaseMethod.showToast(ctx, subscriterId + ":�й���ͨ");
			} else if (subscriterId.startsWith("46003")) {
				BaseMethod.showToast(ctx, subscriterId + ":�й�����");
			} else {
				BaseMethod.showToast(ctx, subscriterId + ":���û�");
			}
		} else {
			BaseMethod.showToast(ctx, "�û�idΪ��==" + subscriterId);
		}
	}

	/**
	 * ����״̬������ɫ(ע�⣺setStatusBarTintResource���������ɫ����д��color.xml�ļ��У�����ʹ��Color.
	 * praseColor()��)
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
	 * ����״̬����Ĭ����ɫ
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
	 *            ���ð�͸����״̬
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
	 *            ���ý������
	 */
	protected void setHeadTitle(int id) {
		setHeadTitle(id, false, false, null, 0);
	}

	/**
	 * @param titleStr
	 *            ���ý������
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
	 *            ���ý������
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
	 *            ���ý������
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
	 * ��ʾApp��������ڴ�
	 */
	protected void showHeap() {
		final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		int normalHeap = activityManager.getMemoryClass();
		int largeHeap = activityManager.getLargeMemoryClass();
		System.out.println("APP���䵽���ڴ�(����)��" + normalHeap);
		System.out.println("APP���䵽���ڴ�(���)��" + largeHeap);
		BaseMethod.showToast(ctx, "�ڴ����(����)��" + normalHeap + "M    " + "�ڴ����(���)��" + largeHeap + "M");
	}

	@Override
	protected void onResume() {
		super.onResume();
		setNight(Integer.parseInt(sharedPreferenceUtil.getData(Constant.maskCode)));
		ScreenSwitchLogic.getInstance(this).setScreenOrientation(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenOrientationCode)));
		ScreenSwitchLogic.getInstance(this).setScreenFull(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)));
	}

}
