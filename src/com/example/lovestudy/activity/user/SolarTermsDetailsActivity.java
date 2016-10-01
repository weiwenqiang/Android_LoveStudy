package com.example.lovestudy.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;

public class SolarTermsDetailsActivity extends BaseActivity {

	private Intent intent;
	private BaseImageView imageView;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_solar_terms_details);
		intent = getIntent();
		setHeadTitle(intent.getStringExtra("title"));
		initView();
	}

	private void initView() {
		imageView = (BaseImageView) findViewById(R.id.img_solar_terms);
		textView = (TextView) findViewById(R.id.txt_introduction);
		imageView.setImageResource(intent.getIntExtra("icon", R.drawable.empty_photo));
		textView.setText(intent.getStringExtra("introduct"));
	}
}
