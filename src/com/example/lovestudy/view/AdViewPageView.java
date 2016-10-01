package com.example.lovestudy.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.GalleryAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressWarnings("deprecation")
public class AdViewPageView extends LinearLayout {

	BaseActivity ctx;
	LayoutInflater mLayoutInflater;

	Gallery gallery;
	PointView point;

	List<String> Urls = new ArrayList<String>();

	private Timer timer = new Timer();
	private TimerTask task = new TimerTask() {

		@Override
		public void run() {
			handler.sendEmptyMessage(1);
		}
	};
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			int code = msg.what;
			switch (code) {
			case 1:
				gallery.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
				break;
			}
		}
	};

	public AdViewPageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		ctx = (BaseActivity) context;
		init();
	}

	public AdViewPageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = (BaseActivity) context;
		init();
	}

	public AdViewPageView(Context context) {
		super(context);
		ctx = (BaseActivity) context;
		init();
	}

	private void init() {
		setOrientation(VERTICAL);
		mLayoutInflater = LayoutInflater.from(ctx);
		mLayoutInflater.inflate(R.layout.layout_view_adviewpage_view, this, true);
		gallery = (Gallery) findViewById(R.id.gallery);
		point = (PointView) findViewById(R.id.point);
		setVisibility(View.GONE);
	}

	/**
	 * 外面调用绑定
	 */
	public void BindData(List<String> Urls) {
		if (Urls != null && Urls.size() > 0) {
			setVisibility(View.VISIBLE);
		} else {
			setVisibility(View.GONE);
		}
		this.Urls = Urls;
		initGallery();
		initPoint();
	}

	/**
	 * 初始化gallery
	 */
	private void initGallery() {
		gallery.setAdapter(new GalleryAdapter(ctx, Urls));
		gallery.setOnItemSelectedListener(onItemSelectedListener);
		gallery.setOnItemClickListener(onItemClickListener);
		timer.schedule(task, 5000, 5000);
	}

	/**
	 * 初始化红点
	 */
	private void initPoint() {
		point.setPosition(0);
		point.init(0, Urls.size());
		point.setColor(Color.parseColor("#ff0000"), Color.WHITE);
		point.setRadiu(10, 8);
	}

	OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
			point.setPosition(position % Urls.size());
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

		}
	};

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int positon, long l) {
			BaseMethod.showToast(ctx, (positon + 1) + ":被点击！");
		}
	};

}
