package com.srirang.screens;

import android.widget.ScrollView;
import android.widget.TextView;

import com.srirang.configs.Config;
import com.srirang.mobilevocab.Application;

public class GameScreen extends BaseScreen{

		
	public GameScreen(Application app, String name) {
		super(app, name);
	}

	@Override
	public void init() {
		/*TextView tv = (TextView) entityMap.get("preftest_label");
		String current = tv.getText().toString();
		int current_count = app.prefs.getIntegerPreference(Config.PREF_COUNT, 0);
		tv.setText(current + current_count);
		app.prefs.setIntegerPreference(Config.PREF_COUNT, current_count + 1);*/
		
	} 

}
