package com.example.lovestudy.activity.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.MySQLiteDatabaseAdapter;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.logic.database.DataBaseLogicTest;

public class DataStoreDatabaseActivity extends BaseActivity {

	private EditText editText;
	private Button add;
	private Button update;
	private Button select;
	private Button backup;//备份
	private Button recover;//恢复
	private ListView listView;

	private Cursor cursor;
	private int id;

	MySQLiteDatabaseAdapter adapter;
	DataBaseLogicTest baseLogicTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_data_store_database);
		setHeadTitle(R.string.data_store_database);
		initView();
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.edittext);
		add = (Button) findViewById(R.id.add);
		update = (Button) findViewById(R.id.update);
		select = (Button) findViewById(R.id.select);
		backup = (Button) findViewById(R.id.backup_database);
		recover = (Button) findViewById(R.id.recover_database);
		listView = (ListView) findViewById(R.id.listview);

		add.setOnClickListener(onClickListener);
		update.setOnClickListener(onClickListener);
		select.setOnClickListener(onClickListener);
		backup.setOnClickListener(onClickListener);
		recover.setOnClickListener(onClickListener);
		listView.setOnItemClickListener(onItemClickListener);
		listView.setOnItemLongClickListener(onItemLongClickListener);

		baseLogicTest = new DataBaseLogicTest(this);
	}

	private void bindList() {
		cursor = baseLogicTest.selectDB();
		if (adapter == null) {
			adapter = new MySQLiteDatabaseAdapter(this);
			adapter.setData(cursor);
			listView.setAdapter(adapter);
		}
		adapter.setData(cursor);
		adapter.notifyDataSetChanged();
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.add:
				baseLogicTest.addDB(editText.getText().toString());
				editText.setText("");
				bindList();
				break;
			case R.id.update:
				baseLogicTest.UpdateDB(id, baseLogicTest.getTime(), editText.getText().toString());
				editText.setText("");
				bindList();
				id = 0;
				break;
			case R.id.select:
				BaseMethod.showToast(ctx, getString(R.string.refresh));
				bindList();
				break;
			case R.id.backup_database:
				baseLogicTest.backupDatabase();
				break;
			case R.id.recover_database:
				baseLogicTest.recoverDatabase();
				break;
			}
		}
	};

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long l) {
			/** 下面这一局必须在getInt()之前调用 */
			cursor.moveToPosition(position);
			id = cursor.getInt(0);
			editText.setText(cursor.getString(3));
		}
	};

	OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long l) {
			/** 下面这一局必须在getInt()之前调用 */
			cursor.moveToPosition(position);
			id = cursor.getInt(0);

			AlertDialog.Builder ab = new AlertDialog.Builder(DataStoreDatabaseActivity.this);
			ab.setTitle(getString(R.string.please));
			ab.setMessage(getString(R.string.delete_hint));
			ab.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					baseLogicTest.DeleteDB(id);
					bindList();
				}
			});
			ab.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {

				}
			});
			ab.create().show();
			return true;
		}
	};

}
