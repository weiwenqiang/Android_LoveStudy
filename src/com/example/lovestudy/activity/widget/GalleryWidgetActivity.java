package com.example.lovestudy.activity.widget;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseGallery;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.constant.Urls;
import com.example.lovestudy.logic.function.DynamicSetViewSize;

public class GalleryWidgetActivity extends BaseActivity {

	private BaseImageView imageView;
	private BaseGallery gallery;
	private List<String> mImages = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widget_gallery_widget);
		setHeadTitle(R.string.Gallery);
		initData();
		initView();
	}
	
	private void initData() {
		mImages.add(Urls.GalleryUrl1);
		mImages.add(Urls.GalleryUrl2);
		mImages.add(Urls.GalleryUrl3);
		mImages.add(Urls.GalleryUrl4);
		mImages.add(Urls.GalleryUrl5);
		mImages.add(Urls.GalleryUrl6);
		mImages.add(Urls.GalleryUrl7);
		mImages.add(Urls.GalleryUrl8);
		mImages.add(Urls.GalleryUrl9);
		mImages.add(Urls.GalleryUrl10);
		mImages.add(Urls.GalleryUrl11);
		mImages.add(Urls.GalleryUrl12);
		mImages.add(Urls.GalleryUrl13);
		mImages.add(Urls.GalleryUrl14);
		mImages.add(Urls.GalleryUrl15);
	}

	private void initView() {
		imageView = (BaseImageView) findViewById(R.id.ImageView01);
		gallery = (BaseGallery) findViewById(R.id.Gallery01);

		gallery.setAdapter(new ImageAdapter(this));
		gallery.setOnItemClickListener(onItemClickListener);
		gallery.setOnItemSelectedListener(onItemSelectedListener);
	}

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
			imageView.setImageResource(view.getId());
		}
	};

	OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
			imageView.setImageUrl(mImages.get(position));
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	};

	public class ImageAdapter extends BaseAdapter {

		private Context ctx;

		public ImageAdapter(Context ctx) {
			this.ctx = ctx;
		}

		@Override
		public int getCount() {
			return mImages.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_image_layout, null);
			BaseImageView imageView = (BaseImageView) convertView.findViewById(R.id.imageview);
			imageView.setLayoutParams(DynamicSetViewSize.getInstance().getLayoutParamsImageView(imageView, 300, 400));
			imageView.setImageUrl(mImages.get(position));
			return convertView;
		}
	}

}
