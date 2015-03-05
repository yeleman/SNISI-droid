package com.yeleman.snisidroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences extends PreferenceActivity {

    private final static String TAG = Constants.getLogTag("Preferences");
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(String.format(getString(R.string.label_tmpl),
                               getString(R.string.app_name),
                               getString(R.string.menu_settings)));

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String serverNumber = sharedPrefs.getString("serverPhoneNumber", "");
        if (serverNumber.trim().equals("")) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("serverPhoneNumber", Constants.server_number);
            editor.apply();
        }
        addPreferencesFromResource(R.xml.snisi_preferences);
        Preference button = (Preference)findPreference("magic");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            int counter = 0;
            @Override
            public boolean onPreferenceClick(Preference arg0) {
                counter++;
                Log.d(TAG, counter + " clique");
                if (counter == 10){
                    counter = 0;
                    Intent a = new Intent(getApplicationContext(), DBManager.class);
                    startActivity(a);
                }
                return true;
            }
        });
    }

}
