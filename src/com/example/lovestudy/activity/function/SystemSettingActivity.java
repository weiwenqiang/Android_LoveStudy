package com.example.lovestudy.activity.function;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.SystemSettingAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.logic.function.ScreenSizeLogic;
import com.example.lovestudy.logic.function.ScreenSwitchLogic;
import com.example.lovestudy.utils.SDCardUtil;
import com.example.lovestudy.utils.SharedPreferenceUtil;

public class SystemSettingActivity extends BaseActivity {

	private BaseListView listView;
	private SharedPreferenceUtil sharedPreferenceUtil;
	private SystemSettingAdapter adapter;
	private List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_screen_switch);
		setHeadTitle(R.string.system_set);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listView = (BaseListView) findViewById(R.id.listview_system_setting);
		sharedPreferenceUtil = new SharedPreferenceUtil(this);
	}

	private void bindData() {
		list.add(getResources().getString(R.string.horizontal_screen));
		list.add(getResources().getString(R.string.vertical_screen));
		list.add(getResources().getString(R.string.full_screen));
		list.add(getResources().getString(R.string.unfull_screen));
		list.add(getResources().getString(R.string.screen_width));
		list.add(getResources().getString(R.string.unfull_height));
		list.add(getResources().getString(R.string.largeHeap));
		list.add(getResources().getString(R.string.sdcard_total_size));
		list.add(getResources().getString(R.string.sdcard_avail_size));
	}

	private void bindView() {
		adapter = new SystemSettingAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(onItemClickListener);
	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			switch (position) {
			case 0:
				sharedPreferenceUtil.setData(Constant.screenOrientationCode, "1");
				ScreenSwitchLogic.getInstance(SystemSettingActivity.this).setScreenOrientation(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenOrientationCode)));
				break;
			case 1:
				sharedPreferenceUtil.setData(Constant.screenOrientationCode, "0");
				ScreenSwitchLogic.getInstance(SystemSettingActivity.this).setScreenOrientation(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenOrientationCode)));
				break;
			case 2:
				sharedPreferenceUtil.setData(Constant.screenFullCode, "1");
				ScreenSwitchLogic.getInstance(SystemSettingActivity.this).setScreenFull(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)));
				break;
			case 3:
				sharedPreferenceUtil.setData(Constant.screenFullCode, "0");
				ScreenSwitchLogic.getInstance(SystemSettingActivity.this).setScreenFull(Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)));
				break;
			case 4:
				int ScreenWidth = ScreenSizeLogic.getInstance(SystemSettingActivity.this).getScreenWidth();
				BaseMethod.showToast(ctx, "ÆÁÄ»¿í¶È£º" + ScreenWidth);
				break;
			case 5:
				int ScreenHeight = ScreenSizeLogic.getInstance(SystemSettingActivity.this).getScreenHeight();
				BaseMethod.showToast(ctx, "ÆÁÄ»¸ß¶È£º" + ScreenHeight);
				break;
			case 6:
				showHeap();
				break;
			case 7:
				Toast.makeText(ctx, SDCardUtil.getInstance().getTotalSize(ctx), Toast.LENGTH_SHORT).show();
				break;
			case 8:
				Toast.makeText(ctx, SDCardUtil.getInstance().getAvailSize(ctx), Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

}
