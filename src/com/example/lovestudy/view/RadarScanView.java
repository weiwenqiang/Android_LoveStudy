package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RadarScanView extends FrameLayout {
	private int h;
	private int w;
	private int start;
	private Handler handler = new Handler();
	private Paint mPaintCircle;
	private Paint mPaintNormal;
	private Matrix matrix;

	private Runnable r = new Runnable() {
		public void run() {
			RadarScanView localRadarScanView = RadarScanView.this;
			localRadarScanView.start = (1 + localRadarScanView.start);
			RadarScanView.this.matrix = new Matrix();
			RadarScanView.this.matrix.postRotate(RadarScanView.this.start, RadarScanView.this.w / 2, RadarScanView.this.h / 2);
			RadarScanView.this.invalidate();
			RadarScanView.this.handler.postDelayed(RadarScanView.this.r, 20L);
		}
	};

	public RadarScanView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		initPaint();
		setBackgroundResource(R.drawable.icon_radar_scan_bg);
		getScreenSize(paramContext);
		this.handler.post(this.r);
	}

	private void getScreenSize(Context paramContext) {
		this.w = paramContext.getResources().getDisplayMetrics().widthPixels;
		this.h = paramContext.getResources().getDisplayMetrics().heightPixels;
	}

	private void initPaint() {
		this.mPaintNormal = new Paint();
		this.mPaintNormal.setColor(Color.parseColor("#00ff00"));
		this.mPaintNormal.setStrokeWidth(3.0F);
		this.mPaintNormal.setAntiAlias(true);
		this.mPaintNormal.setStyle(Paint.Style.STROKE);
		this.mPaintCircle = new Paint();
		this.mPaintCircle.setColor(0x9D00ff00);
		this.mPaintCircle.setAntiAlias(true);
	}

	@SuppressLint({ "DrawAllocation" })
	protected void onDraw(Canvas paramCanvas) {
		paramCanvas.drawCircle(this.w / 2, this.h / 2, this.w / 8, this.mPaintNormal);
		paramCanvas.drawCircle(this.w / 2, this.h / 2, 2 * this.w / 8, this.mPaintNormal);
		paramCanvas.drawCircle(this.w / 2, this.h / 2, 3 * this.w / 8, this.mPaintNormal);
		paramCanvas.drawCircle(this.w / 2, this.h / 2, 4 * this.w / 8, this.mPaintNormal);
		SweepGradient localSweepGradient = new SweepGradient(this.w / 2, this.h / 2, 0, Color.parseColor("#AAAAAAAA"));
		this.mPaintCircle.setShader(localSweepGradient);
		paramCanvas.concat(this.matrix);
		paramCanvas.drawCircle(this.w / 2, this.h / 2, 4 * this.w / 8, this.mPaintCircle);
		super.onDraw(paramCanvas);
	}

	protected void onMeasure(int paramInt1, int paramInt2) {
		setMeasuredDimension(this.w, this.h);
	}
}
