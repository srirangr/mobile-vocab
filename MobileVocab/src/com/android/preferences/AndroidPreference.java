package com.android.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AndroidPreference implements IPreference{

	final SharedPreferences preferences;
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String DEFAULT_STRING_VALUE = "";
	public static final String DEFAULT_INT_VALUE = "-1";
	public static final String DEFAULT_BOOL_VALUE = "false";
	
	public AndroidPreference(Context context){
		preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	}
	
	@Override
	public void setPreference(String name, String value) {
		Editor editor = preferences.edit();
		editor.putString(name, value);
		editor.commit();
	}

	@Override
	public String getPreference(String name, String defaultValue) {
		if(preferences.contains(name)){
			return preferences.getString(name, defaultValue);
		}
		return null;
	}

	@Override
	public String getPreference(String name) {
		return getPreference(name, DEFAULT_STRING_VALUE);
	}

	@Override
	public void setIntegerPreference(String name, int value) {
		setPreference(name, String.valueOf(value));
	}

	@Override
	public int getIntegerPreference(String name, int defaultValue) {
		String result = getPreference(name, String.valueOf(defaultValue));
		return result == null ? defaultValue : Integer.parseInt(result);
	}

	@Override
	public int getIntegerPreference(String name) {
		String result = getPreference(name, DEFAULT_INT_VALUE);
		return result == null ? Integer.parseInt(DEFAULT_INT_VALUE) : Integer.parseInt(result);
	}

	@Override
	public void setBooleanPreference(String name, boolean value) {
		setPreference(name, String.valueOf(value));
	}

	@Override
	public boolean getBooleanPreference(String name, boolean defaultValue) {
		String result = getPreference(name, String.valueOf(defaultValue));
		return result == null ? defaultValue :Boolean.parseBoolean(result);
	}

	@Override
	public boolean getBooleanPreference(String name) {
		String result = getPreference(name, DEFAULT_BOOL_VALUE);
		return result == null ? Boolean.parseBoolean(DEFAULT_BOOL_VALUE) :Boolean.parseBoolean(result);
	}

}
