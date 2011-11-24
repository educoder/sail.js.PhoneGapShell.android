package org.educoder.sail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.preference.PreferenceManager;

import com.phonegap.DroidGap;

public class PhoneGapShell extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        String defaultSailAppUrl = getString(R.string.default_sail_app_url);
        String appUrl = prefs.getString("sail_app_url", defaultSailAppUrl);
        if (appUrl.length() == 0) // make sure that the URL isn't blank
        	appUrl = defaultSailAppUrl;
        if (appUrl.length() == 0) // make sure that it still isn't blank (in case defaultSailAppUrl was hosed)
        	appUrl = "file:///android_asset/www/index.html";
        
        super.loadUrl(appUrl);
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
    		startActivity(sailPrefsActivity);
    	}
    }
}

