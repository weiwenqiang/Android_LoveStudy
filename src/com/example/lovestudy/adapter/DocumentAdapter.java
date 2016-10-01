package com.example.lovestudy.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.ViewHolder.DocumentViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.entity.DocumentBean;
import com.example.lovestudy.entity.DocumentItemBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DocumentAdapter extends BaseListAdapter {

	private List<DocumentBean> mList = new ArrayList<DocumentBean>();
	private BaseActivity ctx;

	@SuppressWarnings("unchecked")
	public DocumentAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<DocumentBean>) list;
		this.ctx = (BaseActivity) ctx;
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final DocumentViewHolder holder;
		view = LayoutInflater.from(ctx).inflate(R.layout.item_document, null);
		holder = new DocumentViewHolder(view);
		DocumentBean documentBean = mList.get(position);
		holder.getView().setTag(documentBean);

		holder.Title.setText(documentBean.Title);

		List<DocumentItemBean> itemBeans = documentBean.itemBeans;
		for (int i = 0; i < itemBeans.size(); i++) {
			View child = LayoutInflater.from(ctx).inflate(R.layout.item_document_child, null);
			holder.View.addView(child);
			TextView Name = (TextView) child.findViewById(R.id.txt_name);
			TextView Description = (TextView) child.findViewById(R.id.txt_description);
			
			if (!"".equals(itemBeans.get(i).Name) && itemBeans.get(i).Name.length() > 0) {
				Name.setText(itemBeans.get(i).Name);
			} else {
				Name.setVisibility(View.GONE);
			}
			if (!"".equals(itemBeans.get(i).Description) && itemBeans.get(i).Description.length() > 0) {
				Description.setText(itemBeans.get(i).Description);
			} else {
				Description.setVisibility(View.GONE);
			}
		}
		return view;

	}
}
