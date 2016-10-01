package com.example.lovestudy.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.BitmapUtil;

public class ImgCircularActivity extends BaseActivity {

	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_img_circular);
		setHeadTitle(R.string.circular_img, false, true, onClickListener, 0);
		initView();
	}

	private void initView() {
		image = (ImageView) findViewById(R.id.image);
		image.setImageBitmap(BitmapUtil.getCircular(ResourcesGet.getBitmap(ctx, R.drawable.app_icon)));
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			BaseMethod.openPhotoAlbum(ctx);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			if (data != null)
				image.setImageBitmap(BitmapUtil.getCircular(BitmapUtil.getBitmapFromPhotoAlbum(ctx, data)));
			break;
		}
	}
}
