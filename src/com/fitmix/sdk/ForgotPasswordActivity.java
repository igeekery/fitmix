package com.fitmix.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordActivity extends Activity {
	private EditText editEmail;
	private TextView textError;
	private final int MSG_ERROR_DISAPEAR = 10;
	private final int MSG_ERROR_DISAPEAR_TIME = 10 * 1000;

	private final int ERROR_NO_ERROR = 0;
	private final int ERROR_FORMAT_ERROR = 1;
	private final int ERROR_WRONG_EMAIL_ADDRESS = 2;
	private Animation anim_fade_in;
	private Animation anim_fade_out;

	private String mUsername;
	private String mPassword;
	private String mEmailAddress;
	private String mEmailTitle;
	private String mUsernameTip;
	private String mPasswordTip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forgot_password);
		editEmail = (EditText) findViewById(R.id.forgot_password_email);
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
		case R.id.forgot_password_submit:
			int errorno = checkInputError();
			if (errorno != ERROR_NO_ERROR) {
				showErrorMsg(errorno);
				return;
			}
			new AlertDialog.Builder(this)
					.setTitle(R.string.forgot_password_tip)
					.setMessage(R.string.forgot_password_message)
					.setPositiveButton(R.string.forgot_password_ok,

					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							getUserInfo();
							sendEmail();
							finish();
						}

					}).show();
			break;
		}
	}

	private void init() {
		if (MyConfig.isDemoMode())
			setDemoData();
		refresh();
	}

	private void setDemoData() {
		mEmailAddress = MyConfig.getDefaultEmailAddress();

	}

	private void refresh() {
		editEmail.setText(mEmailAddress);

	}

	private void getUserInfo() {
		mUsername = MyConfig.getUsername();
		mPassword = MyConfig.getPassword();
	}

	private void sendEmail() {
		mEmailTitle = getResources().getString(R.string.email_title);
		mUsernameTip = getResources().getString(R.string.email_username_tip);
		mPasswordTip = getResources().getString(R.string.email_password_tip);

		String sEmailContent = mUsernameTip + mUsername + "\r\n" + mPasswordTip
				+ mPassword;

		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("mailto:" + mEmailAddress));
		intent.putExtra(Intent.EXTRA_SUBJECT, mEmailTitle);
		intent.putExtra(Intent.EXTRA_TEXT, sEmailContent);

		startActivity(intent);
	}

	private int checkInputError() {
		mEmailAddress = editEmail.getText().toString();
		if (!Util.isEmail(mEmailAddress)) {
			return ERROR_FORMAT_ERROR;
		}

		if (!mEmailAddress.equals(MyConfig.getEmailAddress()))
			return ERROR_WRONG_EMAIL_ADDRESS;
		return ERROR_NO_ERROR;
	}

	private void showErrorMsg(int errorno) {
		int msg = ERROR_NO_ERROR;
		switch (errorno) {
		case ERROR_FORMAT_ERROR:
			msg = R.string.rsp_error_email_error;
			break;
		case ERROR_WRONG_EMAIL_ADDRESS:
			msg = R.string.rsp_error_email_address;
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
