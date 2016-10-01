package com.example.lovestudy.activity.function;

import java.util.List;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.utils.WifiUtil;

public class WifiManageActivity extends BaseActivity {
	
	private TextView allNetWork;
	private Button scan;
	private Button start;
	private Button stop;
	private Button check;
	private WifiUtil mWifiUtil;
	// 扫描结果列表
	private List<ScanResult> list;
	private ScanResult mScanResult;
	private StringBuffer sb = new StringBuffer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_wifi_manage);
		setHeadTitle(R.string.wifi_manage);
		initView();
	}
	
	private void initView() {
		allNetWork = (TextView) findViewById(R.id.wifi_manage_all_network);
		scan = (Button) findViewById(R.id.wifi_manage_scan);
		start = (Button) findViewById(R.id.wifi_manage_start);
		stop = (Button) findViewById(R.id.wifi_manage_stop);
		check = (Button) findViewById(R.id.wifi_manage_check);
		scan.setOnClickListener(onClickListener);
		start.setOnClickListener(onClickListener);
		stop.setOnClickListener(onClickListener);
		check.setOnClickListener(onClickListener);
		mWifiUtil = new WifiUtil(this);
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.wifi_manage_scan:// 扫描网络
				getAllNetWorkList();
				break;
			case R.id.wifi_manage_start:// 打开Wifi
				mWifiUtil.openWifi();
				break;
			case R.id.wifi_manage_stop:// 关闭Wifi
				mWifiUtil.closeWifi();
				break;
			case R.id.wifi_manage_check:// Wifi状态
				BaseMethod.showToast(ctx, "当前wifi状态为：" + mWifiUtil.checkState());
				break;
			}
		}

	};
	
	public void getAllNetWorkList() {
		// 每次点击扫描之前清空上一次的扫描结果
		if (sb != null) {
			sb = new StringBuffer();
		}
		// 开始扫描网络
		mWifiUtil.startScan();
		list = mWifiUtil.getWifiList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// 得到扫描结果
				mScanResult = list.get(i);
				sb = sb.append(mScanResult.BSSID + "  ").append(mScanResult.SSID + "   ").append(mScanResult.capabilities + "   ").append(mScanResult.frequency + "   ")
						.append(mScanResult.level + "\n\n");
			}
			allNetWork.setText("扫描到的wifi网络：\n" + sb.toString());
		}
	}
	
}
