package com.srirang.mobilevocab;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class AppAssetManager {

	public final AssetManager assetManager;
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
}
