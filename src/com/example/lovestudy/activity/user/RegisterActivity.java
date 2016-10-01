package com.example.lovestudy.activity.user;

import android.os.Bundle;
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

public class RegisterActivity extends BaseActivity {

	private View viewAccount;
	private EditText editAccount;
	private ImageView imgCancle;
	private View viewPassword;
	private EditText editPassword;
	private ImageView imgHide;
	private TextView btnRegister;

	private boolean isShowPwd = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register);
		setHeadTitle(R.string.register);
		initView();
	}

	private void initView() {
		viewAccount = findViewById(R.id.user_account);
		editAccount = (EditText) viewAccount.findViewById(R.id.edit_user);
		imgCancle = (ImageView) viewAccount.findViewById(R.id.icon_cancel);
		viewPassword = findViewById(R.id.user_password);
		editPassword = (EditText) viewPassword.findViewById(R.id.edit_password);
		imgHide = (ImageView) viewPassword.findViewById(R.id.icon_hide);
		btnRegister = (TextView) findViewById(R.id.btn_register);

		editAccount.addTextChangedListener(textWatcherAccount);
		editPassword.addTextChangedListener(textWatcherPassword);

		imgCancle.setOnClickListener(onClickListener);
		imgHide.setOnClickListener(onClickListener);
		btnRegister.setOnClickListener(onClickListener);
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
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (s != null && s.length() > 0) {
				imgCancle.setVisibility(View.VISIBLE);
				String pwd = editPassword.getText().toString().trim();
				if (pwd.length() >= 6 && pwd.length() <= 20) {
					btnRegister.setEnabled(true);
				} else {
					btnRegister.setEnabled(false);
				}
			} else {
				imgCancle.setVisibility(View.GONE);
				btnRegister.setEnabled(false);
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
					btnRegister.setEnabled(true);
				} else {
					btnRegister.setEnabled(false);
				}
			} else {
				imgHide.setVisibility(View.GONE);
				btnRegister.setEnabled(false);
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
			case R.id.btn_register:

				break;
			}
		}
	};
}
