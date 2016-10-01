package com.example.lovestudy.utils;

import com.example.lovestudy.activity.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogBuilderUtil {

	public BaseAlertDialog mDialog;
	private Context mContext;
	private View v;

	/**
	 * @param context
	 *            构造函数
	 */
	public DialogBuilderUtil(Context context) {
		mContext = context;
		mDialog = new BaseAlertDialog(context);
		v = LayoutInflater.from(context).inflate(R.layout.widget_alertdialog_layout, null);
	}

	/**
	 * @param context
	 * @param style
	 *            构造函数
	 */
	public DialogBuilderUtil(Context context, int style) {
		mContext = context;
		mDialog = new BaseAlertDialog(context, style);
		v = LayoutInflater.from(context).inflate(R.layout.widget_alertdialog_layout, null);
	}

	/**
	 * @param titleId
	 * @return 设置对话框标题--通过ID
	 */
	public DialogBuilderUtil setTitle(int titleId) {
		TextView view = (TextView) v.findViewById(R.id.title);
		view.setText(titleId);
		view.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @param title
	 * @return 设置对话框标题--通过文字
	 */
	public DialogBuilderUtil setTitle(CharSequence title) {
		TextView view = (TextView) v.findViewById(R.id.title);
		view.setText(title);
		view.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @param messageId
	 * @return 设置对话框内容--通过ID
	 */
	public DialogBuilderUtil setMessage(int messageId) {
		TextView view = (TextView) v.findViewById(R.id.desc);
		view.setMovementMethod(new ScrollingMovementMethod());
		view.setText(messageId);
		view.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @param message
	 * @return 设置对话框内容--通过文字
	 */
	public DialogBuilderUtil setMessage(CharSequence message) {
		TextView view = (TextView) v.findViewById(R.id.desc);
		view.setMovementMethod(new ScrollingMovementMethod());
		view.setText(message);
		view.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @param textId
	 * @param listener
	 * @return 设置对话框确定按钮--通过ID
	 */
	public DialogBuilderUtil setPositiveButton(int textId, final OnClickListener listener) {
		View line = v.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		TextView view = (TextView) v.findViewById(R.id.sure);
		view.setText(textId);
		view.setVisibility(View.VISIBLE);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(mDialog, Dialog.BUTTON_POSITIVE);
				}
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
			}
		});
		return this;
	}

	/**
	 * @param text
	 * @param listener
	 * @return 设置对话框确定按钮--通过文字
	 */
	public DialogBuilderUtil setPositiveButton(CharSequence text, final OnClickListener listener) {
		View line = v.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		TextView view = (TextView) v.findViewById(R.id.sure);
		view.setText(text);
		view.setVisibility(View.VISIBLE);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(mDialog, Dialog.BUTTON_POSITIVE);
				}
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
			}
		});
		return this;
	}

	/**
	 * @param bg
	 * @param textColor
	 * @return 设置对话框确定按钮的背景--通过ID
	 */
	public DialogBuilderUtil setPositiveButtonBG(int bg, int textColor) {
		TextView view = (TextView) v.findViewById(R.id.sure);
		view.setTextColor(textColor);
		view.setBackgroundResource(bg);
		return this;
	}

	/**
	 * @param bg
	 * @param textColor
	 * @return 设置对话框确定按钮的背景--通过Drawable
	 */
	@SuppressWarnings("deprecation")
	public DialogBuilderUtil setPositiveButtonBG(Drawable bg, int textColor) {
		TextView view = (TextView) v.findViewById(R.id.sure);
		view.setTextColor(textColor);
		view.setBackgroundDrawable(bg);
		return this;
	}

	/**
	 * @param textId
	 * @param listener
	 * @return 设置对话框取消按钮--通过ID
	 */
	public DialogBuilderUtil setNegativeButton(int textId, final OnClickListener listener) {
		View line = v.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		TextView view = (TextView) v.findViewById(R.id.cancel);
		view.setText(textId);
		view.setVisibility(View.VISIBLE);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(mDialog, Dialog.BUTTON_NEGATIVE);
				}
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
			}
		});
		return this;
	}

	/**
	 * @param text
	 * @param listener
	 * @return 设置对话框取消按钮--通过文字
	 */
	public DialogBuilderUtil setNegativeButton(CharSequence text, final OnClickListener listener) {
		View line = v.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		TextView view = (TextView) v.findViewById(R.id.cancel);
		view.setText(text);
		view.setVisibility(View.VISIBLE);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(mDialog, Dialog.BUTTON_NEGATIVE);
				}
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
			}
		});
		return this;
	}

	/**
	 * @param textId
	 * @param listener
	 * @return 设置对话框再想想按钮--通过ID
	 */
	public DialogBuilderUtil setNeutralButton(int textId, final OnClickListener listener) {
		View line = v.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		TextView view = (TextView) v.findViewById(R.id.Neutral);
		view.setText(textId);
		view.setVisibility(View.VISIBLE);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(mDialog, Dialog.BUTTON_NEUTRAL);
				}
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
			}
		});
		return this;
	}

	/**
	 * @param text
	 * @param listener
	 * @return 设置对话框再想想按钮--通过文字
	 */
	public DialogBuilderUtil setNeutralButton(CharSequence text, final OnClickListener listener) {
		View line = v.findViewById(R.id.line);
		line.setVisibility(View.VISIBLE);
		TextView view = (TextView) v.findViewById(R.id.Neutral);
		view.setText(text);
		view.setVisibility(View.VISIBLE);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClick(mDialog, Dialog.BUTTON_NEUTRAL);
				}
				if (mDialog.isShowing()) {
					mDialog.dismiss();
				}
			}
		});
		return this;
	}

	/**
	 * @param itemsId
	 * @param checkedItem
	 * @param listener
	 * @return 设置单项选择视图
	 */
	public DialogBuilderUtil setSingleChoiceItems(int itemsId, int checkedItem, final OnClickListener listener) {
		CharSequence[] items = mContext.getResources().getTextArray(itemsId);
		final Adapter adapter = new Adapter();
		adapter.checkedPosition = checkedItem;
		adapter.items = items;
		adapter.showCheckbox = true;
		ListView lv = (ListView) v.findViewById(R.id.listview);
		if (items.length > 5) {
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv.getLayoutParams();
			lp.height = dip2px(mContext, 45) * 5 + 10;
			lv.setLayoutParams(lp);
		}
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.checkedPosition = position;
				listener.onClick(mDialog, position);
			}
		});
		lv.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @param items
	 * @param checkedItem
	 * @param listener
	 * @return 设置单项选择视图
	 */
	public DialogBuilderUtil setSingleChoiceItems(CharSequence[] items, int checkedItem, final OnClickListener listener) {
		final Adapter adapter = new Adapter();
		adapter.checkedPosition = checkedItem;
		adapter.items = items;
		adapter.showCheckbox = true;
		ListView lv = (ListView) v.findViewById(R.id.listview);
		if (items.length > 5) {
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv.getLayoutParams();
			lp.height = dip2px(mContext, 45) * 5 + 10;
			lv.setLayoutParams(lp);
		}
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.checkedPosition = position;
				listener.onClick(mDialog, position);
			}
		});
		lv.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @param context
	 * @param dipValue
	 * @return 将dp转换成px
	 */
	private int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * @param view
	 * @return 设置自定义视图(固定左右边距为20dp)
	 */
	public DialogBuilderUtil setView(View view) {
		setView(view, (int) mContext.getResources().getDimension(R.dimen.length_20), (int) mContext.getResources().getDimension(R.dimen.length_20));
		return this;
	}

	/**
	 * @param view
	 * @return 设置自定义视图(没有默认边距的)
	 */
	public DialogBuilderUtil setViewNoPadding(View view) {
		setView(view, 0, 0);
		return this;
	}

	/**
	 * @param view
	 * @param left
	 * @param right
	 * @return 设置自定义视图(可手动设置边距)
	 */
	public DialogBuilderUtil setView(View view, int left, int right) {
		ViewGroup viewG = (ViewGroup) v.findViewById(R.id.custom_view);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = left;
		lp.rightMargin = right;
		viewG.addView(view, lp);
		viewG.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * @return 显示对话框
	 */
	public DialogBuilderUtil show() {
		TextView view1 = (TextView) v.findViewById(R.id.desc);
		TextView view2 = (TextView) v.findViewById(R.id.title);
		if (view1.getVisibility() == View.VISIBLE && view2.getVisibility() == View.GONE) {
			View topmargin = v.findViewById(R.id.topmargin);
			topmargin.setVisibility(View.VISIBLE);
		}
		mDialog.show();
		return this;
	}

	/**
	 * 关闭对话框
	 */
	public void cancelDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}

	private class Adapter extends BaseAdapter {

		CharSequence[] items;
		int checkedPosition = -1;
		boolean showCheckbox = false;

		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			return items[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = LayoutInflater.from(mContext).inflate(R.layout.widget_alertdialog_listview_item, null);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
				holder.checkBox.setClickable(false);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			if (showCheckbox) {
				holder.checkBox.setVisibility(View.VISIBLE);
			} else {
				holder.checkBox.setVisibility(View.GONE);
			}
			holder.title.setText(items[position]);
			if (checkedPosition == position) {
				holder.checkBox.setChecked(true);
			} else {
				holder.checkBox.setChecked(false);
			}
			return convertView;
		}
	}

	private class Holder {
		TextView title;
		CheckBox checkBox;
	}

	public class BaseAlertDialog extends AlertDialog {

		protected BaseAlertDialog(Context context) {
			super(context);
		}

		protected BaseAlertDialog(Context context, int style) {
			super(context, style);
		}

		public void show() {
			try {
				if (getContext() instanceof Activity) {
					if (((Activity) getContext()).isFinishing()) {
						return;
					}
				}
				super.show();
				getWindow().setContentView(v);
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.width = WindowManager.LayoutParams.MATCH_PARENT;
				getWindow().setAttributes(lp);
				getWindow().setGravity(Gravity.BOTTOM);
				getWindow().setBackgroundDrawable(null);
				getWindow().setWindowAnimations(R.style.dialog_show_anim);
				// 不加这句，软键盘弹不出来
				getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 设置能否点击外围消失
	 */
	public void setCanceledOnTouchOutside(boolean b) {
		mDialog.setCanceledOnTouchOutside(b);
	}

	/**
	 * 获取显示的状态
	 */
	public boolean isShowing() {
		return mDialog.isShowing();
	}

	/**
	 * 对话框消失的方法
	 */
	public void dismiss() {
		if (mDialog != null) {
			try {
				mDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
