package org.educoder.sail;

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class SailSettings extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.sail_preferences_screen);
		setResult(RESULT_OK);
	}

}
