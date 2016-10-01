package com.example.lovestudy.activity.view;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.CheckBoxListViewAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;

import android.os.Bundle;

public class CheckBoxListViewActivity extends BaseActivity {
	
	private BaseListView listView; 
	private List<String> list = new ArrayList<String>();
	private CheckBoxListViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_checkbox_listview);
		setHeadTitle(R.string.view_CheckBoxListView);
		initView();
		bindData();
		bindView();
	}
	
	private void initView() {
		listView = (BaseListView) findViewById(R.id.listview);
	}

	private void bindData() {
		for (int i = 0; i < 20; i++) {
			list.add("测试数据-"+(i+1));
		}
	}

	private void bindView() {
		adapter = new CheckBoxListViewAdapter(this, list);
		listView.setAdapter(adapter);
	}
	
}
