package com.srirang.screens;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.srirang.configs.Config;
import com.srirang.mobilevocab.Application;
import com.srirang.views.ScalableLayoutParams;

public class HomeScreen extends BaseScreen{

	public HomeScreen(Application app, String name) {
		super(app, name);
	}
 
	@Override
	public void init() {

		ScrollView scroller = new ScrollView(app);
		scroller.setLayoutParams(new ScalableLayoutParams(app, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
		TableLayout layout = new TableLayout(app);
		
		for (int i=0; i < 100 ; i++) {
			TableRow row = new TableRow(app);
			RelativeLayout rLayout = new RelativeLayout(app);
			
			TextView view = new TextView(app);
			view.setText("Param " + i);
			view.setTextColor(Color.RED);
			view.setPadding(70, 15, 0, 20);
			ImageView imageView = app.assetManager.getImage(app, "default_profile.png");//(ImageView)entityMap.get("hint1");
			
			rLayout.addView(imageView);
			rLayout.addView(view);
			
			rLayout.setPadding(50, 5, 100, 5);
			
			row.addView(rLayout);
			layout.addView(row);
		}
		scroller.addView(layout);
		addView(scroller);
	}

}
