package com.yeleman.snisidroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class Preferences extends PreferenceActivity {

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

        addPreferencesFromResource(R.layout.snisi_preferences);
    }

}
