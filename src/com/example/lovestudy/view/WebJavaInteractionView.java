package com.example.lovestudy.view;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.entity.WebJavaInteractionEntityObject;
import com.example.lovestudy.logic.function.ActionUrlProcess;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebJavaInteractionView extends LinearLayout {

	BaseActivity ctx;
	LayoutInflater inflater;
	WebView webView;
	WebSettings settings;

	public WebJavaInteractionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		ctx = (BaseActivity) context;
		initView();
	}

	public WebJavaInteractionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		ctx = (BaseActivity) context;
		initView();
	}

	public WebJavaInteractionView(Context context) {
		super(context);
		ctx = (BaseActivity) context;
		initView();
	}

	private void initView() {
		inflater = LayoutInflater.from(ctx);
		inflater.inflate(R.layout.layout_function_webjava_interaction_view, this, true);
		webView = (WebView) findViewById(R.id.webview);
		settings = webView.getSettings();
		/** �ܹ�ִ��JS�ű� */
		settings.setJavaScriptEnabled(true);
		/** ֧������ */
		settings.setBuiltInZoomControls(true);
		/** ����һ��ʵ�����Ķ��󣻲����������������JS�еı��� */
		webView.addJavascriptInterface(new WebJavaInteractionEntityObject(ctx), "TestDemo");
		webView.setWebViewClient(webViewClient);
	}

	public void setUrl(String url) {
		webView.loadUrl(url);
	}

	/**
	 * Web��ͼ�ͻ���(����������ͼ����)
	 */
	WebViewClient webViewClient = new WebViewClient() {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.toLowerCase().startsWith("testdemo")) {
				ActionUrlProcess.process(ctx, Uri.parse(url));
			}
			return true;/** true��ʾ���¼��ڴ˴�����������Ҫ�ٹ㲥*/ 
		}

	};
}
