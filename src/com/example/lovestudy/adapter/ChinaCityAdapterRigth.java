package com.example.lovestudy.adapter;

import java.util.List;

import com.example.lovestudy.ViewHolder.ChinaCityViewHolder;
import com.example.lovestudy.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChinaCityAdapterRigth extends BaseListAdapter {

	private List<String> mList;

	@SuppressWarnings("unchecked")
	public ChinaCityAdapterRigth(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<String>) list;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final ChinaCityViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_text_new_layout, null);
			holder = new ChinaCityViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ChinaCityViewHolder) view.getTag();
		}
		holder.txtContent.setText(mList.get(position));
		return view;
	}

}
