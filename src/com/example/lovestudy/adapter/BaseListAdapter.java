package com.example.lovestudy.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter extends BaseAdapter {
	
	public Context ctx;
	public List<?> list;
	
	public abstract View getViewNew(int position, View view, ViewGroup parent);
	
	public BaseListAdapter(Context ctx) {
		this.ctx = ctx;
	}

	public BaseListAdapter(Context ctx, List<?> list) {
		this.ctx = ctx;
		this.list = list;
	}
	
	public BaseListAdapter(Context ctx, View view, List<?> list) {
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
		return getViewNew(position, view, parent);
	}

}
