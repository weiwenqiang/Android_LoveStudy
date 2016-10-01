package com.example.lovestudy.activity.widget;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.GridViewWidgetAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseGridView;
import com.example.lovestudy.constant.Urls;

public class GridViewWidgetActivity extends BaseActivity {

	private BaseGridView gridView;
	private GridViewWidgetAdapter adapter;
	private List<String> arrayList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_gridview_widget);
		setHeadTitle(R.string.GridView);
		initView();
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		arrayList.add(Urls.GalleryUrl1);
		arrayList.add(Urls.GalleryUrl2);
		arrayList.add(Urls.GalleryUrl3);
		arrayList.add(Urls.GalleryUrl4);
		arrayList.add(Urls.GalleryUrl5);
		arrayList.add(Urls.GalleryUrl6);
		arrayList.add(Urls.GalleryUrl7);
		arrayList.add(Urls.GalleryUrl8);
		arrayList.add(Urls.GalleryUrl9);
		arrayList.add(Urls.GalleryUrl10);
	}

	private void bindView() {
		gridView = (BaseGridView) findViewById(R.id.gridview);
		adapter = new GridViewWidgetAdapter(this, arrayList);
		gridView.setAdapter(adapter);
	}

}
