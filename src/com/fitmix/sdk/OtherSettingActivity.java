package com.fitmix.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class OtherSettingActivity extends Activity {
	private ToggleButton btnLocation;
	private ToggleButton btnTrail;
	private ToggleButton btnNotification;

	private boolean bNotification;
	private boolean bLocation;
	private boolean bTrail;

	private final int ERROR_NO_ERROR = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_other_setting);
		btnLocation = (ToggleButton) findViewById(R.id.other_settings_btn_location);
		btnTrail = (ToggleButton) findViewById(R.id.other_settings_btn_trail);
		btnNotification = (ToggleButton) findViewById(R.id.other_settings_btn_notification);

		init();
	}

	private void saveSetting() {
		MyConfig.saveConfig(this);
	}

	public void myClickHandler(View v) {
		switch (v.getId()) {
		case R.id.other_setting_done:
			checkInputError();
			setOtherInfo();
			saveSetting();
			startActivity(new Intent(OtherSettingActivity.this,
					MusicMainActivity.class));
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

	private void init() {
		setData();
		refresh();
	}

	private void setData() {
		bNotification = MyConfig.getNotification();
		bLocation = MyConfig.getLocation();
		bTrail = MyConfig.getTrail();
	}

	private void refresh() {
		btnLocation.setChecked(bLocation);
		btnTrail.setChecked(bTrail);
		btnNotification.setChecked(bNotification);
	}

	private void setOtherInfo() {
		MyConfig.setLocation(bLocation);
		MyConfig.setNotification(bNotification);
		MyConfig.setTrail(bTrail);
	}

	private int checkInputError() {
		bNotification = btnNotification.isChecked();
		bLocation = btnLocation.isChecked();
		bTrail = btnTrail.isChecked();
		return ERROR_NO_ERROR;
	}
}
