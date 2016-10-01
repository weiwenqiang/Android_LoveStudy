package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.DocumentAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.entity.DocumentBean;

public class SQLiteGrammarActivity extends BaseActivity {

	private BaseListView listView;
	private DocumentAdapter adapter;
	private List<DocumentBean> list = new ArrayList<DocumentBean>();
	private JSONArray jsonArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_sqlite_grammar);
		setHeadTitle(R.string.sqlite_grammar);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.listView);
	}

	private void bindData() {
		try {
			jsonArray = BaseMethod.file2json(ctx, "SQLiteGrammarData.json").getJSONArray("Data");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				DocumentBean documentBean = new DocumentBean(obj);
				list.add(documentBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bindView() {
		adapter = new DocumentAdapter(ctx, list);
		listView.setAdapter(adapter);
	}
}
