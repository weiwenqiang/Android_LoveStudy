package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.SolarTermsAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseGridView;
import com.example.lovestudy.data.entity.ColarTermsData;
import com.example.lovestudy.entity.SolarTermsBean;

public class SolarTermsActivity extends BaseActivity {

	private BaseGridView gridView;
	private SolarTermsAdapter adapter;
	private String[] titles;
	private int[] resouces;
	private String[] introducts;
	private List<SolarTermsBean> beans = new ArrayList<SolarTermsBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_solar_terms);
		setHeadTitle(R.string.solar_terms);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		gridView = (BaseGridView) findViewById(R.id.gridview);
	}

	private void bindData() {
		titles = getResources().getStringArray(R.array.solar_terms);
		resouces = ColarTermsData.getIconResouces();
		introducts = getResources().getStringArray(R.array.solar_terms_introduct);
		for (int i = 0; i < titles.length; i++) {
			SolarTermsBean bean = new SolarTermsBean();
			bean.setTitle(titles[i]);
			bean.setResouceId(resouces[i]);
			bean.setIntroduction(introducts[i]);
			beans.add(bean);
		}
	}

	private void bindView() {
		adapter = new SolarTermsAdapter(ctx, beans);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(adapter.onItemClickListener);
	}
}
