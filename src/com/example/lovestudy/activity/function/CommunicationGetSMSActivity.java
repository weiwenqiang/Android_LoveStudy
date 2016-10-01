package com.example.lovestudy.activity.function;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseListView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.utils.SharedPreferenceUtil;

public class CommunicationGetSMSActivity extends BaseActivity {

	private BaseListView listView;
	private List<SmsInfo> list = new ArrayList<SmsInfo>();
	SharedPreferenceUtil sharedPreferenceUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_communication_get_sms);
		setHeadTitle(R.string.communication_get_sms);
		initView();
	}

	private void initView() {
		sharedPreferenceUtil = new SharedPreferenceUtil(ctx);
		listView = (BaseListView) findViewById(R.id.listview);
		listView.setAdapter(new GetSmsAdapter(ctx));
	}

	private class GetSmsAdapter extends BaseAdapter {

		private Context context;

		public GetSmsAdapter(Context context) {
			this.context = context;
			getSms();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View converViews, ViewGroup parent) {

			final ViewHolder viewHolder;

			if (converViews == null) {
				viewHolder = new ViewHolder();
				converViews = LayoutInflater.from(context).inflate(R.layout.item_get_sms, null);
				viewHolder.date = (TextView) converViews.findViewById(R.id.txt_date);
				viewHolder.type = (TextView) converViews.findViewById(R.id.txt_type);
				viewHolder.person = (TextView) converViews.findViewById(R.id.txt_person);
				viewHolder.address = (TextView) converViews.findViewById(R.id.txt_address);
				viewHolder.body = (TextView) converViews.findViewById(R.id.txt_body);
				converViews.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) converViews.getTag();
			}
			if (list.get(position).date != null) {
				viewHolder.date.setVisibility(View.VISIBLE);
				viewHolder.date.setText(list.get(position).date);
			}
			if (list.get(position).type != null) {
				viewHolder.type.setVisibility(View.VISIBLE);
				if ("接收".equals(list.get(position).type))
					viewHolder.type.setTextColor(Color.parseColor("#7C004F15"));
				if ("发送".equals(list.get(position).type))
					viewHolder.type.setTextColor(Color.BLUE);
				viewHolder.type.setText(list.get(position).type);

			}
			if (list.get(position).person != null) {
				viewHolder.person.setVisibility(View.VISIBLE);
				viewHolder.person.setText(list.get(position).person);
			}
			if (list.get(position).address != null) {
				viewHolder.address.setVisibility(View.VISIBLE);
				viewHolder.address.setText(list.get(position).address);
				viewHolder.address.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						BaseMethod.callPhoneUnNow(context, list.get(position).address);
					}
				});
			}
			if (list.get(position).body != null) {
				viewHolder.body.setVisibility(View.VISIBLE);
				viewHolder.body.setText(list.get(position).body);
				viewHolder.body.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View view) {
						if (sharedPreferenceUtil.getData("getSMS").equalsIgnoreCase("0")) {
							viewHolder.body.setMaxLines(Integer.MAX_VALUE);
							sharedPreferenceUtil.setData("getSMS", "1");
						} else if (sharedPreferenceUtil.getData("getSMS").equalsIgnoreCase("1")) {
							viewHolder.body.setMaxLines(2);
							sharedPreferenceUtil.setData("getSMS", "0");
						}
					}
				});
			}
			return converViews;
		}
	}

	private class ViewHolder {
		private TextView date;
		private TextView type;
		private TextView person;
		private TextView address;
		private TextView body;
	}

	/**
	 * @author LVLUFEI 短信信息封装类
	 */
	private class SmsInfo {
		private String person;
		private String address;
		private String body;
		private String date;
		private String type;
	}

	/**
	 * @return 获取短信信息的方法
	 */
	private List<SmsInfo> getSms() {
		final String SMS_URI_ALL = "content://sms/";
		String[] projection = new String[] { "_id", "address", "person", "body", "date", "type" };
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri.parse(SMS_URI_ALL);
		Cursor cursor = contentResolver.query(uri, projection, null, null, "date desc");
		if (cursor != null) {
			while (cursor.moveToNext()) {
				SmsInfo smsInfo = new SmsInfo();
				smsInfo.person = cursor.getString(cursor.getColumnIndex("person"));
				smsInfo.address = cursor.getString(cursor.getColumnIndex("address"));
				smsInfo.body = cursor.getString(cursor.getColumnIndex("body"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date d = new Date(Long.parseLong(cursor.getString(cursor.getColumnIndex("date"))));
				smsInfo.date = dateFormat.format(d);
				int typeId = cursor.getInt(cursor.getColumnIndex("type"));
				if (typeId == 1) {
					smsInfo.type = "接收";
				} else if (typeId == 2) {
					smsInfo.type = "发送";
				}
				list.add(smsInfo);
			}
			cursor.close();
		}
		return list;
	}
}
