package com.yeleman.nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 29/10/14.
 */

public class NutritionURENASReport extends CheckedFormActivity implements View.OnClickListener {
    private final static String TAG = Constants.getLogTag("NutritionURENASReport");

    private Button u59o6ReportButton;
    private Button o59ReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_urenas_report);
        Log.d(TAG, "onCreate NutritionURENASReport");

        setupSMSReceiver();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume(); 
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionMonthlyHome");
        String status = "  ";

        u59o6ReportButton = (Button) findViewById(R.id.u59URENASButton);
        u59o6ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report), new Object[]{"6-59m", status}));
        u59o6ReportButton.setOnClickListener(this);
        o59ReportButton = (Button) findViewById(R.id.o59URENASButton);
        o59ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report), new Object[]{"59m+", status}));
        o59ReportButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Object activity = null;
        switch (view.getId()) {
            case R.id.u59URENiButton:
                //activity = NutritionURENAMReport.class;
                break;
            case R.id.o59URENiButton:
                //activity = NutritionURENASReport.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }
}
