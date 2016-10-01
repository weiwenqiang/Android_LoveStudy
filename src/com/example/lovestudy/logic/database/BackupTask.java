package com.example.lovestudy.logic.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.example.lovestudy.base.BaseMethod;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

public class BackupTask extends AsyncTask<String, Void, Integer> {

	/** 备份命令 */
	private static final String COMMAND_BACKUP = "backupDatabase";
	/** 恢复命令 */
	public static final String COMMAND_RESTORE = "restroeDatabase";
	/** 备份成功 */
	public static final int BACKUP_SUCCEED = 1;
	/** 恢复成功 */
	public static final int RESTORE_SUCCEED = 2;
	/** 备份失败 */
	public static final int BACKUP_FAILURE = -1;
	/** 恢复失败 */
	public static final int RESTORE_FAILURE = -2;

	private Context mContext;

	public BackupTask(Context context) {
		this.mContext = context;
	}

	/**
	 * 后台执行任务
	 */
	@Override
	protected Integer doInBackground(String... params) {
		File dbFile = new File(mContext.getDatabasePath("MySQLiteDatabase.db").getPath());
		File exportDir = new File(Environment.getExternalStorageDirectory(), "/爱学习/数据库");
		if (!exportDir.exists()) {
			exportDir.mkdirs();
		}
		File backup = new File(exportDir, dbFile.getName());
		String command = params[0];
		if (command.equals(COMMAND_BACKUP)) {
			try {
				backup.createNewFile();
				fileCopy(dbFile, backup);
				return BACKUP_SUCCEED;

			} catch (Exception e) {
				e.printStackTrace();
				return BACKUP_FAILURE;
			}

		} else if (command.equals(COMMAND_RESTORE)) {
			try {
				fileCopy(backup, dbFile);
				return RESTORE_SUCCEED;

			} catch (Exception e) {
				e.printStackTrace();
				return RESTORE_FAILURE;
			}
		} else {
			return null;
		}
	}

	/**
	 * 更新UI
	 */
	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		switch (result) {
		case BACKUP_SUCCEED:
			BaseMethod.showToast(mContext, "备份成功");
			break;
		case BACKUP_FAILURE:
			BaseMethod.showToast(mContext, "备份失败");
			break;
		case RESTORE_SUCCEED:
			BaseMethod.showToast(mContext, "恢复成功");
			break;
		case RESTORE_FAILURE:
			BaseMethod.showToast(mContext, "恢复失败");
			break;
		}
	}

	@SuppressWarnings("resource")
	private void fileCopy(File dbFile, File backup) throws IOException {
		FileChannel inChannel = new FileInputStream(dbFile).getChannel();
		FileChannel outChannel = new FileOutputStream(backup).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
		}
	}
}