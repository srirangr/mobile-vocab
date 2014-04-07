package com.srirang.screens;

import java.util.Stack;

import android.content.Context;

import com.srirang.mobilevocab.Application;

public class ScreenManager {
	
	public static ScreenManager instance = null;
	public Application app;
	
	private final Stack<BaseScreen> screenStack;
	
	private ScreenManager(Application app) {
		this.app = app;
		screenStack = new Stack<BaseScreen>();
	}
	
	public static ScreenManager getInstance(Application app){
		if(instance == null){
			instance = new ScreenManager(app);
		}
		return instance;
	}
	
	public void changeScreen(BaseScreen screen){
		System.out.println(screen.name + "added");
		screenStack.push(screen);
	}
	
	public BaseScreen getTopScreen(){
		if(!screenStack.isEmpty()){
			return screenStack.peek();
		}
		return null;
	}
	
	public boolean popCurrentScreen(){
		if(!screenStack.isEmpty()){
			BaseScreen screen = screenStack.pop();
			System.out.println(screen.name + "removed");
		}
		return screenStack.isEmpty();
	}

}
