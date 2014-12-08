package com.yeleman.malaria;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 05/12/14.
 */
public class MalariaMonthlyInputsReport extends Activity{
    private final static String TAG = Constants.getLogTag("MalariaMonthlyInputsReport");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_monthly_inputs_report);
        Log.d(TAG, "onCreate MalariaInpatientReport");

        seputUI();
    }

    protected void seputUI() {
        Log.d(TAG, "setupUI MalariaMonthlyInputsReport");
    }
}
