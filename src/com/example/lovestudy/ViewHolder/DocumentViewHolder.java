package com.example.lovestudy.ViewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseRecyclerViewHolder;

public class DocumentViewHolder extends BaseRecyclerViewHolder {

	public TextView Title;
	public LinearLayout View;

	public DocumentViewHolder(View view) {
		super(view);
		Title = (TextView) view.findViewById(R.id.txt_title);
		View = (LinearLayout) view.findViewById(R.id.view_item);
	}
}
