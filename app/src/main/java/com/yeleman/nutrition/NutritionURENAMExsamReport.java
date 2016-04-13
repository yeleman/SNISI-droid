package com.yeleman.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        LinearLayout referred_parent = (LinearLayout) findViewById(R.id.referredlinearLayout);
        referred_parent.setVisibility(View.GONE);
        LinearLayout in_parent = (LinearLayout) findViewById(R.id.inLinearLayout);
        in_parent.setVisibility(View.GONE);
        LinearLayout out_parent = (LinearLayout) findViewById(R.id.outLinearLayout);
        out_parent.setVisibility(View.GONE);

        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);

        // Grand Total In
        grandTotalInLabel = (TextView) findViewById(R.id.grandTotalInLabel);
        grandTotalInLabel.setText(getString(R.string.nutrition_grand_total_in_label));
        grandTotalInField = (EditText) findViewById(R.id.grandTotalInField);
        grandTotalInField.setHint(getString(R.string.nutrition_grand_total_in_label));

        // Grand Total Out
        grandTotalOutLabel = (TextView) findViewById(R.id.grandTotalOutLabel);
        grandTotalOutLabel.setText(getString(R.string.nutrition_grand_total_out_label));
        grandTotalOutField = (EditText) findViewById(R.id.grandTotalOutField);
        grandTotalOutField.setHint(getString(R.string.nutrition_grand_total_out_label));

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
        report.exsam_grand_total_in = integerFromField(grandTotalInField);
        report.exsam_grand_total_out = integerFromField(grandTotalOutField);
        report.exsam_total_end_m = integerFromField(totalEndMField);
        report.exsam_total_end_f = integerFromField(totalEndFField);
        report.exsam_is_complete = true;
        
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();

        setTextOnField(totalStartMField, report.exsam_total_start_m);
        setTextOnField(totalStartFField, report.exsam_total_start_f);
        setTextOnField(grandTotalInField, report.exsam_grand_total_in);
        setTextOnField(grandTotalOutField, report.exsam_grand_total_out);
        setTextOnField(totalEndMField, report.exsam_total_end_m);
        setTextOnField(totalEndFField, report.exsam_total_end_f);
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStartMField);
        setAssertPositiveInteger(totalStartFField);
        setAssertPositiveInteger(grandTotalInField);
        setAssertPositiveInteger(grandTotalOutField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
}
