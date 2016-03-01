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

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
        setTitle(String.format(getString(R.string.sub_app_name_malaria),
                               getString(R.string.nutrition_weekly_report_label)));
        Log.d(TAG, "onCreate MalariaWeeklyReport");

        setupSMSReceiver();
        setupUI();
    }

    public String getPeriodFromWeekNumber(int day, int dayMaxi) {
        Log.i(TAG, "Day: " + day + " dayMaxi: " + dayMaxi);
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

        GregorianCalendar gcal = new GregorianCalendar();
        int i;
        if (gcal.get(Calendar.DAY_OF_MONTH) <= 7)
        {
            int previousMonth = gcal.get(Calendar.MONTH) - 1;
            i = previousMonth;
            if (previousMonth == 0) {
                i = 12;
            }
            int[] monthWith31Days = {1, 3, 5, 7, 8, 10, 12};
            if (Arrays.asList(monthWith31Days).contains(i)) {
                i = 3;
            } else {
                if (i == 1) {
                    GregorianCalendar gcal2 = new GregorianCalendar();
                    gcal2.set(gcal.get(Calendar.YEAR), 1, 1);
                    // check bisextil (29 feb)
                    if (gcal2.getActualMaximum(Calendar.DAY_OF_MONTH) == 29) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                } else {
                    i = 2;
                }
            }
        } else {
            i = 0;
        }
        int j = 7;
        Calendar cal = Calendar.getInstance();
        if (i != 0) {
            j = cal.get(Calendar.DAY_OF_MONTH);
        }
        cal.add(Calendar.DAY_OF_YEAR, -j);
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        dayMaxiOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        Log.d(TAG, String.valueOf(dayMaxiOfMonth));

        periodLabel = (TextView) findViewById(R.id.periodLabel);
        periodLabel.setText(String.format("Semaine du %s %s",
                getPeriodFromWeekNumber(dayOfMonth, dayMaxiOfMonth),
                String.format(Locale.FRENCH,"%tB", cal) ));

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

        LinearLayout linearLytD2 = (LinearLayout) findViewById(R.id.linearLayoutD2);
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
            linearLytD4.setVisibility(View.GONE);
            linearLytD5.setVisibility(View.GONE);
            linearLytD6.setVisibility(View.GONE);
            linearLytD7.setVisibility(View.GONE);
            if (dayMaxiOfMonth < 30) {
                // linearLytD3
                linearLytD2.setVisibility(View.GONE);
                malariaPWD1Field.setImeOptions(EditorInfo.IME_ACTION_DONE);
            }
            if (dayMaxiOfMonth < 31) {
                linearLytD3.setVisibility(View.GONE);
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
        MalariaWeeklyReportData malariaweeklyreportdata;
        malariaweeklyreportdata = MalariaWeeklyReportData.get();
        malariaweeklyreportdata.updateMetaData();
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d1 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d1 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d1 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d2 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d2 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d2 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d3 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d3 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d3 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d4 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d4 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d4 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d5 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d5 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d5 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d6 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d6 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d6 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d7 = -1;
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d7 = -1;
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d7 = -1;
        malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d1 = integerFromField(malariaO5D1Field);
        malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d1 = integerFromField(malariaU5D1Field);
        malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d1 = integerFromField(malariaPWD1Field);
        if (dayOfMonth <= 28) {
            malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d2 = integerFromField(malariaO5D2Field);
            malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d2 = integerFromField(malariaU5D2Field);
            malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d2 = integerFromField(malariaPWD2Field);
            malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d3 = integerFromField(malariaO5D3Field);
            malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d3 = integerFromField(malariaU5D3Field);
            malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d3 = integerFromField(malariaPWD3Field);
            malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d4 = integerFromField(malariaO5D4Field);
            malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d4 = integerFromField(malariaU5D4Field);
            malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d4 = integerFromField(malariaPWD4Field);
            malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d5 = integerFromField(malariaO5D5Field);
            malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d5 = integerFromField(malariaU5D5Field);
            malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d5 = integerFromField(malariaPWD5Field);
            malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d6 = integerFromField(malariaO5D6Field);
            malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d6 = integerFromField(malariaU5D6Field);
            malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d6 = integerFromField(malariaPWD6Field);
            malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d7 = integerFromField(malariaO5D7Field);
            malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d7 = integerFromField(malariaU5D7Field);
            malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d7 = integerFromField(malariaPWD7Field);

        } else {
            if (dayMaxiOfMonth >= 30)
            {
                malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d2 = integerFromField(malariaO5D2Field);
                malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d2 = integerFromField(malariaU5D2Field);
                malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d2 = integerFromField(malariaPWD2Field);
            }
            if (dayMaxiOfMonth == 31)
            {
                malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d3 = integerFromField(malariaO5D3Field);
                malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d3 = integerFromField(malariaU5D3Field);
                malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d3 = integerFromField(malariaPWD3Field);
            }
        }
        malariaweeklyreportdata.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }
    protected void restoreReportData() {

        Log.d(TAG, "restoreReportData");
        MalariaWeeklyReportData malariaweeklyreportdata = MalariaWeeklyReportData.get();
        setIntegerOnField(malariaO5D1Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d1));
        setIntegerOnField(malariaU5D1Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d1));
        setIntegerOnField(malariaPWD1Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d1));
        if (dayOfMonth <= 28)
        {
            setIntegerOnField(malariaO5D2Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d2));
            setIntegerOnField(malariaU5D2Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d2));
            setIntegerOnField(malariaPWD2Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d2));
            setIntegerOnField(malariaO5D3Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d3));
            setIntegerOnField(malariaU5D3Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d3));
            setIntegerOnField(malariaPWD3Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d3));
            setIntegerOnField(malariaO5D4Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d4));
            setIntegerOnField(malariaU5D4Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d4));
            setIntegerOnField(malariaPWD4Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d4));
            setIntegerOnField(malariaO5D5Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d5));
            setIntegerOnField(malariaU5D5Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d5));
            setIntegerOnField(malariaPWD5Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d5));
            setIntegerOnField(malariaO5D6Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d6));
            setIntegerOnField(malariaU5D6Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d6));
            setIntegerOnField(malariaPWD6Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d6));
            setIntegerOnField(malariaO5D7Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d7));
            setIntegerOnField(malariaU5D7Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d7));
            setIntegerOnField(malariaPWD7Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d7));
        } else
        {
            // if (dayMaxiOfMonth != 29);
            if (dayMaxiOfMonth >= 30)
            {
                setIntegerOnField(malariaO5D2Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d2));
                setIntegerOnField(malariaU5D2Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d2));
                setIntegerOnField(malariaPWD2Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d2));
            }
            if (dayMaxiOfMonth == 31)
            {
                setIntegerOnField(malariaO5D3Field, Integer.valueOf(malariaweeklyreportdata.o5_total_confirmed_malaria_cases_d3));
                setIntegerOnField(malariaU5D3Field, Integer.valueOf(malariaweeklyreportdata.u5_total_confirmed_malaria_cases_d3));
                setIntegerOnField(malariaPWD3Field, Integer.valueOf(malariaweeklyreportdata.pw_total_confirmed_malaria_cases_d3));
                return;
            }
        }
    }

    protected void setupInvalidInputChecks()
    {
        setAssertPositiveInteger(malariaU5D1Field);
        setAssertPositiveInteger(malariaO5D1Field);
        setAssertPositiveInteger(malariaPWD1Field);
        if (dayOfMonth <= 28)
        {
            setAssertPositiveInteger(malariaU5D2Field);
            setAssertPositiveInteger(malariaO5D2Field);
            setAssertPositiveInteger(malariaPWD2Field);
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
        } else
        {
            if (dayMaxiOfMonth != 29);
            if (dayMaxiOfMonth >= 30)
            {
                setAssertPositiveInteger(malariaU5D2Field);
                setAssertPositiveInteger(malariaO5D2Field);
                setAssertPositiveInteger(malariaPWD2Field);
            }
            if (dayMaxiOfMonth == 31)
            {
                setAssertPositiveInteger(malariaU5D3Field);
                setAssertPositiveInteger(malariaO5D3Field);
                setAssertPositiveInteger(malariaPWD3Field);
                return;
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
