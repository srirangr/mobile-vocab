package com.srirang.mobilevocab;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.srirang.screens.Screen;
import com.srirang.screens.ScreenManager;

public class Application {

	public Context context;
	
	public Screen screen;
	public ScreenManager screenManager;
	public AppAssetManager assetManager;
	
	public RelativeLayout mainRelativeLayout;
	
	public Application(Context context, RelativeLayout layout) {
		this.context = context;
		mainRelativeLayout = layout;
		setupApplication();
	}
	
	public void setupApplication(){
		screenManager = ScreenManager.getInstance(context);
		assetManager = AppAssetManager.getInstance(context);
		/*
		TextView tv = new TextView(context);
		LayoutParams params = new LayoutParams(200, 40);
		tv.setLayoutParams(params);
		//tv.setPadding(100, 50, 0, 0);
		tv.setText("Hi Srirang");
		
		mainRelativeLayout.addView(tv); */
		
		ImageView iv = assetManager.getImage("splash-screen.png");
		
		mainRelativeLayout.addView(iv); 
	}
	
	
	public void changeScreen(Screen nextScreen){
	
		screen.hide();
		screen = nextScreen;
		screen.load();
		screen.show();
		screenManager.changeScreen(screen);
	}
	
	public void lastScreen(){
		changeScreen(screenManager.getPreviousScreen());
	}
	
}
