package com.example.lovestudy.activity.view;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.CustomTextView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CustomTextViewActivity extends BaseActivity {

	private ListView listView;
	private CustomTextViewAdapter adapter;
	private List<String> listOne = new ArrayList<String>();
	private List<String> listTwo = new ArrayList<String>();
	private List<String> listThree = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_custom_textview);
		setHeadTitle(R.string.view_CustomTextView);
		initView();
		bindData();
		bindView();
	}
	
	private void initView() {
		listView = (ListView) findViewById(R.id.listview);
	}

	private void bindData() {
		listOne.add("快乐建立及国际化地方感觉");
		listOne.add("分公司读后感入会你别傻了发机构房管局奥斯");
		listOne.add("看过舒服多了科技部后I软件后很");
		listOne.add("公司的会");
		listOne.add("发的不告诉你都没了你就是看了发布会就上课了规划设计客户说发货的告诉对方尽快更新换附近开");

		listTwo.add("25分钟前更新");
		listTwo.add("10分钟前更新");
		listTwo.add("29分钟前更新");
		listTwo.add("7分钟前更新");
		listTwo.add("3小时前更新");

		listThree.add("置顶1");
		listThree.add("置顶2");
		listThree.add("置顶3");
		listThree.add("置顶4");
		listThree.add("置顶5");

	}

	private void bindView() {
		adapter = new CustomTextViewAdapter();
		listView.setAdapter(adapter);
	}

	public class CustomTextViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listOne.size();
		}

		@Override
		public Object getItem(int position) {
			return listOne.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			view = LayoutInflater.from(CustomTextViewActivity.this).inflate(R.layout.item_custom_textview_layout, null);
			CustomTextView customTextView = (CustomTextView) view.findViewById(R.id.custom_textview);
			final Button button = (Button) view.findViewById(R.id.button);
			final Button button2 = (Button) view.findViewById(R.id.button_right);

			customTextView.setText(listOne.get(position));
			button.setText(listTwo.get(position));
			button2.setText(listThree.get(position));

			int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
			button.measure(w, h);
			button2.measure(w, h);
			
			int one = button.getMeasuredWidth();
			int two = button2.getMeasuredWidth();
			int count = one + two;

			System.out.println("one==:" + one);
			System.out.println("two==:" + two);
			System.out.println("count==:" + count);

			customTextView.setMaxWidth(customTextView.setWidgetWidth(count));
			return view;
		}

	}

}
