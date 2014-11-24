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
import java.sql.Struct;

public class NutritionURENAMU23O6Report extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENAMU23O6Report");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENAM; }
    public String getAge() { return U23O6; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.u23o6)));
        Log.d(TAG, "onCreate NutritionURENAMU23O6Report");
        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENAMU23O6Report");

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
        notRespondingField = (EditText) findViewById(R.id.notRespondingField);
        totalOutMField = (EditText) findViewById(R.id.totalOutMField);
        totalOutFField = (EditText) findViewById(R.id.totalOutFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(getString(R.string.nutrition_referred_label_urenam));
        referredField = (EditText) findViewById(R.id.referredField);
        referredField.setHint(getString(R.string.nutrition_referred_label_urenam));
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        if (report.u23o6_is_complete) {
            restoreReportData();
        }

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) {
                    return;
                }
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

        report.u23o6_total_start_m = integerFromField(totalStartMField);
        report.u23o6_total_start_f = integerFromField(totalStartFField);
        report.u23o6_new_cases = integerFromField(newCasesField);
        report.u23o6_returned = integerFromField(returnedField);
        report.u23o6_total_in_m = integerFromField(totalInMField);
        report.u23o6_total_in_f = integerFromField(totalInFField);
        report.u23o6_healed = integerFromField(healedField);
        report.u23o6_deceased = integerFromField(deceasedField);
        report.u23o6_abandon = integerFromField(abandonField);
        report.u23o6_not_responding = integerFromField(notRespondingField);
        report.u23o6_total_out_m = integerFromField(totalOutMField);
        report.u23o6_total_out_f = integerFromField(totalOutFField);
        report.u23o6_referred = integerFromField(referredField);
        report.u23o6_total_end_m = integerFromField(totalEndMField);
        report.u23o6_total_end_f = integerFromField(totalEndFField);
        report.u23o6_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        setTextOnField(totalStartMField, report.u23o6_total_start_m);
        setTextOnField(totalStartFField, report.u23o6_total_start_f);
        setTextOnField(newCasesField, report.u23o6_new_cases);
        setTextOnField(returnedField, report.u23o6_returned);
        setTextOnField(totalInMField, report.u23o6_total_in_m);
        setTextOnField(totalInFField, report.u23o6_total_in_f);
        setTextOnField(healedField, report.u23o6_healed);
        setTextOnField(deceasedField, report.u23o6_deceased);
        setTextOnField(abandonField, report.u23o6_abandon);
        setTextOnField(notRespondingField, report.u23o6_not_responding);
        setTextOnField(totalOutMField, report.u23o6_total_out_m);
        setTextOnField(totalOutFField, report.u23o6_total_out_f);
        setTextOnField(referredField, report.u23o6_referred);
        setTextOnField(totalEndMField, report.u23o6_total_end_m);
        setTextOnField(totalEndFField, report.u23o6_total_end_f);

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
        setAssertPositiveInteger(notRespondingField);
        setAssertPositiveInteger(totalOutMField);
        setAssertPositiveInteger(totalOutFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
}
    
