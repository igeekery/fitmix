package com.fitmix.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginActivity extends Activity {
	//private static final String WECHAT_APP_ID = "123456";

	//private IWXAPI wechat_api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		init();
	}

	private void init() {
		MyConfig.init(this);
		if(MyConfig.isDemoMode())setDemoData();
	}

	private void setDemoData() {

	}

	public void myClickHandler(View v) {
		switch (v.getId()) {
		case R.id.login:
			startActivity(new Intent(LoginActivity.this,
					EmailLoginActivity.class));
			break;
		case R.id.registe:
			startActivity(new Intent(LoginActivity.this,
					EmailRegisteActivity.class));
			break;
		case R.id.qq:
			loginByQQ();
			break;
		case R.id.wechat:
			loginByWechat();
			break;
		case R.id.weibo:
			loginByWeibo();
			break;
		case R.id.try_out:
			startActivity(new Intent(LoginActivity.this,
					MusicMainActivity.class));
			break;
		}
	}

	private void loginByWechat() {
		// wechat_api = WXAPIFactory.createWXAPI(this, WECHAT_APP_ID, true);
		// wechat_api.registerApp(WECHAT_APP_ID);
	}
	private void loginByWeibo(){
		
	}
	private void loginByQQ(){
		
	}

}
