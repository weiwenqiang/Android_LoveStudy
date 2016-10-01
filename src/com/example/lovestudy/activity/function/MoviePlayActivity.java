package com.example.lovestudy.activity.function;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.logic.function.MoviePlayLogic;

public class MoviePlayActivity extends BaseActivity {

	private SurfaceView surfaceView;
	private TextView list;
	private TextView pause;
	private TextView stop;
	private TextView replay;
	private SeekBar seekBar;
	private TextView hasPlayed;
	private TextView duration;

	private SurfaceHolder surfaceHolder;
	private MediaPlayer mPlayer;
	private boolean isPause = false;
	private boolean isPlaying;
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				hasPlayed.setText(MoviePlayLogic.getInstance().dateFormat((Integer) msg.obj));
				break;
			}
		}
		
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_movie_play);
		setHeadTitle(R.string.movie_paly);
		initView();
	}
	
	@SuppressWarnings("deprecation")
	private void initView() {
		surfaceView = (SurfaceView) findViewById(R.id.surface_view);
		list = (TextView) findViewById(R.id.list);
		pause = (TextView) findViewById(R.id.pause);
		stop = (TextView) findViewById(R.id.stop);
		replay = (TextView) findViewById(R.id.replay);
		seekBar = (SeekBar) findViewById(R.id.seekbar);
		hasPlayed = (TextView) findViewById(R.id.hasPlayed);
		duration = (TextView) findViewById(R.id.duration);

		list.setOnClickListener(onClickListener);
		pause.setOnClickListener(onClickListener);
		stop.setOnClickListener(onClickListener);
		replay.setOnClickListener(onClickListener);
		seekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);

		this.getWindow().setFormat(PixelFormat.UNKNOWN);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setFixedSize(176, 144);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mPlayer = new MediaPlayer();

	}

	/**
	 * @param path
	 *            ²¥·Å
	 */
	private void play(String path) {
		if (mPlayer.isPlaying()) {
			mPlayer.reset();
			mPlayer.release();
		}
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mPlayer.setDisplay(surfaceHolder);
		try {
			mPlayer.setDataSource(path);
			mPlayer.prepare();
			mPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(final MediaPlayer mPlayer) {
					mPlayer.start();
					mPlayer.seekTo(0);
					seekBar.setMax(mPlayer.getDuration());
					duration.setText(MoviePlayLogic.getInstance().dateFormat(mPlayer.getDuration()));
					new Thread() {

						@Override
						public void run() {
							try {
								isPlaying = true;
								while (isPlaying) {
									int current = mPlayer.getCurrentPosition();
									seekBar.setProgress(current);
									Message message = new Message();
									message.what = 0;
									message.obj = current;
									handler.sendMessage(message);
									sleep(1000);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}.start();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		mPlayer.start();
	}

	/**
	 * Í£Ö¹
	 */
	private void stop() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			mPlayer.stop();
			mPlayer.release();
		}
	}

	/**
	 * ÖØ²¥
	 */
	private void replay() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			mPlayer.seekTo(0);
			return;
		}
	}

	private OnSeekBarChangeListener mSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			int progress = seekBar.getProgress();
			if (mPlayer != null && mPlayer.isPlaying()) {
				mPlayer.seekTo(progress);
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		}
	};

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.list:
				Intent intent = new Intent();
				intent.setClass(MoviePlayActivity.this, MovieChooseActivity.class);
				startActivityForResult(intent, 0);
				break;
			case R.id.pause:
				if (!isPause) {
					mPlayer.pause();
					isPause = true;
					pause.setText("²¥·Å");
				} else {
					mPlayer.start();
					isPause = false;
					pause.setText("ÔÝÍ£");
				}
				break;
			case R.id.stop:
				stop();
				break;
			case R.id.replay:
				replay();
				break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
			String choosedMovie = data.getStringExtra("path");
			if (choosedMovie != null && choosedMovie.length() > 0) {
				play(choosedMovie);
			}
			return;
		}
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}

}
