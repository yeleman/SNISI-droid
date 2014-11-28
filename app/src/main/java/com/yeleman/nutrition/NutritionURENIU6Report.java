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

public class NutritionURENIU6Report extends NutritionURENForm implements NutritionURENFormIface {

    private final static String TAG = Constants.getLogTag("NutritionURENIU6Report");

    /* NutritionURENFormIFace */
    public String getUREN() { return URENI; }
    public String getAge() { return U6; }

    /* NutritionURENForm */
    protected boolean ensureDataCoherence() { return ensureURENCoherence(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.u6)));
        Log.d(TAG, "onCreate NutritionURENIU6Report");
        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENIU6Report");

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

        LinearLayout grand_total_in_parent = (LinearLayout) findViewById(R.id.grandTotalInLinearLayout);
        grand_total_in_parent.setVisibility(View.GONE);

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

        LinearLayout grand_total_out_parent = (LinearLayout) findViewById(R.id.grandTotalOutLinearLayout);
        grand_total_out_parent.setVisibility(View.GONE);

        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionURENIReportData report = NutritionURENIReportData.get();
        if (report.u6_is_complete){
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

        report.u6_total_start_m = integerFromField(totalStartMField);
        report.u6_total_start_f = integerFromField(totalStartFField);
        report.u6_new_cases = integerFromField(newCasesField);
        report.u6_returned = integerFromField(returnedField);
        report.u6_total_in_m = integerFromField(totalInMField);
        report.u6_total_in_f = integerFromField(totalInFField);
        report.u6_healed = integerFromField(healedField);
        report.u6_transferred = integerFromField(transferredField);
        report.u6_deceased = integerFromField(deceasedField);
        report.u6_abandon = integerFromField(abandonField);
        report.u6_not_responding = integerFromField(notRespondingField);
        report.u6_total_out_m = integerFromField(totalOutMField);
        report.u6_total_out_f = integerFromField(totalOutFField);
        report.u6_referred = integerFromField(referredField);
        report.u6_total_end_m = integerFromField(totalEndMField);
        report.u6_total_end_f = integerFromField(totalEndFField);
        report.u6_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENIReportData report = NutritionURENIReportData.get();
        setTextOnField(totalStartMField, report.u6_total_start_m);
        setTextOnField(totalStartFField, report.u6_total_start_f);
        setTextOnField(newCasesField, report.u6_new_cases);
        setTextOnField(returnedField, report.u6_returned);
        setTextOnField(totalInMField, report.u6_total_in_m);
        setTextOnField(totalInFField, report.u6_total_in_f);
        setTextOnField(healedField, report.u6_healed);
        setTextOnField(transferredField, report.u6_transferred);
        setTextOnField(deceasedField, report.u6_deceased);
        setTextOnField(abandonField, report.u6_abandon);
        setTextOnField(notRespondingField, report.u6_not_responding);
        setTextOnField(totalOutMField, report.u6_total_out_m);
        setTextOnField(totalOutFField, report.u6_total_out_f);
        setTextOnField(referredField, report.u6_referred);
        setTextOnField(totalEndMField, report.u6_total_end_m);
        setTextOnField(totalEndFField, report.u6_total_end_f);
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
