package com.example.lovestudy.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.view.AdviewActivity;
import com.example.lovestudy.activity.view.BrowserActivity;
import com.example.lovestudy.activity.view.CheckBoxListViewActivity;
import com.example.lovestudy.activity.view.CircleProgressBarActivity;
import com.example.lovestudy.activity.view.CustomEditTextActivity;
import com.example.lovestudy.activity.view.CustomRadarScanActivity;
import com.example.lovestudy.activity.view.CustomTextViewActivity;
import com.example.lovestudy.activity.view.DrawBoardActivity;
import com.example.lovestudy.activity.view.LineTextViewActivity;
import com.example.lovestudy.activity.view.LoadingActivity;
import com.example.lovestudy.activity.view.MaskActivity;
import com.example.lovestudy.activity.view.SwitchViewActivity;
import com.example.lovestudy.activity.view.ViewPageCursorActivity;
import com.example.lovestudy.adapter.FragmentListAdapter;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.BaseTextClassBean;
import com.example.lovestudy.resources.ResourcesGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewFragment extends BaseFragment {

	private BaseListView listView;
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	private FragmentListAdapter adapter;
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	View onCreateViewNew(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout_view, container, false);
		setHeadTitle(R.string.view_view);
		initView();
		return view;
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		mapHint.put("0", ResourcesGet.getString(getActivity(), R.string.view_Loading));
		mapClass.put("0", LoadingActivity.class);

		mapHint.put("1", ResourcesGet.getString(getActivity(), R.string.view_Mask));
		mapClass.put("1", MaskActivity.class);

		mapHint.put("2", ResourcesGet.getString(getActivity(), R.string.view_Adview));
		mapClass.put("2", AdviewActivity.class);

		mapHint.put("3", ResourcesGet.getString(getActivity(), R.string.view_Browser));
		mapClass.put("3", BrowserActivity.class);

		mapHint.put("4", ResourcesGet.getString(getActivity(), R.string.view_CheckBoxListView));
		mapClass.put("4", CheckBoxListViewActivity.class);

		mapHint.put("5", ResourcesGet.getString(getActivity(), R.string.view_SwitchView));
		mapClass.put("5", SwitchViewActivity.class);

		mapHint.put("6", ResourcesGet.getString(getActivity(), R.string.view_CircleProgressBar));
		mapClass.put("6", CircleProgressBarActivity.class);

		mapHint.put("7", ResourcesGet.getString(getActivity(), R.string.view_CustomTextView));
		mapClass.put("7", CustomTextViewActivity.class);

		mapHint.put("8", ResourcesGet.getString(getActivity(), R.string.view_CustomEditText));
		mapClass.put("8", CustomEditTextActivity.class);

		mapHint.put("9", ResourcesGet.getString(getActivity(), R.string.view_ViewPageCursor));
		mapClass.put("9", ViewPageCursorActivity.class);

		mapHint.put("10", ResourcesGet.getString(getActivity(), R.string.view_LineTextView));
		mapClass.put("10", LineTextViewActivity.class);
		
		mapHint.put("11", ResourcesGet.getString(getActivity(), R.string.view_custom_radarscan));
	    mapClass.put("11", CustomRadarScanActivity.class);
	    
	    mapHint.put("12", ResourcesGet.getString(getActivity(), R.string.view_custom_drawboard));
	    mapClass.put("12", DrawBoardActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean viewBean = new BaseTextClassBean();
			viewBean.setHint(mapHint.get(String.valueOf(i)));
			viewBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(viewBean);
		}
	}

	private void bindView() {
		listView = (BaseListView) view.findViewById(R.id.list_view);
		adapter = new FragmentListAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}

}
