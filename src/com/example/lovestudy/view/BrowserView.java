package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 内嵌浏览器
 */
public class BrowserView extends LinearLayout {

	BaseActivity ctx;
	LayoutInflater inflater;

	ImageView btnBack;
	TextView txtTitle;
	ImageView btnRefresh;
	SeekBar seekBar;
	WebView webView;

	String url;
	boolean isShowRefresh = true;
	boolean isShowProgress = true;

	public BrowserView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		ctx = (BaseActivity) context;
		init();
	}

	public BrowserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = (BaseActivity) context;
		init();
	}

	public BrowserView(Context context) {
		super(context);
		ctx = (BaseActivity) context;
		init();
	}

	private void init() {
		inflater = LayoutInflater.from(ctx);
		inflater.inflate(R.layout.layout_view_browser_view, this, true);
		btnBack = (ImageView) findViewById(R.id.browser_back);
		txtTitle = (TextView) findViewById(R.id.browser_title);
		btnRefresh = (ImageView) findViewById(R.id.browser_refresh);
		seekBar = (SeekBar) findViewById(R.id.browser_progress);
		webView = (WebView) findViewById(R.id.browser_webview);
	}

	public void bindData(String url, boolean isShowRefresh, boolean isShowProgress) {
		this.url = url;
		this.isShowRefresh = isShowRefresh;
		this.isShowProgress = isShowProgress;
		
		seekBar.setMax(100);

		/** 设置是否显示刷新 */
		if (isShowRefresh) {
			btnRefresh.setVisibility(View.VISIBLE);
		} else {
			btnRefresh.setVisibility(View.GONE);
		}

		/** 设置是否显示进度条 */
		if (isShowProgress) {
			seekBar.setVisibility(View.VISIBLE);
		} else {
			seekBar.setVisibility(View.GONE);
		}

		btnBack.setOnClickListener(onClickListener);
		btnRefresh.setOnClickListener(onClickListener);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl(url);
		/** 各种监听事件 */
		webView.setWebViewClient(webViewClient);
		webView.setDownloadListener(downloadListener);
		webView.setWebChromeClient(webChromeClient);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.browser_back:
				ctx.finish();
				break;
			case R.id.browser_refresh:
				webView.reload();
				break;
			}
		}
	};

	WebViewClient client = new WebViewClient() {

	};

	/**
	 * Web视图客户端
	 */
	WebViewClient webViewClient = new WebViewClient() {

		/** Url加载的回调 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		/** 发生错误的回调 */
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			BaseMethod.showToast(ctx, "Oh no! " + description);
		}
		
		/** 页面加载完成的回调 */
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			seekBar.setVisibility(View.GONE);
		}

		/** 页面开始加载的回调 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			seekBar.setVisibility(View.VISIBLE);
		}

	};

	/**
	 * 下载监听
	 */
	DownloadListener downloadListener = new DownloadListener() {

		@Override
		public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
			Uri uri = Uri.parse(url);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			ctx.startActivity(intent);
		}
	};

	/**
	 * Web浏览器客户端
	 */
	WebChromeClient webChromeClient = new WebChromeClient() {

		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			String string = title;
			txtTitle.setText(string);
		}

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			seekBar.setProgress(newProgress);
			if (newProgress >= 100) {
				seekBar.setVisibility(View.GONE);
			} else {
				seekBar.setVisibility(View.VISIBLE);
			}
		}
	};

}
