package com.yeleman.malaria;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 05/12/14.
 */
public class MalariaInpatientReport extends Activity {
    private final static String TAG = Constants.getLogTag("MalariaInpatientReport");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_inpatient_report);
        Log.d(TAG, "onCreate MalariaInpatientReport");
        seputUI();
    }

    protected void seputUI() {
        Log.d(TAG, "setupUI MalariaInpatientReport");
    }
}
