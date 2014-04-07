package com.srirang.mobilevocab;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.srirang.screens.BaseScreen;
import com.srirang.screens.ScreenManager;
import com.srirang.screens.SplashScreen;

public class Application {

	public Context context;
	
	public BaseScreen screen;
	public ScreenManager screenManager;
	public AppAssetManager assetManager;
	
	public RelativeLayout mainRelativeLayout;
	
	public Application(Context context, RelativeLayout layout) {
		this.context = context;
		mainRelativeLayout = layout;
		setupApplication();
	}
	
	public void setupApplication(){
		screenManager = ScreenManager.getInstance(this);
		assetManager = AppAssetManager.getInstance(context);
		
		screen = new SplashScreen(this, "SplashScreen");
		screen.init();
		//screenManager.changeScreen(screen);
		mainRelativeLayout.addView(screen);
	}
	
	
	public void changeScreen(BaseScreen nextScreen){
	
		if(nextScreen == null) return;
		
		mainRelativeLayout.removeView(screen);
		screen = nextScreen;
		screen.init();
		mainRelativeLayout.addView(screen);
		screenManager.changeScreen(screen);
	}
	
	public boolean isPreviousScreen(){
		boolean previousScreenAvailable = screenManager.popCurrentScreen();	
		return previousScreenAvailable;
	}
	
	public void goToPreviousScreen(){
		changeScreen(screenManager.getTopScreen());
	}
	
	public void setFont(TextView tv, String font){
		if(font.equals("none")) return;
		String[] fontEntities = font.split(":");
		tv.setTypeface(assetManager.getTypeFace());
		tv.setTextSize(Float.parseFloat(fontEntities[0]));
		tv.setTextColor(Color.parseColor(fontEntities[1]));
		if(fontEntities[2].equalsIgnoreCase("c")){
			tv.setGravity(Gravity.CENTER);
		}
	}
	
	public void clear(){
		screenManager = null;
	}
	
}
