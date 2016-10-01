package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.SystemSettingAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.utils.DataCleanManagerUtil;

public class DataCleanManagerActivity extends BaseActivity {

	private BaseListView listView;
	private SystemSettingAdapter adapter;
	private List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_data_clean_manager);
		setHeadTitle(R.string.data_clean_manager);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.listview_data_clean_manager);
	}

	private void bindData() {
		list.add("清除指定数据库");
		list.add("清除所有数据库");
		list.add("清除所有数据");
		list.add("清除外部缓存");
		list.add("清除内部缓存");
		list.add("清除共享参数");
		list.add("清除文件及目录");
	}

	private void bindView() {
		adapter = new SystemSettingAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(onItemClickListener);
	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			switch (position) {
			case 0:
				BaseMethod.showToast(ctx, "已清除MySQLiteDatabase.db数据库");
				DataCleanManagerUtil.cleanDatabaseByName(ctx, "MySQLiteDatabase.db");
				break;
			case 1:
				BaseMethod.showToast(ctx, "已清除所有数据库");
				DataCleanManagerUtil.cleanDatabases(ctx);
				break;
			case 2:
				BaseMethod.showToast(ctx, "已清除所有数据");
				DataCleanManagerUtil.cleanApplicationData(ctx, Environment.getExternalStorageDirectory() + "/爱学习");
				break;
			case 3:
				BaseMethod.showToast(ctx, "已清除外部缓存");
				DataCleanManagerUtil.cleanExternalCache(ctx);
				break;
			case 4:
				BaseMethod.showToast(ctx, "已清除内部缓存");
				DataCleanManagerUtil.cleanInternalCache(ctx);
				break;
			case 5:
				BaseMethod.showToast(ctx, "已清除共享参数");
				DataCleanManagerUtil.cleanSharedPreference(ctx);
				break;
			case 6:
				BaseMethod.showToast(ctx, "已清除文件及目录");
				DataCleanManagerUtil.deleteFolderFile(Environment.getExternalStorageDirectory() + "/爱学习", true);
				break;
			}
		}
	};
}
