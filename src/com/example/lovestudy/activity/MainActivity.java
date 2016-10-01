package com.example.lovestudy.activity;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.fragment.FunctionFragment;
import com.example.lovestudy.fragment.UserFragment;
import com.example.lovestudy.fragment.ViewFragment;
import com.example.lovestudy.fragment.WidgetFragment;
import com.example.lovestudy.utils.LogUtil;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private TextView btnView;
	private TextView btnFunction;
	private TextView btnWidget;
	private TextView btnUser;

	private FragmentManager manager;
	private FragmentTransaction transaction;

	private ViewFragment viewFragment;
	private FunctionFragment functionFragment;
	private WidgetFragment widgetFragment;
	private UserFragment userFragment;

	public int MainScreen = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_main);
		manager = getFragmentManager();
		testLog();
		initView();
		DataBind();
	}

	private void initView() {
		btnView = (TextView) findViewById(R.id.main_view);
		btnFunction = (TextView) findViewById(R.id.main_function);
		btnWidget = (TextView) findViewById(R.id.main_widget);
		btnUser = (TextView) findViewById(R.id.main_user);

		btnView.setOnClickListener(onClickListener);
		btnFunction.setOnClickListener(onClickListener);
		btnWidget.setOnClickListener(onClickListener);
		btnUser.setOnClickListener(onClickListener);
	}

	protected void openView() {
		this.transaction = this.manager.beginTransaction();
		this.viewFragment = new ViewFragment();
		this.transaction.replace(R.id.main_top, this.viewFragment);
		this.transaction.commit();
	}

	protected void openFunction() {
		this.transaction = this.manager.beginTransaction();
		this.functionFragment = new FunctionFragment();
		this.transaction.replace(R.id.main_top, this.functionFragment);
		this.transaction.commit();
	}

	protected void openWidget() {
		this.transaction = this.manager.beginTransaction();
		this.widgetFragment = new WidgetFragment();
		this.transaction.replace(R.id.main_top, this.widgetFragment);
		this.transaction.commit();
	}

	protected void openUser() {
		this.transaction = this.manager.beginTransaction();
		this.userFragment = new UserFragment();
		this.transaction.replace(R.id.main_top, this.userFragment);
		this.transaction.commit();
	}

	public void DataBind() {
		clearSelection();
		goTab(MainScreen);
	}

	private void clearSelection() {
		btnView.setEnabled(true);
		btnFunction.setEnabled(true);
		btnWidget.setEnabled(true);
		btnUser.setEnabled(true);
	}

	private void goTab(int index) {
		switch (index) {
		case 0:
			btnView.setEnabled(false);
			btnFunction.setEnabled(true);
			btnWidget.setEnabled(true);
			btnUser.setEnabled(true);
			openView();
			break;
		case 1:
			btnView.setEnabled(true);
			btnFunction.setEnabled(false);
			btnWidget.setEnabled(true);
			btnUser.setEnabled(true);
			openFunction();
			break;
		case 2:
			btnView.setEnabled(true);
			btnFunction.setEnabled(true);
			btnWidget.setEnabled(false);
			btnUser.setEnabled(true);
			openWidget();
			break;
		case 3:
			btnView.setEnabled(true);
			btnFunction.setEnabled(true);
			btnWidget.setEnabled(true);
			btnUser.setEnabled(false);
			openUser();
			break;
		}
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.main_view:
				MainScreen = 0;
				DataBind();
				break;
			case R.id.main_function:
				MainScreen = 1;
				DataBind();
				break;
			case R.id.main_widget:
				MainScreen = 2;
				DataBind();
				break;
			case R.id.main_user:
				MainScreen = 3;
				DataBind();
				break;
			}
		}
	};

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}

	/**
	 * 点击返回键两下退出APP(这里并没有彻底退出)
	 */
	long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				BaseMethod.showToast(ctx, "再按一次退出程序");
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void testLog() {
		LogUtil.d("1111111");
		LogUtil.e("2222222");
		LogUtil.i("3333333");
		LogUtil.v("4444444");
		LogUtil.w("5555555");
		LogUtil.wtf("6666666");
	}
}
