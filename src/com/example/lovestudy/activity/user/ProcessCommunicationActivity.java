package com.example.lovestudy.activity.user;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class ProcessCommunicationActivity extends BaseActivity {

	private TextView txtProcessCommunication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_process_communication);
		setHeadTitle(R.string.process_communication);
		initView();
	}

	private void initView() {
		txtProcessCommunication = (TextView) findViewById(R.id.txt_process_communication);
		txtProcessCommunication.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.cn.elanmore.DBProject","com.cn.elanmore.DBProject.show.activiity.LoginActivity"));
				startActivity(intent);
			}
		});
	}
}
