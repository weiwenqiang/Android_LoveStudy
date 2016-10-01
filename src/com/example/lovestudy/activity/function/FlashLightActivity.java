package com.example.lovestudy.activity.function;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;

public class FlashLightActivity extends BaseActivity {
	
	private Camera camera;
	private boolean isopent = false;
	private Parameters params;
	private TextView txtOpen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_flash_light);
		setHeadTitle(R.string.flashlight);
		initView();
	}

	private void initView() {
		this.txtOpen = ((TextView) findViewById(R.id.txtOpen));
		this.txtOpen.setOnClickListener(this.onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			if (isopent) {
				params.setFlashMode("off");
				camera.setParameters(params);
				camera.stopPreview();
				isopent = false;
				camera.release();
				txtOpen.setText("´ò¿ª");
				return;
			}
			camera = Camera.open();
			params = camera.getParameters();
			params.setFlashMode("torch");
			camera.setParameters(params);
			camera.startPreview();
			isopent = true;
			txtOpen.setText("¹Ø±Õ");
		}
	};
}
