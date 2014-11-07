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

public class NutritionURENAMPWReport extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENAMPWReport");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENAM; }
    public String getAge() { return PW; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.pw)));
        Log.d(TAG, "onCreate NutritionURENAMPWReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENAMPWReport");

        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);
        newCasesField = (EditText) findViewById(R.id.newCasesField);
        returnedField = (EditText) findViewById(R.id.returnedField);
        totalInMField = (EditText) findViewById(R.id.totalInMField);
        totalInFField = (EditText) findViewById(R.id.totalInFField);
        LinearLayout transferred_parent = (LinearLayout) findViewById(R.id.transferredLinearLayout);
        transferred_parent.setVisibility(View.GONE);
        healedField = (EditText) findViewById(R.id.healedField);
        deceasedField = (EditText) findViewById(R.id.deceasedField);
        abandonField = (EditText) findViewById(R.id.abandonField);
        respondingField = (EditText) findViewById(R.id.respondingField);
        totalOutMField = (EditText) findViewById(R.id.totalOutMField);
        totalOutFField = (EditText) findViewById(R.id.totalOutFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(String.format(getString(R.string.nutrition_referred), "NUT"));
        referredField = (EditText) findViewById(R.id.referredField);
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        if (report.pw_is_complete){
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
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        report.updateMetaData();

        report.pw_total_start_m = integerFromField(totalStartMField);
        report.pw_total_start_f = integerFromField(totalStartFField);
        report.pw_new_cases = integerFromField(newCasesField);
        report.pw_returned = integerFromField(returnedField);
        report.pw_total_in_m = integerFromField(totalInMField);
        report.pw_total_in_f = integerFromField(totalInFField);
        report.pw_healed = integerFromField(healedField);
        report.pw_deceased = integerFromField(deceasedField);
        report.pw_abandon = integerFromField(abandonField);
        report.pw_not_responding = integerFromField(respondingField);
        report.pw_total_out_m = integerFromField(totalOutMField);
        report.pw_total_out_f = integerFromField(totalOutFField);
        report.pw_referred = integerFromField(referredField);
        report.pw_total_end_m = integerFromField(totalEndMField);
        report.pw_total_end_f = integerFromField(totalEndFField);
        report.pw_is_complete = true;
        report.save();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();

        if (report.pw_total_start_m != -1){
            setTextOnField(totalStartMField, report.pw_total_start_m);
            setTextOnField(totalStartFField, report.pw_total_start_f);
            setTextOnField(newCasesField, report.pw_new_cases);
            setTextOnField(returnedField, report.pw_returned);
            setTextOnField(totalInMField, report.pw_total_in_m);
            setTextOnField(totalInFField, report.pw_total_in_f);
            setTextOnField(healedField, report.pw_healed);
            setTextOnField(deceasedField, report.pw_deceased);
            setTextOnField(abandonField, report.pw_abandon);
            setTextOnField(respondingField, report.pw_not_responding);
            setTextOnField(totalOutMField, report.pw_total_out_m);
            setTextOnField(totalOutFField, report.pw_total_out_f);
            setTextOnField(referredField, report.pw_referred);
            setTextOnField(totalEndMField, report.pw_total_end_m);
            setTextOnField(totalEndFField, report.pw_total_end_f);
        }
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStartMField);
        setAssertPositiveInteger(totalStartFField);
        setAssertPositiveInteger(newCasesField);
        setAssertPositiveInteger(returnedField);
        setAssertPositiveInteger(totalInMField);
        setAssertPositiveInteger(totalInFField);
        setAssertPositiveInteger(healedField);
        setAssertPositiveInteger(deceasedField);
        setAssertPositiveInteger(abandonField);
        setAssertPositiveInteger(respondingField);
        setAssertPositiveInteger(totalOutMField);
        setAssertPositiveInteger(totalOutFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
}
