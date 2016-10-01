package com.example.lovestudy.activity.user;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.utils.AppInfo;

public class AboutActivity extends BaseActivity {

	private BaseImageView packageIcon;
	private TextView version;
	private TextView guide;
	private TextView packageSize;
	private TextView appHasInstall;
	private TextView appSign;
	
	private TextView app_sign_md5;
	private TextView app_sign_sha1;
	private TextView app_sign_SigAlgName;
	private TextView app_sign_SubjectDN;

	String packageInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_about);
		setHeadTitle(R.string.about);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		packageIcon = (BaseImageView) findViewById(R.id.package_icon);
		version = (TextView) findViewById(R.id.version_code);
		guide = (TextView) findViewById(R.id.guide);
		packageSize = (TextView) findViewById(R.id.package_size);
		appHasInstall = (TextView) findViewById(R.id.app_has_install);
		appSign = (TextView) findViewById(R.id.app_sign);
		
		app_sign_md5 = (TextView) findViewById(R.id.app_sign_md5);
		app_sign_sha1 = (TextView) findViewById(R.id.app_sign_sha1);
		app_sign_SigAlgName = (TextView) findViewById(R.id.app_sign_SigAlgName);
		app_sign_SubjectDN = (TextView) findViewById(R.id.app_sign_SubjectDN);

	}

	private void bindData() {
		packageInfo = AppInfo.getInstance(ctx).getApkName() + " " + AppInfo.getInstance(ctx).getVersionName() + "  (" + AppInfo.getInstance(ctx).getVersionCode() + ")";
	}

	private void bindView() {
		packageIcon.setImageDrawable(AppInfo.getInstance(ctx).getApkIcon());
		version.setText(packageInfo);
		guide.setOnClickListener(onClickListener);
		packageSize.setOnClickListener(onClickListener);
		appHasInstall.setOnClickListener(onClickListener);
		appSign.setOnClickListener(onClickListener);
		
		app_sign_md5.setOnClickListener(onClickListener);
		app_sign_sha1.setOnClickListener(onClickListener);
		app_sign_SigAlgName.setOnClickListener(onClickListener);
		app_sign_SubjectDN.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.guide:
				gotoTargetActivity(GuideActivity.class);
				break;
			case R.id.package_size:
				BaseMethod.showToast(ctx, "APK大小:" + AppInfo.getInstance(ctx).getApkSize() + " M");
				break;
			case R.id.app_has_install:
				BaseMethod.showToast(ctx, "是否安装爱学习:" + AppInfo.isInstallApp(ctx, "com.example.lovestudy.activity"));
				break;
			case R.id.app_sign:
				BaseMethod.showToast(ctx, "应用签名:" + AppInfo.getSign(ctx, "com.example.lovestudy.activity").toCharsString());
				break;
			case R.id.app_sign_md5:
				BaseMethod.showToast(ctx, "应用签名(MD5):" + AppInfo.getMD5(ctx, "com.example.lovestudy.activity"));
				break;
			case R.id.app_sign_sha1:
				BaseMethod.showToast(ctx, "应用签名(SHA1):" + AppInfo.getSHA1(ctx, "com.example.lovestudy.activity"));
				break;
			case R.id.app_sign_SigAlgName:
				BaseMethod.showToast(ctx, "应用签名(SigAlgName):" + AppInfo.getSigAlgName(ctx, "com.example.lovestudy.activity"));
				break;
			case R.id.app_sign_SubjectDN:
				BaseMethod.showToast(ctx, "应用签名(SubjectDN):" + AppInfo.getSubjectDN(ctx, "com.example.lovestudy.activity"));
				break;
			}
		}
	};
}
