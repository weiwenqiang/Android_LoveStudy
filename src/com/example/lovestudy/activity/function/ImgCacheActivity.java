package com.example.lovestudy.activity.function;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.ImgCacheAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.constant.Urls;

public class ImgCacheActivity extends BaseActivity {
	
	private GridView gridView;
	private ImgCacheAdapter adapter;
	private List<String> list = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_img_cache);
		setHeadTitle(R.string.cache_img);
		bindData();
		bindView();
	}
	
	private void bindData() {
		list.add(Urls.ImgCacheUrl1);
		list.add(Urls.ImgCacheUrl2);
		list.add(Urls.ImgCacheUrl3);
		list.add(Urls.ImgCacheUrl4);
		list.add(Urls.ImgCacheUrl5);
		list.add(Urls.ImgCacheUrl6);
		list.add(Urls.ImgCacheUrl7);
		list.add(Urls.ImgCacheUrl8);
		list.add(Urls.ImgCacheUrl9);
		list.add(Urls.ImgCacheUrl10);
		list.add(Urls.ImgCacheUrl11);
		list.add(Urls.ImgCacheUrl12);
		list.add(Urls.ImgCacheUrl13);
		list.add(Urls.ImgCacheUrl14);
		list.add(Urls.ImgCacheUrl15);
	}

	private void bindView() {
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new ImgCacheAdapter(this, gridView, list);
		gridView.setAdapter(adapter);
	}
	
}
