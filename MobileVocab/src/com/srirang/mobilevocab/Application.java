package com.srirang.mobilevocab;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.preferences.AndroidPreference;
import com.srirang.model.WordListItem;
import com.srirang.screens.BaseScreen;
import com.srirang.screens.ScreenManager;
import com.srirang.screens.SplashScreen;

public abstract class Application extends Activity{

	public Context context;
	
	public BaseScreen screen;
	public ScreenManager screenManager;
	public AppAssetManager assetManager;
	
	public AndroidPreference prefs;
	
	public RelativeLayout mainRelativeLayout;
	
	public void setupApplication(){
		context = this;
		screenManager = ScreenManager.getInstance();
		assetManager = AppAssetManager.getInstance(context.getAssets());
		prefs = new AndroidPreference(context);

        setContentView(R.layout.activity_main);
		mainRelativeLayout = (RelativeLayout) findViewById(R.id.main_relative_layout);
		
		screen = new SplashScreen(this, "SplashScreen");
		screen.init();
		//screenManager.changeScreen(screen);
		mainRelativeLayout.addView(screen);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupApplication();
	}
	
	public Typeface getTypeFace(){
		return assetManager.getTypeFace();
	}
	
	public void changeScreen(BaseScreen newScreen){
		mainRelativeLayout.removeView(screen);
		screen.removeAllViewsInLayout();
		screen = newScreen;
		screen.init(); 
		mainRelativeLayout.addView(screen); 
	}
	
	public void clear(){
		screenManager = null;
	}
	
	public List<WordListItem> getWordList(){
		return assetManager.getWordList();
	}
	
}
