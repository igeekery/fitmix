package com.fitmix.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

public class EmailRegisteActivity extends Activity {
	private EditText editUsername;
	private EditText editPassword;
	private EditText editEmail;
	private TextView textError;
	private final int MSG_ERROR_DISAPEAR = 10;
	private final int MSG_ERROR_DISAPEAR_TIME = 10 * 1000;

	private final int ERROR_NO_ERROR = 0;
	private final int ERROR_USERNAME_TOO_SHORT = 1;
	private final int ERROR_PASSWORD_TOO_SHORT = 2;
	private final int ERROR_FORMAT_ERROR = 3;
	private final int ERROR_USER_EXIST = 4;
	private Animation anim_fade_in;
	private Animation anim_fade_out;

	private String mEmailAddress;
	private String mUsername;
	private String mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_email_registe);
		editUsername = (EditText) findViewById(R.id.email_registe_username);
		editPassword = (EditText) findViewById(R.id.email_registe_password);
		editEmail = (EditText) findViewById(R.id.email_registe_email);
		textError = (TextView) findViewById(R.id.rsp_error);
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
		case R.id.email_registe_next:
			int error = ERROR_NO_ERROR;
			error = checkInputError();
			if (error == ERROR_NO_ERROR)
				error = checkRegisteOK();
			if (error != ERROR_NO_ERROR) {
				showErrorMsg(error);
				return;
			}
			setRegisteInfo();
			startActivity(new Intent(EmailRegisteActivity.this,
					PersonInfoActivity.class));

			break;
		}
	}

	private void init() {
		if (MyConfig.isDemoMode()) {
			setDemoData();
			refresh();
		}

	}

	private void setDemoData() {
		mUsername = MyConfig.getDefaultUsername();
		mPassword = MyConfig.getDefaultPassword();
		mEmailAddress = MyConfig.getDefaultEmailAddress();
	}

	private void refresh() {
		editUsername.setText(mUsername);
		editPassword.setText(mPassword);
		editEmail.setText(mEmailAddress);
	}

	private int checkInputError() {
		mUsername = editUsername.getText().toString();
		mPassword = editPassword.getText().toString();
		mEmailAddress = editEmail.getText().toString();
		if (mUsername.length() < MyConfig.getUsernameLengthMin())
			return ERROR_USERNAME_TOO_SHORT;
		if (mPassword.length() < MyConfig.getPasswordLengthMin())
			return ERROR_PASSWORD_TOO_SHORT;
		if (!Util.isEmail(mEmailAddress)) {
			return ERROR_FORMAT_ERROR;
		}

		return ERROR_NO_ERROR;
	}

	private int checkRegisteOK() {
		int error = ERROR_USER_EXIST;
		boolean bExist = false;
		if (!bExist)
			error = ERROR_NO_ERROR;
		return error;
	}

	private void setRegisteInfo() {
		MyConfig.setEmailAddress(mEmailAddress);
		MyConfig.setUsername(mUsername);
		MyConfig.setPassword(mPassword);
	}

	private void showErrorMsg(int errorno) {
		int msg = ERROR_NO_ERROR;
		switch (errorno) {
		case ERROR_USERNAME_TOO_SHORT:
			msg = R.string.rsp_error_username_length_error;
			break;
		case ERROR_PASSWORD_TOO_SHORT:
			msg = R.string.rsp_error_password_length_error;
			break;
		case ERROR_FORMAT_ERROR:
			msg = R.string.rsp_error_email_error;
			break;
		case ERROR_USER_EXIST:
			msg = R.string.rsp_error_user_exist;
			break;
		}
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
}
