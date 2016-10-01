package com.example.lovestudy.fragment;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.user.DatingActivity;
import com.example.lovestudy.activity.user.EntertainmentActivity;
import com.example.lovestudy.activity.user.HeadPortraitActivity;
import com.example.lovestudy.activity.user.LearnActivity;
import com.example.lovestudy.activity.user.LifeActivity;
import com.example.lovestudy.activity.user.LoginActivity;
import com.example.lovestudy.activity.user.MoreActivity;
import com.example.lovestudy.activity.user.NickNameActivity;
import com.example.lovestudy.activity.user.PersonalDataActivity;
import com.example.lovestudy.activity.user.ResearchActivity;
import com.example.lovestudy.activity.user.WorkActivity;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.constant.Urls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserFragment extends BaseFragment {

	private View viewHead;
	private BaseImageView imgBg;
	private BaseImageView imgUserHead;
	private TextView txtNickName;
	private TextView txtInfo;
	private TextView txtLogin;
	private LinearLayout moduleLeft;
	private LinearLayout moduleCenter;
	private LinearLayout moduleRight;
	
	private View viewContent;
	private LinearLayout moduleOne;
	private LinearLayout moduleTwo;
	private LinearLayout moduleThree;
	private LinearLayout moduleFour;
	private LinearLayout moduleFive;
	private LinearLayout moduleSix;

	@Override
	View onCreateViewNew(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_layout_user, container, false);
		setHeadTitle(R.string.user_view, false, true, listenerMore, R.drawable.icon_setting);
		initView();
		return view;
	}

	private void initView() {
		initHead();
		initContent();
	}

	private void initHead() {
		viewHead = view.findViewById(R.id.head);
		imgBg = (BaseImageView) viewHead.findViewById(R.id.img_bg);
		imgUserHead = (BaseImageView) viewHead.findViewById(R.id.img_head);
		txtNickName = (TextView) viewHead.findViewById(R.id.txt_nickname);
		txtInfo = (TextView) viewHead.findViewById(R.id.txt_info);
		txtLogin = (TextView) viewHead.findViewById(R.id.txt_login);
		moduleLeft = (LinearLayout) viewHead.findViewById(R.id.module_left);
		moduleCenter = (LinearLayout) viewHead.findViewById(R.id.module_center);
		moduleRight = (LinearLayout) viewHead.findViewById(R.id.module_right);

		imgBg.isSetDefaultImage(R.drawable.icon_user_bg);
		imgUserHead.setCircularImageUrl(Urls.UserHead);
		txtNickName.setOnClickListener(listenerHead);
		txtInfo.setOnClickListener(listenerHead);
		txtLogin.setOnClickListener(listenerHead);
		moduleLeft.setOnClickListener(listenerHead);
		moduleCenter.setOnClickListener(listenerHead);
		moduleRight.setOnClickListener(listenerHead);
		imgUserHead.setOnClickListener(listenerHead);
	}

	private void initContent() {
		viewContent = view.findViewById(R.id.content);
		moduleOne = (LinearLayout) viewContent.findViewById(R.id.module_one);
		moduleTwo = (LinearLayout) viewContent.findViewById(R.id.module_two);
		moduleThree = (LinearLayout) viewContent.findViewById(R.id.module_three);
		moduleFour = (LinearLayout) viewContent.findViewById(R.id.module_four);
		moduleFive = (LinearLayout) viewContent.findViewById(R.id.module_five);
		moduleSix = (LinearLayout) viewContent.findViewById(R.id.module_six);
		
		moduleOne.setOnClickListener(listenerContent);
		moduleTwo.setOnClickListener(listenerContent);
		moduleThree.setOnClickListener(listenerContent);
		moduleFour.setOnClickListener(listenerContent);
		moduleFive.setOnClickListener(listenerContent);
		moduleSix.setOnClickListener(listenerContent);
	}

	private OnClickListener listenerMore = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			ctx.gotoTargetActivity(MoreActivity.class);
		}
	};

	private OnClickListener listenerHead = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.img_head:
				ctx.gotoTargetActivity(HeadPortraitActivity.class);
				break;
			case R.id.txt_info:
				ctx.gotoTargetActivity(PersonalDataActivity.class);
				break;
			case R.id.txt_nickname:
				ctx.gotoTargetActivity(NickNameActivity.class);
				break;
			case R.id.txt_login:
				ctx.gotoTargetActivity(LoginActivity.class);
				break;
			case R.id.module_left:
				BaseMethod.showToast(ctx, "left");
				break;
			case R.id.module_center:
				BaseMethod.showToast(ctx, "center");
				break;
			case R.id.module_right:
				BaseMethod.showToast(ctx, "right");
				break;
			}
		}
	};
	
	private OnClickListener listenerContent = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.module_one:
				ctx.gotoTargetActivity(LifeActivity.class);
				break;
			case R.id.module_two:
				ctx.gotoTargetActivity(ResearchActivity.class);
				break;
			case R.id.module_three:
				ctx.gotoTargetActivity(EntertainmentActivity.class);
				break;
			case R.id.module_four:
				ctx.gotoTargetActivity(LearnActivity.class);
				break;
			case R.id.module_five:
				ctx.gotoTargetActivity(WorkActivity.class);
				break;
			case R.id.module_six:
				ctx.gotoTargetActivity(DatingActivity.class);
				break;
			}
		}
	};
}
