package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.LifeAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseGridView;
import com.example.lovestudy.entity.BaseTextClassBean;

public class LifeFoodActivity extends BaseActivity {

	private BaseGridView gridView;
	private LifeAdapter adapter;
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_life_food);
		setHeadTitle(R.string.food);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		gridView = (BaseGridView) findViewById(R.id.gallery);
	}

	private void bindData() {
		mapHint.put("0", getResources().getString(R.string.life_food_one));
		mapClass.put("0", LifeFoodOneActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean textClassBean = new BaseTextClassBean();
			textClassBean.setHint(mapHint.get(String.valueOf(i)));
			textClassBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(textClassBean);
		}
	}

	private void bindView() {
		adapter = new LifeAdapter(ctx, list);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(adapter.onItemClickListener);
	}
}
