package com.yeleman.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

public class NutritionURENAMExsamReport extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENAMExsamReport");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENAM; }
    public String getAge() { return EXSAM; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.exsam)));
        Log.d(TAG, "onCreate NutritionURENAMExsamReport");

        setupSMSReceiver();
        setupUI();
    }


    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENAMExsamReport");

        LinearLayout transferred_parent = (LinearLayout) findViewById(R.id.transferredLinearLayout);
        transferred_parent.setVisibility(View.GONE);
        LinearLayout in_parent = (LinearLayout) findViewById(R.id.inLinearLayout);
        in_parent.setVisibility(View.GONE);
        LinearLayout out_parent = (LinearLayout) findViewById(R.id.outLinearLayout);
        out_parent.setVisibility(View.GONE);

        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(String.format(getString(R.string.nutrition_referred), "NUT"));
        referredField = (EditText) findViewById(R.id.referredField);
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        if (report.exsam_is_complete){
            restoreReportData();
        }

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) { return; }

                // save data to DB
                storeReportData();

                finish();
            }
        });
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        report.updateMetaData();

        report.exsam_total_start_m = integerFromField(totalStartMField);
        report.exsam_total_start_f = integerFromField(totalStartFField);
        report.exsam_referred = integerFromField(referredField);
        report.exsam_total_end_m = integerFromField(totalEndMField);
        report.exsam_total_end_f = integerFromField(totalEndFField);
        report.exsam_is_complete = true;
        
        report.save();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        if(report.exsam_total_start_m != -1){
            setTextOnField(totalStartMField, report.exsam_total_start_m);
            setTextOnField(totalStartFField, report.exsam_total_start_f);
            setTextOnField(referredField, report.exsam_referred);
            setTextOnField(totalEndMField, report.exsam_total_end_m);
            setTextOnField(totalEndFField, report.exsam_total_end_f);
        }
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStartMField);
        setAssertPositiveInteger(totalStartFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
}