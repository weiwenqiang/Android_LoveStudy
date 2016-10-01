package com.example.lovestudy.activity.function;

import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.logic.function.ImgDragDropLogic;

public class ImgDragDropActivity extends BaseActivity {

	private BaseImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_drag_drop);
		setHeadTitle(R.string.dragDrop_img);
		initView();
	}
	
	private void initView() {
		imageView = (BaseImageView) findViewById(R.id.imageview);
		imageView.setImageUrl(Urls.ImgCacheUrl1);
		imageView.setOnTouchListener(ImgDragDropLogic.getInstance(imageView));
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}

}
