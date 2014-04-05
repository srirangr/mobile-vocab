package com.srirang.screens;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.srirang.mobilevocab.Application;
import com.srirang.views.ScalableLayoutParams;

public class ScreenEntity { 

	public String[] values;
	
	public float x;
	public float y;
	public float height;
	public float width;
	public String viewType;
	public String text;
	public String font;
	public String clickable;
	public String asset;
	
	public View makeView(Application app){
		if(viewType.equals("ImageView")){
			return makeImageView(app);
		} else if(viewType.equals("TextView")){
			return makeTextView(app);
		}
		return null;
	}

	private View makeTextView(Application app) {
		TextView tv = new TextView(app.context);
		ScalableLayoutParams params = new ScalableLayoutParams(app.context,(int)width, (int)height);
		params.setMargins((int)x, (int)y, 0, 0);
		tv.setLayoutParams(params);
		app.setFont(tv, font);
		tv.setText(text);
		return tv;
	}

	private View makeImageView(Application app) {
		ImageView view = app.assetManager.getImage(asset);
		ScalableLayoutParams params = new ScalableLayoutParams(app.context,(int)width, (int)height);
		params.setMargins((int)x, (int)y, 0, 0);
		view.setLayoutParams(params);
		view.setScaleType(ScaleType.FIT_XY);
		return view;
	}
	
}
