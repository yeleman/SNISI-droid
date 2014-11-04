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

/**
 * Created by fad on 31/10/14.
 */
public class NutritionExsamURENAMReport extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritionExsamURENAMReport");

    protected TextView referredLabel;

    protected EditText totalStarMField;
    protected EditText totalStarFField;
    protected EditText referredField;
    protected EditText totalEndMField;
    protected EditText totalEndFField;

    protected Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit);
        setTitle(String.format(getString(R.string.nutrition_fillout_urenam_report),
                getString(R.string.exsam)));
        Log.d(TAG, "onCreate NutritionO59URENAMReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionO59URENAMReport");

        LinearLayout transferred_parent = (LinearLayout) findViewById(R.id.TransferredLinearLayout);
        transferred_parent.setVisibility(View.GONE);
        LinearLayout in_parent = (LinearLayout) findViewById(R.id.inLinearLayout);
        in_parent.setVisibility(View.GONE);
        LinearLayout out_parent = (LinearLayout) findViewById(R.id.outLinearLayout);
        out_parent.setVisibility(View.GONE);

        totalStarMField = (EditText) findViewById(R.id.totalStarMField);
        totalStarFField = (EditText) findViewById(R.id.totalStarFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(String.format(getString(R.string.nutrition_referred), "NUT"));
        referredField = (EditText) findViewById(R.id.referredField);
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        Bundle extras = getIntent().getExtras();
        Boolean restoreReport = Boolean.valueOf(extras.getString("restoreReport"));
        if (restoreReport){
            restoreReportData();
        }
        // setup invalid inputs checks
        setupInvalidInputChecks();

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
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        report.updateMetaData();

        report.exsam_total_start_m = integerFromField(totalStarMField);
        report.exsam_total_start_f = integerFromField(totalStarFField);
        report.exsam_referred = integerFromField(referredField);
        report.exsam_total_end_m = integerFromField(totalEndMField);
        report.exsam_total_end_f = integerFromField(totalEndFField);
        report.exsam_is_complete = true;
        
        report.save();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        if(report.exsam_total_start_m != -1){
            setTextOnField(totalStarMField, report.exsam_total_start_m);
            setTextOnField(totalStarFField, report.exsam_total_start_f);
            setTextOnField(referredField, report.exsam_referred);
            setTextOnField(totalEndMField, report.exsam_total_end_m);
            setTextOnField(totalEndFField, report.exsam_total_end_f);
        }
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStarMField);
        setAssertPositiveInteger(totalStarFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
    
    protected boolean ensureDataCoherence() { return true;}

}