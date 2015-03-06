package com.yeleman.snisidroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.orm.SugarContext;

public class Preferences extends PreferenceActivity {

    private final static String TAG = Constants.getLogTag("Preferences");
    private final int counter = 0;
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

        final Preference restBtt = (Preference)findPreference("restDB");
        restBtt.setSummary(String.format(getString(R.string.summary_reset_db), 3));
        
        restBtt.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            int counter = 3;
            @Override
            public boolean onPreferenceClick(Preference preference) {
                counter --;
                restBtt.setSummary(String.format(getString(R.string.summary_reset_db), counter));
                if(counter == 1) {
                    restBtt.setTitle(getString(R.string.rest_confermed));
                }
                if(counter == 0) {
                    counter = 3;
                    restDatabase(Constants.databaseName);
                }
                return false;
            }
        });
        
    }

    public void restDatabase (String databaseName) {
        SugarContext.terminate();
        getApplicationContext().deleteDatabase(databaseName);
        SugarContext.init(this);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        Toast.makeText(getBaseContext(), databaseName +
                " a été supprimée avec succès", Toast.LENGTH_LONG).show();
    }

}
