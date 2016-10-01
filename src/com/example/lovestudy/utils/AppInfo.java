package com.example.lovestudy.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

public class AppInfo {

	private static AppInfo mInstance;
	private static Context mContext;
	private PackageManager mPackageManager;
	private PackageInfo mPackageInfo;
	private ApplicationInfo mApplicationInfo;

	public static AppInfo getInstance(Context context) {
		mContext = context;
		if (mInstance == null) {
			mInstance = new AppInfo();
			mInstance.initConfig();
		}
		return mInstance;
	}

	private void initConfig() {
		try {
			mPackageManager = mContext.getPackageManager();
			mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
			mApplicationInfo = mPackageInfo.applicationInfo;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getApkName() {
		return mPackageManager.getApplicationLabel(mApplicationInfo).toString();
	}

	/**
	 * @return ��ȡ�汾��
	 */
	public int getVersionCode() {
		return mPackageInfo.versionCode;
	}

	/**
	 * @return ��ȡ�汾����
	 */
	public String getVersionName() {
		return mPackageInfo.versionName;
	}

	/**
	 * @return ��ȡӦ�ã�APK���Ĵ�С
	 */
	public String getApkSize() {
		try {
			/** ��ȡӦ�õ�·�� */
			String dir = mApplicationInfo.publicSourceDir;
			/** ��ȡӦ�õĴ�С */
			int size = Integer.valueOf((int) new File(dir).length());
			/** ����Ӧ�õĴ�С */
			BigDecimal bd = new BigDecimal((double) size / (1024 * 1024));
			BigDecimal apkSize = bd.setScale(2, BigDecimal.ROUND_DOWN);
			return apkSize.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return ��ȡӦ��·��
	 */
	public String getApkPath() {
		return mContext.getApplicationContext().getFilesDir().getAbsolutePath();
	}

	/**
	 * @return ��ȡAPKͼ��
	 */
	public Drawable getApkIcon() {
		PackageManager mPackageManager = null;
		PackageInfo mPackageInfo = null;
		ApplicationInfo mApplicationInfo = null;
		try {
			mPackageManager = mContext.getPackageManager();
			mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
			mApplicationInfo = mPackageInfo.applicationInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		mApplicationInfo.sourceDir = getApkPath();
		mApplicationInfo.publicSourceDir = getApkPath();
		return mApplicationInfo.loadIcon(mPackageManager);
	}

	/**
	 * @param ctx
	 * @param packageName
	 * @return �Ƿ�װ��ָ����Ӧ�ó���
	 */
	public static boolean isInstallApp(Context ctx, String packageName) {
		PackageManager mPackageManager = ctx.getPackageManager();
		List<PackageInfo> mPackageInfos = mPackageManager.getInstalledPackages(PackageManager.PERMISSION_GRANTED);
		for (PackageInfo info : mPackageInfos) {
			if (info.packageName.equalsIgnoreCase(packageName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param ctx
	 * @param packageName
	 * @return ��ȡָ��Ӧ�õ�ǩ����Ϣ
	 */
	public static Signature getSign(Context ctx, String packageName) {
		try {
			PackageManager mPackageManager = ctx.getPackageManager();
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			Signature signature = mPackageInfo.signatures[0];
			return signature;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param ctx
	 * @param packageName
	 * @return ��ȡָ��Ӧ�õ�ǩ����ϢMD5
	 */
	public static String getMD5(Context ctx, String packageName) {
		try {
			PackageManager mPackageManager = ctx.getPackageManager();
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			Signature signatures = mPackageInfo.signatures[0];
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(signatures.toByteArray());
			byte[] digest = mDigest.digest();
			String res = toHexString(digest);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param ctx
	 * @param packageName
	 * @return ��ȡָ��Ӧ�õ�ǩ����ϢSHA1
	 */
	public static String getSHA1(Context ctx, String packageName) {
		try {
			PackageManager mPackageManager = ctx.getPackageManager();
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			Signature signatures = mPackageInfo.signatures[0];
			MessageDigest mDigest = MessageDigest.getInstance("SHA1");
			mDigest.update(signatures.toByteArray());
			byte[] digest = mDigest.digest();
			String res = toHexString(digest);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param ctx
	 * @param packageName
	 * @return ��ȡָ��Ӧ�õ�ǩ����Ϣ(SigAlgName)
	 */
	public static String getSigAlgName(Context ctx, String packageName) {
		try {
			PackageManager mPackageManager = ctx.getPackageManager();
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			Signature signatures = mPackageInfo.signatures[0];
			ByteArrayInputStream mByteArrayInputStream = new ByteArrayInputStream(signatures.toByteArray());
			CertificateFactory mCertificateFactory = CertificateFactory.getInstance("X.509");
			X509Certificate mX509Certificate = (X509Certificate) mCertificateFactory.generateCertificate(mByteArrayInputStream);
			String sigAlgName = mX509Certificate.getSigAlgName();
			return sigAlgName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param ctx
	 * @param packageName
	 * @return ��ȡָ��Ӧ�õ�ǩ����Ϣ(SubjectDN)
	 */
	public static String getSubjectDN(Context ctx, String packageName) {
		try {
			PackageManager mPackageManager = ctx.getPackageManager();
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
			Signature signatures = mPackageInfo.signatures[0];
			ByteArrayInputStream mByteArrayInputStream = new ByteArrayInputStream(signatures.toByteArray());
			CertificateFactory mCertificateFactory = CertificateFactory.getInstance("X.509");
			X509Certificate mX509Certificate = (X509Certificate) mCertificateFactory.generateCertificate(mByteArrayInputStream);
			String subjectDN = mX509Certificate.getSubjectDN().toString();
			return subjectDN;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toHexString(byte[] block) {
		StringBuffer buf = new StringBuffer();
		int len = block.length;
		for (int i = 0; i < len; i++) {
			byte2hex(block[i], buf);
			if (i < len - 1) {
				buf.append(":");
			}
		}
		return buf.toString();
	}

	public static void byte2hex(byte b, StringBuffer buf) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		int high = ((b & 0xf0) >> 4);
		int low = (b & 0x0f);
		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
	}

}
