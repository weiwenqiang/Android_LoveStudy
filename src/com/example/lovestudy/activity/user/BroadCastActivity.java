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
import com.example.lovestudy.resources.ResourcesGet;

public class BroadCastActivity extends BaseActivity {

	private BaseListView listView;
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	private FragmentListAdapter adapter;
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_broadcast);
		setHeadTitle(R.string.broadcast);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.list_view);
	}

	private void bindData() {
		mapHint.put("0", ResourcesGet.getString(ctx, R.string.dynamic_broadcast));
		mapClass.put("0", BroadCastDynamicActivity.class);

		mapHint.put("1", ResourcesGet.getString(ctx, R.string.static_broadcast));
		mapClass.put("1", BroadCastStaticActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean viewBean = new BaseTextClassBean();
			viewBean.setHint(mapHint.get(String.valueOf(i)));
			viewBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(viewBean);
		}
	}

	private void bindView() {
		adapter = new FragmentListAdapter(ctx, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}
}
