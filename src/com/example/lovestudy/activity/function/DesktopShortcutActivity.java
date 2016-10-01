package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.logic.function.DesktopShortcutLogic;

public class DesktopShortcutActivity extends BaseActivity {

	private TextView txtAddDesktopShortcut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_desktop_shortcut);
		setHeadTitle(R.string.add_desktop_shortcut);
		initView();
	}

	private void initView() {
		txtAddDesktopShortcut = (TextView) findViewById(R.id.txt_add_desktop_shortcut);
		txtAddDesktopShortcut.setOnClickListener(mOnClickListener);
	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_add_desktop_shortcut:
				DesktopShortcutLogic.addShortcut(ctx);
				break;
			}
		}
	};
}
