package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;

import android.view.View;
import android.widget.TextView;

public class FragmentViewHolder extends BaseViewHolder {
	
	public TextView textView;

	public FragmentViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		textView = (TextView) view.findViewById(R.id.textView);
	}

}
