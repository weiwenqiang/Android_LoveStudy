package com.example.lovestudy.fragment;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.constant.Constant;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.SharedPreferenceUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment {

	private SharedPreferenceUtil sharedPreferenceUtil;

	public BaseActivity ctx;

	public View view;

	abstract View onCreateViewNew(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ctx = (BaseActivity) getActivity();
		if (sharedPreferenceUtil == null) {
			sharedPreferenceUtil = new SharedPreferenceUtil(ctx);
		}
		return onCreateViewNew(inflater, container, savedInstanceState);
	}

	/**
	 * @param id
	 *            设置界面标题
	 */
	protected void setHeadTitle(int id) {
		setHeadTitle(id, false, false, null, 0);
	}

	/**
	 * @param id
	 * @param isShowBack
	 * @param isShowMore
	 * @param listenerMore
	 *            设置界面标题
	 */
	protected void setHeadTitle(int id, boolean isShowBack, boolean isShowMore, OnClickListener listenerMore, int moreIconId) {
		View layout = (RelativeLayout) view.findViewById(R.id.title);
		ImageView back = (ImageView) view.findViewById(R.id.img_back);
		TextView title = (TextView) layout.findViewById(R.id.txt_title);
		ImageView more = (ImageView) layout.findViewById(R.id.img_more);
		title.setText(ResourcesGet.getString(getActivity(), id));
		if (Integer.parseInt(sharedPreferenceUtil.getData(Constant.screenFullCode)) == 1) {
			layout.setPadding(0, BaseMethod.getStatusBarHeight(ctx), 0, 0);
		} else {
			layout.setPadding(0, 0, 0, 0);
		}
		if (isShowBack) {
			back.setVisibility(View.VISIBLE);
		}
		if (isShowMore) {
			more.setVisibility(View.VISIBLE);
		}
		if (moreIconId > 0) {
			more.setImageResource(moreIconId);
		}
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		more.setOnClickListener(listenerMore);
	}
}
