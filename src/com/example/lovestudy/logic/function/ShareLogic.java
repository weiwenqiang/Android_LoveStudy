package com.example.lovestudy.logic.function;

import java.io.File;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.example.lovestudy.base.BaseActivity;

public class ShareLogic {

	static ShareLogic shareLogic;

	public static ShareLogic getInstance() {
		if (shareLogic == null) {
			shareLogic = new ShareLogic();
		}
		return shareLogic;
	}

	/**
	 * @param msgContent
	 * @param title
	 *            �ı�����
	 */
	public void shareTxt(BaseActivity ctx, String msgContent, String title) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, msgContent);
		shareIntent.setType("text/*");
		ctx.startActivity(Intent.createChooser(shareIntent, title));
	}

	/**
	 * @param imgUri
	 * @param title
	 *            ͼƬ����
	 */
	public void shareImg(BaseActivity ctx, String imgUri, String title) {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imgUri)));
		shareIntent.setType("image/*");
		ctx.startActivity(Intent.createChooser(shareIntent, title));
	}

	/**
	 * @param ctx
	 * @param data
	 * @return ��ȡͼƬ·�������ֻ�SD���У�
	 */
	public String getImgPath(BaseActivity ctx, Intent data) throws Exception {
		String imgPath = null;
		Uri uri = data.getData();
		imgPath = uri.toString().replace("file://", "");
		return imgPath;
	}

	/**
	 * @param filePath
	 * @return ���ļ��л�ȡBitmap
	 */
	public Bitmap getImage(String filePath) throws Exception {
		Bitmap bitmap;
		bitmap = BitmapFactory.decodeFile(filePath);
		return bitmap;
	}
}
