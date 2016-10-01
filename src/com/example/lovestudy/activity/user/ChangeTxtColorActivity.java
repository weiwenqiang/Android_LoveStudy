package com.example.lovestudy.activity.user;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class ChangeTxtColorActivity extends BaseActivity {

	private TextView txtContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_change_text_color);
		setHeadTitle(R.string.change_text_color);
		initView();
	}

	private void initView() {
		txtContent = (TextView) findViewById(R.id.txt_content);
		txtContent.setText(Html.fromHtml("<font color='#333333'>" + "篮球有" + "</font>" +"<font color='#ff0000'>" + 15 + "</font>"+"<font color='#333333'>" + "个,每个" + "</font>"+ "</font>"+"<font color='#ff0000'>" + 200 + "</font>" +"</font>"+ "</font>"+"<font color='#333333'>" + "元钱。" + "</font>"));
	}
}
