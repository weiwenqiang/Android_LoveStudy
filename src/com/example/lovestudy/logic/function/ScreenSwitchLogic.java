package com.example.lovestudy.logic.function;

import com.example.lovestudy.base.BaseActivity;

import android.content.pm.ActivityInfo;
import android.view.WindowManager;

public class ScreenSwitchLogic {

	static ScreenSwitchLogic screenSwitchLogic;
	static BaseActivity ctx;

	public static ScreenSwitchLogic getInstance(BaseActivity context) {
		if (screenSwitchLogic == null) {
			screenSwitchLogic = new ScreenSwitchLogic();
		}
		init(context);
		return screenSwitchLogic;
	}

	public static void init(BaseActivity context) {
		ctx = context;
	}

	/**
	 * @param orientation
	 *            ���ú�/����--Ĭ������
	 */
	public void setScreenOrientation(int orientation) {
		if (orientation == 0) {
			ctx.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else if (orientation == 1) {
			ctx.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}

	/**
	 * @param full
	 *            ����ȫ/��ȫ��--Ĭ�Ϸ�ȫ��
	 */
	public void setScreenFull(int full) {
		if (full == 0) {
			ctx.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else if (full == 1) {
			ctx.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}

}
