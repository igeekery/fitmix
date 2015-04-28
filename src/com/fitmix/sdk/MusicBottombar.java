package com.fitmix.sdk;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

public class MusicBottombar extends FrameLayout {
	public static final int BOTTOM_BAR_MIX = 0;
	public static final int BOTTOM_BAR_MY = 1;
	public static final int BOTTOM_BAR_COMMUNITY = 2;

	private ToggleButton mMix;
	private ToggleButton mMy;
	private ToggleButton mCommunity;

	private int iBottombarMode = BOTTOM_BAR_MIX;

	public MusicBottombar(Context context) {
		super(context);
		init();
	}

	public MusicBottombar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.music_bottom_bar, this);
		mMix = (ToggleButton) findViewById(R.id.music_mix);
		mMy = (ToggleButton) findViewById(R.id.music_my);
		mCommunity = (ToggleButton) findViewById(R.id.music_community);
		mMix.setOnClickListener(mClickListener);
		mMy.setOnClickListener(mClickListener);
		mCommunity.setOnClickListener(mClickListener);

	}

	private View.OnClickListener mClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.music_mix:
				iBottombarMode = BOTTOM_BAR_MIX;
				onBottombarCkick();
				getContext().startActivity(
						new Intent(getContext(), MusicMainActivity.class));
				break;
			case R.id.music_my:
				iBottombarMode = BOTTOM_BAR_MY;
				onBottombarCkick();
				getContext().startActivity(
						new Intent(getContext(), MyHistoryActivity.class));
				break;
			case R.id.music_community:
				iBottombarMode = BOTTOM_BAR_COMMUNITY;
				onBottombarCkick();
				break;
			}

		}
	};

	private void refresBottombarState() {
		switch (iBottombarMode) {
		case BOTTOM_BAR_MIX:
			mMix.setChecked(true);
			mMy.setChecked(false);
			mCommunity.setChecked(false);
			break;
		case BOTTOM_BAR_MY:
			mMix.setChecked(false);
			mMy.setChecked(true);
			mCommunity.setChecked(false);
			break;
		case BOTTOM_BAR_COMMUNITY:
			mMix.setChecked(false);
			mMy.setChecked(false);
			mCommunity.setChecked(true);
			break;
		}

	}

	private void onBottombarCkick() {
		refresBottombarState();
	}

	public void setCurrentButton(int iMode) {
		iBottombarMode = iMode;
		refresBottombarState();
	}

}
