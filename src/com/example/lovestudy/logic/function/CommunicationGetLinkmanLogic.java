package com.example.lovestudy.logic.function;

import java.util.List;

import com.example.lovestudy.entity.Contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;

public class CommunicationGetLinkmanLogic {

	public static final String[] PHONES_PROJECTION = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER };
	public static final int NAME = 0;
	public static final int NUMBER = 1;

	/**
	 * @param ctx
	 * @param list
	 * @return 获得手机中的联系人
	 */
	public static List<Contacts> getPhoneContacts(Context ctx, List<Contacts> list) {
		ContentResolver resolver = ctx.getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);

		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				Contacts contacts = new Contacts();
				// 得到手机号码
				contacts.number = phoneCursor.getString(NUMBER);
				// 当手机号码为空的或者为空字段 跳过当前循环
				if (TextUtils.isEmpty(contacts.number))
					continue;
				// 得到联系人名称
				contacts.name = phoneCursor.getString(NAME);
				list.add(contacts);
			}
			// 关闭游标资源
			phoneCursor.close();
		}
		return list;
	}

	/**
	 * @param ctx
	 * @param list
	 * @return 得到手机SIM卡联系人人信息
	 */
	public static List<Contacts> getSIMContacts(Context ctx, List<Contacts> list) {
		ContentResolver resolver = ctx.getContentResolver();
		Uri uri = Uri.parse("content://icc/adn");
		Cursor phoneCursor = resolver.query(uri, PHONES_PROJECTION, null, null, null);

		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				Contacts contacts = new Contacts();
				// 得到手机号码
				contacts.number = phoneCursor.getString(NUMBER);
				// 当手机号码为空的或者为空字段 跳过当前循环
				if (TextUtils.isEmpty(contacts.number))
					continue;
				// 得到联系人名称
				contacts.name = phoneCursor.getString(NAME);
				list.add(contacts);
			}
			// 关闭游标资源
			phoneCursor.close();
		}
		return list;
	}
}
