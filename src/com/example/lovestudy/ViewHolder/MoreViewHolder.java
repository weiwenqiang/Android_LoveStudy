package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseRecyclerViewHolder;

import android.view.View;
import android.widget.TextView;

public class MoreViewHolder extends BaseRecyclerViewHolder {
	
	public View layout;
	public TextView Name;
	
	DocumentViewHolder a;

	public MoreViewHolder(View view) {
		super(view);
		layout =  view.findViewById(R.id.layout);
		Name = (TextView) view.findViewById(R.id.Name);
	}

}
