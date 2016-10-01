package com.example.lovestudy.view.item;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;



public class ViewPageCursorItemView extends LinearLayout {

	BaseActivity ctx;
	LayoutInflater inflater;
	View view;

	BaseImageView imageView;

	public ViewPageCursorItemView(Context context, String url) {
		super(context);
		this.ctx = (BaseActivity) context;
		inflater = LayoutInflater.from(ctx);
		initView(url);
	}

	private void initView(String url) {
		view = inflater.inflate(R.layout.item_image_layout, this, true);
		imageView = (BaseImageView) view.findViewById(R.id.imageview);
		imageView.setImageUrl(url);
	}

}
