package com.srirang.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.util.Log;

public class JsonUtils {
	
	private static final String TAG = "JSONUtils";
	
	public JSONObject postData(String path,List<NameValuePair> nameValuePairs, CookieStore mCookieStore) {  

		Log.d(TAG, "postData()");
		
		JSONObject json = null;
		
		// Objects instantiated for HTTP post request
		HttpClient httpclient = new DefaultHttpClient();  
		try {  
			path = URLEncoder.encode(path, "utf-8");
			Log.d(TAG + "path", "path = "+path);
		HttpPost httppost = new HttpPost(path);  
		httppost.addHeader(new Header() {
			
			@Override
			public String getValue() {
				return "9e473d79c22f082b9c075fa8507f04bb4c72a2eb";
			}
			
			@Override
			public String getName() {
				return "bearer";
			}
			
			@Override
			public HeaderElement[] getElements() throws ParseException {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		json = new JSONObject();

		

			// Create local HTTP context
			HttpContext localContext = new BasicHttpContext();
			
			// Bind custom cookie store to the local context
			//localContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);

			// Add your data  
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  

			// Execute HTTP Post Request  
			HttpResponse response = httpclient.execute(httppost,localContext); 

			Log.d(TAG, "response before Converting from Stream to String : " + response.toString());
			if(response!=null)
			{
				InputStream in = response.getEntity().getContent();
				String result= convertStreamToString(in);
				Log.i(TAG+"+postData",result);
				
				json = new JSONObject(result);
				in.close();
			}
			List<Cookie> cookies = mCookieStore.getCookies();
			
			for (int i = 0; i < cookies.size(); i++) {
				Log.i(TAG,"Local cookie: " + cookies.get(i));
			}

		} catch(JSONException je){
			Log.e(TAG, "JSONException in postData() function", je);
		} catch (Exception ex) {
			Log.e(TAG, "Exception in postData() function", ex);
		} 
		return json;
	}
	
	public JSONObject getData(String url, CookieStore mCookieStore) {
		
		Log.d(TAG, "getData()");
		
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		JSONObject json = new JSONObject();
		
		try {
			
			get.addHeader("Authorization", "bearer 9e473d79c22f082b9c075fa8507f04bb4c72a2eb");
			get.addHeader("Accept", "application/json");
			
			HttpResponse response = client.execute(get);
			
			if(response!=null){
				
				InputStream in = response.getEntity().getContent();
				String result= convertStreamToString(in);
				Log.d(TAG+"+getData",result);

				json = new JSONObject(result);
				in.close();
			}

			List<Cookie> cookies = mCookieStore.getCookies();
			
			for (int i = 0; i < cookies.size(); i++) {
				Log.d(TAG,"Local cookie: " + cookies.get(i));
			}
			
		} catch (Exception ex) {
			Log.e(TAG, "Error in getData() function", ex);
		} 

		return json;
	}
	
	private static String convertStreamToString(InputStream is) {
		Log.d(TAG, "convertStreamToString() received");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		Log.d(TAG, "in convertStreamToString");
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
