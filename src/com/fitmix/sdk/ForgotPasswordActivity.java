package com.fitmix.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class ForgotPasswordActivity extends Activity {
	private EditText editEmail;
	private TextView textError;
	private final int MSG_ERROR_DISAPEAR = 10;
	private final int MSG_ERROR_DISAPEAR_TIME = 10 * 1000;
	private Animation anim_fade_in;
	private Animation anim_fade_out;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_forgot_password);
		editEmail = (EditText) findViewById(R.id.forgot_password_email);
		textError = (TextView) findViewById(R.id.rsp_error);
		anim_fade_in = AnimationUtils.loadAnimation(this, R.anim.error_fade_in);
		anim_fade_out = AnimationUtils.loadAnimation(this, R.anim.error_fade_out);
		
		anim_fade_out.setAnimationListener(new AnimationListener(){
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
		case R.id.forgot_password_submit:
			anim_fade_in.cancel();
			anim_fade_out.cancel();
			
			String sEmail = editEmail.getText().toString(); 
			if(!Util.isEmail(sEmail)){
				textError.setText(R.string.rsp_error_email_error);
				textError.setVisibility(View.VISIBLE);
				textError.startAnimation(anim_fade_in);
				myHandler.sendEmptyMessageDelayed(MSG_ERROR_DISAPEAR, MSG_ERROR_DISAPEAR_TIME);
				return;
			}
			new AlertDialog.Builder(this).setTitle(R.string.forgot_password_tip)
						.setMessage(R.string.forgot_password_message)  
						.setPositiveButton(R.string.forgot_password_ok,

				       new DialogInterface.OnClickListener() {

				           @Override

				           public void onClick(DialogInterface dialog, int which) {

				               finish();

				           }

				       }).show();
			break;
		}
	}
	Handler myHandler = new Handler(){
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
