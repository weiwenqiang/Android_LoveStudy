package com.example.lovestudy.activity.function;

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

public class CommunicationActivity extends BaseActivity {
	
	private BaseListView listView;
	private FragmentListAdapter adapter;
	
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_communication);
		setHeadTitle(R.string.communication);
		initView();
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		mapHint.put("0", getResources().getString(R.string.communication_get_linkman));
		mapClass.put("0", CommunicationGetLinkmanActivity.class);
		
		mapHint.put("1", getResources().getString(R.string.communication_get_sms));
		mapClass.put("1", CommunicationGetSMSActivity.class);
		
		mapHint.put("2", getResources().getString(R.string.communication_send_sms));
		mapClass.put("2", CommunicationSendSMSActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean FunctionBean = new BaseTextClassBean();
			FunctionBean.setHint(mapHint.get(String.valueOf(i)));
			FunctionBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(FunctionBean);
		}
	}

	private void bindView() {
		listView = (BaseListView) findViewById(R.id.list_communication);
		adapter = new FragmentListAdapter(ctx, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}
}
