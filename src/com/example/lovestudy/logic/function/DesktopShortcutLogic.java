package com.example.lovestudy.logic.function;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.utils.AppInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

public class DesktopShortcutLogic {

	public static void addShortcut(Context ctx) {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		/** 1,快捷键的标题 */
		String title = AppInfo.getInstance(ctx).getApkName() + ":附加";
		/** 2,快捷键的图标 */
		Parcelable icon = Intent.ShortcutIconResource.fromContext(ctx, R.drawable.icon_jieqi_1);
		/** 3,创建点击快捷键启动本程序的intent */
		Intent shortcutIntent = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

		/** 向系统发送添加快捷键的广播 */
		ctx.sendBroadcast(shortcut);
	}

	public static void delShortcut(Context ctx) {
		Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

		/** 1,获取当前应用名称 */
		String title = AppInfo.getInstance(ctx).getApkName() + ":附加";
		/** 2,快捷键的图标 */
		Parcelable icon = Intent.ShortcutIconResource.fromContext(ctx, R.drawable.icon_jieqi_1);
		/** 3,创建点击快捷键启动本程序的intent */
		Intent shortcutIntent = ctx.getPackageManager().getLaunchIntentForPackage(ctx.getPackageName());

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

		/** 向系统发送添加快捷键的广播 */
		ctx.sendBroadcast(shortcut);
	}
}
