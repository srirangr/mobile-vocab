package com.srirang.mobilevocab;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.srirang.mobilevocab.EnumConfig.FontType;
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
		mainRelativeLayout.setBackgroundColor(Color.parseColor("#FCFAFD"));
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
		tv.setTypeface(assetManager.getTypeFace(FontType.valueOf(fontEntities[2])));
		tv.setTextSize(Float.parseFloat(fontEntities[0]));
		tv.setTextColor(Color.parseColor(fontEntities[1]));
		if(fontEntities.length == 4){
			if(fontEntities[3].equalsIgnoreCase("c"))
				tv.setGravity(Gravity.CENTER);
			else if(fontEntities[3].equalsIgnoreCase("l"))
				tv.setGravity(Gravity.LEFT);
		}
	}
	
	public void clear(){
		screenManager = null;
	}
	
}
