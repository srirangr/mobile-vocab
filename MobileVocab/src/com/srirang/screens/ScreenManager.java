package com.srirang.screens;

import java.util.Stack;

public class ScreenManager {
	
	public static ScreenManager instance = null;
	
	private final Stack<BaseScreen> screenStack;
	
	private ScreenManager() {
		screenStack = new Stack<BaseScreen>();
	}
	
	public static ScreenManager getInstance(){
		if(instance == null){
			instance = new ScreenManager();
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
