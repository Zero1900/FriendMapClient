package com.example.friendmap.ui;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.friendmap.R;
import com.example.friendmap.user.DataManager;
import com.example.friendmap.user.DataUserInfo;

public class MainTabUser extends Fragment {
	private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果

	private ImageView mFace;
	private Bitmap bitmap;

	/* 头像名称 */
	private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
	private File tempFile;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab_user, container, false);

		((TextView) view.findViewById(R.id.ui_title_text))
				.setText(getResources().getString(R.string.ui_title_tab_user));

		DataUserInfo dataUserInfo = DataManager.getInstance().getDataUserInfo();
		String username = dataUserInfo.getUserName();
		String nickname = dataUserInfo.getNickName();

		((TextView) view.findViewById(R.id.ui_tab_user_info_username_textview)).setText(username);
		((TextView) view.findViewById(R.id.ui_tab_user_info_nickname_textview)).setText(nickname);

		// 头像的点击事件
		mFace = (ImageView) view.findViewById(R.id.ui_tab_user_info_avatar_imageview);
		mFace.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				onClickAvatar(view);
			}
		});

		return view;
	}

	public void onClickAvatar(View view) {
		Log.i("点击头像", "22");
		/* 列表对话框 */
		String mList[] = { getResources().getString(R.string.ui_tab_user_camera),
				getResources().getString(R.string.ui_tab_user_gallery), getResources().getString(R.string.ui_cancel) };
		AlertDialog.Builder listDia = new AlertDialog.Builder(this.getActivity());
		listDia.setTitle("列表对话框");
		listDia.setItems(mList, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int witch) {
				// TODO Auto-generated method stub
				/* 下标是从0开始的 */
				switch (witch) {
				case 0:// 拍照
					chooseFromCamera();
					break;
				case 1:// 从相册选择
					chooseFromGallery();
					break;
				default:// 取消
					break;
				}
			}
		});
		listDia.create().show();
	}

	/* 显示点击的内容 */
	private void showToast(String message) {
		Toast.makeText(this.getActivity(), "你选择的是: " + message, Toast.LENGTH_SHORT).show();
	}

	/*
	 * 从相册获取
	 */
	public void chooseFromGallery() {
		// 激活系统图库，选择一张图片
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}

	/*
	 * 从相机获取
	 */
	public void chooseFromCamera() {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if (hasSdcard()) {
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
		}
		startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_REQUEST_GALLERY) {
			if (data != null) {
				// 得到图片的全路径
				Uri uri = data.getData();
				crop(uri);
			}

		} else if (requestCode == PHOTO_REQUEST_CAMERA) {
			if (hasSdcard()) {
				tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
				crop(Uri.fromFile(tempFile));
			} else {
				showToast("未找到存储卡，无法存储照片！");
			}

		} else if (requestCode == PHOTO_REQUEST_CUT) {

			bitmap = data.getParcelableExtra("data");
			this.mFace.setImageBitmap(bitmap);
			if (tempFile != null) {
				boolean delete = tempFile.delete();
				System.out.println("delete = " + delete);
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 剪切图片
	 * 
	 * @function:
	 * @author:Jerry
	 * @date:2013-12-30
	 * @param uri
	 */
	private void crop(Uri uri) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 160);
		intent.putExtra("outputY", 160);
		// 图片格式
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	private boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
