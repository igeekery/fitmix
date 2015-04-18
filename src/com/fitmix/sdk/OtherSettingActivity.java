package com.fitmix.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ToggleButton;

public class OtherSettingActivity extends Activity {
	private ToggleButton btnLocation;
	private ToggleButton btnTrail;
	private ToggleButton btnNotification;
	private final String sConfigFile = "config";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_other_setting);
		btnLocation = (ToggleButton)findViewById(R.id.other_settings_btn_location);
		btnTrail = (ToggleButton)findViewById(R.id.other_settings_btn_trail);
		btnNotification = (ToggleButton)findViewById(R.id.other_settings_btn_notification);
		
		loadSetting();
	}
	private void loadSetting() {
		SharedPreferences sp = getSharedPreferences(sConfigFile, Context.MODE_PRIVATE);
		boolean bLocation = sp.getBoolean("location", true);
		boolean bTrail = sp.getBoolean("trail", true);
		boolean bNotification = sp.getBoolean("notification", true);
		btnLocation.setChecked(bLocation);
		btnTrail.setChecked(bTrail);
		btnNotification.setChecked(bNotification);

	}
	private void saveSetting() {
		boolean bLocation = btnLocation.isChecked();
		boolean bTrail = btnTrail.isChecked();
		boolean bNotification = btnNotification.isChecked();
		SharedPreferences sp = getSharedPreferences(sConfigFile, Context.MODE_PRIVATE);
		Editor editor = sp.edit(); 
		editor.putBoolean("location", bLocation);
		editor.putBoolean("trail", bTrail);
		editor.putBoolean("notification", bNotification);
		editor.commit();
	}
	public void myClickHandler(View v) {
		switch (v.getId()) {
		case R.id.other_setting_done:
			saveSetting();
			break;
		case R.id.other_settings_btn_location:
			btnLocation.setChecked(!btnLocation.isChecked());
			break;
		case R.id.other_settings_btn_trail:
			btnTrail.setChecked(!btnTrail.isChecked());
			break;
		case R.id.other_settings_btn_notification:
			btnNotification.setChecked(!btnNotification.isChecked());
			break;
		}
	}
}
