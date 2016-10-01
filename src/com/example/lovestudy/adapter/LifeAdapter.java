package com.example.lovestudy.adapter;

import java.util.List;

import com.example.lovestudy.ViewHolder.ResearchViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.entity.BaseTextClassBean;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class LifeAdapter extends BaseListAdapter {

	private List<BaseTextClassBean> mList;

	@SuppressWarnings("unchecked")
	public LifeAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<BaseTextClassBean>) list;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final ResearchViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_research_layout, null);
			holder = new ResearchViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (ResearchViewHolder) view.getTag();
		}
		holder.textView.setText(mList.get(position).hint);
		return view;
	}

	public OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			Intent intent = new Intent();
			intent.setClass(ctx, mList.get(position).getActivityName());
			ctx.startActivity(intent);
		}
	};
}
