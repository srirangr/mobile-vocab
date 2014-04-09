package com.srirang.mobilevocab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.srirang.mobilevocab.EnumConfig.FontType;

public class AppAssetManager {

	private final AssetManager assetManager;
	public static AppAssetManager instance = null;
	
	public final Map<FontType, Typeface> typeFaceMap;
	
	public Context context;
	private AppAssetManager(Context context){
		this.context = context;
		assetManager = context.getAssets(); 
		typeFaceMap = new HashMap<EnumConfig.FontType, Typeface>();
		initTypefaces();
	}
	
	public static AppAssetManager getInstance(Context context){
		if(instance == null){
			instance = new AppAssetManager(context);
		}
		return instance;
	}
	
	private void initTypefaces(){
		// add all existing fonts here
		typeFaceMap.put(FontType.ALIKE, Typeface.createFromAsset(assetManager, "fonts/Alike-Regular.ttf"));
		typeFaceMap.put(FontType.ARAPEY, Typeface.createFromAsset(assetManager, "fonts/Arapey-Regular.ttf"));
		typeFaceMap.put(FontType.ARAPEY_ITALIC, Typeface.createFromAsset(assetManager, "fonts/Arapey-Italic.ttf"));
		typeFaceMap.put(FontType.BUDA, Typeface.createFromAsset(assetManager, "fonts/Buda-Light.ttf"));
		typeFaceMap.put(FontType.DELLA_RESPIRA, Typeface.createFromAsset(assetManager, "fonts/DellaRespira-Regular.ttf"));
		typeFaceMap.put(FontType.GILDA_DISPLAY, Typeface.createFromAsset(assetManager, "fonts/GildaDisplay-Regular.ttf"));
		typeFaceMap.put(FontType.POIRET_ONE, Typeface.createFromAsset(assetManager, "fonts/PoiretOne-Regular.ttf"));
		typeFaceMap.put(FontType.TIENNE, Typeface.createFromAsset(assetManager, "fonts/Tienne-Regular.ttf"));
		typeFaceMap.put(FontType.FUTURR, Typeface.createFromAsset(assetManager, "fonts/futurr.ttf"));
		typeFaceMap.put(FontType.SURFACE, Typeface.createFromAsset(assetManager, "fonts/Surface-Medium.otf"));
		typeFaceMap.put(FontType.CARME, Typeface.createFromAsset(assetManager, "fonts/carme-regular.ttf"));
		typeFaceMap.put(FontType.BERTOLT_BRECHT, Typeface.createFromAsset(assetManager, "fonts/bertoltbrecht.ttf"));
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
	
	// not working
	public ImageView getCroppedImage(String name, int targetWidth, int targetHeight){
		try {
			InputStream is = assetManager.open(name);
			Bitmap bmp = BitmapFactory.decodeStream(is);
			
			Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,Bitmap.Config.ARGB_8888);
			
			Paint paint = new Paint();
            paint.setFilterBitmap(true);
            
            RectF rectf = new RectF(0, 0, 100, 100);
 
            Canvas canvas = new Canvas(targetBitmap);
            Path path = new Path();
 
            path.addRect(rectf, Path.Direction.CW);
            canvas.clipPath(path);
 
            canvas.drawBitmap( bmp, new Rect(0, 0, bmp.getWidth(), bmp.getHeight()),
                            new Rect(0, 0, targetWidth, targetHeight), paint);
 
            Matrix matrix = new Matrix();
            matrix.postScale(1f, 1f);
            Bitmap resizedBitmap = Bitmap.createBitmap(targetBitmap, 0, 0, 100, 100, matrix, true);
 
			ImageView iv = new ImageView(context);
			iv.setImageBitmap(resizedBitmap);
		}catch (IOException e) {
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
	
	public Typeface getTypeFace(FontType fontType){
		return typeFaceMap.get(fontType);
	}
}
