package com.example.lovestudy.activity.function;

import java.io.File;
import java.util.LinkedList;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.MovieChooseAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.MovieInfo;
import com.example.lovestudy.logic.function.MoviePlayLogic;
import com.example.lovestudy.view.LoadingDialogView;

public class MovieChooseActivity extends BaseActivity {

	private BaseListView listView;
	private LinkedList<MovieInfo> list = new LinkedList<MovieInfo>();
	private MovieChooseAdapter adapter;

	private LoadingDialogView mLoadingDialogView;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				mLoadingDialogView.dismiss();
				if (list.size() <= 0) {
					listView.setVisibility(View.GONE);
				} else {
					listView.setVisibility(View.VISIBLE);
					adapter = new MovieChooseAdapter(MovieChooseActivity.this, list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(adapter.onItemClickListener);
				}
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_movie_choose);
		setHeadTitle(R.string.movie_paly_list);
		initView();
		bindData();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.listview);
		mLoadingDialogView = new LoadingDialogView(ctx);
		mLoadingDialogView.show();
	}

	private void bindData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				MoviePlayLogic.getInstance().getVideoFile(list, new File(Environment.getExternalStorageDirectory().toString()));
				Message message = new Message();
				message.what = 1;
				mHandler.sendMessage(message);
			}
		}).start();
	}
}
