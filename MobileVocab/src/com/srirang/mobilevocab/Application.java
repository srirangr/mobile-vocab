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
		mainRelativeLayout.addView(screen);
		
		/*Properties pro = assetManager.getCSV("properties.csv");
		String line = pro.toString();
		
		RelativeLayout rl = new RelativeLayout(context);
		
		TextView tv = new TextView(context);
		LayoutParams params = new LayoutParams(320, 400);
		tv.setLayoutParams(params);
		//tv.setPadding(100, 50, 0, 0);
		tv.setText(line);
		
		rl.addView(tv);
		
		mainRelativeLayout.addView(rl); */
		
		/*ImageView iv = assetManager.getImage("splash-screen.png");
		
		mainRelativeLayout.addView(iv); */
		
		
	}
	
	
	public void changeScreen(BaseScreen nextScreen){
	
		mainRelativeLayout.removeView(screen);
		screen = nextScreen;
		mainRelativeLayout.addView(screen);
		screenManager.changeScreen(screen);
	}
	
	public void lastScreen(){
		changeScreen(screenManager.getPreviousScreen());
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
	
}
