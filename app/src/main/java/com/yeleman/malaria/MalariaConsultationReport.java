package com.yeleman.malaria;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 05/12/14.
 */
public class MalariaConsultationReport extends MalariaForm {

    private final static String TAG = Constants.getLogTag("MalariaConsultationReport");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_consultation_report);
        setTitle(String.format(getString(R.string.malaria_app_label),
                getString(R.string.malaria_consultation_label)));
        Log.d(TAG, "onCreate MalariaConsultationReport");
        seputUI();
    }

    protected void seputUI() {
        Log.d(TAG, "setupUI MalariaConsultationReport");

        consultationU5Field = (EditText) findViewById(R.id.consultationU5Field);
        consultationO5Field = (EditText) findViewById(R.id.consultationO5Field);
        consultationPwField = (EditText) findViewById(R.id.consultationPwField);
        totalCasesU5Field = (EditText) findViewById(R.id.totalCasesU5Field);
        totalCasesO5Field = (EditText) findViewById(R.id.totalCasesO5Field);
        totalCasesPwField = (EditText) findViewById(R.id.totalCasesPwField);
        testedCaseU5Field = (EditText) findViewById(R.id.testedCaseU5Field);
        testedCaseO5Field = (EditText) findViewById(R.id.testedCaseO5Field);
        testedCasePwField = (EditText) findViewById(R.id.testedCasePwField);
        confirmedCaseU5Field = (EditText) findViewById(R.id.confirmedCaseU5Field);
        confirmedCaseO5Field = (EditText) findViewById(R.id.confirmedCaseO5Field);
        confirmedCasePwField = (EditText) findViewById(R.id.confirmedCasePwField);
        simpleCaseU5Field = (EditText) findViewById(R.id.simpleCaseU5Field);
        simpleCaseO5Field = (EditText) findViewById(R.id.simpleCaseO5Field);
        severeCaseU5Field = (EditText) findViewById(R.id.severeCaseU5Field);
        severeCaseO5Field = (EditText) findViewById(R.id.severeCaseO5Field);
        severeCasePwField = (EditText) findViewById(R.id.severeCasePwField);
        acttreatedCaseU5Field = (EditText) findViewById(R.id.acttreatedCaseU5Field);
        acttreatedCaseO5Field = (EditText) findViewById(R.id.acttreatedCaseO5Field);
        acttreatedCasePwField = (EditText) findViewById(R.id.acttreatedCasePwField);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        MalariaReportData report = MalariaReportData.get();
        if (report.consultation_is_complete) {
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

        report.o5_total_consultation = integerFromField(consultationU5Field);
        report.pw_total_consultation = integerFromField(consultationO5Field);
        report.u5_total_consultation = integerFromField(consultationPwField);
        report.u5_total_malaria_cases = integerFromField(totalCasesU5Field);
        report.o5_total_malaria_cases = integerFromField(totalCasesO5Field);
        report.pw_total_malaria_cases = integerFromField(totalCasesPwField);
        report.o5_total_simple_malaria_cases = integerFromField(testedCaseU5Field);
        report.u5_total_simple_malaria_cases = integerFromField(testedCaseO5Field);
        report.o5_total_severe_malaria_cases = integerFromField(testedCasePwField);
        report.u5_total_severe_malaria_cases = integerFromField(confirmedCaseU5Field);
        report.pw_total_severe_malaria_cases = integerFromField(confirmedCaseO5Field);
        report.o5_total_tested_malaria_cases = integerFromField(confirmedCasePwField);
        report.u5_total_tested_malaria_cases = integerFromField(simpleCaseU5Field);
        report.pw_total_tested_malaria_cases = integerFromField(simpleCaseO5Field);
        report.o5_total_confirmed_malaria_cases = integerFromField(severeCaseU5Field);
        report.u5_total_confirmed_malaria_cases = integerFromField(severeCaseO5Field);
        report.pw_total_confirmed_malaria_cases = integerFromField(severeCasePwField);
        report.o5_total_acttreated_malaria_cases = integerFromField(acttreatedCaseU5Field);
        report.u5_total_acttreated_malaria_cases = integerFromField(acttreatedCaseO5Field);
        report.pw_total_acttreated_malaria_cases = integerFromField(acttreatedCasePwField);
        report.consultation_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();

        setTextOnField(consultationU5Field, report.o5_total_consultation);
        setTextOnField(consultationO5Field, report.pw_total_consultation);
        setTextOnField(consultationPwField, report.u5_total_consultation);
        setTextOnField(totalCasesU5Field, report.u5_total_malaria_cases);
        setTextOnField(totalCasesO5Field, report.o5_total_malaria_cases);
        setTextOnField(totalCasesPwField, report.pw_total_malaria_cases);
        setTextOnField(testedCaseU5Field, report.o5_total_simple_malaria_cases);
        setTextOnField(testedCaseO5Field, report.u5_total_simple_malaria_cases);
        setTextOnField(testedCasePwField, report.o5_total_severe_malaria_cases);
        setTextOnField(confirmedCaseU5Field, report.u5_total_severe_malaria_cases);
        setTextOnField(confirmedCaseO5Field, report.pw_total_severe_malaria_cases);
        setTextOnField(confirmedCasePwField, report.o5_total_tested_malaria_cases);
        setTextOnField(simpleCaseU5Field, report.u5_total_tested_malaria_cases);
        setTextOnField(simpleCaseO5Field, report.pw_total_tested_malaria_cases);
        setTextOnField(severeCaseU5Field, report.o5_total_confirmed_malaria_cases);
        setTextOnField(severeCaseO5Field, report.u5_total_confirmed_malaria_cases);
        setTextOnField(severeCasePwField, report.pw_total_confirmed_malaria_cases);
        setTextOnField(acttreatedCaseU5Field, report.o5_total_acttreated_malaria_cases);
        setTextOnField(acttreatedCaseO5Field, report.u5_total_acttreated_malaria_cases);
        setTextOnField(acttreatedCasePwField, report.pw_total_acttreated_malaria_cases);
    }

    protected void setupInvalidInputChecks() {
        setAssertPositiveInteger(consultationU5Field);
        setAssertPositiveInteger(consultationO5Field);
        setAssertPositiveInteger(consultationPwField);
        setAssertPositiveInteger(totalCasesU5Field);
        setAssertPositiveInteger(totalCasesO5Field);
        setAssertPositiveInteger(totalCasesPwField);
        setAssertPositiveInteger(testedCaseU5Field);
        setAssertPositiveInteger(testedCaseO5Field);
        setAssertPositiveInteger(testedCasePwField);
        setAssertPositiveInteger(confirmedCaseU5Field);
        setAssertPositiveInteger(confirmedCaseO5Field);
        setAssertPositiveInteger(confirmedCasePwField);
        setAssertPositiveInteger(simpleCaseU5Field);
        setAssertPositiveInteger(simpleCaseO5Field);
        setAssertPositiveInteger(severeCaseU5Field);
        setAssertPositiveInteger(severeCaseO5Field);
        setAssertPositiveInteger(severeCasePwField);
        setAssertPositiveInteger(acttreatedCaseU5Field);
        setAssertPositiveInteger(acttreatedCaseO5Field);
        setAssertPositiveInteger(acttreatedCasePwField);
    }

    protected boolean ensureDataCoherence() {
        return true;
    };
}
