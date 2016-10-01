package com.example.lovestudy.activity.view;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.utils.DownLoadUtil;
import com.example.lovestudy.view.CircleProgressBarView;

public class CircleProgressBarActivity extends BaseActivity {

	private String urlPath = "http://imgstore03.cdn.sogou.com/app/a/100520020/684828142a46dc98c94db1e4eecf953f";
	private CircleProgressBarView mCircleProgressBar;
	private ImageView imageView;

	private int fileSize;// 文件大小
	int downLoadFileSize;// 　下载大小
	private Bitmap bitmap;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				int result = downLoadFileSize * 100 / fileSize;
				mCircleProgressBar.setProgress(result);
				if (result == 100) {
					mCircleProgressBar.setVisibility(View.GONE);
				}else{
					mCircleProgressBar.setVisibility(View.VISIBLE);
				}
				break;
			case 2:
				imageView.setImageBitmap(bitmap);
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_circle_progressbar);
		setHeadTitle(R.string.view_CircleProgressBar);
		initView();
	}

	private void initView() {
		mCircleProgressBar = (CircleProgressBarView) findViewById(R.id.mCircleProgressBar);
		imageView = (ImageView) findViewById(R.id.imageview);
		mCircleProgressBar.setCricleColor(getResources().getColor(R.color.F9F9F9));
		mCircleProgressBar.setCricleProgressColor(getResources().getColor(R.color.red));
		mCircleProgressBar.setTextColor(getResources().getColor(R.color.red));
		mCircleProgressBar.setTextSize(30.0f);
		mCircleProgressBar.setProgressText("下载");
		new Thread(new Runnable() {

			@Override
			public void run() {
				downLoad(urlPath);
			}
		}).start();
	}

	private void sendMsg(int flag) {
		Message msg = new Message();
		msg.what = flag;
		handler.sendMessage(msg);
	}

	private void downLoad(String url) {
		HttpEntity httpEntity;
		InputStream inputStream = null;
		ByteArrayOutputStream baos = null;
		try {
			httpEntity = DownLoadUtil.getHttpEntity(url);
			inputStream = httpEntity.getContent();
			baos = new ByteArrayOutputStream();
			fileSize = (int) httpEntity.getContentLength();
			byte buf[] = new byte[1024];
			downLoadFileSize = 0;
			int numread = 0;
			while ((numread = inputStream.read(buf)) != -1) {
				baos.write(buf, 0, numread);
				downLoadFileSize += numread;
				sendMsg(1);
			}
			byte[] imageArray = baos.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
			sendMsg(2);
			inputStream.close();
			baos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
