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

public class NutritionURENIU59O6Report extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENIU59O6Report");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENI; }
    public String getAge() { return U59O6; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.u59o6)));
        Log.d(TAG, "onCreate NutritionURENIU59O6Report");
        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENIU59O6Report");

        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);
        newCasesField = (EditText) findViewById(R.id.newCasesField);
        returnedField = (EditText) findViewById(R.id.returnedField);
        totalInMField = (EditText) findViewById(R.id.totalInMField);
        totalInFField = (EditText) findViewById(R.id.totalInFField);
        transferredLabel = (TextView) findViewById(R.id.totalTransferredLabel);
        transferredLabel.setText(getString(R.string.nutrition_transfer_label_ureni));
        transferredField = (EditText) findViewById(R.id.transferredField);
        transferredField.setHint(getString(R.string.nutrition_transfer_label_ureni));
        healedField = (EditText) findViewById(R.id.healedField);
        healedField.setHint(getString(R.string.nutrition_healed_label_ureni));
        deceasedField = (EditText) findViewById(R.id.deceasedField);
        abandonField = (EditText) findViewById(R.id.abandonField);
        notRespondingField = (EditText) findViewById(R.id.notRespondingField);
        notRespondingField.setHint(getString(R.string.nutrition_not_responding_label_ureni));
        totalOutMField = (EditText) findViewById(R.id.totalOutMField);
        totalOutFField = (EditText) findViewById(R.id.totalOutFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(getString(R.string.nutrition_referred_label_ureni));
        referredField = (EditText) findViewById(R.id.referredField);
        referredField.setHint(getString(R.string.nutrition_referred_label_ureni));
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionURENIReportData report = NutritionURENIReportData.get();
        if (report.u59o6_is_complete){
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
        NutritionURENIReportData report = NutritionURENIReportData.get();
        report.updateMetaData();

        report.u59o6_total_start_m = integerFromField(totalStartMField);
        report.u59o6_total_start_f = integerFromField(totalStartFField);
        report.u59o6_new_cases = integerFromField(newCasesField);
        report.u59o6_returned = integerFromField(returnedField);
        report.u59o6_total_in_m = integerFromField(totalInMField);
        report.u59o6_total_in_f = integerFromField(totalInFField);
        report.u59o6_healed = integerFromField(healedField);
        report.u59o6_transferred = integerFromField(transferredField);
        report.u59o6_deceased = integerFromField(deceasedField);
        report.u59o6_abandon = integerFromField(abandonField);
        report.u59o6_not_responding = integerFromField(notRespondingField);
        report.u59o6_total_out_m = integerFromField(totalOutMField);
        report.u59o6_total_out_f = integerFromField(totalOutFField);
        report.u59o6_referred = integerFromField(referredField);
        report.u59o6_total_end_m = integerFromField(totalEndMField);
        report.u59o6_total_end_f = integerFromField(totalEndFField);
        report.u59o6_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENIReportData report = NutritionURENIReportData.get();
        setTextOnField(totalStartMField, report.u59o6_total_start_m);
        setTextOnField(totalStartFField, report.u59o6_total_start_f);
        setTextOnField(newCasesField, report.u59o6_new_cases);
        setTextOnField(returnedField, report.u59o6_returned);
        setTextOnField(totalInMField, report.u59o6_total_in_m);
        setTextOnField(totalInFField, report.u59o6_total_in_f);
        setTextOnField(healedField, report.u59o6_healed);
        setTextOnField(transferredField, report.u59o6_transferred);
        setTextOnField(deceasedField, report.u59o6_deceased);
        setTextOnField(abandonField, report.u59o6_abandon);
        setTextOnField(notRespondingField, report.u59o6_not_responding);
        setTextOnField(totalOutMField, report.u59o6_total_out_m);
        setTextOnField(totalOutFField, report.u59o6_total_out_f);
        setTextOnField(referredField, report.u59o6_referred);
        setTextOnField(totalEndMField, report.u59o6_total_end_m);
        setTextOnField(totalEndFField, report.u59o6_total_end_f);
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(totalStartMField);
        setAssertPositiveInteger(totalStartFField);
        setAssertPositiveInteger(newCasesField);
        setAssertPositiveInteger(returnedField);
        setAssertPositiveInteger(totalInMField);
        setAssertPositiveInteger(totalInFField);
        setAssertPositiveInteger(healedField);
        setAssertPositiveInteger(transferredField);
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
