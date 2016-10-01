package com.example.lovestudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;

public class DrawBoardView extends SurfaceView {

	/** ���� */
	public Paint paint = new Paint();
	public Path path = new Path();
	public int zise = 5;
	public int color = Color.RED;

	public DrawBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public DrawBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(mCallback);
		setOnTouchListener(mOnTouchListener);
	}

	public DrawBoardView(Context context) {
		super(context);
	}

	/*
	 * ��--�ķ���
	 */
	public void draw(int size, int color) {
		// ����
		paint.setColor(color); // �������ɫ
		paint.setStrokeWidth(zise); // ����Ĵ�С
		paint.setAntiAlias(true); // �����
		paint.setStyle(Style.STROKE); // �������ʽ
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.WHITE);
		canvas.drawPath(path, paint);
		getHolder().unlockCanvasAndPost(canvas);
	}

	private SurfaceHolder.Callback mCallback = new Callback() {

		/** �������ٵ�ʱ�� */
		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {

		}

		/** ���洴����ʱ�� */
		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			draw(zise, color);
		}

		/** ����ı��ʱ�� */
		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

		}
	};

	private OnTouchListener mOnTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				path.moveTo(event.getX(), event.getY());
				draw(zise, color);
				break;

			case MotionEvent.ACTION_MOVE:
				path.lineTo(event.getX(), event.getY());
				draw(zise, color);
				break;
			}
			return true;
		}
	};

	/**
	 * ���--�ķ���
	 */
	public void clear() {
		path.reset();// ����
		draw(zise, color);
	}
}
