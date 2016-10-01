package com.example.lovestudy.adapter;

import java.util.List;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.logic.function.DynamicSetViewSize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

public class GridViewWidgetAdapter extends BaseListAdapter {

	List<String> list;

	@SuppressWarnings("unchecked")
	public GridViewWidgetAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.list = (List<String>) list;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		view = LayoutInflater.from(ctx).inflate(R.layout.item_image_layout, null);
		BaseImageView imageView = (BaseImageView) view.findViewById(R.id.imageview);
		imageView.setLayoutParams(DynamicSetViewSize.getInstance().getLayoutParamsImageView(imageView, LayoutParams.MATCH_PARENT, 540));
		imageView.setImageUrl(list.get(position));
		return view;
	}

}
