package com.srirang.screens;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
	public String action;
	public String asset;
	public String id;
	
	public View makeView(Application app){
		if(viewType.equals("ImageView")){
			return makeImageView(app);
		} else if(viewType.equals("TextView")){
			return makeTextView(app);
		} else if (viewType.equals("Button")){
			return makeButton(app);
		}
		return null;
	}

	private View makeButton(Application app) {
		Button button = new Button(app);
		ScalableLayoutParams params = new ScalableLayoutParams(app.context,(int)width, (int)height);
		params.setMargins((int)x, (int)y, 0, 0);
		button.setLayoutParams(params);
		button.setTypeface(app.getTypeFace());
		String[] fontEntities = font.split(":");
		button.setTextSize(Float.parseFloat(fontEntities[0]));
		button.setTextColor(Color.parseColor(fontEntities[1]));
		button.setText(text);
		
		button.setBackgroundColor(Color.parseColor(asset));
		return button;
	}

	private View makeTextView(Application app) {
		TextView tv = new TextView(app.context);
		ScalableLayoutParams params = new ScalableLayoutParams(app.context,(int)width, (int)height);
		params.setMargins((int)x, (int)y, 0, 0);
		tv.setLayoutParams(params);
		tv.setTypeface(app.getTypeFace());
		String[] fontEntities = font.split(":");
		tv.setTextSize(Float.parseFloat(fontEntities[0]));
		tv.setTextColor(Color.parseColor(fontEntities[1]));
		if(fontEntities.length >= 3 && fontEntities[2].equalsIgnoreCase("c")){
			tv.setGravity(Gravity.CENTER);
		}
		tv.setText(text);
		
		if(!asset.equalsIgnoreCase("none")){
			tv.setBackgroundColor(Color.parseColor(asset));
		}
		
		return tv;
	}

	private View makeImageView(Application app) {
		ImageView view = app.assetManager.getImage(app.context, asset);
		ScalableLayoutParams params = new ScalableLayoutParams(app.context,(int)width, (int)height);
		params.setMargins((int)x, (int)y, 0, 0);
		view.setLayoutParams(params);
		view.setScaleType(ScaleType.FIT_XY);
		return view;
	}
	
	@Override
	public String toString() {
		String result = "x:"+x+","
				+"y:"+y+","
				+"height:"+height+","
				+"width:"+width+","
				+"asset:"+asset+","
				+"font:"+font+","
				+"text:"+text+","
				+"action:"+action;
		return result;
	}
	
}
