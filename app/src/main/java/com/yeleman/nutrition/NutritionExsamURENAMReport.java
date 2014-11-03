package com.yeleman.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 31/10/14.
 */
public class NutritionExsamURENAMReport extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritionExsamURENAMReport");

    protected TextView transferredLabel;
    protected TextView referredLabel;

    protected EditText totalStarMField;
    protected EditText totalStarFField;
    protected EditText newCasesField;
    protected EditText returnedField;
    protected EditText totalAdmMField;
    protected EditText totalAdmFField;
    protected EditText transferredField;
    protected EditText healedField;
    protected EditText deceasedField;
    protected EditText abandonField;
    protected EditText respondingField;
    protected EditText totalOutsMField;
    protected EditText totalOutsFField;
    protected EditText referredField;
    protected EditText totalEndMField;
    protected EditText totalEndFField;

    protected Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit);
        setTitle(String.format(getString(R.string.nutrition_fillout_urenam_report),
                getString(R.string.o59)));
        Log.d(TAG, "onCreate NutritionO59URENAMReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionO59URENAMReport");

        newCasesField = (EditText) findViewById(R.id.newCasesField);
        newCasesField.setVisibility(View.GONE);
        returnedField = (EditText) findViewById(R.id.returnedField);
        returnedField.setVisibility(View.GONE);
        totalAdmMField = (EditText) findViewById(R.id.totalAdmMField);
        totalAdmMField.setVisibility(View.GONE);
        totalAdmFField = (EditText) findViewById(R.id.totalAdmFField);
        totalAdmFField.setVisibility(View.GONE);
        transferredLabel = (TextView) findViewById(R.id.totalTransferredLabel);
        transferredLabel.setVisibility(View.GONE);
        transferredField = (EditText) findViewById(R.id.transferredField);
        transferredField.setVisibility(View.GONE);
        healedField = (EditText) findViewById(R.id.healedField);
        healedField.setVisibility(View.GONE);
        deceasedField = (EditText) findViewById(R.id.deceasedField);
        deceasedField.setVisibility(View.GONE);
        abandonField = (EditText) findViewById(R.id.abandonField);
        abandonField.setVisibility(View.GONE);
        respondingField = (EditText) findViewById(R.id.respondingField);
        respondingField.setVisibility(View.GONE);

        totalStarMField = (EditText) findViewById(R.id.totalStarMField);
        totalStarFField = (EditText) findViewById(R.id.totalStarFField);
        totalOutsMField = (EditText) findViewById(R.id.totalOutsMField);
        totalOutsFField = (EditText) findViewById(R.id.totalOutsFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(String.format(getString(R.string.nutrition_referred), "NUT"));
        referredField = (EditText) findViewById(R.id.referredField);
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);


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

        Log.d(TAG, "requestForResumeReport NutritionO59URENAMReport");
        requestForResumeReport(this, NutritonURENAMReportData.get());
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        report.updateMetaData();

        setTextOnField(totalStarMField, report.exsam_total_start_m);
        setTextOnField(totalStarFField, report.exsam_total_start_f);
        setTextOnField(totalOutsMField, report.exsam_total_out_m);
        setTextOnField(totalOutsFField, report.exsam_total_out_f);
        setTextOnField(referredField, report.exsam_referred);
        setTextOnField(totalEndMField, report.exsam_total_end_m);
        setTextOnField(totalEndFField, report.exsam_total_end_f);

    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStarMField);
        setAssertPositiveInteger(totalStarFField);
        setAssertPositiveInteger(totalOutsMField);
        setAssertPositiveInteger(totalOutsFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }

    protected boolean ensureDataCoherence() { return true;}
}
