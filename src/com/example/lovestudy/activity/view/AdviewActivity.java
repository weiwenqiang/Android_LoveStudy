package com.example.lovestudy.activity.view;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.view.AdViewPageView;

import android.os.Bundle;

/**
 * 公共循环广告界面
 */
public class AdviewActivity extends BaseActivity {
	
	private AdViewPageView adview;
	private List<String> list = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_adview);
		setHeadTitle(R.string.view_Adview);
		initView();
		bindData();
		bindView();
	}
	
	private void initView() {
		adview = (AdViewPageView) findViewById(R.id.adview);
	}

	private void bindData() {
		list.add(Urls.AdViewUrl1);
		list.add(Urls.AdViewUrl2);
		list.add(Urls.AdViewUrl3);
		list.add(Urls.AdViewUrl4);
		list.add(Urls.AdViewUrl5);
	}
	
	private void bindView() {
		adview.BindData(list);;
	}
	
}
