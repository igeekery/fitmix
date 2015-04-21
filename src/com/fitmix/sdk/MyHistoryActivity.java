package com.fitmix.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MyHistoryActivity extends Activity {
	private MusicBottombar bottombar;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_myhistory);
        bottombar = (MusicBottombar)findViewById(R.id.bottombar);		
	    bottombar.setCurrentButton(MusicBottombar.BOTTOM_BAR_MY);
	}
	public void myClickHandler(View v){
		switch(v.getId()){

		}
	}
}
