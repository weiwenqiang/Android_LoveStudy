package com.example.lovestudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseSQLiteDatabase extends SQLiteOpenHelper {

	/**
	 * 数据库的名称
	 */
	private final static String DATABASE_NAME = "MySQLiteDatabase.db";
	/**
	 * 数据库的版本
	 */
	private final static int DATABASE_VERSION = 1;
	/**
	 * 创建一张表（及结构）
	 */
	String sql_info = "CREATE TABLE " + "info_table" + " (" + 
								"info_id" + " INTEGER primary key autoincrement, " + 
								"info_name" + " text, " + 
								"info_time" + " text, " + 
								"info_content" + " text);";
	String sql_test = "CREATE TABLE test_table(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20))";

	/**
	 * @param context
	 *            构造函数
	 */
	public BaseSQLiteDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * 当创建数据库的时候被调用
	 */
	@Override
	public void onCreate(SQLiteDatabase sdb) {
		sdb.execSQL(sql_info);
		sdb.execSQL(sql_test);
	}

	/**
	 * 当数据库版本升级时被调用
	 */
	@Override
	public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
		
	}
}
