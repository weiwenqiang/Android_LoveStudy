package com.example.lovestudy.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.ViewHolder.BaseTextImgViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.entity.TextImgBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseTextImgAdapter extends BaseListAdapter {

	private List<TextImgBean> mList = new ArrayList<TextImgBean>();
	private BaseActivity ctx;

	@SuppressWarnings("unchecked")
	public BaseTextImgAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<TextImgBean>) list;
		this.ctx = (BaseActivity) ctx;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final BaseTextImgViewHolder holder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_text_img_layout, null);
			holder = new BaseTextImgViewHolder(view);
			view.setTag(holder);
		} else {
			holder = (BaseTextImgViewHolder) view.getTag();
		}
		holder.mTxt.setText(mList.get(position).mString);
		holder.mImg.setImageBitmap(mList.get(position).mBitmap);
		return view;
	}
	
}
