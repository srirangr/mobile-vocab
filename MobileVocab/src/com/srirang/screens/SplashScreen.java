package com.srirang.screens;

import android.os.Handler;

import com.srirang.mobilevocab.Application;

public class SplashScreen extends BaseScreen {

	public SplashScreen(Application app, String _name) {
		super(app, _name);
	}

	@Override
	public void init() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				app.changeScreen(new GameScreen(app,"GameScreen"));
			}
		}, 1500);
	}

}
