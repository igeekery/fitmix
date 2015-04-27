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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EmailLoginActivity extends Activity {
	private EditText editUsername;
	private EditText editPassword;
	private TextView textError;
	private ImageView imagePhoto;
	private final int MSG_ERROR_DISAPEAR = 10;
	private final int MSG_ERROR_DISAPEAR_TIME = 10 * 1000;
	private final int ERROR_NO_ERROR = 0;
	private final int ERROR_USERNAME_TOO_SHORT = 1;
	private final int ERROR_PASSWORD_TOO_SHORT = 2;
	private final int ERROR_CHECK_INVALIDE = 3;
	private String mUsername;
	private String mPassword;

	private Animation anim_fade_in;
	private Animation anim_fade_out;
	private final static int REQUEST_CODE_CAPTURE_CAMEIA = 10;
	private final static int REQUEST_CODE_PICK_IMAGE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_email_login);

		editUsername = (EditText) findViewById(R.id.email_login_username);
		editPassword = (EditText) findViewById(R.id.email_login_password);
		imagePhoto = (ImageView) findViewById(R.id.photo);
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

	private void init() {
		MyConfig.loadConfig(this);
		if (MyConfig.isDemoMode()) {
			setDemoData();
			refresh();
		}
		getPhoto();
	}

	private void setDemoData() {
		mUsername = MyConfig.getUsername();
		mPassword = MyConfig.getPassword();

	}

	private void refresh() {
		editUsername.setText(mUsername);
		editPassword.setText(mPassword);
	}

	public void myClickHandler(View v) {
		switch (v.getId()) {
		case R.id.photo:
			takePhoto();
			getPhoto();
			break;
		case R.id.email_login_forgot_password:
			startActivity(new Intent(EmailLoginActivity.this,
					ForgotPasswordActivity.class));
			break;
		case R.id.email_login_login:
			int error = ERROR_NO_ERROR;
			error = checkInputError();
			if (error == ERROR_NO_ERROR)
				error = checkLoginOK();
			if (error != ERROR_NO_ERROR) {
				showErrorMsg(error);
				return;
			}
			setLoginInfo();
			startActivity(new Intent(EmailLoginActivity.this,
					MusicMainActivity.class));
			break;
		}
	}

	private int checkInputError() {
		mUsername = editUsername.getText().toString();
		mPassword = editPassword.getText().toString();
		if (mUsername.length() < MyConfig.getUsernameLengthMin())
			return ERROR_USERNAME_TOO_SHORT;
		if (mPassword.length() < MyConfig.getPasswordLengthMin())
			return ERROR_PASSWORD_TOO_SHORT;
		return ERROR_NO_ERROR;
	}

	private int checkLoginOK() {
		int error = ERROR_CHECK_INVALIDE;
		if (mUsername.equals(MyConfig.getUsername())
				&& mPassword.equals(MyConfig.getPassword()))
			error = ERROR_NO_ERROR;
		return error;
	}

	private void setLoginInfo() {
		MyConfig.setUsername(mUsername);
		MyConfig.setLoginSuccess(true);
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
		case ERROR_CHECK_INVALIDE:
			msg = R.string.rsp_error_login_error;
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
	           Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");     
	           startActivityForResult(getImageByCamera, REQUEST_CODE_CAPTURE_CAMEIA);  
	       }  
	       else {  
	    	   Toast.makeText(this, R.string.insert_sd_card, Toast.LENGTH_LONG).show(); 
	       }
	}	
	@Override  
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	       if (requestCode == REQUEST_CODE_PICK_IMAGE) {             
	             //  Uri uri = data.getData();  
	                
	           
	       } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA ) {             
	       Uri uri = data.getData();  
	       if(uri == null){  
	           Bundle bundle = data.getExtras();    
	               if (bundle != null) {                 
	               Bitmap  photo = (Bitmap) bundle.get("data");
	               Util.saveImage(photo, Util.getPhotoFilename());  
	               } else {           
	                   Toast.makeText(this, R.string.take_photo_error, Toast.LENGTH_LONG).show();           
	                   return;        
	               }    
	       }   
	   }  
	}  
	
}
