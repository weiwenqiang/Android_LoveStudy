package com.example.lovestudy.activity.function;

import java.io.File;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.BitmapUtil;

public class ImgZoomActivity extends BaseActivity {

	private BaseImageView image;
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_img_zoom);
		setHeadTitle(R.string.zoom_img);
		initView();
	}
	
	private void initView() {
		image = (BaseImageView) findViewById(R.id.img_zoom);
		layout = (LinearLayout) findViewById(R.id.layout_zoom);
		layout.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			showDialog();
		}
	};

	/**
	 * œ‘ æ∂‘ª∞øÚ
	 */
	private void showDialog() {
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle(ResourcesGet.getString(this, R.string.set_head_portrait));
		ab.setPositiveButton(ResourcesGet.getString(this, R.string.photo_album), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				BaseMethod.openPhotoAlbum(ImgZoomActivity.this);
			}
		});
		ab.setNegativeButton(ResourcesGet.getString(this, R.string.camera), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				BaseMethod.openCamera(ImgZoomActivity.this,"∞Æ—ßœ∞/Õº∆¨≤√ºÙ.jpg");
			}
		});
		ab.create().show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_CANCELED) {
			switch (requestCode) {
			case 0:
				BaseMethod.cutPicture(ImgZoomActivity.this,data.getData());
				break;
			case 1:
				BaseMethod.cutPicture(ImgZoomActivity.this,Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "∞Æ—ßœ∞/Õº∆¨≤√ºÙ.jpg")));
				break;
			case 2:
				if (data != null)
					image.setImageBitmap(BitmapUtil.getTailorBitmap(data));
				break;
			}
		}
	}
}
