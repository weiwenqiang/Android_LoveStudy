package com.example.lovestudy.adapter;

import java.util.List;

import com.example.lovestudy.ViewHolder.SolarTermsViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.user.SolarTermsDetailsActivity;
import com.example.lovestudy.entity.SolarTermsBean;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class SolarTermsAdapter extends BaseListAdapter {

	private List<SolarTermsBean> mList;

	@SuppressWarnings("unchecked")
	public SolarTermsAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<SolarTermsBean>) list;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final SolarTermsViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_solar_terms_layout, null);
			holder = new SolarTermsViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (SolarTermsViewHolder) view.getTag();
		}
		holder.imageView.setImageResource(mList.get(position).getResouceId());
		holder.textView.setText(mList.get(position).getTitle());
		return view;
	}

	public OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
			Intent intent = new Intent();
			intent.putExtra("title", mList.get(position).getTitle());
			intent.putExtra("icon", mList.get(position).getResouceId());
			intent.putExtra("introduct", mList.get(position).getIntroduction());
			intent.setClass(ctx, SolarTermsDetailsActivity.class);
			ctx.startActivity(intent);
		}
	};

}
