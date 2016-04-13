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

public class NutritionURENAMO59Report extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENAMO59Report");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENAM; }
    public String getAge() { return O59; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.o59)));
        Log.d(TAG, "onCreate NutritionURENAMO59Report");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENAMO59Report");

        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);
        newCasesField = (EditText) findViewById(R.id.newCasesField);
        returnedField = (EditText) findViewById(R.id.returnedField);
        totalInMField = (EditText) findViewById(R.id.totalInMField);
        totalInFField = (EditText) findViewById(R.id.totalInFField);
        LinearLayout transferred_parent = (LinearLayout) findViewById(R.id.transferredLinearLayout);
        transferred_parent.setVisibility(View.GONE);

        LinearLayout grand_total_in_parent = (LinearLayout) findViewById(R.id.grandTotalInLinearLayout);
        grand_total_in_parent.setVisibility(View.GONE);

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

        LinearLayout grand_total_out_parent = (LinearLayout) findViewById(R.id.grandTotalOutLinearLayout);
        grand_total_out_parent.setVisibility(View.GONE);

        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        if (report.o59_is_complete){
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

        report.o59_total_start_m = integerFromField(totalStartMField);
        report.o59_total_start_f = integerFromField(totalStartFField);
        report.o59_new_cases = integerFromField(newCasesField);
        report.o59_returned = integerFromField(returnedField);
        report.o59_total_in_m = integerFromField(totalInMField);
        report.o59_total_in_f = integerFromField(totalInFField);
        report.o59_healed = integerFromField(healedField);
        report.o59_deceased = integerFromField(deceasedField);
        report.o59_abandon = integerFromField(abandonField);
        report.o59_not_responding = integerFromField(notRespondingField);
        report.o59_total_out_m = integerFromField(totalOutMField);
        report.o59_total_out_f = integerFromField(totalOutFField);
        report.o59_referred = integerFromField(referredField);
        report.o59_total_end_m = integerFromField(totalEndMField);
        report.o59_total_end_f = integerFromField(totalEndFField);
        report.o59_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        setTextOnField(totalStartMField, report.o59_total_start_m);
        setTextOnField(totalStartFField, report.o59_total_start_f);
        setTextOnField(newCasesField, report.o59_new_cases);
        setTextOnField(returnedField, report.o59_returned);
        setTextOnField(totalInMField, report.o59_total_in_m);
        setTextOnField(totalInFField, report.o59_total_in_f);
        setTextOnField(healedField, report.o59_healed);
        setTextOnField(deceasedField, report.o59_deceased);
        setTextOnField(abandonField, report.o59_abandon);
        setTextOnField(notRespondingField, report.o59_not_responding);
        setTextOnField(totalOutMField, report.o59_total_out_m);
        setTextOnField(totalOutFField, report.o59_total_out_f);
        setTextOnField(referredField, report.o59_referred);
        setTextOnField(totalEndMField, report.o59_total_end_m);
        setTextOnField(totalEndFField, report.o59_total_end_f);
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
