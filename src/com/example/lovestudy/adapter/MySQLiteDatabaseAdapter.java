package com.example.lovestudy.adapter;

import com.example.lovestudy.activity.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MySQLiteDatabaseAdapter extends BaseAdapter {

	private Context context;
	private Cursor cursor;
	private LinearLayout layout;

	public MySQLiteDatabaseAdapter(Context context) {
		this.context = context;
	}
	
	public void setData(Cursor cursor){
		this.cursor = cursor;
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		layout = (LinearLayout) inflater.inflate(R.layout.item_datastore_databaselayout, null);
		TextView list_text = (TextView) layout.findViewById(R.id.list_text);
		TextView list_time = (TextView) layout.findViewById(R.id.list_time);
		cursor.moveToPosition(position);
		String content = cursor.getString(cursor.getColumnIndex("info_content"));
		String time = cursor.getString(cursor.getColumnIndex("info_time"));
		list_text.setText(content);
		list_time.setText(time);
		return layout;
	}
}
