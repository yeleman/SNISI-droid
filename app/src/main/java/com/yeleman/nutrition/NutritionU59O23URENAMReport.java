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
public class NutritionU59O23URENAMReport extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("NutritionU59O23URENAMReport");

    protected TextView transferredLabel;
    protected TextView referredLabel;

    protected EditText totalStartMField;
    protected EditText totalStartFField;
    protected EditText newCasesField;
    protected EditText returnedField;
    protected EditText totalInMField;
    protected EditText totalInFField;
    protected EditText transferredField;
    protected EditText healedField;
    protected EditText deceasedField;
    protected EditText abandonField;
    protected EditText respondingField;
    protected EditText totalOutMField;
    protected EditText totalOutFField;
    protected EditText referredField;
    protected EditText totalEndMField;
    protected EditText totalEndFField;

    protected Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.u59o23)));
        Log.d(TAG, "onCreate NutritionU59O23URENAMReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionU59O23URENAMReport");

        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);
        newCasesField = (EditText) findViewById(R.id.newCasesField);
        returnedField = (EditText) findViewById(R.id.returnedField);
        totalInMField = (EditText) findViewById(R.id.totalInMField);
        totalInFField = (EditText) findViewById(R.id.totalInFField);
        LinearLayout transferred_parent = (LinearLayout) findViewById(R.id.TransferredLinearLayout);
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

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        if (report.u59o23_is_complete){
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
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        report.updateMetaData();

        report.u59o23_total_start_m = integerFromField(totalStartMField);
        report.u59o23_total_start_f = integerFromField(totalStartFField);
        report.u59o23_new_cases = integerFromField(newCasesField);
        report.u59o23_returned = integerFromField(returnedField);
        report.u59o23_total_in_m = integerFromField(totalInMField);
        report.u59o23_total_in_f = integerFromField(totalInFField);
        report.u59o23_healed = integerFromField(healedField);
        report.u59o23_deceased = integerFromField(deceasedField);
        report.u59o23_abandon = integerFromField(abandonField);
        report.u59o23_not_responding = integerFromField(respondingField);
        report.u59o23_total_out_m = integerFromField(totalOutMField);
        report.u59o23_total_out_f = integerFromField(totalOutFField);
        report.u59o23_referred = integerFromField(referredField);
        report.u59o23_total_end_m = integerFromField(totalEndMField);
        report.u59o23_total_end_f = integerFromField(totalEndFField);
        report.u59o23_is_complete = true;
        report.save();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        if (report.u59o23_total_start_m != -1){
            setTextOnField(totalStartMField, report.u59o23_total_start_m);
            setTextOnField(totalStartFField, report.u59o23_total_start_f);
            setTextOnField(newCasesField, report.u59o23_new_cases);
            setTextOnField(returnedField, report.u59o23_returned);
            setTextOnField(totalInMField, report.u59o23_total_in_m);
            setTextOnField(totalInFField, report.u59o23_total_in_f);
            setTextOnField(healedField, report.u59o23_healed);
            setTextOnField(deceasedField, report.u59o23_deceased);
            setTextOnField(abandonField, report.u59o23_abandon);
            setTextOnField(respondingField, report.u59o23_not_responding);
            setTextOnField(totalOutMField, report.u59o23_total_out_m);
            setTextOnField(totalOutFField, report.u59o23_total_out_f);
            setTextOnField(referredField, report.u59o23_referred);
            setTextOnField(totalEndMField, report.u59o23_total_end_m);
            setTextOnField(totalEndFField, report.u59o23_total_end_f);
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
    
    protected boolean ensureDataCoherence() { return true;}

}
