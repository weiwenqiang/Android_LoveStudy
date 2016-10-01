package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.function.NotifyActivity;
import com.example.lovestudy.adapter.LifeAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseGridView;
import com.example.lovestudy.entity.BaseTextClassBean;

public class LearnActivity extends BaseActivity {

	private BaseGridView gridView;
	private LifeAdapter adapter;
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_learn);
		setHeadTitle(R.string.learn);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		gridView = (BaseGridView) findViewById(R.id.gallery);
	}

	private void bindData() {
		mapHint.put("0", getResources().getString(R.string.broadcast));
		mapClass.put("0", BroadCastActivity.class);
		
		mapHint.put("1", getResources().getString(R.string.service));
		mapClass.put("1", ServiceActivity.class);
		
		mapHint.put("2", getResources().getString(R.string.notify));
		mapClass.put("2", NotifyActivity.class);
		
		mapHint.put("3", getResources().getString(R.string.process_communication));
		mapClass.put("3", ProcessCommunicationActivity.class);
		
		mapHint.put("4", getResources().getString(R.string.new_process));
		mapClass.put("4", NewProcessActivity.class);
		
		mapHint.put("5", getResources().getString(R.string.change_text_color));
		mapClass.put("5", ChangeTxtColorActivity.class);
		
		mapHint.put("6", getResources().getString(R.string.interface_callback));
		mapClass.put("6", InterfaceCallBackActivity.class);
		
		mapHint.put("7", getResources().getString(R.string.data_clean_manager));
		mapClass.put("7", DataCleanManagerActivity.class);
		
		mapHint.put("8", getResources().getString(R.string.data_store_database));
		mapClass.put("8", DatabaseActivity.class);
		
		mapHint.put("9", getResources().getString(R.string.learn_document));
		mapClass.put("9", LearnDocumentActivity.class);

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
