package com.fitmix.sdk;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;


public class AlbumActivity extends Activity {
	private TextView textHeart;
	private TextView textBPM;
	private TextView textDownloaded;
	private TextView textAlbumName;
	private TextView textAlbumAuthor;	
	private TextView textIntroduce;
	private TextView textPercent;
	
	private Button mBtnDownload;
	
	private String sHeart;
	private String sBPM;
	private String sDownloadNum;
	private String sAlbumName;
	private String sAlbumAuthor;
	private String sIntroduce;
	
	private int mPercent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_album);
        init();
        setDemoData();
        refreshData();
    }
    private void init(){
        textHeart = (TextView)findViewById(R.id.album_heart);
        textBPM = (TextView)findViewById(R.id.album_bpm);
        textDownloaded = (TextView)findViewById(R.id.album_download_num);
        textAlbumName = (TextView)findViewById(R.id.album_name);
        textAlbumAuthor = (TextView)findViewById(R.id.album_author);
        textIntroduce = (TextView)findViewById(R.id.album_introduce);
        textPercent = (TextView)findViewById(R.id.album_percent);
        
    }
    private void setDemoData(){
    	sHeart = "5680";
    	sBPM = "120";
    	sDownloadNum = "120";
    	sAlbumName = "Hybird Theory";
    	sAlbumAuthor = "linkin park";
    	sIntroduce = getResources().getString(R.string.album_demo);
    	mPercent = 50;
    }
    private void refreshData(){
    	textHeart.setText(sHeart);
    	textBPM.setText(sAlbumName);
    	textDownloaded.setText(sAlbumName);
    	textAlbumName.setText(sAlbumName);
    	textAlbumAuthor.setText(sAlbumAuthor);
    	textIntroduce.setText(sIntroduce);
    	textPercent.setText("" + mPercent);
    }
    
    public void myClickHandler(View v){
		switch(v.getId()){
			case R.id.go:
				break;
			case R.id.album_play:
				break;
			case R.id.album_download:
				break;
		}
	}    
}
