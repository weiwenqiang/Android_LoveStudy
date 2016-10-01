package com.example.lovestudy.logic.function;

import com.example.lovestudy.base.BaseActivity;
import android.util.DisplayMetrics;

/**
 * 屏幕大小获取类
 */
public class ScreenSizeLogic {

	static ScreenSizeLogic screenSizeLogic;
	static DisplayMetrics metrics;
	static BaseActivity ctx;
	
	public static ScreenSizeLogic getInstance(BaseActivity context){
		ctx = context;
		if(screenSizeLogic == null){
			screenSizeLogic = new ScreenSizeLogic();
		}
		init();
		return screenSizeLogic;
	}
	
	public static void init(){
		metrics = new DisplayMetrics();
		ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
	}
	
	public int getScreenWidth(){
		return metrics.widthPixels;
	}
	
	public int getScreenHeight(){
		return metrics.heightPixels;
	}
}
