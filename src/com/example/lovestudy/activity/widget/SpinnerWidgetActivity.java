package com.example.lovestudy.activity.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class SpinnerWidgetActivity extends BaseActivity {

	private TextView txt;
	private Spinner spinner;
	private int[] drawableIds = { R.drawable.icon_basketball, R.drawable.icon_football, R.drawable.icon_billiards };
	private String[] msgIds = { "篮球", "足球", "台球" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_spinner);
		setHeadTitle(R.string.Spinner);
		initView();
		bindView();
	}

	private void initView() {
		txt = (TextView) findViewById(R.id.txt_spinner);
		spinner = (Spinner) findViewById(R.id.Spinner);
	}

	private void bindView() {
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				LinearLayout linearLayout = (LinearLayout) view;
				/** 获取TextView控件 */
				TextView tvn = (TextView) linearLayout.getChildAt(1);
				StringBuilder sb = new StringBuilder();
				sb.append(getResources().getText(R.string.your_love)).append(":").append(tvn.getText());
				txt.setText(sb.toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});
	}

	@SuppressLint("ResourceAsColor")
	private BaseAdapter adapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return drawableIds.length;
		}

		@Override
		public Object getItem(int position) {
			return drawableIds[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout linearLayout = new LinearLayout(SpinnerWidgetActivity.this);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			linearLayout.setGravity(Gravity.CENTER | Gravity.LEFT);

			ImageView imageView = new ImageView(SpinnerWidgetActivity.this);
			imageView.setImageResource(drawableIds[position]);
			linearLayout.addView(imageView);

			TextView textView = new TextView(SpinnerWidgetActivity.this);
			textView.setText(msgIds[position]);
			textView.setTextSize(20);
			textView.setTextColor(R.color.black);
			textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 150));
			textView.setGravity(Gravity.CENTER | Gravity.LEFT);
			linearLayout.addView(textView);
			return linearLayout;
		}
	};
}
