package com.srirang.views;

import com.srirang.configs.Config;

import android.content.Context;
import android.widget.RelativeLayout.LayoutParams;

public class ScalableLayoutParams extends LayoutParams{

	private int screenWidth;
	private int screenHeight;
	
	public ScalableLayoutParams(Context context, int w, int h) {
		super(w, h);
		screenHeight = context.getResources().getDisplayMetrics().heightPixels;
		screenWidth = context.getResources().getDisplayMetrics().widthPixels;
		
		height = (height*screenHeight)/Config.SCREEN_HEIGHT;
		width = (width * screenWidth)/Config.SCREEN_WIDTH;
	}
	
	@Override
	public void setMargins(int left, int top, int right, int bottom) {
		left = (left * screenWidth)/Config.SCREEN_WIDTH;
		top = (top* screenHeight)/Config.SCREEN_HEIGHT;
		super.setMargins(left, top, right, bottom);
	}

}
