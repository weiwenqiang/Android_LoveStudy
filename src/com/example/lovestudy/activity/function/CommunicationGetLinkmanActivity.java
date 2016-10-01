package com.example.lovestudy.activity.function;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.CommunicationGetLinkmanAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.entity.Contacts;
import com.example.lovestudy.logic.function.CommunicationGetLinkmanLogic;

public class CommunicationGetLinkmanActivity extends BaseActivity {
	
	private List<Contacts> list = new ArrayList<Contacts>();
	private BaseListView listView;
	private CommunicationGetLinkmanAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_communication_get_linkmen);
		setHeadTitle(R.string.communication_get_linkman);
		initView();
	}

	private void initView() {
		bindData();
		bindView();
	}
	
	private void bindData() {
		CommunicationGetLinkmanLogic.getPhoneContacts(ctx, list);
		CommunicationGetLinkmanLogic.getSIMContacts(ctx, list);
	}

	private void bindView() {
		listView = (BaseListView) findViewById(R.id.list_communication_get_linkman);
		adapter = new CommunicationGetLinkmanAdapter(ctx, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
				BaseMethod.callPhoneUnNow(ctx, list.get(position).number);
			}
		});
	}
}
