package com.example.lovestudy.adapter;

import java.util.List;
import com.example.lovestudy.ViewHolder.ChinaCityViewHolder;
import com.example.lovestudy.activity.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChinaCityAdapterLeft extends BaseListAdapter {

	private List<String> mList;
	int selectItem = 0;

	@SuppressWarnings("unchecked")
	public ChinaCityAdapterLeft(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<String>) list;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final ChinaCityViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_text_layout, null);
			holder = new ChinaCityViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ChinaCityViewHolder) view.getTag();
		}
		holder.txtContent.setText(mList.get(position));
		if (position == selectItem) {
			holder.txtContent.setBackgroundColor(ctx.getResources().getColor(R.color.red));
		} else {
			holder.txtContent.setBackgroundColor(Color.parseColor("#f0f0f0"));
		}
		return view;
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}

}
