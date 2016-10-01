package com.example.lovestudy.activity.function;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.logic.function.QrCodeLogic;
import com.example.lovestudy.utils.ConversionUtil;

public class QrCodeActivity extends BaseActivity {

	private EditText editText;
	private TextView txtSave;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_qrcode);
		setHeadTitle(R.string.qrcode);
		initView();
	}

	private void initView() {
		this.editText = ((EditText) findViewById(R.id.edittext));
		this.txtSave = ((TextView) findViewById(R.id.txtSave));
		this.imageView = ((ImageView) findViewById(R.id.imgQrcode));
		this.editText.addTextChangedListener(this.textWatcher);
		this.txtSave.setOnClickListener(this.onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			try {
				Bitmap localBitmap = QrCodeLogic.createQRCode(QrCodeActivity.this.editText.getText().toString(), 450);
				if (localBitmap != null) {
					QrCodeActivity.this.imageView.setImageBitmap(localBitmap);
					return;
				}
				QrCodeActivity.this.imageView.setImageBitmap(ConversionUtil.drawable2Bitmap(getResources().getDrawable(R.drawable.empty_photo)));
				return;
			} catch (Exception localException) {
				localException.printStackTrace();
			}
		}
	};

	private TextWatcher textWatcher = new TextWatcher() {
		public void afterTextChanged(Editable paramAnonymousEditable) {
		}

		public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
		}

		public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
			QrCodeActivity.this.editText.setSelection(paramAnonymousCharSequence.length());
			if ((paramAnonymousCharSequence != null) && (paramAnonymousCharSequence.length() > 0)) {
				QrCodeActivity.this.txtSave.setEnabled(true);
				return;
			}
			QrCodeActivity.this.txtSave.setEnabled(false);
		}
	};

}
