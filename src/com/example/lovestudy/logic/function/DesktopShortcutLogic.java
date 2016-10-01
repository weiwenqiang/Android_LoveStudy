package com.example.lovestudy.logic.function;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.utils.AppInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

public class DesktopShortcutLogic {

	public static void addShortcut(Context ctx) {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		/** 1,��ݼ��ı��� */
		String title = AppInfo.getInstance(ctx).getApkName() + ":����";
		/** 2,��ݼ���ͼ�� */
		Parcelable icon = Intent.ShortcutIconResource.fromContext(ctx, R.drawable.icon_jieqi_1);
		/** 3,���������ݼ������������intent */
		Intent shortcutIntent = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

		/** ��ϵͳ������ӿ�ݼ��Ĺ㲥 */
		ctx.sendBroadcast(shortcut);
	}

	public static void delShortcut(Context ctx) {
		Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

		/** 1,��ȡ��ǰӦ������ */
		String title = AppInfo.getInstance(ctx).getApkName() + ":����";
		/** 2,��ݼ���ͼ�� */
		Parcelable icon = Intent.ShortcutIconResource.fromContext(ctx, R.drawable.icon_jieqi_1);
		/** 3,���������ݼ������������intent */
		Intent shortcutIntent = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

		/** ��ϵͳ������ӿ�ݼ��Ĺ㲥 */
		ctx.sendBroadcast(shortcut);
	}
}
