package com.example.lovestudy.adapter;

import java.util.List;
import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseImageView;
import com.example.lovestudy.callback.AsyncImageLoaderCallBack;
import com.example.lovestudy.logic.function.DynamicSetViewSize;
import com.example.lovestudy.logic.function.ImageDownLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout.LayoutParams;

public class ImgCacheAdapter extends BaseListAdapter{
	
	public List<String> list;
	private GridView gridView;

	/** ͼƬ������ */
	private ImageDownLoader loader;

	@SuppressWarnings("unchecked")
	public ImgCacheAdapter(Context ctx, View view, List<?> list) {
		super(ctx, view, list);
		this.gridView = (GridView) view;
		this.list = (List<String>) list;
		this.gridView.setOnScrollListener(onScrollListener);
		loader = new ImageDownLoader(ctx);
	}

	@Override
	public View getViewNew(int position, View view, ViewGroup parent) {
		String url = list.get(position);
		if (view == null) {
			view = LayoutInflater.from(ctx).inflate(R.layout.item_image_layout,null);
		}
		BaseImageView imageView = (BaseImageView) view.findViewById(R.id.imageview);
		imageView.setLayoutParams(DynamicSetViewSize.getInstance().getLayoutParamsImageView(imageView, LayoutParams.MATCH_PARENT, 540));
		/** ����Tag����֤�첽����ͼƬ������ */
		imageView.setTag(url);
		setImageView(imageView, url);
		return view;
	}

	/**
	 * ����ͼƬ��Դ
	 */
	private void setImageView(ImageView imageView, String url) {
		Bitmap bitmap = loader.getBitmapCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		} else {
			imageView.setImageResource(R.drawable.empty_photo);
		}
	}

	/**
	 * ����ͼƬ����������û�У������Url����
	 */
	private void loadImage(int firstVisibleItem, int visibleItemCount) {
		Bitmap bitmap = null;
		for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
			String url = list.get(i);
			final ImageView imageView = (ImageView) gridView.findViewWithTag(url);
			/** �ȴӻ����л�ȡ*/
			bitmap = loader.getBitmapCache(url);
			if (bitmap != null) {
				imageView.setImageBitmap(bitmap);
			} else {
				imageView.setImageDrawable(ctx.getResources().getDrawable(R.drawable.empty_photo));
				/** ����URL����*/
				loader.loadImage(url, new AsyncImageLoaderCallBack() {

					@Override
					public void onImageLoader(Bitmap bitmap) {
						if (imageView != null && bitmap != null) {
							imageView.setImageBitmap(bitmap);
						}
					}
				});
			}
		}
	}
	
	/**
	 * ȡ����������
	 */
	public void cancelTasks() {
		loader.cancelTasks();
	}

	/**
	 * ���������¼�
	 */
	OnScrollListener onScrollListener = new OnScrollListener() {

		/** ��һ�ſɼ�Item�±� */
		private int firstVisibleItem;
		/** ÿ��Item�ɼ��� */
		private int visibleItemCount;
		/** �ж��Ƿ��һ�μ��� */
		private boolean isFirstEnter = true;

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			/** ��ֹͣ����ʱ������ͼƬ */
			if (scrollState == SCROLL_STATE_IDLE) {
				loadImage(firstVisibleItem, visibleItemCount);
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			this.firstVisibleItem = firstVisibleItem;
			this.visibleItemCount = visibleItemCount;
			if (isFirstEnter && visibleItemCount > 0) {
				loadImage(firstVisibleItem, visibleItemCount);
				isFirstEnter = false;
			}
		}
	};
	
}