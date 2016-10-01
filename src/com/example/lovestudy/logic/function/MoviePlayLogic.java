package com.example.lovestudy.logic.function;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.example.lovestudy.entity.MovieInfo;

public class MoviePlayLogic {

	static MoviePlayLogic moviePlayLogic;

	public static MoviePlayLogic getInstance() {
		if (moviePlayLogic == null) {
			moviePlayLogic = new MoviePlayLogic();
		}
		return moviePlayLogic;
	}

	/**
	 * @param list
	 * @param file
	 *            获取视频文件
	 */
	public void getVideoFile(final LinkedList<MovieInfo> list, File file) {

		file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				String name = file.getName();
				int i = name.indexOf('.');
				if (i != -1) {
					name = name.substring(i);
					if (name.equalsIgnoreCase(".mp4") || name.equalsIgnoreCase(".3gp")) {
						MovieInfo movieInfo = new MovieInfo();
						movieInfo.displayName = file.getName();
						movieInfo.path = file.getAbsolutePath();
						list.add(movieInfo);
						return true;
					}
				} else if (file.isDirectory()) {
					getVideoFile(list, file);
				}
				return false;
			}
		});
	}

	/**
	 * @param time
	 * @return 时间转换
	 */
	public String dateFormat(int time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
		return simpleDateFormat.format(time);
	}
}
