package com.example.lovestudy.module.RightSlideFinish;

import com.example.lovestudy.activity.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class Slidr {

	public static SlidrInterface attach(final Activity activity, final SlidrConfig config) {

		ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
		View oldScreen = decorView.getChildAt(0);
		decorView.removeViewAt(0);

		final SliderPanel panel = new SliderPanel(activity, oldScreen, config);
		panel.setId(R.id.slidable_panel);
		oldScreen.setId(R.id.slidable_content);
		panel.addView(oldScreen);
		decorView.addView(panel, 0);

		panel.setOnPanelSlideListener(new SliderPanel.OnPanelSlideListener() {

			@Override
			public void onStateChanged(int state) {
				if (config.getListener() != null) {
					config.getListener().onSlideStateChanged(state);
				}
			}

			@Override
			public void onClosed() {
				if (config.getListener() != null) {
					config.getListener().onSlideClosed();
				}

				activity.finish();
				activity.overridePendingTransition(0, 0);
			}

			@Override
			public void onOpened() {
				if (config.getListener() != null) {
					config.getListener().onSlideOpened();
				}
			}

			@Override
			public void onSlideChange(int pos) {
				if (config.getListener() != null) {
					config.getListener().onSlideChange(pos);
				}
			}
		});

		SlidrInterface slidrInterface = new SlidrInterface() {
			@Override
			public void lock() {
				panel.lock();
			}

			@Override
			public void unlock() {
				panel.unlock();
			}

			@Override
			public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
				panel.requestDisallowInterceptTouchEvent(disallowIntercept);
			}
		};
		return slidrInterface;
	}

}
