package com.example.lovestudy.adapter;

import java.util.List;
import com.example.lovestudy.ViewHolder.FragmentViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.entity.BaseTextClassBean;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentListAdapter extends BaseListAdapter {
	
	private List<BaseTextClassBean> list;
	
	@SuppressWarnings("unchecked")
	public FragmentListAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.list = (List<BaseTextClassBean>) list;
	}
	
	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final FragmentViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_text_class_layout, null);
			holder = new FragmentViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (FragmentViewHolder) view.getTag();
		}
		holder.textView.setText(list.get(position).hint);
		return view;
	}
	
	public OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			Intent intent = new Intent();
			intent.setClass(ctx, list.get(position).getActivityName());
			ctx.startActivity(intent);
		}
	};

}
