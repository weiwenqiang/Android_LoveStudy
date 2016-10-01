package com.example.lovestudy.activity.widget;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.logic.widget.TextViewLogic;

public class TextViewWidgetActivity extends BaseActivity {

	private TextView txtSetLine;
	private ImageView imgSetLine;
	private TextView txtHtml;
	private TextView txtMarquee;

	private String strSetLine = "        这篇写景的文章将春天拟人化，赋予了生命，显得生动活泼，全文语言流畅。比喻句“小麦穿着整齐的绿色新衣，排成一列一列的，好像在欢迎春姐姐”胆大新颖。“翩翩起舞”用词准确生动，形象地写出了动态下的春风。是篇成功之作，值得推荐。";
	private String strMarquee = "雀巢咖啡——味道好极了.这是人们最熟悉的一句广告语，也是人们最喜欢的广告语。简单而又意味深远，朗朗上口，因为发自内心的感受可以脱口而出，正是其经典之所在。以至于雀巢以重金在全球征集新广告语时，发现没有一句比这句话更经典，所以就永久地保留了它。";
	String strHtml = "<b>text3:</b>  Text with a " + "<a href=\"http://www.google.com\">link</a> " + "created in the Java source code using HTML.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_textview_widget);
		setHeadTitle(R.string.TextView);
		initView();
	}
	
	private void initView() {
		txtSetLine = (TextView) findViewById(R.id.txt_setLine);
		imgSetLine = (ImageView) findViewById(R.id.img_setLine);
		txtMarquee = (TextView) findViewById(R.id.txt_marquee);
		txtHtml = (TextView) findViewById(R.id.txt_html);

		TextViewLogic.getInstance().setExtendAndRotateStatu(true, true);
		txtSetLine.setOnClickListener(onClickListener);

		txtSetLine.setText(strSetLine);
		txtMarquee.setText(strMarquee);
		txtHtml.setText(Html.fromHtml(strHtml));
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.txt_setLine:
				TextViewLogic.getInstance().openOrShutContext(txtSetLine);
				TextViewLogic.getInstance().setRotateAnimation(imgSetLine);
				break;
			}
		}
	};

}
