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
				ab.setTitle("画笔大小选择");
				list.clear();
				ab.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						list.add(which);
					}
				});

				ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {

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
						Toast.makeText(DrawBoardActivity.this, "你选择:" + Integer.parseInt(str) + " 大小的画笔", Toast.LENGTH_SHORT).show();
					}
				});

				ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
				ab.show();
			}
		});
		color.setOnClickListener(new OnClickListener() {

			final String[] item = { "红色", "绿色", "蓝色", "黄色", "黑色", "灰色", "青色", "香蕉色", "瓜色", "胡萝卜色", "桔黄色", "石板蓝色", "青绿色" };
			ArrayList<Integer> list = new ArrayList<Integer>();

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder ab = new AlertDialog.Builder(DrawBoardActivity.this);
				ab.setTitle("画笔颜色选择");
				list.clear();
				ab.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						list.add(which);
					}
				});

				ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int which) {
						String str = "";
						int size = list.size();
						for (int i = 0; i < size; i++) {
							str = item[list.get(i)];
						}
						if (str == null || str.equals("")) {
							str = "红色";
						}
						if (str.equals("红色")) {
							view.color = Color.RED;
						} else if (str.equals("绿色")) {
							view.color = Color.GREEN;
						} else if (str.equals("蓝色")) {
							view.color = Color.BLUE;
						} else if (str.equals("黄色")) {
							view.color = Color.YELLOW;
						} else if (str.equals("黑色")) {
							view.color = Color.BLACK;
						} else if (str.equals("灰色")) {
							view.color = Color.GRAY;
						} else if (str.equals("青色")) {
							view.color = Color.parseColor("#00FFFF");
						} else if (str.equals("香蕉色")) {
							view.color = Color.parseColor("#E3CF57");
						} else if (str.equals("瓜色")) {
							view.color = Color.parseColor("#E3A869");
						} else if (str.equals("胡萝卜色")) {
							view.color = Color.parseColor("#ED9121");
						} else if (str.equals("桔黄色")) {
							view.color = Color.parseColor("#FF8000");
						} else if (str.equals("石板蓝色")) {
							view.color = Color.parseColor("#6A5ACD");
						} else if (str.equals("青绿色")) {
							view.color = Color.parseColor("#40E0D0");
						}
						Toast.makeText(DrawBoardActivity.this, "你选择了:" + str + " 画笔", Toast.LENGTH_SHORT).show();
					}
				});

				ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {

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
