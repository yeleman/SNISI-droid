package com.yeleman.malaria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;


public class MalariaO5Report extends MalariaForm {

    private final static String TAG = Constants.getLogTag("MalariaO5Report");

    protected boolean ensureDataCoherence() {return ensureMalariaCoherence();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_unit_fillout);
        setTitle(String.format(getString(R.string.malaria_app_label),
                getString(R.string.malaria_o5_label)));
        Log.d(TAG, "onCreate MalariaO5Report");
        seputUI();
    }

    protected void seputUI() {
        Log.d(TAG, "setupUI MalariaO5Report");

        malariaTotalConsultationField = (EditText) findViewById(R.id.malariaTotalConsultationField);
        malariaTotalMalariaCasesField = (EditText) findViewById(R.id.malariaTotalMalariaCasesField);
        malariaTotalTestedMalariaCasesField = (EditText) findViewById(R.id.malariaTotalTestedMalariaCasesField);
        malariaTotalConfirmedMalariaCasesField = (EditText) findViewById(R.id.malariaTotalConfirmedMalariaCasesField);
        malariaTotalSimpleMalariaCasesField = (EditText) findViewById(R.id.malariaTotalSimpleMalariaCasesField);
        malariaTotalSevereMalariaCasesField = (EditText) findViewById(R.id.malariaTotalSevereMalariaCasesField);
        malariaTotalActtreatedMalariaCasesField = (EditText) findViewById(R.id.malariaTotalActtreatedMalariaCasesField);
        malariaTotalInpatientField = (EditText) findViewById(R.id.malariaTotalInpatientField);
        malariaTotalMalariaInpatientField = (EditText) findViewById(R.id.malariaTotalMalariaInpatientField);
        malariaTotalDeathField = (EditText) findViewById(R.id.malariaTotalDeathField);
        malariaTotalMalariaDeathField = (EditText) findViewById(R.id.malariaTotalMalariaDeathField);
        malariaTotalMalariaDeathField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        LinearLayout distributed_bednetsLinearLayout = (LinearLayout) findViewById(R.id.distributed_bednetsLinearLayout);
        distributed_bednetsLinearLayout.setVisibility(View.GONE);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        MalariaReportData report = MalariaReportData.get();
        if (report.o5_is_complete) {
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
        MalariaReportData report = MalariaReportData.get();
        report.updateMetaData();

        report.o5_total_consultation = integerFromField(malariaTotalConsultationField);
        report.o5_total_malaria_cases = integerFromField(malariaTotalMalariaCasesField);
        report.o5_total_tested_malaria_cases = integerFromField(malariaTotalTestedMalariaCasesField);
        report.o5_total_confirmed_malaria_cases = integerFromField(malariaTotalConfirmedMalariaCasesField);
        report.o5_total_simple_malaria_cases = integerFromField(malariaTotalSimpleMalariaCasesField);
        report.o5_total_severe_malaria_cases = integerFromField(malariaTotalSevereMalariaCasesField);
        report.o5_total_acttreated_malaria_cases = integerFromField(malariaTotalActtreatedMalariaCasesField);
        report.o5_total_inpatient = integerFromField(malariaTotalInpatientField);
        report.o5_total_malaria_impatient = integerFromField(malariaTotalMalariaInpatientField);
        report.o5_total_death = integerFromField(malariaTotalDeathField);
        report.o5_total_malaria_death = integerFromField(malariaTotalMalariaDeathField);
        report.o5_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();

        setTextOnField(malariaTotalConsultationField, report.o5_total_consultation);
        setTextOnField(malariaTotalMalariaCasesField, report.o5_total_malaria_cases);
        setTextOnField(malariaTotalTestedMalariaCasesField, report.o5_total_tested_malaria_cases);
        setTextOnField(malariaTotalConfirmedMalariaCasesField, report.o5_total_confirmed_malaria_cases);
        setTextOnField(malariaTotalSimpleMalariaCasesField, report.o5_total_simple_malaria_cases);
        setTextOnField(malariaTotalSevereMalariaCasesField, report.o5_total_severe_malaria_cases);
        setTextOnField(malariaTotalActtreatedMalariaCasesField, report.o5_total_acttreated_malaria_cases);
        setTextOnField(malariaTotalInpatientField, report.o5_total_inpatient);
        setTextOnField(malariaTotalMalariaInpatientField, report.o5_total_malaria_impatient);
        setTextOnField(malariaTotalDeathField, report.o5_total_death);
        setTextOnField(malariaTotalMalariaDeathField, report.o5_total_malaria_death);
    }

    protected void setupInvalidInputChecks() {

        setAssertPositiveInteger(malariaTotalConsultationField);
        setAssertPositiveInteger(malariaTotalMalariaCasesField);
        setAssertPositiveInteger(malariaTotalTestedMalariaCasesField);
        setAssertPositiveInteger(malariaTotalConfirmedMalariaCasesField);
        setAssertPositiveInteger(malariaTotalSimpleMalariaCasesField);
        setAssertPositiveInteger(malariaTotalSevereMalariaCasesField);
        setAssertPositiveInteger(malariaTotalActtreatedMalariaCasesField);
        setAssertPositiveInteger(malariaTotalInpatientField);
        setAssertPositiveInteger(malariaTotalMalariaInpatientField);
        setAssertPositiveInteger(malariaTotalDeathField);
        setAssertPositiveInteger(malariaTotalMalariaDeathField);
    }

}
