package com.example.lovestudy.ViewHolder;

import com.example.lovestudy.activity.R;

import android.view.View;
import android.widget.TextView;

public class CommunicationGetLinkmanViewHolder extends BaseViewHolder {

	public TextView contactsName;
	public TextView contactsNumber;

	public CommunicationGetLinkmanViewHolder(View view) {
		super(view);
	}

	@Override
	void initView(View view) {
		contactsName = (TextView) view.findViewById(R.id.txt_name);
		contactsNumber = (TextView) view.findViewById(R.id.txt_number);
	}

}
