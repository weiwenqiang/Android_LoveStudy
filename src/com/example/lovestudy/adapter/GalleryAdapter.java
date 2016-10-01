package com.example.lovestudy.adapter;

import java.util.List;
import org.json.JSONArray;

import com.example.lovestudy.base.BaseImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.SpinnerAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView.ScaleType;

@SuppressWarnings("deprecation")
public class GalleryAdapter extends BaseAdapter implements SpinnerAdapter {

	private Context context;
	private List<String> list;

	@SuppressWarnings("unchecked")
	public GalleryAdapter(Context context, List<?> list) {
		this.context = context;
		this.list = (List<String>) list;
	}

	public List<?> getData(JSONArray array) {
		return null;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position % list.size());
	}

	@Override
	public long getItemId(int position) {
		return position % list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseImageView view = new BaseImageView(context);
		view.setImageUrl(list.get(position % list.size()));
		view.setLayoutParams(new Gallery.LayoutParams(android.widget.Gallery.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		view.setScaleType(ScaleType.FIT_XY);
		return view;
	}

}
