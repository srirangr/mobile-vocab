package com.srirang.screens;

import java.util.ArrayList;
import java.util.Stack;

import android.content.Context;

public class ScreenManager {
	
	public static ScreenManager instance = null;
	
	private final Stack<Screen> screenStack;
	
	private ScreenManager(Context context) {
		screenStack = new Stack<Screen>();
	}
	
	public static ScreenManager getInstance(Context context){
		if(instance == null){
			instance = new ScreenManager(context);
		}
		return instance;
	}
	
	public void changeScreen(Screen screen){
		screenStack.push(screen);
	}
	
	public Screen getTopScreen(){
		if(screenStack != null){
			return screenStack.peek();
		}
		return null;
	}
	
	public Screen getPreviousScreen(){
		if(screenStack != null){
			screenStack.pop();
		}
		return getTopScreen();
	}

}
