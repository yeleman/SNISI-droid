package com.yeleman.nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 29/10/14.
 */

public class NutritionURENASReport extends CheckedFormActivity implements View.OnClickListener {
    private final static String TAG = Constants.getLogTag("NutritionURENASReport");

    private TextView instructionLabel;
    private Button u59o6ReportButton;
    private Button o59ReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_urenas_report);
        setTitle(String.format(getString(R.string.sub_app_name_nut),
                getString(R.string.urenas)));
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
        
        NutritionURENASReportData report = NutritionURENASReportData.get();

        instructionLabel = (TextView) findViewById(R.id.instructionLabel);
        if (report.isComplete()) {
            instructionLabel.setText(R.string.nutrition_instructions_complete);
        } else{
            instructionLabel.setText(R.string.nutrition_instructions_incomplete);
        }

        u59o6ReportButton = (Button) findViewById(R.id.u59o6URENASButton);
        u59o6ReportButton.setText(String.format(getString(R.string.nutrition_fillout_section), getString(R.string.u59o6)));
        Constants.updateButtonCompletion(u59o6ReportButton, report.u59o6_is_complete);
        u59o6ReportButton.setOnClickListener(this);
        o59ReportButton = (Button) findViewById(R.id.o59URENASButton);
        o59ReportButton.setText(String.format(getString(R.string.nutrition_fillout_section), getString(R.string.o59)));
        Constants.updateButtonCompletion(o59ReportButton, report.o59_is_complete);
        o59ReportButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Object activity = null;
        switch (view.getId()) {
            case R.id.u59o6URENASButton:
                activity = NutritionURENASU59O6Report.class;
                break;
            case R.id.o59URENASButton:
                activity = NutritionURENASO59Report.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }

    protected String buildSMSText() {
        NutritionURENASReportData report = NutritionURENASReportData.get();
        return report.buildSMSText();
    }
}
