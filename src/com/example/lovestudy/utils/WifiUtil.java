package com.example.lovestudy.utils;

import java.util.List;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiUtil {
	// 定义一个WifiManager对象
	private WifiManager mWifiManager;
	// 定义一个WifiInfo对象
	private WifiInfo mWifiInfo;
	// 扫描出的网络连接列表
	private List<ScanResult> mWifiList;
	// 网络连接列表
	private List<WifiConfiguration> mWifiConfigurations;
	WifiLock mWifiLock;

	public WifiUtil(Context context) {
		// 取得WifiManager对象
		mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		// 取得WifiInfo对象
		mWifiInfo = mWifiManager.getConnectionInfo();
	}

	// 打开wifi
	public void openWifi() {
		mWifiManager.setWifiEnabled(true);
	}

	// 关闭wifi
	public void closeWifi() {
		mWifiManager.setWifiEnabled(false);
	}

	// 检查当前wifi状态
	public String checkState() {
		String sWifiState = "";
		int iWifiState = mWifiManager.getWifiState();
		switch (iWifiState) {
		case 0:
			sWifiState = "0-WIFI网卡正在关闭";
			break;
		case 1:
			sWifiState = "1-WIFI网卡不可用";
			break;
		case 2:
			sWifiState = "2-WIFI网正在打开";
			break;
		case 3:
			sWifiState = "3-WIFI网卡可用";
			break;
		}
		return sWifiState;
	}

	// 锁定wifiLock
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	// 解锁wifiLock
	public void releaseWifiLock() {
		// 判断是否锁定
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	// 创建一个wifiLock
	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("test");
	}

	// 得到配置好的网络
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurations;
	}

	// 指定配置好的网络进行连接
	public void connetionConfiguration(int index) {
		if (index > mWifiConfigurations.size()) {
			return;
		}
		// 连接配置好指定ID的网络
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId, true);
	}

	public void startScan() {
		mWifiManager.startScan();
		// 得到扫描结果
		mWifiList = mWifiManager.getScanResults();
		// 得到配置好的网络连接
		mWifiConfigurations = mWifiManager.getConfiguredNetworks();
	}

	// 得到网络列表
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	// 查看扫描结果
	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mWifiList.size(); i++) {
			sb.append("Index_" + new Integer(i + 1).toString() + ":");
			sb.append((mWifiList.get(i)).toString()).append("\n");
		}
		return sb;
	}

	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	public int getIpAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	// 得到连接的ID
	public int getNetWordId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// 得到wifiInfo的所有信息
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	// 添加一个网络并连接
	public void addNetWork(WifiConfiguration configuration) {
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgId, true);
	}

	// 断开指定ID的网络
	public void disConnectionWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}
}
