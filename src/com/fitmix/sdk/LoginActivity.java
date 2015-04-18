package com.fitmix.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends Activity {
	private static final String WECHAT_APP_ID="123456";
//	private IWXAPI wechat_api;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

	}
	public void myClickHandler(View v){
		switch(v.getId()){
			case R.id.login:
				startActivity(new Intent(LoginActivity.this, EmailLoginActivity.class));
				break;
			case R.id.registe:
				startActivity(new Intent(LoginActivity.this, EmailRegisteActivity.class));
				break;
			case R.id.qq:
				break;
			case R.id.wechat:
				loginByWechat();
				break;
			case R.id.weibo:
				break;
			case R.id.try_out:				
				startActivity(new Intent(LoginActivity.this, MusicMainActivity.class));
				break;
		}
	}
	private void loginByWechat(){
//		wechat_api = WXAPIFactory.createWXAPI(this, WECHAT_APP_ID, true);
//		wechat_api.registerApp(WECHAT_APP_ID);
	}
	
}
