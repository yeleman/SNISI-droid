package com.yeleman.nutrition;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class NutritionWeeklyReport extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("NutritionWeeklyReport");

    private boolean is_urenam, is_urenas, is_ureni;

    protected TextView urenamLabel, urenasLabel, ureniLabel;

    protected EditText mamScreeningField;
    protected EditText samScreeningField;
    protected EditText samcScreeningField;

    protected EditText mamCasesField;
    protected EditText mamDeathsField;

    protected EditText samCasesField;
    protected EditText samDeathsField;

    protected EditText samcCasesField;
    protected EditText samcDeathsField;

    protected Button saveAndSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_weekly_report);
        setTitle(String.format(getString(R.string.sub_app_name_nut),
                               getString(R.string.nutrition_weekly_report_label)));
        Log.d(TAG, "onCreate NutritionWeeklyReport");

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionWeeklyReport");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        is_urenam = sharedPrefs.getBoolean("hc_is_urenam", false);
        is_urenas = sharedPrefs.getBoolean("hc_is_urenas", false);
        is_ureni = sharedPrefs.getBoolean("hc_is_ureni", false);

        mamScreeningField = (EditText) findViewById(R.id.screeningURENAMField);
        mamCasesField = (EditText) findViewById(R.id.casesURENAMField);
        mamDeathsField = (EditText) findViewById(R.id.deathsURENAMField);
        mamDeathsField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        samScreeningField = (EditText) findViewById(R.id.screeningURENASField);
        samCasesField = (EditText) findViewById(R.id.casesURENASField);
        samDeathsField = (EditText) findViewById(R.id.deathsURENASField);
        samDeathsField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        samcScreeningField = (EditText) findViewById(R.id.screeningURENIField);
        samcCasesField = (EditText) findViewById(R.id.casesURENIField);
        samcDeathsField = (EditText) findViewById(R.id.deathsURENIField);
        samcDeathsField.setImeOptions(EditorInfo.IME_ACTION_DONE);

        if (!is_urenam) {
            LinearLayout linearLayoutURENAM = (LinearLayout) findViewById(R.id.linearLayoutURENAM);
            linearLayoutURENAM.setVisibility(View.GONE);
        }
        if (!is_urenas) {
            LinearLayout linearLayoutURENAS = (LinearLayout) findViewById(R.id.linearLayoutURENAS);
            linearLayoutURENAS.setVisibility(View.GONE);
        } else {
            mamDeathsField.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        }
        if (!is_ureni) {
            LinearLayout linearLayoutURENI = (LinearLayout) findViewById(R.id.linearLayoutURENI);
            linearLayoutURENI.setVisibility(View.GONE);
        } else {
            mamDeathsField.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            samDeathsField.setImeOptions(EditorInfo.IME_ACTION_NEXT);
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
                requestPasswordAndTransmitSMS(activity, NutritionWeeklyReportData.get().getName(),
                                              Constants.SMS_KEYWORD_NUT_WEEKLY, buildSMSText());
            }
        });

        Log.d(TAG, "requestForResumeReport NutritionWeeklyReport");
        requestForResumeReport(this, NutritionWeeklyReportData.get());
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");
    	NutritionWeeklyReportData report = NutritionWeeklyReportData.get();
    	report.updateMetaData();

    	if (is_urenam) {
    		report.mam_screening = integerFromField(mamScreeningField);
    		report.mam_cases = integerFromField(mamCasesField);
    		report.mam_deaths = integerFromField(mamDeathsField);
    	}

    	if (is_urenas) {
    		report.sam_screening = integerFromField(samScreeningField);
    		report.sam_cases = integerFromField(samCasesField);
    		report.sam_deaths = integerFromField(samDeathsField);
    	}

    	if (is_ureni) {
    		report.samc_screening = integerFromField(samcScreeningField);
    		report.samc_cases = integerFromField(samcCasesField);
    		report.samc_deaths = integerFromField(samcDeathsField);
    	}
    	report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
		NutritionWeeklyReportData report = NutritionWeeklyReportData.get();

		if (is_urenam) {
    		setTextOnField(mamScreeningField, report.mam_screening);
    		setTextOnField(mamCasesField, report.mam_cases);
    		setTextOnField(mamDeathsField, report.mam_deaths);
    	}

    	if (is_urenas) {
    		setTextOnField(samScreeningField, report.sam_screening);
    		setTextOnField(samCasesField, report.sam_cases);
    		setTextOnField(samDeathsField, report.sam_deaths);
    	}

    	if (is_ureni) {
    		setTextOnField(samcScreeningField, report.samc_screening);
    		setTextOnField(samcCasesField, report.samc_cases);
    		setTextOnField(samcDeathsField, report.samc_deaths);
    	}
    }

    protected void setupInvalidInputChecks() {
        if (is_urenam) {
            setAssertPositiveInteger(mamScreeningField);
            setAssertPositiveInteger(mamCasesField);
            setAssertPositiveInteger(mamDeathsField);
        }

        if (is_urenas) {
            setAssertPositiveInteger(samScreeningField);
            setAssertPositiveInteger(samCasesField);
            setAssertPositiveInteger(samDeathsField);
        }

        if (is_ureni) {
            setAssertPositiveInteger(samcScreeningField);
            setAssertPositiveInteger(samcCasesField);
            setAssertPositiveInteger(samcDeathsField);
        }
    }

    protected boolean ensureDataCoherence() {
        boolean urenam_checks, urenas_checks, ureni_checks;

        if (is_urenam) {
            urenam_checks = mustBeInferiorOrEqual(mamCasesField, mamCasesField, mamScreeningField) &&
                            mustBeInferiorOrEqual(mamDeathsField, mamDeathsField, mamCasesField);
        } else { urenam_checks = true; }

        if (is_urenas) {
            urenas_checks = mustBeInferiorOrEqual(samCasesField, samCasesField, samScreeningField) &&
                            mustBeInferiorOrEqual(samDeathsField, samDeathsField, samCasesField);
        } else { urenas_checks = true; }

        if (is_ureni) {
            ureni_checks = mustBeInferiorOrEqual(samcCasesField, samcCasesField, samcScreeningField) &&
                           mustBeInferiorOrEqual(samcDeathsField, samcDeathsField, samcCasesField);
        } else { ureni_checks = true; }

        return urenam_checks && urenas_checks && ureni_checks;
    }

    protected String buildSMSText() {
        NutritionWeeklyReportData report = NutritionWeeklyReportData.get();
        return report.buildSMSText();
    }
}
