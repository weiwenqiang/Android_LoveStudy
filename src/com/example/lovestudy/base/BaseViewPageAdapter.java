package com.example.lovestudy.base;

import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class BaseViewPageAdapter extends PagerAdapter{

	public ArrayList<View> mViewArray;

	public BaseViewPageAdapter(ArrayList<View> viewArray) {
		mViewArray = viewArray;
	}


	@Override
	public int getCount() {
		if (mViewArray == null) {
			return 0;
		}
		return mViewArray.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup collection, int position) {
		if (mViewArray == null)
			return null;
		collection.addView(mViewArray.get(position), 0);
		return mViewArray.get(position);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}
	
	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
	}

}
