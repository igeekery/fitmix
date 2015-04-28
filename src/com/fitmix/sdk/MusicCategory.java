package com.fitmix.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MusicCategory extends LinearLayout {
	private TextView textCategory;
	private Button btnMore;
	private ViewGroup mAlbumStand;
	private String[] mImageArray = null;
	private int[] mImageIdArray = null;
	private int[] mBPM = null;
	private boolean bExpand = false;

	public static final int MODE_EXPAND = 0;
	public static final int MODE_NEXT_GROUP = 1;
	private int mMode = MODE_NEXT_GROUP;
	private int mCurrentGroup = 0;
	
	public MusicCategory(Context context) {
		super(context);
		init();
	}

	public MusicCategory(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.music_category, this);
		btnMore = (Button) findViewById(R.id.more);
		textCategory = (TextView) findViewById(R.id.category);
		mAlbumStand = (ViewGroup) findViewById(R.id.album_stand);
		btnMore.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				bExpand = !bExpand;
				refreshImages();
			}
		});

	}

	public void setCategoryIcon(int resId) {
		Drawable drawable = null;
		if (resId != 0)drawable = getResources().getDrawable(resId);
		textCategory.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
	}

	@SuppressWarnings("deprecation")
	public void setCategoryIcon(Bitmap bmp) {
		Drawable drawable = null;
		if (bmp != null)drawable = new BitmapDrawable(bmp);
		textCategory.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
	}

	@SuppressWarnings("deprecation")
	public void setCategoryIcon(String sFilename) {
		Drawable drawable = null;
		textCategory.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		if (sFilename != null && (!sFilename.isEmpty())){
			Bitmap bmp = BitmapFactory.decodeFile(MyConfig.getDataPath() + sFilename);
			drawable = new BitmapDrawable(bmp);			
		}
		textCategory.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
	}

	public void setCategoryText(String text) {
		textCategory.setText(text);
	}

	public void setCategoryText(int resId) {
		textCategory.setText(resId);
	}

	private void clearImageArray() {
		bExpand = false;
		mImageArray = null;
		mImageIdArray = null;
		mAlbumStand.removeAllViews();
	}
	private void addAllImagesByFilename(){
		int iCount = mImageArray.length;		
		String sFile1, sFile2, sFile3;
		int bpm1, bpm2, bpm3;
		int iLoop = iCount / 3;
		int iEndIndex = iCount % 3;
		if (iEndIndex != 0)
			iLoop++;
		for (int i = 0, iIndex = 0; i < iLoop; i++, iIndex += 3) {
			sFile1 = mImageArray[iIndex];
			bpm1 = mBPM[iIndex];
			sFile2 = null;
			sFile3 = null;
			bpm2 = 0;
			bpm3 = 0;
			if ((iIndex + 1) < iCount) {
				sFile2 = mImageArray[iIndex + 1];
				bpm2 = mBPM[iIndex + 1];
			}
			if ((iIndex + 2) < iCount) {
				sFile3 = mImageArray[iIndex + 2];
				bpm3 = mBPM[iIndex + 2];

			}
			addNewRow(sFile1, bpm1, sFile2, bpm2, sFile3, bpm3);
		}
		refreshImages();
		
	}
	public void setImageArray(String[] array, int[] bpm) {
		clearImageArray();
		if (array == null || bpm == null)
			return;
		mImageArray = array.clone();
		int iCount = mImageArray.length;
		if (iCount <= 0)
			return;
		if (bpm.length != iCount)
			return;
		mBPM = bpm.clone();
			addAllImagesByFilename();
	}
	private void addAllImagesByID(){
		int iCount = mImageIdArray.length;		
		int id1, id2, id3;
		int iLoop = iCount / 3;
		int iEndIndex = iCount % 3;
		if (iEndIndex != 0)
			iLoop++;
		for (int i = 0, iIndex = 0; i < iLoop; i++, iIndex += 3) {
			id1 = mImageIdArray[iIndex];
			id2 = 0;
			id3 = 0;
			if (iIndex + 1 < iCount)
				id2 = mImageIdArray[iIndex + 1];
			if (iIndex + 2 < iCount)
				id3 = mImageIdArray[iIndex + 2];
			addNewRow(id1, id2, id3);
		}
		refreshImages();		
	}
	
	public void setImageArray(int[] array) {
		clearImageArray();
		if (array != null)
			mImageIdArray = array.clone();
		int iCount = mImageIdArray.length;
		if (iCount <= 0)
			return;
		addAllImagesByID();
	}
	private void addNewRow(int id1, int id2, int id3) {
		LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout layoutRow = new LinearLayout(getContext());
		layoutRow.setOrientation(LinearLayout.HORIZONTAL);

		LayoutParams lpImage = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 1);

		MusicCorver corver1 = new MusicCorver(getContext());
		corver1.setImageByResourceId(id1);
		layoutRow.addView(corver1, lpImage);

		MusicCorver corver2 = new MusicCorver(getContext());
		corver2.setImageByResourceId(id2);
		layoutRow.addView(corver2, lpImage);

		MusicCorver corver3 = new MusicCorver(getContext());
		corver3.setImageByResourceId(id3);
		layoutRow.addView(corver3, lpImage);

		mAlbumStand.addView(layoutRow, lpRow);
	}

	private void addNewRow(String sFile1, int bpm1, String sFile2, int bpm2,
			String sFile3, int bpm3) {
		LinearLayout.LayoutParams lpRow = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout layoutRow = new LinearLayout(getContext());
		layoutRow.setOrientation(LinearLayout.HORIZONTAL);

		LayoutParams lpImage = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, 1);

		MusicCorver corver1 = new MusicCorver(getContext());
		corver1.setImageByFilename(sFile1);
		corver1.setBPM(bpm1);
		layoutRow.addView(corver1, lpImage);

		MusicCorver corver2 = new MusicCorver(getContext());
		corver2.setImageByFilename(sFile2);
		corver2.setBPM(bpm2);
		layoutRow.addView(corver2, lpImage);

		MusicCorver corver3 = new MusicCorver(getContext());
		corver3.setImageByFilename(sFile3);
		corver3.setBPM(bpm3);
		layoutRow.addView(corver3, lpImage);

		mAlbumStand.addView(layoutRow, lpRow);
	}
	private void refreshByExpandMode(){
		btnMore.setText(bExpand ? R.string.music_close : R.string.music_more);
		int iCount = mAlbumStand.getChildCount();
		for (int i = 0; i < iCount; i++) {
			if (i < 2)
				continue;
			mAlbumStand.getChildAt(i).setVisibility(
					bExpand ? View.VISIBLE : View.GONE);
		}
	}
	private void refreshByNextGroupMode(){
		int iCount = mAlbumStand.getChildCount();
		int start = mCurrentGroup * 2;
		int end = start + 2;
		boolean bShow  = false;
		for (int i = 0; i < iCount; i++) {
			bShow = false;
			if((i >= start) && (i < end))bShow = true;
			mAlbumStand.getChildAt(i).setVisibility(
					bShow ? View.VISIBLE : View.GONE);
		}		
		mCurrentGroup++;
		int iTotalGroup = iCount / 2;
		if((iCount % 2) != 0)iTotalGroup++;
		if(mCurrentGroup >= iTotalGroup)mCurrentGroup = 0;
	}
	private void refreshImages() {
		switch(mMode){
		case MODE_EXPAND:
			refreshByExpandMode();
			break;
		case MODE_NEXT_GROUP:
		default:
			refreshByNextGroupMode();
			break;
		}
	}
	public void setMode(int mode){
		mMode = mode;
	}
	public int getMode(){
		return mMode;
	}
}
