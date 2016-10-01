package com.example.lovestudy.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseTextView;
import com.example.lovestudy.dialog.ShareDialog;
import com.example.lovestudy.logic.function.ShareLogic;
import com.example.lovestudy.resources.ResourcesGet;

public class ShareActivity extends BaseActivity {

	BaseTextView txtShare;
	BaseTextView imgShare;
	String imagePath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_share);
		setHeadTitle(R.string.data_share);
		initView();
	}
	
	private void initView() {
		txtShare = (BaseTextView) findViewById(R.id.txt_share);
		imgShare = (BaseTextView) findViewById(R.id.img_share);
		txtShare.setOnClickListener(onClickListener);
		imgShare.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_share:
				ShareDialog.getInstance().showShareTxtDialog(ShareActivity.this);
				break;
			case R.id.img_share:
				ShareDialog.getInstance().showShareImgDialog(ShareActivity.this);
				break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			try {
				imagePath = ShareLogic.getInstance().getImgPath(ShareActivity.this, data);
				ShareLogic.getInstance().shareImg(ShareActivity.this, imagePath, ResourcesGet.getString(ShareActivity.this, R.string.share_img));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
