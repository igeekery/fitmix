package com.fitmix.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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
	private Animation anim_fade_in;
	private Animation anim_fade_out;

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
	}

	public void myClickHandler(View v) {
		switch (v.getId()) {
		case R.id.email_registe_next:
			String sUsername = editUsername.getText().toString();
			String sPassword = editPassword.getText().toString();
			String sEmail = editEmail.getText().toString();
			anim_fade_in.cancel();
			anim_fade_out.cancel();
/*
			if (!Util.isEmail(sEmail)) {
				textError.setText(R.string.rsp_error_email_error);
				textError.setVisibility(View.VISIBLE);
				textError.startAnimation(anim_fade_in);
				myHandler.sendEmptyMessageDelayed(MSG_ERROR_DISAPEAR,
						MSG_ERROR_DISAPEAR_TIME);
				return;
			}
			if (sPassword.length() < 6) {
				textError.setText(R.string.rsp_error_password_length_error);
				textError.setVisibility(View.VISIBLE);
				textError.startAnimation(anim_fade_in);
				myHandler.sendEmptyMessageDelayed(MSG_ERROR_DISAPEAR,
						MSG_ERROR_DISAPEAR_TIME);
				return;
			}
*/			
			startActivity(new Intent(EmailRegisteActivity.this,
					PersonInfoActivity.class));

			break;
		}
	}

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
