package com.example.lovestudy.activity.user;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.sqlite.BaseSQLiteDatabase;

/**
 * @author Administrator 数据库事务优化
 * 
 */
public class DatabaseTransactionOptimizeActivity extends BaseActivity {

	protected static final int SUCCESS_INSERT_TO_DB_ONE = 1;
	protected static final int SUCCESS_INSERT_TO_DB_TWO = 2;
	private TextView txt1;
	private TextView txt2;
	private Button btn1;
	private Button btn2;

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SUCCESS_INSERT_TO_DB_ONE:
				btn1.setEnabled(true);
				Integer usetime_one = (Integer) msg.obj;
				txt1.setText("插入10000条数据耗时：" + usetime_one / 1000 + "秒");
				break;
			case SUCCESS_INSERT_TO_DB_TWO:
				btn2.setEnabled(true);
				Integer usetime_two = (Integer) msg.obj;
				txt2.setText("插入10000条数据耗时：" + usetime_two / 1000 + "秒");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_database_transaction_optimize);
		setHeadTitle(R.string.database_transaction_optimize);
		initView();
	}

	private void initView() {
		txt1 = (TextView) findViewById(R.id.txt_one);
		txt2 = (TextView) findViewById(R.id.txt_two);
		btn1 = (Button) findViewById(R.id.btn_one);
		btn2 = (Button) findViewById(R.id.btn_two);
		btn1.setOnClickListener(mOnClickListener);
		btn2.setOnClickListener(mOnClickListener);
	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.btn_one:
				btn1.setEnabled(false);
				insert1();
				break;
			case R.id.btn_two:
				btn2.setEnabled(false);
				insert2();
				break;
			}
		}
	};

	/**
	 * 普通方式插入数据库 10000 条数据
	 */
	private void insert1() {
		BaseSQLiteDatabase openHelper = new BaseSQLiteDatabase(this);
		final SQLiteDatabase database = openHelper.getWritableDatabase();
		if (database.isOpen()) {
			new Thread() {
				public void run() {
					long start = System.currentTimeMillis();
					for (int i = 0; i < 10000; i++) {
						ContentValues values = new ContentValues();
						values.put("name", "tom:" + i);
						database.insert("test_table", "_id", values);
					}
					database.close();
					long end = System.currentTimeMillis();
					int usetime_one = (int) (end - start);
					Message message = new Message();
					message.what = SUCCESS_INSERT_TO_DB_ONE;
					message.obj = usetime_one;
					handler.sendMessage(message);
				};
			}.start();
		}
	}

	/**
	 * 开启事务插入数据库 10000 条数据
	 */
	private void insert2() {
		BaseSQLiteDatabase openHelper = new BaseSQLiteDatabase(this);
		final SQLiteDatabase database = openHelper.getWritableDatabase();
		if (database.isOpen()) {
			new Thread() {
				public void run() {
					long start = System.currentTimeMillis();
					database.beginTransaction();
					for (int i = 0; i < 10000; i++) {
						ContentValues values = new ContentValues();
						values.put("name", "tom:" + i);
						database.insert("test_table", "_id", values);
					}
					database.setTransactionSuccessful();
					database.endTransaction();
					database.close();
					long end = System.currentTimeMillis();
					int usetime_two = (int) (end - start);
					Message message = new Message();
					message.what = SUCCESS_INSERT_TO_DB_TWO;
					message.obj = usetime_two;
					handler.sendMessage(message);
				};
			}.start();
		}
	}
}
