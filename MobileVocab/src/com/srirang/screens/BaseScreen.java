package com.srirang.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.view.View;
import android.widget.RelativeLayout;

import com.srirang.mobilevocab.Application;

public abstract class BaseScreen extends RelativeLayout{

	public Application app;
	public final String name;
	
	Map<String, View> entityMap = new HashMap<String, View>();
	
	public BaseScreen(Application app, String name){
		super(app.context);
		this.app = app;
		this.name = name;
		
		readFromConfig("configs/"+name+".csv");
		addViews();
	}

	public abstract void init();
	
	//For game loop. Not needed as of now
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}
	
	private void readFromConfig(String csvFile) {
		
		try {
			InputStream is = app.assetManager.open(csvFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			
			String line = null;
			int rowNumber = 1;
			String[] headers = null;
			
			while( (line = br.readLine()) != null){
				String row = line;
				if(row.startsWith("//")) continue;
				if(rowNumber == 1) { // headers
					headers = row.split(",");
				} else {
					String [] values = row.split(",");
					ScreenEntity entity = new ScreenEntity();
					// create entity objects using reflection
					Class clazz = entity.getClass();
					for(int i=0; i < headers.length ; i++) {
						Field field = clazz.getField(headers[i]);
						if(field.getType().getSimpleName().equalsIgnoreCase("float")){
							field.set(entity, Float.parseFloat(values[i]));
						} else {
							field.set(entity, values[i]);
						}
					}
					entityMap.put(entity.id, entity.makeView(app));
				}
				rowNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addViews(){
		for(View entity : entityMap.values()) {
			addView(entity);
		}
	}

}
