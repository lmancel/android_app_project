package com.example.projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class fluxActivity extends Activity{
	
	static final String TAG = "app";
	
	protected void onCreate(){
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://cci.corellis.eu/pois.php");
		try {
			HttpResponse response = httpclient.execute(httpget);
			if(response != null) {
				String line = "";
				InputStream inputstream = response.getEntity().getContent(); line = convertStreamToString(inputstream);
				JSONObject theObject = new JSONObject(line);
				JSONArray jsonArray = theObject.getJSONArray("results");
				Log.i(TAG,
						"Number of entries " + jsonArray.length());}
				else {
					Toast.makeText(this, "Unable to complete your request", Toast.LENGTH_LONG).show();
				}
			
		} 
		catch (ClientProtocolException e) {
			Toast.makeText(this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
			Log.e("test",e.getMessage());
		} 
		catch (IOException e) {
			Toast.makeText(this, "Caught IOException", Toast.LENGTH_SHORT).show();
			Log.e("test",e.getMessage());
		}
		catch (Exception e) {
			Toast.makeText(this, "Caught Exception", Toast.LENGTH_SHORT).show();
			Log.e("test",e.getMessage());
		}
	}	
	
	private String convertStreamToString(InputStream is) {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = rd.readLine()) != null) {
				total.append(line);
			}
		}
		catch (Exception e) {
			Toast.makeText(this, "Stream Exception", Toast.LENGTH_SHORT).show(); 
		}
		return total.toString(); 
	}
}

	