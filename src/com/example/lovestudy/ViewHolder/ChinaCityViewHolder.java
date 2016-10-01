package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;

import android.view.View;
import android.widget.TextView;

public class ChinaCityViewHolder extends BaseViewHolder {

	public TextView txtContent;

	public ChinaCityViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		txtContent = (TextView) view.findViewById(R.id.textview);
	}

}
