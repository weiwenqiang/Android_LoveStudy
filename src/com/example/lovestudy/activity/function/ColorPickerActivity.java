package com.example.lovestudy.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.colorpicker.ColorPickerDialog;

public class ColorPickerActivity extends BaseActivity implements ColorPickerDialog.OnColorChangedListener {

	private TextView txtChooseColor;
	private View preView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_color_picker);
		setHeadTitle(R.string.color_picker);
		initView();
	}

	private void initView() {
		txtChooseColor = (TextView) findViewById(R.id.txtChooseColor);
		preView = findViewById(R.id.preView);
		txtChooseColor.setOnClickListener(onClickListener);
	}

	@Override
	public void onColorChanged(int color) {
		preView.setBackgroundColor(color);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			ColorPickerDialog mDialog = new ColorPickerDialog(ctx, 0xff333333);
			mDialog.setOnColorChangedListener(ColorPickerActivity.this);
			mDialog.setAlphaSliderVisible(true);
			mDialog.setHexValueEnabled(true);
			mDialog.show();
		}
	};
}
