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

public class NutritionURENASU59O6Report extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("NutritionURENASU59O6Report");

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
        setContentView(R.layout.nutrition_uren_unit_fillout);
        setTitle(String.format(getString(R.string.nutrition_fillout_section),
                getString(R.string.u59o6)));
        Log.d(TAG, "onCreate NutritionURENASU59O6Report");
        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENASU59O6Report");
        
        totalStartMField = (EditText) findViewById(R.id.totalStartMField);
        totalStartFField = (EditText) findViewById(R.id.totalStartFField);
        newCasesField = (EditText) findViewById(R.id.newCasesField);
        returnedField = (EditText) findViewById(R.id.returnedField);
        totalInMField = (EditText) findViewById(R.id.totalInMField);
        totalInFField = (EditText) findViewById(R.id.totalInFField);
        transferredField = (EditText) findViewById(R.id.transferredField);
        healedField = (EditText) findViewById(R.id.healedField);
        transferredField = (EditText) findViewById(R.id.transferredField);
        deceasedField = (EditText) findViewById(R.id.deceasedField);
        abandonField = (EditText) findViewById(R.id.abandonField);
        respondingField = (EditText) findViewById(R.id.respondingField);
        totalOutMField = (EditText) findViewById(R.id.totalOutMField);
        totalOutFField = (EditText) findViewById(R.id.totalOutFField);
        referredLabel = (TextView) findViewById(R.id.referredLabel);
        referredLabel.setText(String.format(getString(R.string.nutrition_referred), "URENI"));
        referredField = (EditText) findViewById(R.id.referredField);
        totalEndMField = (EditText) findViewById(R.id.totalEndMField);
        totalEndFField = (EditText) findViewById(R.id.totalEndFField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        NutritionURENASReportData report = NutritionURENASReportData.get();
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
        NutritionURENASReportData report = NutritionURENASReportData.get();
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
        report.u59o6_not_responding = integerFromField(respondingField);
        report.u59o6_total_out_m = integerFromField(totalOutMField);
        report.u59o6_total_out_f = integerFromField(totalOutFField);
        report.u59o6_referred = integerFromField(referredField);
        report.u59o6_total_end_m = integerFromField(totalEndMField);
        report.u59o6_total_end_f = integerFromField(totalEndFField);
        report.u59o6_is_complete = true;
        report.save();
        Log.d(TAG, "storeReportData -- end");

    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        NutritionURENASReportData report = NutritionURENASReportData.get();
        if(report.u59o6_total_end_m != -1){
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
            setTextOnField(respondingField, report.u59o6_not_responding);
            setTextOnField(totalOutMField, report.u59o6_total_out_m);
            setTextOnField(totalOutFField, report.u59o6_total_out_f);
            setTextOnField(referredField, report.u59o6_referred);
            setTextOnField(totalEndMField, report.u59o6_total_end_m);
            setTextOnField(totalEndFField, report.u59o6_total_end_f);
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
        setAssertPositiveInteger(transferredField);
        setAssertPositiveInteger(deceasedField);
        setAssertPositiveInteger(abandonField);
        setAssertPositiveInteger(respondingField);
        setAssertPositiveInteger(totalOutMField);
        setAssertPositiveInteger(totalOutFField);
        setAssertPositiveInteger(referredField);
        setAssertPositiveInteger(totalEndMField);
        setAssertPositiveInteger(totalEndFField);
    }
    
    protected boolean ensureDataCoherence() {

        // newCases + returned == totalIn
        int newsCaseAndReferred = integerFromField(newCasesField) + integerFromField(returnedField);
        int totalIn = integerFromField(totalInMField) + integerFromField(totalInFField);

        if (newsCaseAndReferred != totalIn) {
            String errorMsg = String.format(getString(R.string.error_must_be_equal,
                                            newCasesField.getHint() + " + " + returnedField.getHint(),
                                            newsCaseAndReferred,
                                            "total admis ", totalIn));
            fireErrorDialog(this, errorMsg, newCasesField);
            return false;
        }
        // Détails sorties
        // allOut == totalOut
        int totalOut = integerFromField(totalOutFField) + integerFromField(totalOutMField);
        int allOutReasons = integerFromField(healedField) +
                            integerFromField(deceasedField) +
                            integerFromField(abandonField) +
                            integerFromField(respondingField);
        if (allOutReasons != totalOut){
            String errorMsg = String.format(getString(R.string.error_must_be_equal,
                    "guéris, décès, abandons, non-resp.",
                    allOutReasons, "total sorties", totalOut));
            fireErrorDialog(this, errorMsg, healedField);
            return false;
        }
        // Sorties inferieur ou egal à PEC
        int totalStart = integerFromField(totalStartFField) + integerFromField(totalStartMField);
        int grandTotalIn = totalIn + integerFromField(newCasesField) + integerFromField(returnedField) +
                integerFromField(transferredField);
        int allAvail = totalStart + grandTotalIn;
        int grandTotalOut = totalOut + integerFromField(healedField) +
                            integerFromField(deceasedField) +
                            integerFromField(abandonField) +
                            integerFromField(respondingField);
        if (grandTotalOut > allAvail){
            String errorMsg = String.format("total sorties général (%1$d) ne peut pas dépasser le " +
                                            "total début + admissions (%2$d)", grandTotalOut, allAvail);
            fireErrorDialog(this, errorMsg, newCasesField);
            return false;
        }
        // Total fin de mois
        // totalEnd = totalStart + grand_totalIn - grand_totalOut
        int totalEnd = integerFromField(totalEndFField) + integerFromField(totalEndMField);
        int startInNotOut = totalStart + grandTotalIn - grandTotalOut;
        if (totalEnd != (startInNotOut)){
            String errorMsg = String.format("total fin de mois (%1$d) doit être égal au début " +
                                            "+ admissions - sorties (%2$d)", totalEnd, startInNotOut);
            fireErrorDialog(this, errorMsg, totalStartFField);
            return false;
        }
        return true;

    }
}
