package com.fitmix.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonInfoActivity extends Activity {
	private SwitchButton btnSex;
	private EditText editAge;
	private EditText editHeight;
	private EditText editWeight;
	private SwitchButton btnUnit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_person_info);
		btnSex = (SwitchButton)findViewById(R.id.person_info_sex);
		editAge = (EditText)findViewById(R.id.person_info_age);
		editHeight = (EditText)findViewById(R.id.person_info_height);
		btnUnit = (SwitchButton)findViewById(R.id.person_info_unit);
	}
	public void myClickHandler(View v){
		switch(v.getId()){
			case R.id.person_info_next:				
				startActivity(new Intent(PersonInfoActivity.this, OtherSettingActivity.class));				
				
				break;
		}
	}			
}
