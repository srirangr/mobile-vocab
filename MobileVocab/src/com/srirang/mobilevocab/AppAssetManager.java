package com.srirang.mobilevocab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.widget.ImageView;

import com.android.utils.Utility;
import com.srirang.model.WordListItem;

public class AppAssetManager {

	private final AssetManager assetManager;
	public static AppAssetManager instance = null;
	
	public Typeface typeface = null;
	
	private AppAssetManager(AssetManager assetManager){
		this.assetManager = assetManager; 
	}
	
	public static AppAssetManager getInstance(AssetManager assetManager){
		if(instance == null){
			instance = new AppAssetManager(assetManager);
		}
		return instance;
	}
	
	
	public ImageView getImage(Context context, String name){
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
		if(typeface == null)
		{		
			typeface =  Typeface.createFromAsset(assetManager, "fonts/futurr.ttf");
		}
		return typeface;
	}
	
	public List<WordListItem> getWordList(){
		List<WordListItem> list = new ArrayList<WordListItem>();
		
		try {
			InputStream is = assetManager.open("json/wordlist_1.json");
			String json = Utility.convertStreamToString(is);
			JSONArray jsonArray = new JSONArray(json);
			for(int i=0; i < jsonArray.length() ; i++){
				JSONObject obj = (JSONObject) jsonArray.get(i);
				System.out.println(obj.get("word") + ":" + obj.get("meaning"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
