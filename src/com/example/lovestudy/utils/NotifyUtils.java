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
 * 通知工具
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
	 *            系统默认的通知
	 */
	@SuppressWarnings("deprecation")
	public static void DefaultNotify(Context context, String title, String content, Class<?> cls) {
		initNotificationManager(context);
		// 通知活动
		notification = new Notification();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.icon = R.drawable.app_icon;
		// 通知意图
		intent = new Intent(context, cls);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context, title, content, pendingIntent);
		// 发送通知
		notificationManager.notify(1, notification);
	}

	/**
	 * @param context
	 * @param title
	 * @param content
	 * @param photoID
	 * @param cls
	 *            自定义通知
	 */
	@SuppressWarnings("deprecation")
	public static void CustomNotify(Context context, String title, String content, int photoID, Class<?> cls) {
		initNotificationManager(context);
		// 通知的必要数据（没有将不发生通知事件）
		int icon = R.drawable.jiaju;
		CharSequence tickerText = "文字";
		long when = System.currentTimeMillis();

		// 创建通知对象
		notification = new Notification(icon, tickerText, when);

		// 指定内容意图
		Intent intent = new Intent(context, cls);
		pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// 指定个性化视图
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.layout_notify_custom);// 自定义通知栏布局
		contentView.setTextViewText(R.id.notify_title, title);
		contentView.setTextViewText(R.id.notify_content, content);
		contentView.setImageViewResource(R.id.notify_icon, photoID);

		// 设置通知各个参数
		notification.contentView = contentView;// 通知的视图
		notification.flags = Notification.FLAG_AUTO_CANCEL;// 自动消失
		notification.defaults = Notification.DEFAULT_SOUND;// 声音
		notification.contentIntent = pendingIntent;

		// 发送通知
		notificationManager.notify(2, notification);
	}

	/**
	 * @param context
	 * @param title
	 * @param content
	 * @param cls
	 *            大视图通知
	 */
	public static void LargeViewNotify(Context context, String title, String content, Class<?> cls) {
		initNotificationManager(context);
		// 指定内容意图
		Intent intent = new Intent(context, cls);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// 大视图
		NotificationCompat.BigPictureStyle bigStyle = new NotificationCompat.BigPictureStyle();
		bigStyle.setSummaryText("nihao");// 设置在细节区域底端添加一行文本
		bigStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.jiaju));

		Builder builder = new Builder(context);
		builder.setAutoCancel(true); // 设置自动消失
		builder.setContentInfo(String.valueOf(8));// 设置附加内容
		builder.setContentTitle(title);// 设置内容标题
		builder.setContentText(content);// 设置内容文本
		builder.setDefaults(Notification.DEFAULT_ALL);// 设置使用所有默认值
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.jiaju));// 设置大型图标
		builder.setSmallIcon(R.drawable.app_icon);// 设置小型图标
		builder.setTicker("BigPicture Notification");// 设置状态栏提示信息
		builder.setStyle(bigStyle); // 设置通知样式为大型图片样式
		builder.setContentIntent(pendingIntent); // 设置内容意图

		notificationManager.notify(3, builder.build());
	}

	/**
	 * @param context
	 * @param bitmap
	 * @param cls
	 *            大图片通知
	 */
	@SuppressWarnings("deprecation")
	public static void LargeImgNotify(Context context, Bitmap bitmap, Class<?> cls) {
		initNotificationManager(context);
		// 创建一个通知
		notification = new Notification(R.drawable.jiaju, null, System.currentTimeMillis());

		// 指定个性化视图
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.layout_notify_large_photo);// 自定义通知栏布局
		contentView.setImageViewBitmap(R.id.notify_icon, bitmap);

		// 指定内容意图
		Intent intent = new Intent(context, cls);
		pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// 设置通知各个参数
		notification.contentView = contentView;// 视图
		notification.contentIntent = pendingIntent;// 意图
		notification.flags = Notification.FLAG_AUTO_CANCEL;// 自动消失
		notification.defaults = Notification.DEFAULT_SOUND;// 声音

		// 发送通知
		notificationManager.notify(4, notification);
	}

	/**
	 * @param context
	 *            初始化通知管理器
	 */
	@SuppressWarnings("static-access")
	public static void initNotificationManager(Context context) {
		notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
	}

	/**
	 * @param context
	 * @param id
	 *            取消通知栏弹窗
	 */
	public static void cancleNotification(Context context, int id) {
		initNotificationManager(context);
		notificationManager.cancel(id);
	}
}
