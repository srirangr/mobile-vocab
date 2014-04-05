package com.srirang.mobilevocab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.widget.ImageView;

public class AppAssetManager {

	private final AssetManager assetManager;
	public static AppAssetManager instance = null;
	
	public Context context;
	private AppAssetManager(Context context){
		this.context = context;
		assetManager = context.getAssets(); 
	}
	
	public static AppAssetManager getInstance(Context context){
		if(instance == null){
			instance = new AppAssetManager(context);
		}
		return instance;
	}
	
	
	public ImageView getImage(String name){
		try {
			InputStream is = assetManager.open(name);
			Bitmap bmp = BitmapFactory.decodeStream(is);
			ImageView iv = new ImageView(context);
			iv.setImageBitmap(bmp);
			return iv;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Properties getCSV(String csvFile){
		
		Properties properties = new Properties();
		try {
			InputStream is = assetManager.open(csvFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			
			String line = null;
			int rowNumber = 1;
			String[] headers = null;
			while( (line = br.readLine()) != null){
				String row = line;
				if(rowNumber == 1) { // headers
					headers = row.split(",");
				} else {
					Properties column = new Properties();
					String [] values = row.split(",");
					for(int i=0; i < headers.length ; i++) {
						column.put(headers[i], values[i]);
					}
					properties.put(rowNumber - 1, column);
				}
				rowNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public InputStream open(String fileName){
		try {
			return assetManager.open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Typeface getTypeFace(){
		return Typeface.createFromAsset(assetManager, "futurr.ttf");
	}
}
