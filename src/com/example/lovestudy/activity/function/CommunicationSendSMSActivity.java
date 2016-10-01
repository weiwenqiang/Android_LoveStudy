package com.example.lovestudy.activity.function;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.ConversionUtil;
import com.example.lovestudy.utils.DialogBuilderUtil;

public class CommunicationSendSMSActivity extends BaseActivity implements OnClickListener {
	
	private static final String[] PHONES_PROJECTION = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER };
	private Context context = this;
	
	private TextView btnChooseFriends;
	private TextView btnChooseContent;
	private EditText editFriends;
	private EditText editContent;
	private Button btnSent;
	
	private String dateAddress;
	private String dateContent;

	ArrayList<Integer> addressList = new ArrayList<Integer>();
	ArrayList<Integer> contentList = new ArrayList<Integer>();
	ArrayList<Integer> phoneNameList = new ArrayList<Integer>();
	String[] addressArray;
	String[] contentArray;
	String[] phoneName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_communication_send_sms);
		setHeadTitle(R.string.communication_send_sms);
		initView();
	}

	private void initView() {
		btnChooseFriends = (TextView) findViewById(R.id.address);
		btnChooseContent = (TextView) findViewById(R.id.content);
		editFriends = (EditText) findViewById(R.id.edit_address);
		editContent = (EditText) findViewById(R.id.edit_content);
		btnSent = (Button) findViewById(R.id.btn_sent_sms);

		btnChooseFriends.setOnClickListener(this);
		btnChooseContent.setOnClickListener(this);
		btnSent.setOnClickListener(this);

		contentArray = getResources().getStringArray(R.array.yuanxiaojie_jiyu);
		addressArray = ConversionUtil.listToArray(getPhoneNumber());
		phoneName = ConversionUtil.listToArray(getPhoneName());
	}
	
	/**
	 * 获取编辑框内的数据
	 */
	private void getEditValue() {
		dateAddress = BaseMethod.getNumFromString(editFriends.getText().toString().trim());
		dateContent = editContent.getText().toString().trim();
	}

	/**
	 * @param view
	 *            控件的点击事件
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.address:
			showLinkmanDialog();
			break;
		case R.id.content:
			showContentDialog();
			break;
		case R.id.btn_sent_sms:
			getEditValue();
			if (dateAddress == null || "".equals(dateAddress)) {
				BaseMethod.showToast(ctx, "好友不能没有！");
			} else if (dateContent == null || "".equals(dateContent)) {
				BaseMethod.showToast(ctx, "寄语不能为空！");
			} else {
				BaseMethod.sentSms(context, dateAddress, dateContent);
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * 显示好友选择对话框
	 */
	private void showLinkmanDialog(){
		DialogBuilderUtil builderUtil = new DialogBuilderUtil(ctx);
		builderUtil.setTitle("选择好友");
		builderUtil.setSingleChoiceItems(addressArray, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int which) {
				addressList.add(which);
			}
		});
		builderUtil.setPositiveButton(ResourcesGet.getString(ctx, R.string.sure), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int which) {
				int size = addressList.size();
				String str = null;
				for (int i = 0; i < size; i++) {
					str = addressArray[addressList.get(i)] + phoneName[addressList.get(i)];
				}
				editFriends.setText(str);
			}
		});
		builderUtil.setNegativeButton(ResourcesGet.getString(ctx, R.string.cancel), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
		builderUtil.show();
	}
	
	/**
	 * 显示寄语选择对话框
	 */
	private void showContentDialog(){
		DialogBuilderUtil builderUtil = new DialogBuilderUtil(ctx);
		builderUtil.setTitle("选择寄语");
		builderUtil.setSingleChoiceItems(contentArray, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int which) {
				contentList.add(which);
			}
		});
		builderUtil.setPositiveButton(ResourcesGet.getString(ctx, R.string.sure), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialogInterface, int which) {
				int size = contentList.size();
				String str = null;
				for (int i = 0; i < size; i++) {
					str = contentArray[contentList.get(i)];
				}
				editContent.setText(str);
			}
		});
		builderUtil.setNegativeButton(ResourcesGet.getString(ctx, R.string.cancel), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
		});
		builderUtil.show();
	}

	/**
	 * @return 获得通讯录中的手机号码
	 */
	private List<String> getPhoneNumber() {
		ContentResolver resolver = context.getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		List<String> list = new ArrayList<String>();
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				list.add(phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER)));
			}
			phoneCursor.close();
		}
		return list;
	}

	/**
	 * @return 获取通讯录中手机存储人的姓名
	 */
	private List<String> getPhoneName() {
		ContentResolver resolver = context.getContentResolver();
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		List<String> list = new ArrayList<String>();
		if (phoneCursor != null) {
			while (phoneCursor.moveToNext()) {
				list.add(phoneCursor.getString(phoneCursor.getColumnIndex(Phone.DISPLAY_NAME)));
			}
			phoneCursor.close();
		}
		return list;
	}
}
