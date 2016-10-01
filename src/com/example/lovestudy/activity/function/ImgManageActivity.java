package com.example.lovestudy.activity.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.FragmentListAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.entity.BaseTextClassBean;
import com.example.lovestudy.resources.ResourcesGet;

public class ImgManageActivity extends BaseActivity {

	private BaseListView listView;
	private FragmentListAdapter adapter;
	private List<BaseTextClassBean> list = new ArrayList<BaseTextClassBean>();
	Map<String, String> mapHint = new HashMap<String, String>();
	Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_img_manage);
		setHeadTitle(R.string.image_manage);
		initView();
	}
	
	private void initView() {
		bindData();
		bindView();
	}

	private void bindData() {
		mapHint.put("0", ResourcesGet.getString(ctx, R.string.asynchload_img));
		mapClass.put("0", AsynchLoadImgActivity.class);

		mapHint.put("1", ResourcesGet.getString(ctx, R.string.cache_img));
		mapClass.put("1", ImgCacheActivity.class);

		mapHint.put("2", ResourcesGet.getString(ctx, R.string.zoom_img));
		mapClass.put("2", ImgZoomActivity.class);

		mapHint.put("3", ResourcesGet.getString(ctx, R.string.dragDrop_img));
		mapClass.put("3", ImgDragDropActivity.class);

		mapHint.put("4", ResourcesGet.getString(ctx, R.string.circular_img));
		mapClass.put("4", ImgCircularActivity.class);

		mapHint.put("5", ResourcesGet.getString(ctx, R.string.roundCorner_img));
		mapClass.put("5", ImgRoundCornerActivity.class);
		
		mapHint.put("6", ResourcesGet.getString(ctx, R.string.cover_flow_img));
		mapClass.put("6", ImgCoverFlowActivity.class);

		for (int i = 0; i < mapClass.size(); i++) {
			BaseTextClassBean FunctionBean = new BaseTextClassBean();
			FunctionBean.setHint(mapHint.get(String.valueOf(i)));
			FunctionBean.setActivityName(mapClass.get(String.valueOf(i)));
			list.add(FunctionBean);
		}
	}

	private void bindView() {
		listView = (BaseListView) findViewById(R.id.listview_img_manage);
		adapter = new FragmentListAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(adapter.onItemClickListener);
	}

}
