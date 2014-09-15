package com.yeleman.projetsnisi;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Parametres extends PreferenceActivity {

//   private CheckBoxPreference showSplash;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.layout.parametres);
    }

}
