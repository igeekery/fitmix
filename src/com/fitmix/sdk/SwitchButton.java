package com.fitmix.sdk;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;

import android.widget.TextView;

import com.fitmix.sdk.R;;

public class SwitchButton extends LinearLayout {
	private TextView mLeft;
	private TextView mRight;
	private View mSwitch;
	private boolean mState = false;
	private String sLeftText;
	private String sRightText;

	public SwitchButton(Context context, String leftText, String rightText) {
		super(context);
		sLeftText = leftText;
		sRightText = rightText;
		init();
	}

	public SwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = getContext().obtainStyledAttributes(attrs, 
				R.styleable.SwitchButton); 
		
		sLeftText = a.getString(R.styleable.SwitchButton_textOff);
		sRightText = a.getString(R.styleable.SwitchButton_textOn);

		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.switch_button, this);
		mLeft = (TextView) findViewById(R.id.left);
		mRight = (TextView) findViewById(R.id.right);
		mSwitch = (View) findViewById(R.id.switchBtn);
		
		mLeft.setText(sLeftText);
		mRight.setText(sRightText);
		refreshState();
		mSwitch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mState = !mState;
				refreshState();
			}
		});
	}

	private void refreshState() {
		if(!mState){
			mLeft.setBackgroundColor(getResources().getColor(R.color.switch_button_down_color));
			mRight.setBackgroundColor(getResources().getColor(R.color.switch_button_up_color));
		}else{
			mLeft.setBackgroundColor(getResources().getColor(R.color.switch_button_up_color));
			mRight.setBackgroundColor(getResources().getColor(R.color.switch_button_down_color));
		}

	}
	
	public void setState(boolean bCheck) {
		mState = bCheck;
	}

	public boolean getState() {
		return mState;
	}

}
