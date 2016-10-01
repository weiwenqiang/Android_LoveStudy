package com.example.lovestudy.activity.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.adapter.ChinaCityAdapterLeft;
import com.example.lovestudy.adapter.ChinaCityAdapterRigth;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.utils.ConversionUtil;

public class ChinaCityActivity extends BaseActivity {

	private ListView listLeft, listRight;
	private String[] strLeft, strRight;
	private List<String> dataLeft = new ArrayList<String>();
	private List<String> dataRight = new ArrayList<String>();
	private ChinaCityAdapterLeft adapterLeft;
	private ChinaCityAdapterRigth adapterRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_china_city);
		setHeadTitle(R.string.china_city);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		listLeft = (ListView) findViewById(R.id.list_left);
		listRight = (ListView) findViewById(R.id.list_right);
	}

	private void bindData() {
		strLeft = getResources().getStringArray(R.array.china_city);
		dataLeft = ConversionUtil.arrayToList(strLeft);
		strRight = getResources().getStringArray(R.array.china_liaoning);
		dataRight = ConversionUtil.arrayToList(strRight);
	}

	private void bindView() {
		adapterLeft = new ChinaCityAdapterLeft(ctx, dataLeft);
		listLeft.setAdapter(adapterLeft);
		listLeft.setOnItemClickListener(onItemClickListener);
		adapterRight = new ChinaCityAdapterRigth(ctx, dataRight);
		listRight.setAdapter(adapterRight);
	}

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			switch (position) {
			case 0:
				strRight = getResources().getStringArray(R.array.china_liaoning);
				break;
			case 1:
				strRight = getResources().getStringArray(R.array.china_jiling);
				break;
			case 2:
				strRight = getResources().getStringArray(R.array.china_heilongjiang);
				break;
			case 3:
				strRight = getResources().getStringArray(R.array.china_hebei);
				break;
			case 4:
				strRight = getResources().getStringArray(R.array.china_shanxi1);
				break;
			case 5:
				strRight = getResources().getStringArray(R.array.china_shanxi2);
				break;
			case 6:
				strRight = getResources().getStringArray(R.array.china_gansu);
				break;
			case 7:
				strRight = getResources().getStringArray(R.array.china_qinghai);
				break;
			case 8:
				strRight = getResources().getStringArray(R.array.china_shandong);
				break;
			case 9:
				strRight = getResources().getStringArray(R.array.china_anhui);
				break;
			case 10:
				strRight = getResources().getStringArray(R.array.china_jiangsu);
				break;
			case 11:
				strRight = getResources().getStringArray(R.array.china_zhejiang);
				break;
			case 12:
				strRight = getResources().getStringArray(R.array.china_henan);
				break;
			case 13:
				strRight = getResources().getStringArray(R.array.china_hubei);
				break;
			case 14:
				strRight = getResources().getStringArray(R.array.china_hunan);
				break;
			case 15:
				strRight = getResources().getStringArray(R.array.china_jiangxi);
				break;
			case 16:
				strRight = getResources().getStringArray(R.array.china_taiwan);
				break;
			case 17:
				strRight = getResources().getStringArray(R.array.china_fujian);
				break;
			case 18:
				strRight = getResources().getStringArray(R.array.china_yunnan);
				break;
			case 19:
				strRight = getResources().getStringArray(R.array.china_hainan);
				break;
			case 20:
				strRight = getResources().getStringArray(R.array.china_sichuan);
				break;
			case 21:
				strRight = getResources().getStringArray(R.array.china_guizhou);
				break;
			case 22:
				strRight = getResources().getStringArray(R.array.china_guangdong);
				break;
			case 23:
				strRight = getResources().getStringArray(R.array.china_neimenggu);
				break;
			case 24:
				strRight = getResources().getStringArray(R.array.china_xinjiagn);
				break;
			case 25:
				strRight = getResources().getStringArray(R.array.china_guangxi);
				break;
			case 26:
				strRight = getResources().getStringArray(R.array.china_xizang);
				break;
			case 27:
				strRight = getResources().getStringArray(R.array.china_ningxia);
				break;
			case 28:
				strRight = getResources().getStringArray(R.array.china_beijing);
				break;
			case 29:
				strRight = getResources().getStringArray(R.array.china_shanghai);
				break;
			case 30:
				strRight = getResources().getStringArray(R.array.china_tianjin);
				break;
			case 31:
				strRight = getResources().getStringArray(R.array.china_chongqing);
				break;
			case 32:
				strRight = getResources().getStringArray(R.array.china_xianggang);
				break;
			case 33:
				strRight = getResources().getStringArray(R.array.china_aomen);
				break;
			}

			dataRight = ConversionUtil.arrayToList(strRight);
			adapterRight = new ChinaCityAdapterRigth(ctx, dataRight);
			listRight.setAdapter(adapterRight);

			adapterLeft.setSelectItem(position);
			adapterLeft.notifyDataSetChanged();
		}
	};
}
