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

public class NutritionURENAMU59O23Report extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENAMU59O23Report");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENAM; }
    public String getAge() { return U59O23; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.u59o23)));
        Log.d(TAG, "onCreate NutritionURENAMU59O23Report");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENAMU59O23Report");

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
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
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
        report.u59o23_not_responding = integerFromField(notRespondingField);
        report.u59o23_total_out_m = integerFromField(totalOutMField);
        report.u59o23_total_out_f = integerFromField(totalOutFField);
        report.u59o23_referred = integerFromField(referredField);
        report.u59o23_total_end_m = integerFromField(totalEndMField);
        report.u59o23_total_end_f = integerFromField(totalEndFField);
        report.u59o23_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        setTextOnField(totalStartMField, report.u59o23_total_start_m);
        setTextOnField(totalStartFField, report.u59o23_total_start_f);
        setTextOnField(newCasesField, report.u59o23_new_cases);
        setTextOnField(returnedField, report.u59o23_returned);
        setTextOnField(totalInMField, report.u59o23_total_in_m);
        setTextOnField(totalInFField, report.u59o23_total_in_f);
        setTextOnField(healedField, report.u59o23_healed);
        setTextOnField(deceasedField, report.u59o23_deceased);
        setTextOnField(abandonField, report.u59o23_abandon);
        setTextOnField(notRespondingField, report.u59o23_not_responding);
        setTextOnField(totalOutMField, report.u59o23_total_out_m);
        setTextOnField(totalOutFField, report.u59o23_total_out_f);
        setTextOnField(referredField, report.u59o23_referred);
        setTextOnField(totalEndMField, report.u59o23_total_end_m);
        setTextOnField(totalEndFField, report.u59o23_total_end_f);

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
