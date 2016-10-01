package com.example.lovestudy.activity.widget;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseViewPageAdapter;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.view.custom.CustomImageView;

public class ViewPagerWidgetActivity extends BaseActivity {

	private ViewPager viewPager;
	private BaseViewPageAdapter adapter;
	private ArrayList<View> views;
	private List<String> urlLists = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_viewpager_widget);
		setHeadTitle(R.string.ViewPager);
		initView();
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		urlLists.add(Urls.GalleryUrl1);
		urlLists.add(Urls.GalleryUrl2);
		urlLists.add(Urls.GalleryUrl3);
		urlLists.add(Urls.GalleryUrl4);
		urlLists.add(Urls.GalleryUrl5);
		urlLists.add(Urls.GalleryUrl6);
		urlLists.add(Urls.GalleryUrl7);
		urlLists.add(Urls.GalleryUrl8);
		urlLists.add(Urls.GalleryUrl9);
		urlLists.add(Urls.GalleryUrl10);
		urlLists.add(Urls.GalleryUrl11);
		urlLists.add(Urls.GalleryUrl12);
		urlLists.add(Urls.GalleryUrl13);
		urlLists.add(Urls.GalleryUrl14);
		urlLists.add(Urls.GalleryUrl15);

		urlLists.add(Urls.ImgCacheUrl1);
		urlLists.add(Urls.ImgCacheUrl2);
		urlLists.add(Urls.ImgCacheUrl3);
		urlLists.add(Urls.ImgCacheUrl4);
		urlLists.add(Urls.ImgCacheUrl5);
		urlLists.add(Urls.ImgCacheUrl6);
		urlLists.add(Urls.ImgCacheUrl7);
		urlLists.add(Urls.ImgCacheUrl8);
		urlLists.add(Urls.ImgCacheUrl9);
		urlLists.add(Urls.ImgCacheUrl10);
		urlLists.add(Urls.ImgCacheUrl11);
		urlLists.add(Urls.ImgCacheUrl12);
		urlLists.add(Urls.ImgCacheUrl13);
		urlLists.add(Urls.ImgCacheUrl14);
		urlLists.add(Urls.ImgCacheUrl15);

		urlLists.add(Urls.ViewPageUrl1);
		urlLists.add(Urls.ViewPageUrl2);
		urlLists.add(Urls.ViewPageUrl3);

		urlLists.add(Urls.AdViewUrl1);
		urlLists.add(Urls.AdViewUrl2);
		urlLists.add(Urls.AdViewUrl3);
		urlLists.add(Urls.AdViewUrl4);
		urlLists.add(Urls.AdViewUrl5);

		urlLists.add(Urls.AsynchLoadHandlerUrl1);
		urlLists.add(Urls.AsynchLoadHandlerUrl2);
		urlLists.add(Urls.AsynchLoadHandlerUrl3);
		urlLists.add(Urls.AsynchLoadHandlerUrl4);
		urlLists.add(Urls.AsynchLoadHandlerUrl5);

		urlLists.add(Urls.AsynchLoadAsynchTaskUrl1);
		urlLists.add(Urls.AsynchLoadAsynchTaskUrl2);
		urlLists.add(Urls.AsynchLoadAsynchTaskUrl3);
		urlLists.add(Urls.AsynchLoadAsynchTaskUrl4);
		urlLists.add(Urls.AsynchLoadAsynchTaskUrl5);

		urlLists.add(Urls.AsynchLoadExecutorServiceUrl1);
		urlLists.add(Urls.AsynchLoadExecutorServiceUrl2);
		urlLists.add(Urls.AsynchLoadExecutorServiceUrl3);
		urlLists.add(Urls.AsynchLoadExecutorServiceUrl4);
		urlLists.add(Urls.AsynchLoadExecutorServiceUrl5);

		views = new ArrayList<View>();
		for (int i = 0; i < urlLists.size(); i++) {
			CustomImageView view = new CustomImageView(this);
			view.setImageUrl(urlLists.get(i));
			view.setText((i + 1) + "/" + urlLists.size());
			views.add(view);
		}
	}

	private void bindView() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		/** 关闭预加载，默认一次只加载一个 */
		viewPager.setOffscreenPageLimit(1);
		adapter = new BaseViewPageAdapter(views);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
}
