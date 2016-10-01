package com.example.lovestudy.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcHandle {

	private List<String> mWords = new ArrayList<String>();
	private List<Integer> mTimeList = new ArrayList<Integer>();

	// �������ļ�
	public void readLRC(String path) {
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
			bufferedReader = new BufferedReader(inputStreamReader);
			String s = "";
			while ((s = bufferedReader.readLine()) != null) {
				addTimeToList(s);
				if ((s.indexOf("[ar:") != -1) || (s.indexOf("[ti:") != -1) || (s.indexOf("[by:") != -1)) {
					s = s.substring(s.indexOf(":") + 1, s.indexOf("]"));
				} else {
					String ss = s.substring(s.indexOf("["), s.indexOf("]") + 1);
					s = s.replace(ss, "");
				}
				mWords.add(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			mWords.add("û�и���ļ����Ͻ�ȥ����");
		} catch (IOException e) {
			e.printStackTrace();
			mWords.add("û�ж�ȡ�����");
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void readLRC(InputStream inputStream) {
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStreamReader = new InputStreamReader(inputStream, "GBK");
			bufferedReader = new BufferedReader(inputStreamReader);
			String s = "";
			while ((s = bufferedReader.readLine()) != null) {
				addTimeToList(s);
				if ((s.indexOf("[ar:") != -1) || (s.indexOf("[ti:") != -1) || (s.indexOf("[by:") != -1)) {
					s = s.substring(s.indexOf(":") + 1, s.indexOf("]"));
				} else {
					String ss = s.substring(s.indexOf("["), s.indexOf("]") + 1);
					s = s.replace(ss, "");
				}
				mWords.add(s);
			}

			bufferedReader.close();
			inputStreamReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			mWords.add("û�и���ļ����Ͻ�ȥ����");
		} catch (IOException e) {
			e.printStackTrace();
			mWords.add("û�ж�ȡ�����");
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> getWords() {
		return mWords;
	}

	public List<Integer> getTimes() {
		return mTimeList;
	}

	// �����ʱ��
	private int timeHandler(String string) {
		string = string.replace(".", ":");
		String timeData[] = string.split(":");
		// ������֡��벢ת��Ϊ����
		int minute = Integer.parseInt(timeData[0]);
		int second = Integer.parseInt(timeData[1]);
		int millisecond = Integer.parseInt(timeData[2]);

		// ������һ������һ�е�ʱ��ת��Ϊ������
		int currentTime = (minute * 60 + second) * 1000 + millisecond * 10;

		return currentTime;
	}

	private void addTimeToList(String string) {
		Matcher matcher = Pattern.compile("\\[\\d{1,2}:\\d{1,2}([\\.:]\\d{1,2})?\\]").matcher(string);
		if (matcher.find()) {
			String str = matcher.group();
			mTimeList.add(new LrcHandle().timeHandler(str.substring(1, str.length() - 1)));
		}

	}
}
