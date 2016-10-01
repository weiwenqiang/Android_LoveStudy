package com.example.lovestudy.adapter;

import java.util.LinkedList;
import java.util.List;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.function.MovieChooseActivity;
import com.example.lovestudy.entity.MovieInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MovieChooseAdapter extends BaseListAdapter {

	private LinkedList<MovieInfo> list;
	private MovieChooseActivity ctx;

	@SuppressWarnings("unchecked")
	public MovieChooseAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.ctx = (MovieChooseActivity) ctx;
		this.list = (LinkedList<MovieInfo>) list;
	}

	@SuppressLint("InflateParams") @Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		view = LayoutInflater.from(ctx).inflate(R.layout.item_text_layout, null);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(list.get(position).displayName);
		return view;
	}
	
	public OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			Intent intent = new Intent();
			intent.putExtra("path", list.get(position).path);
			ctx.setResult(Activity.RESULT_OK, intent);
			ctx.finish();
		}
	};

}
