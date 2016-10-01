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
	 * @return ����ֻ��е���ϵ��
	 */
	public static List<Contacts> getPhoneContacts(Context ctx, List<Contacts> list) {
		ContentResolver resolver = ctx.getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);

		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				Contacts contacts = new Contacts();
				// �õ��ֻ�����
				contacts.number = phoneCursor.getString(NUMBER);
				// ���ֻ�����Ϊ�յĻ���Ϊ���ֶ� ������ǰѭ��
				if (TextUtils.isEmpty(contacts.number))
					continue;
				// �õ���ϵ������
				contacts.name = phoneCursor.getString(NAME);
				list.add(contacts);
			}
			// �ر��α���Դ
			phoneCursor.close();
		}
		return list;
	}

	/**
	 * @param ctx
	 * @param list
	 * @return �õ��ֻ�SIM����ϵ������Ϣ
	 */
	public static List<Contacts> getSIMContacts(Context ctx, List<Contacts> list) {
		ContentResolver resolver = ctx.getContentResolver();
		Uri uri = Uri.parse("content://icc/adn");
		Cursor phoneCursor = resolver.query(uri, PHONES_PROJECTION, null, null, null);

		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				Contacts contacts = new Contacts();
				// �õ��ֻ�����
				contacts.number = phoneCursor.getString(NUMBER);
				// ���ֻ�����Ϊ�յĻ���Ϊ���ֶ� ������ǰѭ��
				if (TextUtils.isEmpty(contacts.number))
					continue;
				// �õ���ϵ������
				contacts.name = phoneCursor.getString(NAME);
				list.add(contacts);
			}
			// �ر��α���Դ
			phoneCursor.close();
		}
		return list;
	}
}
