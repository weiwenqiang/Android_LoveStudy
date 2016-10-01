package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.FragmentListAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.BaseTextClassBean;

public class DatabaseActivity extends BaseActivity {

	private BaseListView listView;
	private FragmentListAdapter adapter;

	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_database);
		setHeadTitle(R.string.data_store_database);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.listView);
	}

	private void bindData() {
		mapHint.put("0", getResources().getString(R.string.database_transaction_optimize));
		mapClass.put("0", DatabaseTransactionOptimizeActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean FunctionBean = new BaseTextClassBean();
			FunctionBean.setHint(mapHint.get(String.valueOf(i)));
			FunctionBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(FunctionBean);
		}
	}

	private void bindView() {
		adapter = new FragmentListAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}
}
