package com.example.lovestudy.activity.function;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.view.LoadingDialogView;

public class MusicActivity extends BaseActivity {

	private MediaPlayer mediaPlayer;
	private List<String> audioList = new ArrayList<String>();
	private LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	private int currentItem = 0;
	private static String[] imageFormatSet = new String[] { "ape", "mp3", "wav" };

	/**
	 * 控件（）
	 */
	private ListView listView;
	private SeekBar seekBar;
	private Button play;
	private Button stop;
	private Button next;
	private Button front;

	private LoadingDialogView mLoadingDialogView;
	private TextView txtNoData;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				mLoadingDialogView.dismiss();
				if (audioList.size() <= 0) {
					txtNoData.setVisibility(View.VISIBLE);
				} else {
					txtNoData.setVisibility(View.GONE);
					bindListView();
				}
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_music_play);
		setHeadTitle(R.string.music_paly);
		initView();
		initListView();
		initSeekBar();
		initMediaPlayer();
	}

	private void initView() {
		listView = (ListView) findViewById(R.id.listView1);
		seekBar = (SeekBar) findViewById(R.id.seekbar);
		play = (Button) findViewById(R.id.play);
		stop = (Button) findViewById(R.id.stop);
		next = (Button) findViewById(R.id.next);
		front = (Button) findViewById(R.id.front);

		play.setOnClickListener(onClickListener);
		stop.setOnClickListener(onClickListener);
		next.setOnClickListener(onClickListener);
		front.setOnClickListener(onClickListener);

		mLoadingDialogView = new LoadingDialogView(ctx);
		mLoadingDialogView.show();

		txtNoData = (TextView) findViewById(R.id.txt_nodata);
	}

	private void initListView() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				getFiles(Environment.getExternalStorageDirectory().toString());
				Message message = new Message();
				message.what = 1;
				mHandler.sendMessage(message);
			}
		}).start();
	}

	private void bindListView() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, audioList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> viewList, View view, int position, long id) {
				currentItem = position;
				String path = map.get(audioList.get(position));
				playMusic(path);
			}
		});
	}

	private void initSeekBar() {
		final AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		seekBar.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		int progress = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		seekBar.setProgress(progress);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_PLAY_SOUND);
			}
		});
	}

	private void initMediaPlayer() {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				nextMusic();
			}
		});
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.play:
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.pause();
					((Button) view).setText("播放");
				} else {
					mediaPlayer.start();
					((Button) view).setText("暂停");
				}
				break;
			case R.id.stop:
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
				}
				play.setText("播放");
				break;
			case R.id.next:
				nextMusic();
				break;
			case R.id.front:
				frontMusic();
				break;
			}
		}
	};

	/**
	 * @param path
	 *            获取音乐文件
	 */
	private void getFiles(String path) {
		File files = new File(path);
		File[] file = files.listFiles();
		try {
			for (File f : file) {
				if (f.isDirectory()) {
					getFiles(f.getAbsolutePath());
				} else {
					if (f.length() > 512)
						if (isAudioFile(f.getPath())) {
							audioList.add(f.getPath().substring(f.getPath().lastIndexOf("/") + 1));
							map.put(f.getPath().substring(f.getPath().lastIndexOf("/") + 1), f.getPath());
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path
	 * @return 判断是否为音乐文件
	 */
	private boolean isAudioFile(String path) {
		for (String format : imageFormatSet) {
			if (path.contains(format)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param path
	 *            播放音乐
	 */
	protected void playMusic(String path) {
		try {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			mediaPlayer.reset();// 重置
			mediaPlayer.setDataSource(path);// 指定要播放的音频文件
			mediaPlayer.prepare();// 预加载音频文件
			mediaPlayer.start();
			play.setText("暂停");
			play.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下一首
	 */
	private void nextMusic() {
		if (++currentItem >= audioList.size()) {
			currentItem = 0;
		}
		String path = map.get(audioList.get(currentItem));
		playMusic(path);
	}

	/**
	 * 上一首
	 */
	private void frontMusic() {
		if (--currentItem >= 0) {
			if (currentItem >= audioList.size()) {
				currentItem = 0;
			}
		} else {
			currentItem = audioList.size() - 1;
		}
		String path = map.get(audioList.get(currentItem));
		playMusic(path);
	}

}
