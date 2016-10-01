package com.example.lovestudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseSQLiteDatabase extends SQLiteOpenHelper {

	/**
	 * ���ݿ������
	 */
	private final static String DATABASE_NAME = "MySQLiteDatabase.db";
	/**
	 * ���ݿ�İ汾
	 */
	private final static int DATABASE_VERSION = 1;
	/**
	 * ����һ�ű����ṹ��
	 */
	String sql_info = "CREATE TABLE " + "info_table" + " (" + 
								"info_id" + " INTEGER primary key autoincrement, " + 
								"info_name" + " text, " + 
								"info_time" + " text, " + 
								"info_content" + " text);";
	String sql_test = "CREATE TABLE test_table(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20))";

	/**
	 * @param context
	 *            ���캯��
	 */
	public BaseSQLiteDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * ���������ݿ��ʱ�򱻵���
	 */
	@Override
	public void onCreate(SQLiteDatabase sdb) {
		sdb.execSQL(sql_info);
		sdb.execSQL(sql_test);
	}

	/**
	 * �����ݿ�汾����ʱ������
	 */
	@Override
	public void onUpgrade(SQLiteDatabase sdb, int oldVersion, int newVersion) {
		
	}
}
