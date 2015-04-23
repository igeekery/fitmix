package com.fitmix.sdk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.TextView;

public class MusicCorver extends FrameLayout {
	private ImageView mCorver;
	private TextView mBpm;
	private int iBpm;
	private Uri uriMp3;

	public MusicCorver(Context context) {
		super(context);
		init();
	}

	public MusicCorver(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.music_corver, this);
		mCorver = (ImageView) findViewById(R.id.corver);
		mBpm = (TextView) findViewById(R.id.bpm);
		mCorver.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getContext().startActivity(new Intent(getContext(), AlbumActivity.class));
				
			}
		});
	}
	private void clearImage(){
		this.setVisibility(View.INVISIBLE);
		iBpm = 0;
	}
	public void setImageByResourceId(int resId) {
		if (resId == 0){
			clearImage();
			return;
		}			
		mCorver.setImageResource(resId);
	}

	public void setImageByFilename(String sFilename) {
		if ((sFilename == null) || sFilename.isEmpty()){
			clearImage();
			return;
		}
			
		Bitmap bm = BitmapFactory.decodeFile(sFilename);
		if(bm == null)return;
		mCorver.setImageBitmap(bm);
	}

	public void setImageByUri(Uri image) {
		if (image == null){
			clearImage();
			return;
		}
		mCorver.setImageURI(image);
	}

	public void setBPM(int bmp) {
		iBpm = bmp;
		mBpm.setText("" + iBpm);
	}

	public void setMp3Uri(Uri mp3) {
		uriMp3 = mp3;
	}

	public Uri getMp3Uri() {
		return uriMp3;
	}

	public int getBpm() {
		return iBpm;
	}
}
