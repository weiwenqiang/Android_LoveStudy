package com.example.lovestudy.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.function.AlarmActivity;
import com.example.lovestudy.activity.function.AnimationActivity;
import com.example.lovestudy.activity.function.BackgroundGradientActivity;
import com.example.lovestudy.activity.function.BrightNessActivity;
import com.example.lovestudy.activity.function.CallbackFunctionActivity;
import com.example.lovestudy.activity.function.ColorPickerActivity;
import com.example.lovestudy.activity.function.CommunicationActivity;
import com.example.lovestudy.activity.function.DataStoreActivity;
import com.example.lovestudy.activity.function.DesktopShortcutActivity;
import com.example.lovestudy.activity.function.FlashLightActivity;
import com.example.lovestudy.activity.function.FontSetActivity;
import com.example.lovestudy.activity.function.ImgManageActivity;
import com.example.lovestudy.activity.function.LockScreenActivity;
import com.example.lovestudy.activity.function.MD5EncryptActivity;
import com.example.lovestudy.activity.function.MoviePlayActivity;
import com.example.lovestudy.activity.function.MusicActivity;
import com.example.lovestudy.activity.function.QrCodeActivity;
import com.example.lovestudy.activity.function.ShareActivity;
import com.example.lovestudy.activity.function.ShowGifActivity;
import com.example.lovestudy.activity.function.SweepActivity;
import com.example.lovestudy.activity.function.SystemSettingActivity;
import com.example.lovestudy.activity.function.VibrationManagerActivity;
import com.example.lovestudy.activity.function.WebJavaInteractionActivity;
import com.example.lovestudy.activity.function.WifiManageActivity;
import com.example.lovestudy.adapter.FragmentListAdapter;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.BaseTextClassBean;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FunctionFragment extends BaseFragment {

	private BaseListView listView;
	private FragmentListAdapter adapter;

	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	View onCreateViewNew(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout_function, container, false);
		setHeadTitle(R.string.function_view);
		initView();
		return view;
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		mapHint.put("0", getResources().getString(R.string.system_set));
		mapClass.put("0", SystemSettingActivity.class);

		mapHint.put("1", getResources().getString(R.string.image_manage));
		mapClass.put("1", ImgManageActivity.class);

		mapHint.put("2", getResources().getString(R.string.font_set));
		mapClass.put("2", FontSetActivity.class);

		mapHint.put("3", getResources().getString(R.string.java_js));
		mapClass.put("3", WebJavaInteractionActivity.class);

		mapHint.put("4", getResources().getString(R.string.wifi_manage));
		mapClass.put("4", WifiManageActivity.class);

		mapHint.put("5", getResources().getString(R.string.data_save));
		mapClass.put("5", DataStoreActivity.class);

		mapHint.put("6", getResources().getString(R.string.data_share));
		mapClass.put("6", ShareActivity.class);

		mapHint.put("7", getResources().getString(R.string.gif_paly));
		mapClass.put("7", ShowGifActivity.class);

		mapHint.put("8", getResources().getString(R.string.music_paly));
		mapClass.put("8", MusicActivity.class);

		mapHint.put("9", getResources().getString(R.string.movie_paly));
		mapClass.put("9", MoviePlayActivity.class);

		mapHint.put("10", getResources().getString(R.string.communication));
		mapClass.put("10", CommunicationActivity.class);

		mapHint.put("11", getResources().getString(R.string.md5));
		mapClass.put("11", MD5EncryptActivity.class);
		
		mapHint.put("12", getResources().getString(R.string.qrcode));
		mapClass.put("12", QrCodeActivity.class);
		
		mapHint.put("13", getResources().getString(R.string.sweep));
		mapClass.put("13", SweepActivity.class);
		
		mapHint.put("14", getResources().getString(R.string.flashlight));
		mapClass.put("14", FlashLightActivity.class);
		
		mapHint.put("15", getResources().getString(R.string.bright_ness));
		mapClass.put("15", BrightNessActivity.class);
		
		mapHint.put("16", getResources().getString(R.string.color_picker));
		mapClass.put("16", ColorPickerActivity.class);
		
		mapHint.put("17", getResources().getString(R.string.lock_screen));
		mapClass.put("17", LockScreenActivity.class);
		
		mapHint.put("18", getResources().getString(R.string.callback_function));
		mapClass.put("18", CallbackFunctionActivity.class);
		
		mapHint.put("19", getResources().getString(R.string.add_desktop_shortcut));
		mapClass.put("19", DesktopShortcutActivity.class);
		
		mapHint.put("20", getResources().getString(R.string.animation));
		mapClass.put("20", AnimationActivity.class);
		
		mapHint.put("21", getResources().getString(R.string.background_color_gradient));
		mapClass.put("21", BackgroundGradientActivity.class);
		
		mapHint.put("22", getResources().getString(R.string.alarm));
		mapClass.put("22", AlarmActivity.class);
		
		mapHint.put("23", getResources().getString(R.string.vibration_manager));
		mapClass.put("23", VibrationManagerActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean FunctionBean = new BaseTextClassBean();
			FunctionBean.setHint(mapHint.get(String.valueOf(i)));
			FunctionBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(FunctionBean);
		}
	}

	private void bindView() {
		listView = (BaseListView) view.findViewById(R.id.list_function);
		adapter = new FragmentListAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}

}
