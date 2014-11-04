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

public class NutritionO59URENAMReport extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritionO59URENAMReport");

    protected TextView referredLabel;

    protected EditText totalStarMField;
    protected EditText totalStarFField;
    protected EditText newCasesField;
    protected EditText returnedField;
    protected EditText totalInMField;
    protected EditText totalInFField;
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
        setTitle(String.format(getString(R.string.nutrition_fillout_urenam_report),
                getString(R.string.o59)));
        Log.d(TAG, "onCreate NutritionO59URENAMReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionO59URENAMReport");

        totalStarMField = (EditText) findViewById(R.id.totalStarMField);
        totalStarFField = (EditText) findViewById(R.id.totalStarFField);
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

        //Log.d(TAG, "requestForResumeReport NutritionO59URENAMReport");
        //requestForResumeReport(this, NutritonURENAMReportData.get());
    }
    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        report.updateMetaData();

        report.o59_total_start_m = integerFromField(totalStarMField);
        report.o59_total_start_f = integerFromField(totalStarFField);
        report.o59_new_cases = integerFromField(newCasesField);
        report.o59_returned = integerFromField(returnedField);
        report.o59_total_in_m = integerFromField(totalInMField);
        report.o59_total_in_f = integerFromField(totalInFField);
        report.o59_healed = integerFromField(healedField);
        report.o59_deceased = integerFromField(deceasedField);
        report.o59_abandon = integerFromField(abandonField);
        report.o59_not_responding = integerFromField(respondingField);
        report.o59_total_out_m = integerFromField(totalOutMField);
        report.o59_total_out_f = integerFromField(totalOutFField);
        report.o59_referred = integerFromField(referredField);
        report.o59_total_end_m = integerFromField(totalEndMField);
        report.o59_total_end_f = integerFromField(totalEndFField);
        report.o59_is_complete = true;
        report.save();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritonURENAMReportData report = NutritonURENAMReportData.get();
        if (report.o59_total_start_m != -1){
            setTextOnField(totalStarMField, report.o59_total_start_m);
            setTextOnField(totalStarFField, report.o59_total_start_f);
            setTextOnField(newCasesField, report.o59_new_cases);
            setTextOnField(returnedField, report.o59_returned);
            setTextOnField(totalInMField, report.o59_total_in_m);
            setTextOnField(totalInFField, report.o59_total_in_f);
            setTextOnField(healedField, report.o59_healed);
            setTextOnField(deceasedField, report.o59_deceased);
            setTextOnField(abandonField, report.o59_abandon);
            setTextOnField(respondingField, report.o59_not_responding);
            setTextOnField(totalOutMField, report.o59_total_out_m);
            setTextOnField(totalOutFField, report.o59_total_out_f);
            setTextOnField(referredField, report.o59_referred);
            setTextOnField(totalEndMField, report.o59_total_end_m);
            setTextOnField(totalEndFField, report.o59_total_end_f);
        }
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStarMField);
        setAssertPositiveInteger(totalStarFField);
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
