package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.utils.MD5Util;

public class MD5EncryptActivity extends BaseActivity {

	private TextView txtGet;
	private TextView txtSave;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_md5);
		setHeadTitle(R.string.md5);
		initView();
	}

	private void initView() {
		this.editText = ((EditText) findViewById(R.id.edittext));
		this.txtSave = ((TextView) findViewById(R.id.txtSave));
		this.txtGet = ((TextView) findViewById(R.id.txtGet));
		this.editText.addTextChangedListener(this.textWatcher);
		this.txtSave.setOnClickListener(this.onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			txtGet.setText(MD5Util.getMD5Str(editText.getText().toString()));
		}
	};

	private TextWatcher textWatcher = new TextWatcher() {
		public void afterTextChanged(Editable paramAnonymousEditable) {
		}
		public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
		}
		public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
			MD5EncryptActivity.this.editText.setSelection(paramAnonymousCharSequence.length());
			if ((paramAnonymousCharSequence != null) && (paramAnonymousCharSequence.length() > 0)) {
				MD5EncryptActivity.this.txtSave.setEnabled(true);
				return;
			}
			MD5EncryptActivity.this.txtSave.setEnabled(false);
		}
	};

}
