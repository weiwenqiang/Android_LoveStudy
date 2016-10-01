package com.example.lovestudy.activity.user;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.utils.Util;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class LoginActivity extends BaseActivity {

	private View viewAccount;
	private EditText editAccount;
	private ImageView imgCancle;
	private View viewPassword;
	private EditText editPassword;
	private ImageView imgHide;
	private TextView txtLogin;
	private TextView txtForgetPassword;
	private TextView txtRegister;

	private ImageView imgWX;
	private ImageView imgQQ;
	private ImageView imgXL;

	private boolean isShowPwd = false;

	private ImageView mUserLogo;
	private TextView mUserInfo;
	public static String mAppid = "222222";
	private Tencent mTencent;
	private UserInfo mInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		setHeadTitle(R.string.login);

		mUserLogo = (ImageView) findViewById(R.id.user_logo);
		mUserInfo = (TextView) findViewById(R.id.user_nickname);
		mTencent = Tencent.createInstance(mAppid, this);

		initView();

	}

	private void initView() {
		viewAccount = findViewById(R.id.user_account);
		editAccount = (EditText) viewAccount.findViewById(R.id.edit_user);
		imgCancle = (ImageView) viewAccount.findViewById(R.id.icon_cancel);
		viewPassword = findViewById(R.id.user_password);
		editPassword = (EditText) viewPassword.findViewById(R.id.edit_password);
		imgHide = (ImageView) viewPassword.findViewById(R.id.icon_hide);
		txtLogin = (TextView) findViewById(R.id.btn_login);
		txtForgetPassword = (TextView) findViewById(R.id.txt_forget_password);
		txtRegister = (TextView) findViewById(R.id.txt_register);
		imgWX = (ImageView) findViewById(R.id.img_wx);
		imgQQ = (ImageView) findViewById(R.id.img_qq);
		imgXL = (ImageView) findViewById(R.id.img_xl);

		editAccount.addTextChangedListener(textWatcherAccount);
		editPassword.addTextChangedListener(textWatcherPassword);
		imgCancle.setOnClickListener(onClickListener);
		imgHide.setOnClickListener(onClickListener);
		txtLogin.setOnClickListener(onClickListener);
		txtForgetPassword.setOnClickListener(onClickListener);
		txtRegister.setOnClickListener(onClickListener);
		imgWX.setOnClickListener(onClickListener);
		imgQQ.setOnClickListener(onClickListener);
		imgXL.setOnClickListener(onClickListener);
	}

	private void showPwd() {
		if (!isShowPwd) {
			/** 设置以密码的方式显示 */
			editPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			isShowPwd = true;
			imgHide.setImageResource(R.drawable.icon_show);
			/** 设置光标位置 */
			editPassword.setSelection(editPassword.getText().toString().length());
		} else if (isShowPwd) {
			/** 设置以文本的方式显示 */
			editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			isShowPwd = false;
			imgHide.setImageResource(R.drawable.icon_hide);
			/** 设置光标位置 */
			editPassword.setSelection(editPassword.getText().toString().length());
		}
	}

	private TextWatcher textWatcherAccount = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
			if (charSequence != null && charSequence.length() > 0) {
				imgCancle.setVisibility(View.VISIBLE);
				String pwd = editPassword.getText().toString().trim();
				if (pwd.length() >= 6 && pwd.length() <= 20) {
					txtLogin.setEnabled(true);
				} else {
					txtLogin.setEnabled(false);
				}
			} else {
				imgCancle.setVisibility(View.GONE);
				txtLogin.setEnabled(false);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

		}

		@Override
		public void afterTextChanged(Editable arg0) {

		}
	};

	private TextWatcher textWatcherPassword = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (s != null && s.length() > 0) {
				imgHide.setVisibility(View.VISIBLE);
				if (isShowPwd) {
					imgHide.setImageResource(R.drawable.icon_show);
				} else {
					imgHide.setImageResource(R.drawable.icon_hide);
				}
				int length = editAccount.getText().toString().trim().length();
				if (s.length() >= 6 && s.length() <= 20 && length > 0) {
					txtLogin.setEnabled(true);
				} else {
					txtLogin.setEnabled(false);
				}
			} else {
				imgHide.setVisibility(View.GONE);
				txtLogin.setEnabled(false);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

		}

		@Override
		public void afterTextChanged(Editable arg0) {

		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.icon_cancel:
				editAccount.setText("");
				break;
			case R.id.icon_hide:
				showPwd();
				break;
			case R.id.txt_login:

				break;
			case R.id.txt_forget_password:
				ctx.gotoTargetActivity(ForgetPasswordActivity.class);
				break;
			case R.id.txt_register:
				ctx.gotoTargetActivity(RegisterActivity.class);
				break;
			case R.id.img_wx:
				BaseMethod.showToast(ctx, "微信登录");
				break;
			case R.id.img_qq:
				QQLogin();
				break;
			case R.id.img_xl:
				BaseMethod.showToast(ctx, "新浪登录");
				break;
			}
		}
	};

	private void QQLogin() {
		if (!mTencent.isSessionValid()) {
			mTencent.login(this, "all", mIUiListener);
		} else {
			mTencent.logout(this);
			updateUserInfo();
		}
	}

	IUiListener mIUiListener = new IUiListener() {

		@Override
		public void onError(UiError e) {
			BaseMethod.showToast(ctx, e.errorDetail);
		}

		@Override
		public void onComplete(Object response) {
			BaseMethod.showToast(ctx, response.toString());
			updateUserInfo();
		}

		@Override
		public void onCancel() {
		}
	};

	private void updateUserInfo() {
		if (mTencent != null && mTencent.isSessionValid()) {
			IUiListener listener = new IUiListener() {

				@Override
				public void onError(UiError e) {
				}

				@Override
				public void onComplete(final Object response) {
					Message msg = new Message();
					msg.obj = response;
					msg.what = 0;
					mHandler.sendMessage(msg);
					new Thread() {

						@Override
						public void run() {
							JSONObject json = (JSONObject) response;
							if (json.has("figureurl")) {
								Bitmap bitmap = null;
								try {
									bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
								} catch (JSONException e) {
									e.printStackTrace();
								}
								Message msg = new Message();
								msg.obj = bitmap;
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						}
					}.start();
				}

				@Override
				public void onCancel() {
				}
			};
			mInfo = new UserInfo(this, mTencent.getQQToken());
			mInfo.getUserInfo(listener);

		} else {
			mUserInfo.setText("");
			mUserInfo.setVisibility(android.view.View.GONE);
			mUserLogo.setVisibility(android.view.View.GONE);
		}
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				JSONObject response = (JSONObject) msg.obj;
				if (response.has("nickname")) {
					try {
						mUserInfo.setVisibility(android.view.View.VISIBLE);
						mUserInfo.setText(response.getString("nickname"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else if (msg.what == 1) {
				Bitmap bitmap = (Bitmap) msg.obj;
				mUserLogo.setImageBitmap(bitmap);
				mUserLogo.setVisibility(android.view.View.VISIBLE);
			}
		}

	};
}
