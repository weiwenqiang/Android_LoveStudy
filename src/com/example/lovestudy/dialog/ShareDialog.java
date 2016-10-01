package com.example.lovestudy.dialog;

import com.example.lovestudy.activity.R;
import com.example.lovestudy.base.BaseActivity;
import com.example.lovestudy.base.BaseMethod;
import com.example.lovestudy.logic.function.ShareLogic;
import com.example.lovestudy.resources.ResourcesGet;
import com.example.lovestudy.utils.DialogBuilderUtil;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ShareDialog {

	static ShareDialog shareImgDialog;

	public static ShareDialog getInstance() {
		if (shareImgDialog == null) {
			shareImgDialog = new ShareDialog();
		}
		return shareImgDialog;
	}

	/**
	 * @param ctx
	 *            展示分享图片对话框
	 */
	public void showShareImgDialog(final BaseActivity ctx) {
		DialogBuilderUtil builder = new DialogBuilderUtil(ctx).setTitle(ResourcesGet.getString(ctx, R.string.share_img))
				.setPositiveButton(ResourcesGet.getString(ctx, R.string.choose_img), new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						BaseMethod.openPhotoAlbum(ctx);
					}
				}).setNegativeButton(ResourcesGet.getString(ctx, R.string.cancel), new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}
				});
		builder.show();
	}

	/**
	 * @param ctx
	 *            展示分享文本对话框
	 */
	public void showShareTxtDialog(final BaseActivity ctx) {
		View view = LayoutInflater.from(ctx).inflate(R.layout.layout_share_txt, null);
		final EditText editText = (EditText) view.findViewById(R.id.edit_share_txt);
		DialogBuilderUtil builder = new DialogBuilderUtil(ctx).setTitle(ResourcesGet.getString(ctx, R.string.share_txt)).setView(view)
				.setPositiveButton(ResourcesGet.getString(ctx, R.string.sure), new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						if (editText.getText().toString().length() > 0) {
							ShareLogic.getInstance().shareTxt(ctx, editText.getText().toString(), ResourcesGet.getString(ctx, R.string.share_txt));
						} else {
							BaseMethod.showToast(ctx, ResourcesGet.getString(ctx, R.string.empty_content_error));
						}
					}
				}).setNegativeButton(ResourcesGet.getString(ctx, R.string.cancel), new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}
				});
		builder.show();
	}
}
