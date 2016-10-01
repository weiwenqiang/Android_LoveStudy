package com.example.lovestudy.adapter;

import com.example.lovestudy.ViewHolder.GifViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.resources.ImageResources;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

public class ShowGifAdapter extends BaseAdapter {

	Context mContext;
	int[] mData;

	public ShowGifAdapter(Context context, int[] data) {
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		return mData[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View covertView, ViewGroup parent) {
		final GifViewHolder holder;
		if (covertView == null) {
			covertView = LayoutInflater.from(mContext).inflate(R.layout.item_gif, null);
			holder = new GifViewHolder(covertView);
			covertView.setTag(holder);
		} else {
			holder = (GifViewHolder) covertView.getTag();
		}
		holder.gifView.setMovieResource(ImageResources.GifIcon[position]);
		holder.gifView.setClickable(true);
		holder.gifView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (holder.gifView.isPaused()) {
					holder.gifView.setPaused(false);
				} else {
					holder.gifView.setPaused(true);
				}
			}
		});
		return covertView;
	}

}
