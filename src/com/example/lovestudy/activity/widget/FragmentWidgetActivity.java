package com.example.lovestudy.activity.widget;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.fragment.OneFragment;
import com.example.lovestudy.fragment.ThreeFragment;
import com.example.lovestudy.fragment.TwoFragment;

public class FragmentWidgetActivity extends BaseActivity {
	
	private Button one;
	private Button two;
	private Button three;

	private OneFragment oneFragment;
	private TwoFragment twoFragment;
	private ThreeFragment threeFragment;

	private FragmentManager manager;
	private FragmentTransaction transaction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_fragment_widget);
		setHeadTitle(R.string.Fragment);
		initView();
		initListener();
		openOne();
	}
	
	private void initView() {
		one = ((Button) findViewById(R.id.btn_one));
		two = ((Button) findViewById(R.id.btn_two));
		three = ((Button) findViewById(R.id.btn_three));
	}

	private void initListener() {
		one.setOnClickListener(onClickListener);
		two.setOnClickListener(onClickListener);
		three.setOnClickListener(onClickListener);
	}

	private void openOne() {
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		oneFragment = new OneFragment();
		transaction.replace(R.id.content, this.oneFragment);
		transaction.commit();
	}

	private void openTwo() {
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		twoFragment = new TwoFragment();
		transaction.replace(R.id.content, this.twoFragment);
		transaction.commit();
	}

	private void openThree() {
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		threeFragment = new ThreeFragment();
		transaction.replace(R.id.content, this.threeFragment);
		transaction.commit();
	}

	View.OnClickListener onClickListener = new View.OnClickListener() {
		public void onClick(View paramAnonymousView) {
			switch (paramAnonymousView.getId()) {
			case R.id.btn_one:
				openOne();
				break;
			case R.id.btn_two:
				openTwo();
				break;
			case R.id.btn_three:
				openThree();
				break;
			}
		}
	};
}
