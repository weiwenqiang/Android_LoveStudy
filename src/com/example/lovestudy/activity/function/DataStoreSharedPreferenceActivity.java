package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.utils.SharedPreferenceUtil;

public class DataStoreSharedPreferenceActivity extends BaseActivity {
	
	EditText editText;
	Button setData;
	Button getData;
	TextView showData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_data_store_sharedpreference);
		setHeadTitle(R.string.data_store_sharedPreferences);
		initView();
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.edittext);
		setData = (Button) findViewById(R.id.set_data);
		getData = (Button) findViewById(R.id.get_data);
		showData = (TextView) findViewById(R.id.content);
		setData.setOnClickListener(onClickListener);
		getData.setOnClickListener(onClickListener);
	}
	
	private void setStringData(String data) {
		if (data.length() == 0) {
			BaseMethod.showToast(ctx, "«Î ‰»Îƒ⁄»›£°£°£°");
		} else {
			new SharedPreferenceUtil(this).setData(Constant.dataStoreSharedPreferenceCode, data);
			editText.setText("");
		}
	}

	private void getStringData() {
		String data = new SharedPreferenceUtil(this).getData(Constant.dataStoreSharedPreferenceCode);
		if(data.length() == 0){
			showData.setText("----");
		}else{
			showData.setText(data);
		}
	}
	
	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.set_data:
				setStringData(editText.getText().toString());
				break;
			case R.id.get_data:
				getStringData();
				break;
			}
		}
	};

}
