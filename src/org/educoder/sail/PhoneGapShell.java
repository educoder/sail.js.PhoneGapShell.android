package org.educoder.sail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.phonegap.DroidGap;

public class PhoneGapShell extends DroidGap
{
	static final int SET_PREFERENCES = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        super.loadUrl(getAppUrl());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sail, menu);
        return true;
    }
    
    @Override
	public boolean onKeyDown(int i,KeyEvent e){
		return false;
	}
    
    public void showSailPrefPane(MenuItem item) {
    	Log.v(this.getClass().getName(), "Menu item selected: " + item.toString() + " (" + item.getItemId() + ")");
    	if (item.getItemId() == R.id.sail_settings) {
    		Intent sailPrefsActivity = new Intent(getBaseContext(), SailSettings.class);
    		startActivityForResult(sailPrefsActivity, PhoneGapShell.SET_PREFERENCES);
    	}
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SET_PREFERENCES) {
            if (resultCode == RESULT_OK) {
            	super.onActivityResult(requestCode, resultCode, data);
        		this.loadUrl(this.getAppUrl());
            }
        }
    }
    
    public void reload(MenuItem item) {
    	Log.d("PhoneGapShell", "Deleting cache...");
    	this.getCacheDir().delete();
    	this.loadUrl(this.appView.getOriginalUrl());
    }
    
    public String getAppUrl() {
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        
    	String defaultSailAppUrl = getString(R.string.default_sail_app_url);
        String appUrl = prefs.getString("sail_app_url", defaultSailAppUrl);
        if (appUrl.length() == 0) // make sure that the URL isn't blank
        	appUrl = defaultSailAppUrl;
        if (appUrl.length() == 0) // make sure that it still isn't blank (in case defaultSailAppUrl was hosed)
        	appUrl = "file:///android_asset/www/index.html";
        
        return appUrl;
    }
}

