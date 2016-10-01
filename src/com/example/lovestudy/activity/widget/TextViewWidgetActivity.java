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

	private String strSetLine = "        ��ƪд�������½��������˻����������������Ե��������ã�ȫ�����������������䡰С�����������ɫ���£��ų�һ��һ�еģ������ڻ�ӭ����㡱������ӱ�����������衱�ô�׼ȷ�����������д���˶�̬�µĴ��硣��ƪ�ɹ�֮����ֵ���Ƽ���";
	private String strMarquee = "ȸ�����ȡ���ζ���ü���.������������Ϥ��һ�����Ҳ��������ϲ���Ĺ����򵥶�����ζ��Զ�������Ͽڣ���Ϊ�������ĵĸ��ܿ����ѿڶ����������侭��֮���ڡ�������ȸ�����ؽ���ȫ�������¹����ʱ������û��һ�����仰�����䣬���Ծ����õر���������";
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
