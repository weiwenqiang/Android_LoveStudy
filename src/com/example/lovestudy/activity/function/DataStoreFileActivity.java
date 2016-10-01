package com.example.lovestudy.activity.function;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.logic.function.DataStoreFileLogic;

public class DataStoreFileActivity extends BaseActivity {
	
	EditText editText;
	Button setString;
	Button getString;
	Button setImage;
	Button getImage;
	TextView textView;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_data_store_file);
		setHeadTitle(R.string.data_store_file);
		initView();
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.edittext);
		setString = (Button) findViewById(R.id.set_string);
		getString = (Button) findViewById(R.id.get_string);
		setImage = (Button) findViewById(R.id.set_image);
		getImage = (Button) findViewById(R.id.get_image);
		textView = (TextView) findViewById(R.id.content);
		imageView = (ImageView) findViewById(R.id.imageview);

		setString.setOnClickListener(onClickListener);
		getString.setOnClickListener(onClickListener);
		setImage.setOnClickListener(onClickListener);
		getImage.setOnClickListener(onClickListener);
	}
	
	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.set_string:
				String data = editText.getText().toString();
				if (data.length() == 0) {
					BaseMethod.showToast(ctx, "请输入要保存的数据！");
				} else {
					DataStoreFileLogic.getInstent(DataStoreFileActivity.this).setString("/爱学习", "testText.txt", editText.getText().toString());
				}
				break;
			case R.id.get_string:
				String stringData = DataStoreFileLogic.getInstent(DataStoreFileActivity.this).getString("/爱学习", "testText.txt");
				if (stringData.length() == 0) {
					BaseMethod.showToast(ctx, "文件中没有文字！");
				} else {
					textView.setText(stringData);
				}
				break;
			case R.id.set_image:
				DataStoreFileLogic.getInstent(DataStoreFileActivity.this).setImage(DataStoreFileLogic.getInstent(DataStoreFileActivity.this).getBitmap(R.drawable.jiaju), "/爱学习","test.jpg");
				break;
			case R.id.get_image:
				Bitmap bitmap = DataStoreFileLogic.getInstent(DataStoreFileActivity.this).getImage("/爱学习/test.jpg");
				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					BaseMethod.showToast(ctx, "文件中没有图片！");
				}
				break;
			}
		}
	};
}
