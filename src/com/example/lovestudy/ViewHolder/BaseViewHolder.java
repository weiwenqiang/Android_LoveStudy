package com.example.lovestudy.ViewHolder;

import android.view.View;

public abstract class BaseViewHolder{

	public BaseViewHolder(View view) {
		initView(view);
	}

	public void initView(View view, int position) {
	}

	abstract void initView(View view);
}
