package com.example.lovestudy.activity.user;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class NewProcessActivity extends BaseActivity {
	
	private TextView txtKillProcess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_new_process);
		setHeadTitle(R.string.new_process);
		initView();
	}

	private void initView() {
		txtKillProcess = (TextView) findViewById(R.id.txt_kill_process);
		txtKillProcess.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
				String packgeName = getPackageName();
				activityManager.killBackgroundProcesses(packgeName);
			}
		});
	}

}
