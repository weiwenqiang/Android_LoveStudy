package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.widget.ImageView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.BitmapUtil;

public class ImgRoundCornerActivity extends BaseActivity {

	private ImageView image;
	private int angle = 30;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_img_round_corner);
		setHeadTitle(R.string.circular_img);
		initView();
	}

	private void initView() {
		image = (ImageView) findViewById(R.id.image);
		image.setImageBitmap(BitmapUtil.getRoundCorner(ResourcesGet.getBitmap(ctx, R.drawable.app_icon), angle));
	}

}
