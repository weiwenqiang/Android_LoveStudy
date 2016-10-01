package com.example.lovestudy.adapter;

import java.util.List;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.view.CheckBoxListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CheckBoxListViewAdapter extends BaseAdapter {

	private Context ctx;
	private List<String> list;

	public CheckBoxListViewAdapter(Context ctx, List<String> list) {
		this.ctx = ctx;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		view = LayoutInflater.from(ctx).inflate(R.layout.item_checkbox_listview_layout, null);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(list.get(position));
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				CheckBoxListView checkBox = (CheckBoxListView) view.findViewById(R.id.checkbox);
				checkBox.setCheckAnimation(!checkBox.isCheck());
			}
		});
		return view;
	}

}
