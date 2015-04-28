package com.fitmix.sdk;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;


public class MusicMainActivity extends Activity {
	private MusicCategoryPage musicCategoryPage;
	private MusicBottombar bottombar;
	
	private final int TOP_BAR_SEARCH = 0;
	private final int TOP_BAR_EXERCISE = 1;
	private final int TOP_BAR_SCHOOL = 2;
	
	private ToggleButton mSearch;
	private ToggleButton mExercise;
	private ToggleButton mSchool;
	
	private int iTopbarMode = TOP_BAR_EXERCISE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_main);
        musicCategoryPage = (MusicCategoryPage)findViewById(R.id.category_page);
        bottombar = (MusicBottombar)findViewById(R.id.bottombar);
        mSearch = (ToggleButton)findViewById(R.id.music_search);
        mExercise = (ToggleButton)findViewById(R.id.music_exercise);
        mSchool = (ToggleButton)findViewById(R.id.music_school);
        
        onTopbarCkick();
        bottombar.setCurrentButton(MusicBottombar.BOTTOM_BAR_MIX);
    }
    private void refresTopbarState(){
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
    private void setExerciseDemoData(){
		int [] array1 = new int[]{R.drawable.music_corver, R.drawable.music_corver};
		int [] array2 = new int[]{R.drawable.music_corver, R.drawable.music_corver, R.drawable.music_corver, R.drawable.music_corver};
		String [] array3 = new String[]{"corver.png", "corver.png", "corver.png", "corver.png", "corver.png", "corver.png"};
		int[] bpm = new int[]{98, 108, 128, 138, 148, 88};
		musicCategoryPage.clearAllCategory();
		musicCategoryPage.AddNewCategory(R.drawable.music_run, R.string.music_run, array1);
		musicCategoryPage.AddNewCategory(R.drawable.music_ride, R.string.music_ride, array2);
		musicCategoryPage.AddNewCategory("music_run.png", "Happy", array3, bpm);   	
    }
    private void setContentForExercies(){
    	if(MyConfig.isDemoMode())setExerciseDemoData();
    	else{
    		
    	}
    }
    private void setSchoolDemoData(){
		String [] array1 = new String[]{"corver.png", "corver.png"};
		String [] array2 = new String[]{"corver.png", "corver.png", "corver.png", "corver.png"};
		String [] array3 = new String[]{"corver.png", "corver.png", "corver.png", "corver.png", "corver.png", "corver.png"};
		int[] bpm1 = new int[]{128, 128};
		int[] bpm2 = new int[]{128, 128, 128, 128};
		int[] bpm3 = new int[]{98, 108, 128, 138, 148, 88};
		musicCategoryPage.clearAllCategory();
		
		musicCategoryPage.AddNewCategory(null, "Dance", array1, bpm1);   	
		musicCategoryPage.AddNewCategory(null, "Pop", array2, bpm2);   	
		musicCategoryPage.AddNewCategory("music_run.png", "Happy", array3, bpm3);   	
    }
    private void setContentForSchool(){
    	if(MyConfig.isDemoMode())setSchoolDemoData();
    	else{
    		
    	}
    }    
    private void onTopbarCkick(){
    	refresTopbarState();
    	refresContent();
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
		}
	}    
}
