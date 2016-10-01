package com.example.lovestudy.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.widget.ButtonWidgetActivity;
import com.example.lovestudy.activity.widget.DatePickerWidgetActivity;
import com.example.lovestudy.activity.widget.EditTextWidgetActivity;
import com.example.lovestudy.activity.widget.FragmentWidgetActivity;
import com.example.lovestudy.activity.widget.GalleryWidgetActivity;
import com.example.lovestudy.activity.widget.GridViewWidgetActivity;
import com.example.lovestudy.activity.widget.ListViewWidgetActivity;
import com.example.lovestudy.activity.widget.ProgressWidgetActivity;
import com.example.lovestudy.activity.widget.SpinnerWidgetActivity;
import com.example.lovestudy.activity.widget.TextViewWidgetActivity;
import com.example.lovestudy.activity.widget.TimePickerWidgetActivity;
import com.example.lovestudy.activity.widget.ViewPagerWidgetActivity;
import com.example.lovestudy.adapter.FragmentListAdapter;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.BaseTextClassBean;
import com.example.lovestudy.resources.ResourcesGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WidgetFragment extends BaseFragment {

	private BaseListView listView;
	private FragmentListAdapter adapter;

	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	View onCreateViewNew(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout_widget, container, false);
		setHeadTitle(R.string.widget_view);
		initView();
		return view;
	}

	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		mapHint.put("0", ResourcesGet.getString(getActivity(), R.string.Button));
		mapClass.put("0", ButtonWidgetActivity.class);

		mapHint.put("1", ResourcesGet.getString(getActivity(), R.string.TextView));
		mapClass.put("1", TextViewWidgetActivity.class);

		mapHint.put("2", ResourcesGet.getString(getActivity(), R.string.EditText));
		mapClass.put("2", EditTextWidgetActivity.class);

		mapHint.put("3", ResourcesGet.getString(getActivity(), R.string.Fragment));
		mapClass.put("3", FragmentWidgetActivity.class);

		mapHint.put("4", ResourcesGet.getString(getActivity(), R.string.ViewPager));
		mapClass.put("4", ViewPagerWidgetActivity.class);

		mapHint.put("5", ResourcesGet.getString(getActivity(), R.string.ListView));
		mapClass.put("5", ListViewWidgetActivity.class);

		mapHint.put("6", ResourcesGet.getString(getActivity(), R.string.GridView));
		mapClass.put("6", GridViewWidgetActivity.class);

		mapHint.put("7", ResourcesGet.getString(getActivity(), R.string.Gallery));
		mapClass.put("7", GalleryWidgetActivity.class);
		
		mapHint.put("7", ResourcesGet.getString(getActivity(), R.string.Progress));
		mapClass.put("7", ProgressWidgetActivity.class);
		
		mapHint.put("8", ResourcesGet.getString(getActivity(), R.string.DatePicker));
		mapClass.put("8", DatePickerWidgetActivity.class);
		
		mapHint.put("9", ResourcesGet.getString(getActivity(), R.string.TimePicker));
		mapClass.put("9", TimePickerWidgetActivity.class);
		
		mapHint.put("10", ResourcesGet.getString(getActivity(), R.string.Spinner));
		mapClass.put("10", SpinnerWidgetActivity.class);
		
		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean widgetBean = new BaseTextClassBean();
			widgetBean.setHint(mapHint.get(String.valueOf(i)));
			widgetBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(widgetBean);
		}
	}

	private void bindView() {
		listView = (BaseListView) view.findViewById(R.id.list_widget);
		adapter = new FragmentListAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}
}
