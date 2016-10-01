package com.example.lovestudy.activity.view;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.DrawBoardView;

public class DrawBoardActivity extends BaseActivity {

	private Button size;
	private Button color;

	private Button clear;
	private DrawBoardView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_draw_board);
		setHeadTitle(R.string.view_custom_drawboard);
		initStyle();
		initDrawBoard();
		initButton();
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}

	private void initStyle() {
		size = (Button) findViewById(R.id.size);
		color = (Button) findViewById(R.id.color);
		size.setOnClickListener(new OnClickListener() {

			final String[] item = { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100" };
			ArrayList<Integer> list = new ArrayList<Integer>();

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder ab = new AlertDialog.Builder(DrawBoardActivity.this);
				ab.setTitle("���ʴ�Сѡ��");
				list.clear();
				ab.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						list.add(which);
					}
				});

				ab.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						String str = "";
						int size = list.size();
						for (int i = 0; i < size; i++) {
							str = item[list.get(i)];
						}
						if (str == null || str.equals("")) {
							str = "5";
						}
						view.zise = Integer.parseInt(str);
						Toast.makeText(DrawBoardActivity.this, "��ѡ��:" + Integer.parseInt(str) + " ��С�Ļ���", Toast.LENGTH_SHORT).show();
					}
				});

				ab.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
				ab.show();
			}
		});
		color.setOnClickListener(new OnClickListener() {

			final String[] item = { "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ", "�㽶ɫ", "��ɫ", "���ܲ�ɫ", "�ۻ�ɫ", "ʯ����ɫ", "����ɫ" };
			ArrayList<Integer> list = new ArrayList<Integer>();

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder ab = new AlertDialog.Builder(DrawBoardActivity.this);
				ab.setTitle("������ɫѡ��");
				list.clear();
				ab.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						list.add(which);
					}
				});

				ab.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						String str = "";
						int size = list.size();
						for (int i = 0; i < size; i++) {
							str = item[list.get(i)];
						}
						if (str == null || str.equals("")) {
							str = "��ɫ";
						}
						if (str.equals("��ɫ")) {
							view.color = Color.RED;
						} else if (str.equals("��ɫ")) {
							view.color = Color.GREEN;
						} else if (str.equals("��ɫ")) {
							view.color = Color.BLUE;
						} else if (str.equals("��ɫ")) {
							view.color = Color.YELLOW;
						} else if (str.equals("��ɫ")) {
							view.color = Color.BLACK;
						} else if (str.equals("��ɫ")) {
							view.color = Color.GRAY;
						} else if (str.equals("��ɫ")) {
							view.color = Color.parseColor("#00FFFF");
						} else if (str.equals("�㽶ɫ")) {
							view.color = Color.parseColor("#E3CF57");
						} else if (str.equals("��ɫ")) {
							view.color = Color.parseColor("#E3A869");
						} else if (str.equals("���ܲ�ɫ")) {
							view.color = Color.parseColor("#ED9121");
						} else if (str.equals("�ۻ�ɫ")) {
							view.color = Color.parseColor("#FF8000");
						} else if (str.equals("ʯ����ɫ")) {
							view.color = Color.parseColor("#6A5ACD");
						} else if (str.equals("����ɫ")) {
							view.color = Color.parseColor("#40E0D0");
						}
						Toast.makeText(DrawBoardActivity.this, "��ѡ����:" + str + " ����", Toast.LENGTH_SHORT).show();
					}
				});

				ab.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
				ab.show();
			}
		});
	}

	private void initDrawBoard() {
		view = (DrawBoardView) findViewById(R.id.draw);
	}

	private void initButton() {
		clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				view.clear();
			}
		});
	}
}
