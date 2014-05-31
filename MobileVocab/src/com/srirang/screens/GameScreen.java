package com.srirang.screens;

import com.srirang.mobilevocab.Application;

public class GameScreen extends BaseScreen{

	public GameScreen(Application app, String name) {
		super(app, name);
	}

	@Override
	public void init() {
		app.getWordList();
	}

}
