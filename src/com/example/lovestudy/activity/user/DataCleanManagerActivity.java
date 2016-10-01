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
		list.add("���ָ�����ݿ�");
		list.add("����������ݿ�");
		list.add("�����������");
		list.add("����ⲿ����");
		list.add("����ڲ�����");
		list.add("����������");
		list.add("����ļ���Ŀ¼");
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
				BaseMethod.showToast(ctx, "�����MySQLiteDatabase.db���ݿ�");
				DataCleanManagerUtil.cleanDatabaseByName(ctx, "MySQLiteDatabase.db");
				break;
			case 1:
				BaseMethod.showToast(ctx, "������������ݿ�");
				DataCleanManagerUtil.cleanDatabases(ctx);
				break;
			case 2:
				BaseMethod.showToast(ctx, "�������������");
				DataCleanManagerUtil.cleanApplicationData(ctx, Environment.getExternalStorageDirectory() + "/��ѧϰ");
				break;
			case 3:
				BaseMethod.showToast(ctx, "������ⲿ����");
				DataCleanManagerUtil.cleanExternalCache(ctx);
				break;
			case 4:
				BaseMethod.showToast(ctx, "������ڲ�����");
				DataCleanManagerUtil.cleanInternalCache(ctx);
				break;
			case 5:
				BaseMethod.showToast(ctx, "������������");
				DataCleanManagerUtil.cleanSharedPreference(ctx);
				break;
			case 6:
				BaseMethod.showToast(ctx, "������ļ���Ŀ¼");
				DataCleanManagerUtil.deleteFolderFile(Environment.getExternalStorageDirectory() + "/��ѧϰ", true);
				break;
			}
		}
	};
}
