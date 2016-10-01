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

public class LifeActivity extends BaseActivity {
	
	private BaseGridView gridView;
	private LifeAdapter adapter;
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_life);
		setHeadTitle(R.string.life);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		gridView = (BaseGridView) findViewById(R.id.gallery);
	}

	private void bindData() {
		mapHint.put("0", getResources().getString(R.string.clothing));
		mapClass.put("0", LifeClothActivity.class);

		mapHint.put("1", getResources().getString(R.string.food));
		mapClass.put("1", LifeFoodActivity.class);

		mapHint.put("2", getResources().getString(R.string.live));
		mapClass.put("2", LifeLiveActivity.class);
		
		mapHint.put("3", getResources().getString(R.string.traffic));
		mapClass.put("3", LifeTrafficActivity.class);
		
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
