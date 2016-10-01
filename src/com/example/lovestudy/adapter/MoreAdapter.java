package com.example.lovestudy.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.lovestudy.ViewHolder.MoreViewHolder;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.activity.SplashActivity;
import com.example.lovestudy.activity.user.AboutActivity;
import com.example.lovestudy.activity.user.HelpActivity;
import com.example.lovestudy.activity.user.LoginActivity;
import com.example.lovestudy.activity.user.SettingActivity;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.entity.MoreBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MoreAdapter extends BaseListAdapter {

	private List<MoreBean> mList = new ArrayList<MoreBean>();
	private BaseActivity ctx;

	@SuppressWarnings("unchecked")
	public MoreAdapter(Context ctx, List<?> list) {
		super(ctx, list);
		this.mList = (List<MoreBean>) list;
		this.ctx = (BaseActivity) ctx;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		final MoreViewHolder holder;
		view = LayoutInflater.from(ctx).inflate(R.layout.item_more, null);
		holder = new MoreViewHolder(view);
		MoreBean moreBean = mList.get(position);
		holder.getView().setTag(moreBean);

		/** 是否加头 */
		if (moreBean.IsNewGroup == 1) {
			holder.layout.setVisibility(View.VISIBLE);
		} else if (moreBean.IsNewGroup == 0) {
			holder.layout.setVisibility(View.GONE);
		}
		/** 文字 */
		if (moreBean.Name != null && !"".equalsIgnoreCase(moreBean.Name) && moreBean.Name.length() > 0) {
			holder.Name.setText(moreBean.Name);
		}
		return view;
	}

	public OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			MoreBean moreBean = (MoreBean) view.getTag();
			String key = moreBean.Key;
			if ("About".equalsIgnoreCase(key)) {
				ctx.gotoTargetActivity(AboutActivity.class);
			}
			if ("Setting".equalsIgnoreCase(key)) {
				ctx.gotoTargetActivity(SettingActivity.class);
			}
			if ("Login".equalsIgnoreCase(key)) {
				ctx.gotoTargetActivity(LoginActivity.class);
			}
			if ("Help".equalsIgnoreCase(key)) {
				ctx.gotoTargetActivity(HelpActivity.class);
			}
			if ("Splash".equalsIgnoreCase(key)) {
				ctx.gotoTargetActivity(SplashActivity.class);
			}
		}
	};

}
