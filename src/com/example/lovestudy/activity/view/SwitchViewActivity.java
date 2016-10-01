package com.example.lovestudy.activity.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.view.SwitchView;
import com.example.lovestudy.view.SwitchView.OnSwitchChangedListener;

import android.os.Bundle;
import android.view.View;

public class SwitchViewActivity extends BaseActivity {

	private SwitchView switchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_switch_view);
		setHeadTitle(R.string.view_SwitchView);
		initView();
	}

	private void initView() {
		switchView = (SwitchView) findViewById(R.id.switchview);
		switchView.setOnSwitchChangedListener(new OnSwitchChangedListener() {

			@Override
			public void onSwitchChanged(View view, int value) {
				StringBuffer msg = new StringBuffer(getString(R.string.now_state));
				if (value == SwitchView.OFF) {
					msg.append(getString(R.string.off));
				} else {
					msg.append(getString(R.string.on));
				}
				BaseMethod.showToast(ctx, msg.toString());
			}
		});
	}
}
