package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.view.LockScreenView;
import com.example.lovestudy.view.LockScreenView.OnSelectedListener;

public class LockScreenActivity extends BaseActivity {

	private LockScreenView lockScreenView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_lock_screen);
		setHeadTitle(R.string.lock_screen);
		initView();
	}

	private void initView() {
		lockScreenView = (LockScreenView) findViewById(R.id.lockScreen);
		lockScreenView.setOnSelectedListener(new OnSelectedListener() {

			@Override
			public void onSelected(View view, int[] values) {
				StringBuffer sb = new StringBuffer();
				for (int val : values) {
					if (sb.length() > 0)
						sb.append(",");
					sb.append(val);
				}
				BaseMethod.showToast(ctx, sb.toString());
			}
		});
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}
}
