package com.example.lovestudy.logic.database;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.sqlite.BaseSQLiteDatabase;

public class DataBaseLogicTest {

	BaseActivity ctx;
	BaseSQLiteDatabase baseSQLiteDatabase;

	public DataBaseLogicTest(Context ctx) {
		this.ctx = (BaseActivity) ctx;
		baseSQLiteDatabase = new BaseSQLiteDatabase(ctx);
	}

	/**
	 * @param content
	 *            增加数据
	 */
	public void addDB(String content) {
		if (content.length() == 0) {
			BaseMethod.showToast(ctx, ctx.getString(R.string.empty_content_hint));
		} else {
			ContentValues cv = new ContentValues();
			cv.put("info_content", content);
			cv.put("info_time", getTime());
			baseSQLiteDatabase.getWritableDatabase().insert("info_table", null, cv);
		}
	}

	/**
	 * @param id
	 *            通过ID删除数据
	 */
	public void DeleteDB(int id) {
		String where = "info_id = ?";
		String[] whereValue = { Integer.toString(id) };
		baseSQLiteDatabase.getWritableDatabase().delete("info_table", where, whereValue);
	}

	/**
	 * @param id
	 * @param time
	 * @param content
	 *            通过ID修改数据
	 */
	public void UpdateDB(int id, String time, String content) {
		System.out.println("id===" + id);
		if (id == 0) {
			BaseMethod.showToast(ctx, ctx.getString(R.string.please_choose_need_update_item));
			return;
		}
		if (content.length() == 0) {
			BaseMethod.showToast(ctx, ctx.getString(R.string.empty_content_hint));
			return;
		}
		String where = "info_id = ?";
		String[] whereValue = { Integer.toString(id) };
		ContentValues cv = new ContentValues();
		cv.put("info_time", time);
		cv.put("info_content", content);
		baseSQLiteDatabase.getWritableDatabase().update("info_table", cv, where, whereValue);
	}

	/**
	 * @return 查询数据库表中的所有数据
	 */
	public Cursor selectDB() {
		return baseSQLiteDatabase.getReadableDatabase().query("info_table", null, null, null, null, null, null);
	}

	/**
	 * 获取系统当前时间
	 */
	@SuppressLint("SimpleDateFormat")
	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH：mm:ss");
		Date date = new Date();
		String string = format.format(date);
		return string;
	}

	/**
	 * 恢复数据库
	 */
	public void recoverDatabase() {
		new BackupTask(ctx).execute("restroeDatabase");
	}

	/**
	 * 备份数据库
	 */
	public void backupDatabase() {
		new BackupTask(ctx).execute("backupDatabase");
	}
}
