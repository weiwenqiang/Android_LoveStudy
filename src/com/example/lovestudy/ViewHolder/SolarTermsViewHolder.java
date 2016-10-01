package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseImageView;

import android.view.View;
import android.widget.TextView;

public class SolarTermsViewHolder extends BaseViewHolder {
	
	public BaseImageView imageView;
	public TextView textView;

	public SolarTermsViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		imageView = (BaseImageView) view.findViewById(R.id.img_solar_terms);
		textView = (TextView) view.findViewById(R.id.txt_solar_terms);
	}

}
