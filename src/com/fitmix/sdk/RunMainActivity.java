package com.fitmix.sdk;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;


public class RunMainActivity extends Activity {

	private TextView textDistance;
	private TextView textSpeed;
	private TextView textBPM;
	private TextView textTime;
	
	private TextView textTimeCurrent;
	private TextView textTimeTotal;
	private TextView textAlbum;
	private TextView textAuthor;
	
	private int mSpeed;
	private int mBPM;
	private int mTime;
	private int mDistance;
	
	private int mTimeCurrent;
	private int mTimeTotal;
	private String mAlbumName;
	private String mAuthorName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_run_main);
        
        init();
        setDemoData();
        refresData();
    }
    
    private void init(){
        textDistance = (TextView)findViewById(R.id.run_distance);
        textSpeed = (TextView)findViewById(R.id.run_speed);
        textBPM = (TextView)findViewById(R.id.run_bpm);
        textTime = (TextView)findViewById(R.id.run_time);
        textTimeCurrent = (TextView)findViewById(R.id.time_current);
        textTimeTotal = (TextView)findViewById(R.id.time_total);
        textAlbum = (TextView)findViewById(R.id.run_album);
        textAuthor = (TextView)findViewById(R.id.run_author);
  	
    }
    private void setDemoData(){
    	mSpeed= 3530;
    	mBPM = 120;
    	mTime=1800;
    	mDistance=30260;
    	mTimeCurrent = 500;
    	mTimeTotal = 3000;
    	mAlbumName = "Hybrid Theory";
    	mAuthorName = "Linkin Park";		
    }
    private void refresData(){
    	textDistance.setText("" + (mDistance * 1.0 / 1000));
    	textSpeed.setText("" + (mSpeed * 1.0 / 1000));
    	textBPM.setText("" + mBPM);
    	textTime.setText("" + mTime);
    	textTimeCurrent.setText(formatTime(mTimeCurrent));
    	textTimeTotal.setText(formatTime(mTimeTotal));
    	textAlbum.setText(mAlbumName);
    	textAuthor.setText(mAuthorName);
        	
    	
    	
    }
    private String formatTime(int iTime){
    	int iSecond = iTime % 60;
    	int iHour = iTime / 3600;    	
    	int iMinute = (iTime / 60) % 60 - iHour * 60;
    	String sResult = (iMinute >= 10) ? ("" + iMinute) : ("0" + iMinute);
    	sResult += ":" + ((iSecond >= 10) ? ("" + iSecond) : ("0" + iSecond));
    	if(iHour > 0)sResult =  ((iSecond >= 10) ? ("" + iSecond) : ("0" + iSecond)) + ":" + sResult; 
    	return sResult;
    	
    	 
    	
    }
    

      public void myClickHandler(View v){
		switch(v.getId()){
		}
	}    
}
