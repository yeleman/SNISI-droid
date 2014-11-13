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
public class NutritionURENIReport extends CheckedFormActivity implements View.OnClickListener {
    private final static String TAG = Constants.getLogTag("NutritionURENIReport");


    private Button u6ReportButton;
    private Button u59o6URENASButton;
    private Button o59ReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_ureni_report);
        Log.d(TAG, "onCreate NutritionURENIReport");

        setupSMSReceiver();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume(); 
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENIReport");
        NutritionURENIReportData report = NutritionURENIReportData.get();

        u6ReportButton = (Button) findViewById(R.id.u6URENIButton);
        u6ReportButton.setText(String.format(getString(R.string.nutrition_fillout_section), getString(R.string.u6)));
        Constants.updateButtonCompletion(u6ReportButton, report.u6_is_complete);
        u6ReportButton.setOnClickListener(this);

        u59o6URENASButton = (Button) findViewById(R.id.u59o6URENASButton);
        u59o6URENASButton.setText(String.format(getString(R.string.nutrition_fillout_section), getString(R.string.u59o6)));
        Constants.updateButtonCompletion(u59o6URENASButton, report.u59o6_is_complete);
        u59o6URENASButton.setOnClickListener(this);

        o59ReportButton = (Button) findViewById(R.id.o59URENiButton);
        o59ReportButton.setText(String.format(getString(R.string.nutrition_fillout_section), getString(R.string.o59)));
        Constants.updateButtonCompletion(o59ReportButton, report.o59_is_complete);
        o59ReportButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Object activity = null;
        switch (view.getId()) {
            case R.id.u6URENIButton:
                activity = NutritionURENIU6Report.class;
                break;
            case R.id.u59o6URENASButton:
                activity = NutritionURENIU59O6Report.class;
                break;
            case R.id.o59URENiButton:
                activity = NutritionURENIO59Report.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }

    protected String buildSMSText() {
        NutritionURENIReportData report = NutritionURENIReportData.get();
        return report.buildSMSText();
    }
}