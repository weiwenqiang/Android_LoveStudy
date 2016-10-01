package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.BaseTextImgAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.TextImgBean;
import com.example.lovestudy.utils.ConversionUtil;

public class LifeFoodOneActivity extends BaseActivity {

	private BaseListView listView;
	private BaseTextImgAdapter adapter;
	private List<TextImgBean> list = new ArrayList<TextImgBean>();
	private String[] strings;
	private int[] integers = { R.drawable.icon_life_food_one_1, R.drawable.icon_life_food_one_2, R.drawable.icon_life_food_one_3, R.drawable.icon_life_food_one_4, R.drawable.icon_life_food_one_5 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_life_food_one);
		setHeadTitle(R.string.life_food_one);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.listView);
	}

	private void bindData() {
		strings = getResources().getStringArray(R.array.life_food_one_txt);
		for (int i = 0; i < strings.length; i++) {
			TextImgBean textImgBean = new TextImgBean();
			textImgBean.setmString(strings[i]);
			textImgBean.setmBitmap(ConversionUtil.drawable2Bitmap(getResources().getDrawable(integers[i])));
			list.add(textImgBean);
		}
	}

	private void bindView() {
		adapter = new BaseTextImgAdapter(ctx, list);
		listView.setAdapter(adapter);
	}

}
