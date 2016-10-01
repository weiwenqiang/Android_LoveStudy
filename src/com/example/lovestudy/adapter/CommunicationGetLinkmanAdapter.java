package com.example.lovestudy.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.ViewHolder.CommunicationGetLinkmanViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.entity.Contacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommunicationGetLinkmanAdapter extends BaseListAdapter {

	private List<Contacts> mList = new ArrayList<Contacts>();

	@SuppressWarnings("unchecked")
	public CommunicationGetLinkmanAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<Contacts>) list;
	}

	@SuppressLint("InflateParams") @Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		CommunicationGetLinkmanViewHolder viewHolder;
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_linkman, null);
			viewHolder = new CommunicationGetLinkmanViewHolder(view);
			view.setTag(viewHolder);
		} else {
			viewHolder = (CommunicationGetLinkmanViewHolder) view.getTag();
		}
		viewHolder.contactsName.setText(mList.get(position).name);
		viewHolder.contactsNumber.setText(mList.get(position).number);
		return view;
	}

}
