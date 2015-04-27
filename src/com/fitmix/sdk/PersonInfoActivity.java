package com.fitmix.sdk;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonInfoActivity extends Activity {
	private SwitchButton btnSex;
	private EditText editAge;
	private EditText editHeight;
	private EditText editWeight;
	private TextView textError;
	private SwitchButton btnUnit;
	private ImageView imagePhoto;

	private boolean bFemal;
	private boolean bEnglishUnit;
	private int mAge;
	private int mHeight;
	private int mWeight;

	private final int ERROR_NO_ERROR = 0;
	private final int MSG_ERROR_DISAPEAR = 10;
	private final int MSG_ERROR_DISAPEAR_TIME = 10 * 1000;

	private Animation anim_fade_in;
	private Animation anim_fade_out;

	private final static int REQUEST_CODE_CAPTURE_CAMEIA = 10;
	private final static int REQUEST_CODE_PICK_IMAGE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_person_info);
		btnSex = (SwitchButton) findViewById(R.id.person_info_sex);
		editAge = (EditText) findViewById(R.id.person_info_age);
		editHeight = (EditText) findViewById(R.id.person_info_height);
		editWeight = (EditText) findViewById(R.id.person_info_weight);
		btnUnit = (SwitchButton) findViewById(R.id.person_info_unit);
		textError = (TextView) findViewById(R.id.rsp_error);
		imagePhoto = (ImageView) findViewById(R.id.photo);
		anim_fade_in = AnimationUtils.loadAnimation(this, R.anim.error_fade_in);
		anim_fade_out = AnimationUtils.loadAnimation(this,
				R.anim.error_fade_out);

		anim_fade_out.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				textError.setVisibility(View.GONE);
			}

			public void onAnimationRepeat(Animation animation) {

			}
		});
		init();
	}

	public void myClickHandler(View v) {
		switch (v.getId()) {
		case R.id.photo:
			takePhoto();
			getPhoto();
			break;
		case R.id.person_info_next:
			int error = ERROR_NO_ERROR;
			error = checkInputError();
			if (error != ERROR_NO_ERROR) {
				showErrorMsg(error);
				return;
			}
			setPersonInfo();
			startActivity(new Intent(PersonInfoActivity.this,
					OtherSettingActivity.class));
			break;
		}
	}

	private void init() {
		if (MyConfig.isDemoMode()) {
			setDemoData();
			refresh();
		}
		getPhoto();
	}

	private void setDemoData() {
		mAge = MyConfig.getDefaultAge();
		mHeight = MyConfig.getDefaultHeight();
		mWeight = MyConfig.getDefaultWeight();
		bFemal = (MyConfig.getDefaultSex() == 1) ? true : false;
		bEnglishUnit = (MyConfig.getDefaultUnit() == 1) ? true : false;
	}

	private void refresh() {
		editAge.setText("" + mAge);
		editHeight.setText("" + mHeight);
		editWeight.setText("" + mWeight);
		btnSex.setState(bFemal);
		btnUnit.setState(bEnglishUnit);
	}

	private int checkInputError() {
		mAge = Integer.parseInt(editAge.getText().toString());
		mHeight = Integer.parseInt(editHeight.getText().toString());
		mWeight = Integer.parseInt(editWeight.getText().toString());
		bFemal = btnSex.getState();
		bEnglishUnit = btnUnit.getState();

		return ERROR_NO_ERROR;
	}

	private void setPersonInfo() {
		MyConfig.setAge(mAge);
		MyConfig.setHeight(mHeight);
		MyConfig.setWeight(mWeight);
		MyConfig.setSex(bFemal ? 1 : 0);
		MyConfig.setUnit(bEnglishUnit ? 1 : 0);

	}

	private void showErrorMsg(int errorno) {
		int msg = ERROR_NO_ERROR;
		if (msg == ERROR_NO_ERROR)
			return;
		anim_fade_in.cancel();
		anim_fade_out.cancel();
		textError.setText(msg);
		textError.setVisibility(View.VISIBLE);
		textError.startAnimation(anim_fade_in);
		myHandler.sendEmptyMessageDelayed(MSG_ERROR_DISAPEAR,
				MSG_ERROR_DISAPEAR_TIME);
	}

	@SuppressLint("HandlerLeak")
	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_ERROR_DISAPEAR:
				textError.startAnimation(anim_fade_out);
				break;
			}
			super.handleMessage(msg);
		}
	};

	private boolean getPhoto() {
		Bitmap bm = Util.getPhotoFile();
		if (bm == null)
			return false;
		imagePhoto.setImageBitmap(bm);

		return true;
	}

	private void takePhoto() {
		File file = new File(MyConfig.getDataPath());
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Intent getImageByCamera = new Intent(
					"android.media.action.IMAGE_CAPTURE");
			startActivityForResult(getImageByCamera,
					REQUEST_CODE_CAPTURE_CAMEIA);
		} else {
			Toast.makeText(this, R.string.insert_sd_card, Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_PICK_IMAGE) {
			// Uri uri = data.getData();

		} else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
			Uri uri = data.getData();
			if (uri == null) {
				Bundle bundle = data.getExtras();
				if (bundle != null) {
					Bitmap photo = (Bitmap) bundle.get("data");
					Util.saveImage(photo, Util.getPhotoFilename());
				} else {
					Toast.makeText(this, R.string.take_photo_error,
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		}
	}

}
