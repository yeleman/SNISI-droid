package com.yeleman.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

import java.lang.reflect.Field;

public class NutritonU23o6URENAMReport extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritonU23o6URENAMReport");

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
                getString(R.string.u23o6)));
        Log.d(TAG, "onCreate NutritonU23o6URENAMReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritonU23o6URENAMReport");

        totalStarMField = (EditText) findViewById(R.id.totalStarMField);
        totalStarFField = (EditText) findViewById(R.id.totalStarFField);
        newCasesField = (EditText) findViewById(R.id.newCasesField);
        returnedField = (EditText) findViewById(R.id.returnedField);
        totalAdmMField = (EditText) findViewById(R.id.totalAdmMField);
        totalAdmFField = (EditText) findViewById(R.id.totalAdmFField);

        LinearLayout transferred_parent = (LinearLayout) findViewById(R.id.linearLayoutReferred);
        transferred_parent.setVisibility(View.GONE);

        //transferredLabel = (TextView) findViewById(R.id.totalTransferredLabel);
        // transferredLabel.setVisibility(View.GONE);
        //transferredField = (EditText) findViewById(R.id.transferredField);
        //transferredField.setVisibility(View.GONE);

        healedField = (EditText) findViewById(R.id.healedField);
        deceasedField = (EditText) findViewById(R.id.deceasedField);
        abandonField = (EditText) findViewById(R.id.abandonField);
        respondingField = (EditText) findViewById(R.id.respondingField);
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

        Log.d(TAG, "requestForResumeReport NutritonU23o6URENAMReport");
        requestForResumeReport(this, NutritonURENAMReportData.get());
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        report.updateMetaData();

        report.u23o6_total_start_m = integerFromField(totalStarMField);
        
        report.save();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();

        setTextOnField(totalStarMField, report.u23o6_total_start_m);
        setTextOnField(totalStarFField, report.u23o6_total_start_f);
        setTextOnField(newCasesField, report.u23o6_new_cases);
        setTextOnField(returnedField, report.u23o6_returned);
        setTextOnField(totalAdmMField, report.u23o6_total_in_m);
        setTextOnField(totalAdmFField, report.u23o6_total_in_f);
        setTextOnField(healedField, report.u23o6_healed);
        setTextOnField(deceasedField, report.u23o6_deceased);
        setTextOnField(abandonField, report.u23o6_abandon);
        setTextOnField(respondingField, report.u23o6_not_responding);
        setTextOnField(totalOutsMField, report.u23o6_total_out_m);
        setTextOnField(totalOutsFField, report.u23o6_total_out_f);
        setTextOnField(referredField, report.u23o6_referred);
        setTextOnField(totalEndMField, report.u23o6_total_end_m);
        setTextOnField(totalEndFField, report.u23o6_total_end_f);
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStarMField);
        setAssertPositiveInteger(totalStarFField);
        setAssertPositiveInteger(newCasesField);
        setAssertPositiveInteger(returnedField);
        setAssertPositiveInteger(totalAdmMField);
        setAssertPositiveInteger(totalAdmFField);
        setAssertPositiveInteger(healedField);
        setAssertPositiveInteger(deceasedField);
        setAssertPositiveInteger(abandonField);
        setAssertPositiveInteger(respondingField);
        setAssertPositiveInteger(totalOutsMField);
        setAssertPositiveInteger(totalOutsFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
    
    protected boolean ensureDataCoherence() { return true;}

}
