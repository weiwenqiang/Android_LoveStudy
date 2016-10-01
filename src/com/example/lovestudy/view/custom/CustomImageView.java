package com.example.lovestudy.view.custom;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.base.BaseMethod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomImageView extends LinearLayout {

	private BaseActivity ctx;
	private BaseImageView imageView;
	private TextView textView;

	public CustomImageView(Context ctx) {
		super(ctx);
		this.ctx = (BaseActivity) ctx;
		init();
	}

	private void init() {
		LayoutInflater.from(this.ctx).inflate(R.layout.layout_imageview_item, this, true);
		imageView = (BaseImageView) findViewById(R.id.imageview);
		textView = (TextView) findViewById(R.id.textview);
		imageView.setOnClickListener(this.onClickListener);
	}

	public void setImageUrl(String url) {
		imageView.setImageUrl(url);
	}
	
	public void setText(String text){
		textView.setText(text);
	}

	View.OnClickListener onClickListener = new View.OnClickListener() {
		public void onClick(View paramAnonymousView) {
			BaseMethod.showToast(ctx, "±ªµ„¡À£°");
		}
	};
}
