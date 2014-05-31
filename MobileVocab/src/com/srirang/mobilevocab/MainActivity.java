package com.srirang.mobilevocab;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.RelativeLayout;

public class MainActivity extends Application {

	public static Application app;
	
	public RelativeLayout layout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.main_relative_layout);
        app = this;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	if(app.isPreviousScreen())
    		finish();
    	else {
    		app.goToPreviousScreen();
    	}
    }
    
    /*@Override
    protected void onDestroy() {
    	app.clear();
    	app = null;
    	super.onDestroy();
    }
    */
}
