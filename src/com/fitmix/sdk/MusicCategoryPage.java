package com.fitmix.sdk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MusicCategoryPage extends LinearLayout {
	private ViewGroup mCategoryGroup;
	private int mMode = MusicCategory.MODE_NEXT_GROUP;

	public MusicCategoryPage(Context context) {
		super(context);
		init();
	}

	public MusicCategoryPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.music_category_page, this);
		mCategoryGroup = (ViewGroup) findViewById(R.id.music_categorys);
	}

	public void clearAllCategory() {
		mCategoryGroup.removeAllViews();
	}

	public void AddNewCategory(int icon, int title, int[] array) {
		MusicCategory category = new MusicCategory(getContext());
		category.setCategoryIcon(icon);
		category.setCategoryText(title);
		category.setImageArray(array);
		category.setMode(mMode);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		category.setOrientation(LinearLayout.VERTICAL);

		mCategoryGroup.addView(category, lp);
	}

	public void AddNewCategory(String icon, String title, String[] array,
			int[] bpm) {
		MusicCategory category = new MusicCategory(getContext());
		category.setCategoryIcon(icon);
		category.setCategoryText(title);
		category.setImageArray(array, bpm);
		category.setMode(mMode);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		category.setOrientation(LinearLayout.VERTICAL);

		mCategoryGroup.addView(category, lp);
	}

	public void setMode(int mode) {
		mMode = mode;
	}

	public int getMode() {
		return mMode;
	}
}
