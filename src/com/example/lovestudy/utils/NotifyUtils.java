package com.example.lovestudy.utils;

import com.example.lovestudy.activity.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;

/*
 * ֪ͨ����
 */
public class NotifyUtils {

	public static NotificationManager notificationManager;
	public static Notification notification;
	public static Intent intent;
	public static PendingIntent pendingIntent;

	/**
	 * @param context
	 * @param title
	 * @param content
	 * @param cls
	 *            ϵͳĬ�ϵ�֪ͨ
	 */
	@SuppressWarnings("deprecation")
	public static void DefaultNotify(Context context, String title, String content, Class<?> cls) {
		initNotificationManager(context);
		// ֪ͨ�
		notification = new Notification();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.icon = R.drawable.app_icon;
		// ֪ͨ��ͼ
		intent = new Intent(context, cls);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context, title, content, pendingIntent);
		// ����֪ͨ
		notificationManager.notify(1, notification);
	}

	/**
	 * @param context
	 * @param title
	 * @param content
	 * @param photoID
	 * @param cls
	 *            �Զ���֪ͨ
	 */
	@SuppressWarnings("deprecation")
	public static void CustomNotify(Context context, String title, String content, int photoID, Class<?> cls) {
		initNotificationManager(context);
		// ֪ͨ�ı�Ҫ���ݣ�û�н�������֪ͨ�¼���
		int icon = R.drawable.jiaju;
		CharSequence tickerText = "����";
		long when = System.currentTimeMillis();

		// ����֪ͨ����
		notification = new Notification(icon, tickerText, when);

		// ָ��������ͼ
		Intent intent = new Intent(context, cls);
		pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// ָ�����Ի���ͼ
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.layout_notify_custom);// �Զ���֪ͨ������
		contentView.setTextViewText(R.id.notify_title, title);
		contentView.setTextViewText(R.id.notify_content, content);
		contentView.setImageViewResource(R.id.notify_icon, photoID);

		// ����֪ͨ��������
		notification.contentView = contentView;// ֪ͨ����ͼ
		notification.flags = Notification.FLAG_AUTO_CANCEL;// �Զ���ʧ
		notification.defaults = Notification.DEFAULT_SOUND;// ����
		notification.contentIntent = pendingIntent;

		// ����֪ͨ
		notificationManager.notify(2, notification);
	}

	/**
	 * @param context
	 * @param title
	 * @param content
	 * @param cls
	 *            ����ͼ֪ͨ
	 */
	public static void LargeViewNotify(Context context, String title, String content, Class<?> cls) {
		initNotificationManager(context);
		// ָ��������ͼ
		Intent intent = new Intent(context, cls);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// ����ͼ
		NotificationCompat.BigPictureStyle bigStyle = new NotificationCompat.BigPictureStyle();
		bigStyle.setSummaryText("nihao");// ������ϸ������׶����һ���ı�
		bigStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.jiaju));

		Builder builder = new Builder(context);
		builder.setAutoCancel(true); // �����Զ���ʧ
		builder.setContentInfo(String.valueOf(8));// ���ø�������
		builder.setContentTitle(title);// �������ݱ���
		builder.setContentText(content);// ���������ı�
		builder.setDefaults(Notification.DEFAULT_ALL);// ����ʹ������Ĭ��ֵ
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.jiaju));// ���ô���ͼ��
		builder.setSmallIcon(R.drawable.app_icon);// ����С��ͼ��
		builder.setTicker("BigPicture Notification");// ����״̬����ʾ��Ϣ
		builder.setStyle(bigStyle); // ����֪ͨ��ʽΪ����ͼƬ��ʽ
		builder.setContentIntent(pendingIntent); // ����������ͼ

		notificationManager.notify(3, builder.build());
	}

	/**
	 * @param context
	 * @param bitmap
	 * @param cls
	 *            ��ͼƬ֪ͨ
	 */
	@SuppressWarnings("deprecation")
	public static void LargeImgNotify(Context context, Bitmap bitmap, Class<?> cls) {
		initNotificationManager(context);
		// ����һ��֪ͨ
		notification = new Notification(R.drawable.jiaju, null, System.currentTimeMillis());

		// ָ�����Ի���ͼ
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.layout_notify_large_photo);// �Զ���֪ͨ������
		contentView.setImageViewBitmap(R.id.notify_icon, bitmap);

		// ָ��������ͼ
		Intent intent = new Intent(context, cls);
		pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// ����֪ͨ��������
		notification.contentView = contentView;// ��ͼ
		notification.contentIntent = pendingIntent;// ��ͼ
		notification.flags = Notification.FLAG_AUTO_CANCEL;// �Զ���ʧ
		notification.defaults = Notification.DEFAULT_SOUND;// ����

		// ����֪ͨ
		notificationManager.notify(4, notification);
	}

	/**
	 * @param context
	 *            ��ʼ��֪ͨ������
	 */
	@SuppressWarnings("static-access")
	public static void initNotificationManager(Context context) {
		notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
	}

	/**
	 * @param context
	 * @param id
	 *            ȡ��֪ͨ������
	 */
	public static void cancleNotification(Context context, int id) {
		initNotificationManager(context);
		notificationManager.cancel(id);
	}
}
