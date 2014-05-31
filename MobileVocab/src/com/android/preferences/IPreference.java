package com.android.preferences;

public interface IPreference {
	
	public void setPreference(String name, String value);
	public String getPreference(String name, String defaultValue);
	public String getPreference(String name);
	
	public void setIntegerPreference(String name, int value);
	public int getIntegerPreference(String name, int defaultValue);
	public int getIntegerPreference(String name);

	public void setBooleanPreference(String name, boolean value);
	public boolean getBooleanPreference(String name, boolean defaultValue);
	public boolean getBooleanPreference(String name);
}
