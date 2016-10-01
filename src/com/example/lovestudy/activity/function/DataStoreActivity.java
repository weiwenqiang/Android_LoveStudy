package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class DataStoreActivity extends BaseActivity {

	private Button dataStoreSharePreference;
	private Button dataStoreFile;
	private Button dataStoreDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_data_store);
		setHeadTitle(R.string.data_save);
		initView();
	}
	
	private void initView() {
		dataStoreSharePreference = (Button) findViewById(R.id.data_store_sharedPreferences);
		dataStoreFile = (Button) findViewById(R.id.data_store_file);
		dataStoreDatabase = (Button) findViewById(R.id.data_store_database);

		dataStoreSharePreference.setOnClickListener(onClickListener);
		dataStoreFile.setOnClickListener(onClickListener);
		dataStoreDatabase.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.data_store_sharedPreferences:
				gotoTargetActivity(DataStoreSharedPreferenceActivity.class);
				break;
			case R.id.data_store_file:
				gotoTargetActivity(DataStoreFileActivity.class);
				break;
			case R.id.data_store_database:
				gotoTargetActivity(DataStoreDatabaseActivity.class);
				break;
			}
		}
	};

}
