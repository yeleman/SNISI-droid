package com.yeleman.malaria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fad on 10/02/15.
 */
public class MalariaWeeklyReport extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("MalariaWeeklyReport");
    
    protected EditText malariaO5D1Field;
    protected EditText malariaU5D1Field;
    protected EditText malariaPWD1Field;
    protected EditText malariaO5D2Field;
    protected EditText malariaU5D2Field;
    protected EditText malariaPWD2Field;
    protected EditText malariaO5D3Field;
    protected EditText malariaU5D3Field;
    protected EditText malariaPWD3Field;
    protected EditText malariaO5D4Field;
    protected EditText malariaU5D4Field;
    protected EditText malariaPWD4Field;
    protected EditText malariaO5D5Field;
    protected EditText malariaU5D5Field;
    protected EditText malariaPWD5Field;
    protected EditText malariaO5D6Field;
    protected EditText malariaU5D6Field;
    protected EditText malariaPWD6Field;
    protected EditText malariaO5D7Field;
    protected EditText malariaU5D7Field;
    protected EditText malariaPWD7Field;

    protected Button saveAndSubmitButton;
    private TextView labelD1;
    private TextView labelD2;
    private TextView labelD3;
    private TextView labelD4;
    private TextView labelD5;
    private TextView labelD6;
    private TextView labelD7;
    private TextView periodLabel;
    private int dayOfMonth;
    private int dayMaxiOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_weekly_report);
        setTitle(String.format(getString(R.string.malaria_app_label),
                               getString(R.string.nutrition_weekly_report_label)));
        Log.d(TAG, "onCreate MalariaWeeklyReport");

        setupSMSReceiver();
        setupUI();
    }
    
    public String getPeriodFromWeekNumber(int day, int dayMaxi) {
        if (day >= 1 && day <= 7)
            return "1-7";
        if (day >= 8 && day <= 14)
            return "8-14";
        if (day >= 15 && day <= 21)
            return "15-21";
        if (day >= 22 && day <= 28)
            return "22-28";
        if (day > 28)
            return "29-" + String.valueOf(dayMaxi);
        return null;
    }
    
    protected void setupUI() {
        Log.d(TAG, "setupUI MalariaWeeklyReport");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -7);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        dayMaxiOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        periodLabel = (TextView) findViewById(R.id.periodLabel);
        periodLabel.setText(String.format("Semaine du %s %s",
                getPeriodFromWeekNumber(dayOfMonth, dayMaxiOfMonth),
                String.format(Locale.FRENCH,"%tB",c) ));

        labelD1 = (TextView) findViewById(R.id.levelLabelD1);
        labelD1.setText(String.format(getString(R.string.malaria_week_label), "1"));
        malariaO5D1Field = (EditText) findViewById(R.id.malariaO5D1Field);
        malariaU5D1Field = (EditText) findViewById(R.id.malariaU5D1Field);
        malariaPWD1Field = (EditText) findViewById(R.id.malariaPwD1Field);
        
        labelD2 = (TextView) findViewById(R.id.levelLabelD2);
        labelD2.setText(String.format(getString(R.string.malaria_week_label), "2"));
        malariaO5D2Field = (EditText) findViewById(R.id.malariaO5D2Field);
        malariaU5D2Field = (EditText) findViewById(R.id.malariaU5D2Field);
        malariaPWD2Field = (EditText) findViewById(R.id.malariaPwD2Field);

        LinearLayout linearLytD3 = (LinearLayout) findViewById(R.id.linearLayoutD3);
        LinearLayout linearLytD4 = (LinearLayout) findViewById(R.id.linearLayoutD4);
        LinearLayout linearLytD5 = (LinearLayout) findViewById(R.id.linearLayoutD5);
        LinearLayout linearLytD6 = (LinearLayout) findViewById(R.id.linearLayoutD6);
        LinearLayout linearLytD7 = (LinearLayout) findViewById(R.id.linearLayoutD7);

        labelD3 = (TextView) findViewById(R.id.levelLabelD3);
        labelD3.setText(String.format(getString(R.string.malaria_week_label), "3"));
        malariaO5D3Field = (EditText) findViewById(R.id.malariaO5D3Field);
        malariaU5D3Field = (EditText) findViewById(R.id.malariaU5D3Field);
        malariaPWD3Field = (EditText) findViewById(R.id.malariaPwD3Field);
        if (dayOfMonth > 28) {
            malariaPWD3Field.setImeOptions(EditorInfo.IME_ACTION_DONE);
            linearLytD4.setVisibility(View.INVISIBLE);
            linearLytD5.setVisibility(View.INVISIBLE);
            linearLytD6.setVisibility(View.INVISIBLE);
            linearLytD7.setVisibility(View.INVISIBLE);
            if (dayMaxiOfMonth < 31) {
                linearLytD3.setVisibility(View.INVISIBLE);
                malariaPWD2Field.setImeOptions(EditorInfo.IME_ACTION_DONE);
            }
        } else {
            labelD4 = (TextView) findViewById(R.id.levelLabelD4);
            labelD4.setText(String.format(getString(R.string.malaria_week_label), "4"));
            malariaO5D4Field = (EditText) findViewById(R.id.malariaO5D4Field);
            malariaU5D4Field = (EditText) findViewById(R.id.malariaU5D4Field);
            malariaPWD4Field = (EditText) findViewById(R.id.malariaPwD4Field);

            labelD5 = (TextView) findViewById(R.id.levelLabelD5);
            labelD5.setText(String.format(getString(R.string.malaria_week_label), "5"));
            malariaO5D5Field = (EditText) findViewById(R.id.malariaO5D5Field);
            malariaU5D5Field = (EditText) findViewById(R.id.malariaU5D5Field);
            malariaPWD5Field = (EditText) findViewById(R.id.malariaPwD5Field);

            labelD6 = (TextView) findViewById(R.id.levelLabelD6);
            labelD6.setText(String.format(getString(R.string.malaria_week_label), "6"));
            malariaO5D6Field = (EditText) findViewById(R.id.malariaO5D6Field);
            malariaU5D6Field = (EditText) findViewById(R.id.malariaU5D6Field);
            malariaPWD6Field = (EditText) findViewById(R.id.malariaPwD6Field);

            labelD7 = (TextView) findViewById(R.id.levelLabelD7);
            labelD7.setText(String.format(getString(R.string.malaria_week_label), "7"));
            malariaO5D7Field = (EditText) findViewById(R.id.malariaO5D7Field);
            malariaU5D7Field = (EditText) findViewById(R.id.malariaU5D7Field);
            malariaPWD7Field = (EditText) findViewById(R.id.malariaPwD7Field);
        }
        // setup invalid inputs checks
        setupInvalidInputChecks();

        final CheckedFormActivity activity = this;
        saveAndSubmitButton = (Button) findViewById(R.id.saveAndSubmitButton);
        saveAndSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) { return; }

                // save data to DB
                storeReportData();

                // transmit SMS
                requestPasswordAndTransmitSMS(activity, MalariaWeeklyReportData.get().getName(),
                        Constants.SMS_KEYWORD_MALARIA_WEEKLY, buildSMSText());
            }
        });

        Log.d(TAG, "requestForResumeReport MalariaWeeklyReportData");
        requestForResumeReport(this, MalariaWeeklyReportData.get());
    }
    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
        MalariaWeeklyReportData report = MalariaWeeklyReportData.get();
        report.updateMetaData();
        report.o5_total_confirmed_malaria_cases_d1 = integerFromField(malariaO5D1Field);
        report.u5_total_confirmed_malaria_cases_d1 = integerFromField(malariaU5D1Field);
        report.pw_total_confirmed_malaria_cases_d1 = integerFromField(malariaPWD1Field);
        report.o5_total_confirmed_malaria_cases_d2 = integerFromField(malariaO5D2Field);
        report.u5_total_confirmed_malaria_cases_d2 = integerFromField(malariaU5D2Field);
        report.pw_total_confirmed_malaria_cases_d2 = integerFromField(malariaPWD2Field);
        if (dayOfMonth <= 28) {
            report.o5_total_confirmed_malaria_cases_d3 = integerFromField(malariaO5D3Field);
            report.u5_total_confirmed_malaria_cases_d3 = integerFromField(malariaU5D3Field);
            report.pw_total_confirmed_malaria_cases_d3 = integerFromField(malariaPWD3Field);
            report.o5_total_confirmed_malaria_cases_d4 = integerFromField(malariaO5D4Field);
            report.u5_total_confirmed_malaria_cases_d4 = integerFromField(malariaU5D4Field);
            report.pw_total_confirmed_malaria_cases_d4 = integerFromField(malariaPWD4Field);
            report.o5_total_confirmed_malaria_cases_d5 = integerFromField(malariaO5D5Field);
            report.u5_total_confirmed_malaria_cases_d5 = integerFromField(malariaU5D5Field);
            report.pw_total_confirmed_malaria_cases_d5 = integerFromField(malariaPWD5Field);
            report.o5_total_confirmed_malaria_cases_d6 = integerFromField(malariaO5D6Field);
            report.u5_total_confirmed_malaria_cases_d6 = integerFromField(malariaU5D6Field);
            report.pw_total_confirmed_malaria_cases_d6 = integerFromField(malariaPWD6Field);
            report.o5_total_confirmed_malaria_cases_d7 = integerFromField(malariaO5D7Field);
            report.u5_total_confirmed_malaria_cases_d7 = integerFromField(malariaU5D7Field);
            report.pw_total_confirmed_malaria_cases_d7 = integerFromField(malariaPWD7Field);
        } else {
            if (dayMaxiOfMonth == 31) {
                report.o5_total_confirmed_malaria_cases_d3 = integerFromField(malariaO5D3Field);
                report.u5_total_confirmed_malaria_cases_d3 = integerFromField(malariaU5D3Field);
                report.pw_total_confirmed_malaria_cases_d3 = integerFromField(malariaPWD3Field);
            }
        }
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }
    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaWeeklyReportData report = MalariaWeeklyReportData.get();
        
        setTextOnField(malariaO5D1Field, report.o5_total_confirmed_malaria_cases_d1);
        setTextOnField(malariaU5D1Field, report.u5_total_confirmed_malaria_cases_d1);
        setTextOnField(malariaPWD1Field, report.pw_total_confirmed_malaria_cases_d1);
        setTextOnField(malariaO5D2Field, report.o5_total_confirmed_malaria_cases_d2);
        setTextOnField(malariaU5D2Field, report.u5_total_confirmed_malaria_cases_d2);
        setTextOnField(malariaPWD2Field, report.pw_total_confirmed_malaria_cases_d2);
        if (dayOfMonth <= 28) {
            setTextOnField(malariaO5D3Field, report.o5_total_confirmed_malaria_cases_d3);
            setTextOnField(malariaU5D3Field, report.u5_total_confirmed_malaria_cases_d3);
            setTextOnField(malariaPWD3Field, report.pw_total_confirmed_malaria_cases_d3);
            setTextOnField(malariaO5D4Field, report.o5_total_confirmed_malaria_cases_d4);
            setTextOnField(malariaU5D4Field, report.u5_total_confirmed_malaria_cases_d4);
            setTextOnField(malariaPWD4Field, report.pw_total_confirmed_malaria_cases_d4);
            setTextOnField(malariaO5D5Field, report.o5_total_confirmed_malaria_cases_d5);
            setTextOnField(malariaU5D5Field, report.u5_total_confirmed_malaria_cases_d5);
            setTextOnField(malariaPWD5Field, report.pw_total_confirmed_malaria_cases_d5);
            setTextOnField(malariaO5D6Field, report.o5_total_confirmed_malaria_cases_d6);
            setTextOnField(malariaU5D6Field, report.u5_total_confirmed_malaria_cases_d6);
            setTextOnField(malariaPWD6Field, report.pw_total_confirmed_malaria_cases_d6);
            setTextOnField(malariaO5D7Field, report.o5_total_confirmed_malaria_cases_d7);
            setTextOnField(malariaU5D7Field, report.u5_total_confirmed_malaria_cases_d7);
            setTextOnField(malariaPWD7Field, report.pw_total_confirmed_malaria_cases_d7);
        } else {
            if (dayMaxiOfMonth == 31) {
                setTextOnField(malariaO5D3Field, report.o5_total_confirmed_malaria_cases_d3);
                setTextOnField(malariaU5D3Field, report.u5_total_confirmed_malaria_cases_d3);
                setTextOnField(malariaPWD3Field, report.pw_total_confirmed_malaria_cases_d3);
            }
        }
    }
    protected void setupInvalidInputChecks() {
        setAssertPositiveInteger(malariaU5D1Field);
        setAssertPositiveInteger(malariaO5D1Field);
        setAssertPositiveInteger(malariaPWD1Field);
        setAssertPositiveInteger(malariaU5D2Field);
        setAssertPositiveInteger(malariaO5D2Field);
        setAssertPositiveInteger(malariaPWD2Field);
        if (dayOfMonth <= 28) {
            setAssertPositiveInteger(malariaU5D3Field);
            setAssertPositiveInteger(malariaO5D3Field);
            setAssertPositiveInteger(malariaPWD3Field);
            setAssertPositiveInteger(malariaU5D4Field);
            setAssertPositiveInteger(malariaO5D4Field);
            setAssertPositiveInteger(malariaPWD4Field);
            setAssertPositiveInteger(malariaU5D5Field);
            setAssertPositiveInteger(malariaO5D5Field);
            setAssertPositiveInteger(malariaPWD5Field);
            setAssertPositiveInteger(malariaU5D6Field);
            setAssertPositiveInteger(malariaO5D6Field);
            setAssertPositiveInteger(malariaPWD6Field);
            setAssertPositiveInteger(malariaU5D7Field);
            setAssertPositiveInteger(malariaO5D7Field);
            setAssertPositiveInteger(malariaPWD7Field);
        } else {
            if (dayMaxiOfMonth == 31) {
                setAssertPositiveInteger(malariaU5D3Field);
                setAssertPositiveInteger(malariaO5D3Field);
                setAssertPositiveInteger(malariaPWD3Field);
            }
        }
    }
    
    protected boolean ensureDataCoherence() {
        return true;
    }
    protected String buildSMSText() {
        MalariaWeeklyReportData report = MalariaWeeklyReportData.get();
        return report.buildSMSText();
    }
}
