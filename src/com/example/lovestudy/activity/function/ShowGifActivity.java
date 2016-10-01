package com.example.lovestudy.activity.function;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.ShowGifAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.resources.ImageResources;

public class ShowGifActivity extends BaseActivity {

	private BaseListView listView;
	private ShowGifAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_showgif);
		setHeadTitle(R.string.gif_paly);
		initWidget();
	}
	
	private void initWidget() {
		listView = (BaseListView) findViewById(R.id.listView);
		adapter = new ShowGifAdapter(ctx, ImageResources.GifIcon);
		listView.setAdapter(adapter);
	}
}
