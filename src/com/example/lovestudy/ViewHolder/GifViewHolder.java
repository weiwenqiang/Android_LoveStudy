package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.view.GifView;
import android.view.View;

public class GifViewHolder extends BaseViewHolder {

	public GifView gifView;

	public GifViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		gifView = (GifView) view.findViewById(R.id.gif);
	}

}
