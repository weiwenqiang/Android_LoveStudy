package com.example.lovestudy.activity.function;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.coverFlow.CoverFlowAdapter;
import com.example.lovestudy.coverFlow.FeatureCoverFlow;
import com.example.lovestudy.coverFlow.GameEntity;

public class ImgCoverFlowActivity extends BaseActivity {

	private FeatureCoverFlow mCoverFlow;
	private CoverFlowAdapter mAdapter;
	private ArrayList<GameEntity> mData = new ArrayList<GameEntity>(0);
	private TextSwitcher mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_img_cover_flow);
		setHeadTitle(R.string.cover_flow_img);
		initView();
	}

	private void initView() {
		mData.add(new GameEntity(R.drawable.image_1, R.string.title1));
		mData.add(new GameEntity(R.drawable.image_2, R.string.title2));
		mData.add(new GameEntity(R.drawable.image_3, R.string.title3));
		mData.add(new GameEntity(R.drawable.image_4, R.string.title4));

		mTitle = (TextSwitcher) findViewById(R.id.title_item);
		mTitle.setFactory(new ViewSwitcher.ViewFactory() {
			@Override
			public View makeView() {
				LayoutInflater inflater = LayoutInflater.from(ctx);
				TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
				return textView;
			}
		});
		Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
		Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
		mTitle.setInAnimation(in);
		mTitle.setOutAnimation(out);

		mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
		mAdapter = new CoverFlowAdapter(this);
		mAdapter.setData(mData);
		mCoverFlow.setAdapter(mAdapter);

		mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				BaseMethod.showToast(ctx, getResources().getString(mData.get(position).titleResId));
			}
		});

		mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
			@Override
			public void onScrolledToPosition(int position) {
				mTitle.setText(getResources().getString(mData.get(position).titleResId));
			}

			@Override
			public void onScrolling() {
				mTitle.setText("");
			}
		});
	}

	@Override
	protected boolean getFlingBackFeature(boolean b) {
		return super.getFlingBackFeature(false);
	}

}
