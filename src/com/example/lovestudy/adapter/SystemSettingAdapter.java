package com.example.lovestudy.adapter;

import java.util.List;

import com.example.lovestudy.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SystemSettingAdapter extends BaseListAdapter {
	
	Context ctx;
	List<String> list;

	@SuppressWarnings("unchecked")
	public SystemSettingAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.ctx = ctx;
		this.list = (List<String>) list;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		view = LayoutInflater.from(ctx).inflate(R.layout.item_text_class_layout, null);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(list.get(position));
		return view;
	}

}
