package com.yeleman.nutrition;

import android.os.Bundle;
import android.util.Log;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 29/10/14.
 */
public class NutritionInputsReport extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritionInputsReport");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_inputs_report);
        Log.d(TAG, "onCreate NutritionInputsReport");

        setupSMSReceiver();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume(); 
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionInputsReport");

    }
}
