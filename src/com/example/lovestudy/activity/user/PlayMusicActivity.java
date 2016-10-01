package com.example.lovestudy.activity.user;

import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.entity.LrcHandle;
import com.example.lovestudy.logic.function.MoviePlayLogic;
import com.example.lovestudy.view.WordView;

public class PlayMusicActivity extends BaseActivity {

	private WordView mWordView;
	private List<Integer> mTimeList;

	private TextView txtCurrentPosition;
	private SeekBar seekBar;
	private TextView txtDuration;

	private TextView txtServiceStart;
	private TextView txtServiceStop;

	private MediaPlayer mPlayer;
	private boolean isPlaying = true;
	private int currentPosition = 0;
	private Thread mThreadShowCurrentPosition;
	private Thread mThreadShowLrc;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				txtCurrentPosition.setText(MoviePlayLogic.getInstance().dateFormat((Integer) msg.obj));
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_play_music);
		setHeadTitle(R.string.music_paly_show_lrc);
		initView();
		bindData();
		bindView();
	}

	private void initView() {
		mWordView = (WordView) findViewById(R.id.text);
		txtCurrentPosition = (TextView) findViewById(R.id.txt_current_position);
		seekBar = (SeekBar) findViewById(R.id.seekbar);
		txtDuration = (TextView) findViewById(R.id.txt_duration);
		txtServiceStart = (TextView) findViewById(R.id.txt_service_start);
		txtServiceStop = (TextView) findViewById(R.id.txt_service_stop);
	}

	private void bindData() {
		try {
			mPlayer = MediaPlayer.create(ctx, R.raw.yougeshaguaaiguoni);
			mWordView.init(ctx.getResources().getAssets().open("yougeshaguaaiguoni.lrc"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void bindView() {
		txtServiceStart.setOnClickListener(onClickListener);
		txtServiceStop.setOnClickListener(onClickListener);
		seekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
	}

	private void createThreadShowCurrentPosition() {
		mThreadShowCurrentPosition = new Thread() {

			@Override
			public void run() {
				try {
					while (isPlaying) {
						currentPosition = mPlayer.getCurrentPosition();
						seekBar.setProgress(currentPosition);
						Message message = new Message();
						message.what = 0;
						message.obj = currentPosition;
						handler.sendMessage(message);
						sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		/** 设置守护进程 */
		mThreadShowCurrentPosition.setDaemon(true);
		mThreadShowCurrentPosition.start();
	}

	private void createThreadShowLrc() {
		try {
			LrcHandle lrcHandler = new LrcHandle();
			lrcHandler.readLRC(ctx.getResources().getAssets().open("yougeshaguaaiguoni.lrc"));
			mTimeList = lrcHandler.getTimes();

			mThreadShowLrc = new Thread(new Runnable() {
				int i = 0;

				@Override
				public void run() {
					synchronized (ACCESSIBILITY_SERVICE) {
						while (mPlayer.isPlaying()) {
							handler.post(new Runnable() {

								@Override
								public void run() {
									mWordView.invalidate();
								}
							});
							try {
								Thread.sleep(Long.parseLong(String.valueOf(mTimeList.get(i + 1))) - Long.parseLong(String.valueOf(mTimeList.get(i))));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							i++;
						}
					}
				}
			});
			/** 设置守护进程 */
			mThreadShowLrc.setDaemon(true);
			mThreadShowLrc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_service_start:
				startPlay();
				break;
			case R.id.txt_service_stop:
				stopPlay();
				break;
			}
		}
	};

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

	private void startPlay() {
		try {
			if (mPlayer.isPlaying()) {
				mPlayer.pause();
				txtServiceStart.setText("播放");
				return;
			}

			/** 初始化MediaPlayer */
			mPlayer = MediaPlayer.create(ctx, R.raw.yougeshaguaaiguoni);
			mPlayer.seekTo(currentPosition);
			seekBar.setMax(mPlayer.getDuration());
			mPlayer.start();

			/** 控件绑定数据 */
			txtServiceStart.setText("暂停");
			txtDuration.setText(MoviePlayLogic.getInstance().dateFormat(mPlayer.getDuration()));

			/** 启动线程（1，显示音乐当前播放时间；2，显示当前歌词的进度。） */
			createThreadShowCurrentPosition();
			createThreadShowLrc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stopPlay() {
		txtServiceStart.setText("播放");
		currentPosition = 0;
		/** 中断线程 */
		if (mPlayer.isPlaying()) {
			mThreadShowCurrentPosition.interrupt();
			mThreadShowLrc.interrupt();
		}
		mPlayer.stop();
	}
}
