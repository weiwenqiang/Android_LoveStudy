package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.MoreAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.entity.MoreBean;

public class MoreActivity extends BaseActivity {

	private BaseListView listView;
	private MoreAdapter adapter;
	private List<MoreBean> list = new ArrayList<MoreBean>();
	private JSONArray jsonArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_more);
		setHeadTitle(R.string.more);
		initView();
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		try {
			jsonArray = BaseMethod.file2json(ctx, "MoreData.json").getJSONArray("Data");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				MoreBean moreBean = new MoreBean(obj);
				list.add(moreBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bindView() {
		listView = (BaseListView) findViewById(R.id.listView);
		adapter = new MoreAdapter(ctx, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}

}
