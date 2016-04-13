package com.yeleman.malaria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 09/12/14.
 */
public class MalariaPwReport  extends MalariaForm {

    private final static String TAG = Constants.getLogTag("MalariaPwReport");

    public String getAge() { return PW; }

    protected boolean ensureDataCoherence() { return ensureMalariaCoherence(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_unit_fillout);
        setTitle(String.format(getString(R.string.sub_app_name_malaria),
                getString(R.string.malaria_pw_label)));
        Log.d(TAG, "onCreate MalariaPwReport");
        seputUI();
    }

    protected void seputUI() {
        Log.d(TAG, "setupUI MalariaPwReport");

        malariaTotalConsultationField = (EditText) findViewById(R.id.malariaTotalConsultationField);
        malariaTotalMalariaCasesField = (EditText) findViewById(R.id.malariaTotalMalariaCasesField);
        malariaTotalTestedMalariaCasesField = (EditText) findViewById(R.id.malariaTotalTestedMalariaCasesField);
        malariaTotalConfirmedMalariaCasesField = (EditText) findViewById(R.id.malariaTotalConfirmedMalariaCasesField);
        malariaTotalSimpleMalariaCasesField = (EditText) findViewById(R.id.malariaTotalSimpleMalariaCasesField);
//        LinearLayout malariaTotalSimpleMalariaCasesLinearLayout = (LinearLayout) findViewById(R.id.malariaTotalSimpleMalariaCasesLinearLayout);
//        malariaTotalSimpleMalariaCasesLinearLayout.setVisibility(View.GONE);
        malariaTotalSevereMalariaCasesField = (EditText) findViewById(R.id.malariaTotalSevereMalariaCasesField);
        malariaTotalActtreatedMalariaCasesField = (EditText) findViewById(R.id.malariaTotalActtreatedMalariaCasesField);
        malariaTotalInpatientField = (EditText) findViewById(R.id.malariaTotalInpatientField);
        malariaTotalMalariaInpatientField = (EditText) findViewById(R.id.malariaTotalMalariaInpatientField);
        malariaTotalDeathField = (EditText) findViewById(R.id.malariaTotalDeathField);
        malariaTotalMalariaDeathField = (EditText) findViewById(R.id.malariaTotalMalariaDeathField);
        malariaTotalDistributedBednetsField = (EditText) findViewById(R.id.malariaTotalDistributedBednetsField);
        // setup invalid inputs checks
        setupInvalidInputChecks();

        MalariaReportData report = MalariaReportData.get();
        if (report.pw_is_complete) {
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

        report.pw_total_consultation = integerFromField(malariaTotalConsultationField);
        report.pw_total_malaria_cases = integerFromField(malariaTotalMalariaCasesField);
        report.pw_total_tested_malaria_cases = integerFromField(malariaTotalTestedMalariaCasesField);
        report.pw_total_confirmed_malaria_cases = integerFromField(malariaTotalConfirmedMalariaCasesField);
        report.pw_total_simple_malaria_cases = integerFromField(malariaTotalSimpleMalariaCasesField);
        report.pw_total_severe_malaria_cases = integerFromField(malariaTotalSevereMalariaCasesField);
        report.pw_total_acttreated_malaria_cases = integerFromField(malariaTotalActtreatedMalariaCasesField);
        report.pw_total_inpatient = integerFromField(malariaTotalInpatientField);
        report.pw_total_malaria_impatient = integerFromField(malariaTotalMalariaInpatientField);
        report.pw_total_death = integerFromField(malariaTotalDeathField);
        report.pw_total_malaria_death = integerFromField(malariaTotalMalariaDeathField);
        report.pw_malaria_total_distributed_bednets = integerFromField(malariaTotalDistributedBednetsField);

        report.pw_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();

        setTextOnField(malariaTotalConsultationField, report.pw_total_consultation);
        setTextOnField(malariaTotalMalariaCasesField, report.pw_total_malaria_cases);
        setTextOnField(malariaTotalTestedMalariaCasesField, report.pw_total_tested_malaria_cases);
        setTextOnField(malariaTotalConfirmedMalariaCasesField, report.pw_total_confirmed_malaria_cases);
        setTextOnField(malariaTotalSimpleMalariaCasesField, report.pw_total_simple_malaria_cases);
        setTextOnField(malariaTotalSevereMalariaCasesField, report.pw_total_severe_malaria_cases);
        setTextOnField(malariaTotalActtreatedMalariaCasesField, report.pw_total_acttreated_malaria_cases);
        setTextOnField(malariaTotalInpatientField, report.pw_total_inpatient);
        setTextOnField(malariaTotalMalariaInpatientField, report.pw_total_malaria_impatient);
        setTextOnField(malariaTotalDeathField, report.pw_total_death);
        setTextOnField(malariaTotalMalariaDeathField, report.pw_total_malaria_death);
        setTextOnField(malariaTotalDistributedBednetsField, report.pw_malaria_total_distributed_bednets);
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
        setAssertPositiveInteger(malariaTotalDistributedBednetsField);
    }
}
