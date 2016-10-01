package com.example.lovestudy.activity.view;

import java.util.ArrayList;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseViewPageAdapter;
import com.example.lovestudy.base.BaseViewPager;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.view.ViewPageCursorView;
import com.example.lovestudy.view.item.ViewPageCursorItemView;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ViewPageCursorActivity extends BaseActivity {

	private TextView tabOne;
	private TextView tabTwo;
	private TextView tabThree;
	private ViewPageCursorView viewPageCursorView;
	private BaseViewPager viewPager;
	private BaseViewPageAdapter adapter;
	private ArrayList<View> mViewArray;
	private ViewPageCursorItemView viewOne;
	private ViewPageCursorItemView viewTwo;
	private ViewPageCursorItemView viewThree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpage_cursor);
		setHeadTitle(R.string.view_ViewPageCursor);
		initView();
	}
	
	private void initView() {
		tabOne = (TextView) findViewById(R.id.tap_one);
		tabTwo = (TextView) findViewById(R.id.tap_two);
		tabThree = (TextView) findViewById(R.id.tab_three);
		viewPageCursorView = (ViewPageCursorView) findViewById(R.id.viewpage_cursor);
		viewPageCursorView.initView(3, 0);
		viewPager = (BaseViewPager) findViewById(R.id.viewpage);
		mViewArray = new ArrayList<View>();
		
		
		viewOne = new ViewPageCursorItemView(this, Urls.ViewPageUrl1);
		viewTwo = new ViewPageCursorItemView(this, Urls.ViewPageUrl2);
		viewThree = new ViewPageCursorItemView(this, Urls.ViewPageUrl3);
			
		mViewArray.add(viewOne);
		mViewArray.add(viewTwo);
		mViewArray.add(viewThree);
		
		adapter = new BaseViewPageAdapter(mViewArray);
		viewPager.setAdapter(adapter);

		viewPager.setOnPageChangeListener(onPageChangeListener);
		tabOne.setOnClickListener(onClickListener);
		tabTwo.setOnClickListener(onClickListener);
		tabThree.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tap_one:
				setTabSelect(0);
				break;
			case R.id.tap_two:
				setTabSelect(1);
				break;
			case R.id.tab_three:
				setTabSelect(2);
				break;
			default:
				break;
			}
		}
	};
	
	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int index) {
			setTabSelect(index);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	
	private void setTabSelect(int index){
		viewPageCursorView.initView(3, index);
		viewPager.setCurrentItem(index, true);
	}

}
