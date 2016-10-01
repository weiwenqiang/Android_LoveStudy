package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;

import android.view.View;
import android.widget.TextView;

public class ResearchViewHolder extends BaseViewHolder {

	public TextView textView;

	public ResearchViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		textView = (TextView) view.findViewById(R.id.txt_mask);
	}

}
