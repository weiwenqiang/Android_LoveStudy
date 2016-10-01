package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseImageView;

import android.view.View;
import android.widget.TextView;

public class BaseTextImgViewHolder extends BaseViewHolder {

	public TextView mTxt;
	public BaseImageView mImg;

	public BaseTextImgViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		mTxt = (TextView) view.findViewById(R.id.mTxt);
		mImg = (BaseImageView) view.findViewById(R.id.mImg);
	}

}
