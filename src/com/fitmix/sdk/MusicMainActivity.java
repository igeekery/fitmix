package com.fitmix.sdk;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;


public class MusicMainActivity extends Activity {
	private MusicCategoryPage musicCategoryPage;
	private final int TOP_BAR_SEARCH = 0;
	private final int TOP_BAR_EXERCISE = 1;
	private final int TOP_BAR_SCHOOL = 2;
	private final int BOTTOM_BAR_MIX = 0;
	private final int BOTTOM_BAR_MY = 1;
	private final int BOTTOM_BAR_COMMUNITY = 2;
	
	private ToggleButton mSearch;
	private ToggleButton mExercise;
	private ToggleButton mSchool;

	private ToggleButton mMix;
	private ToggleButton mMy;
	private ToggleButton mCommunity;
	
	private int iTopbarMode = TOP_BAR_EXERCISE;
	private int iBottombarMode = BOTTOM_BAR_MIX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_main);
        musicCategoryPage = (MusicCategoryPage)findViewById(R.id.category_page);
        mSearch = (ToggleButton)findViewById(R.id.music_search);
        mExercise = (ToggleButton)findViewById(R.id.music_exercise);
        mSchool = (ToggleButton)findViewById(R.id.music_school);
        
        mMix = (ToggleButton)findViewById(R.id.music_mix);
        mMy = (ToggleButton)findViewById(R.id.music_my);
        mCommunity = (ToggleButton)findViewById(R.id.music_community);
        onTopbarCkick();
        onBottombarCkick();
    }
    private void refresTopbarState(){
    	Log.d("fhq", "11111111:" + iTopbarMode);
    	switch(iTopbarMode){
    		case TOP_BAR_SEARCH:
    			mSearch.setChecked(true);
    			mExercise.setChecked(false);
    			mSchool.setChecked(false);
    			break;
    		case TOP_BAR_EXERCISE:    		
    			mSearch.setChecked(false);
    			mExercise.setChecked(true);
    			mSchool.setChecked(false);
    			break;
    		case TOP_BAR_SCHOOL:    		
    			mSearch.setChecked(false);
    			mExercise.setChecked(false);
    			mSchool.setChecked(true);
    			break;
    	}
    	
    }
    private void refresContent(){
    	switch(iTopbarMode){
		case TOP_BAR_SEARCH:
			setContentFoSearch();
			break;
		case TOP_BAR_EXERCISE:    		
			setContentForExercies();
			break;
		case TOP_BAR_SCHOOL:
			setContentForSchool();	
			break;
	}
    }	

    private void setContentFoSearch(){
		musicCategoryPage.clearAllCategory();
		
	}
    private void setContentForExercies(){
		int [] array1 = new int[]{R.drawable.music_corver, R.drawable.music_corver};
		int [] array2 = new int[]{R.drawable.music_corver, R.drawable.music_corver, R.drawable.music_corver, R.drawable.music_corver};
		String [] array3 = new String[]{"/mnt/sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png"};
		int[] bpm = new int[]{98, 108, 128, 138, 148, 88};
		musicCategoryPage.clearAllCategory();
		musicCategoryPage.AddNewCategory(R.drawable.music_run, R.string.music_run, array1);
		musicCategoryPage.AddNewCategory(R.drawable.music_ride, R.string.music_ride, array2);
		
		musicCategoryPage.AddNewCategory("/mnt/sdcard/music_run.png", "Happy", array3, bpm);   	
    }
    private void setContentForSchool(){
		String [] array1 = new String[]{"/mnt/sdcard/corver.png", "sdcard/corver.png"};
		String [] array2 = new String[]{"/mnt/sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png"};
		String [] array3 = new String[]{"/mnt/sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png", "sdcard/corver.png"};
		int[] bpm1 = new int[]{128, 128};
		int[] bpm2 = new int[]{128, 128, 128, 128};
		int[] bpm3 = new int[]{98, 108, 128, 138, 148, 88};
		musicCategoryPage.clearAllCategory();
		
		musicCategoryPage.AddNewCategory(null, "Dance", array1, bpm1);   	
		musicCategoryPage.AddNewCategory(null, "Pop", array2, bpm2);   	
		musicCategoryPage.AddNewCategory("/mnt/sdcard/music_run.png", "Happy", array3, bpm3);   	
    }    
    private void onTopbarCkick(){
    	refresTopbarState();
    	refresContent();
    }
    
    private void refresBottombarState(){
    	Log.d("fhq", "2222222:" + iBottombarMode);
    	switch(iBottombarMode){
    		case BOTTOM_BAR_MIX:
    			mMix.setChecked(true);
    			mMy.setChecked(false);
    			mCommunity.setChecked(false);
    			break;
    		case TOP_BAR_EXERCISE:    		
    			mMix.setChecked(false);
    			mMy.setChecked(true);
    			mCommunity.setChecked(false);
    			break;
    		case TOP_BAR_SCHOOL:    		
    			mMix.setChecked(false);
    			mMy.setChecked(false);
    			mCommunity.setChecked(true);
    			break;
    	}
    	
    }   
    private void onBottombarCkick(){
    	refresBottombarState();
    }    
    public void myClickHandler(View v){
		switch(v.getId()){
			case R.id.music_search:
				iTopbarMode = TOP_BAR_SEARCH;
				onTopbarCkick();
				break;
			case R.id.music_exercise:
				iTopbarMode = TOP_BAR_EXERCISE;
				onTopbarCkick();
				break;
			case R.id.music_school:
				iTopbarMode = TOP_BAR_SCHOOL;
				onTopbarCkick();
				break;
			case R.id.music_mix:
				iBottombarMode = BOTTOM_BAR_MIX;
				onBottombarCkick();
				break;
			case R.id.music_my:
				startActivity(new Intent(MusicMainActivity.this, MyHistoryActivity.class));
				break;
			case R.id.music_community:
				iBottombarMode = BOTTOM_BAR_COMMUNITY;
				onBottombarCkick();
				break;
		}
	}    
}
