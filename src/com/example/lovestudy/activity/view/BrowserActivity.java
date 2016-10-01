package com.example.lovestudy.activity.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.view.BrowserView;
import android.os.Bundle;

/**
 * 内嵌浏览器
 */
public class BrowserActivity extends BaseActivity {

	private BrowserView browserView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_browser);
		initView();
		bindView();
	}

	private void initView() {
		browserView = (BrowserView) findViewById(R.id.browser_view);
	}

	private void bindView() {
		browserView.bindData(Urls.BaiDuUrl, true, true);
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}

}
