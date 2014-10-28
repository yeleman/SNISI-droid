package com.yeleman.nutrition;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;
import static com.orm.SugarRecord.save;

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

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (is_urenam) {
            ViewGroup urenam_parent = (ViewGroup) findViewById(R.id.linearLayoutURENAM);
            View inflated_urenam = inflater.inflate(R.layout.nutrition_weekly_urenam, urenam_parent);
            urenamLabel = (TextView) inflated_urenam.findViewById(R.id.levelLabel);
            urenamLabel.setText(getString(R.string.nutrition_weekly_urenam));
            mamScreeningField = (EditText) inflated_urenam.findViewById(R.id.screeningField);
            mamCasesField = (EditText) inflated_urenam.findViewById(R.id.casesField);
            mamDeathsField = (EditText) inflated_urenam.findViewById(R.id.deathsField);
        }

        if (is_urenas) {
            ViewGroup urenas_parent = (ViewGroup) findViewById(R.id.linearLayoutURENAS);
            View inflated_urenas = inflater.inflate(R.layout.nutrition_weekly_urenam, urenas_parent);
            urenasLabel = (TextView) inflated_urenas.findViewById(R.id.levelLabel);
            urenasLabel.setText(getString(R.string.nutrition_weekly_urenas));
            samScreeningField = (EditText) inflated_urenas.findViewById(R.id.screeningField);
            samCasesField = (EditText) inflated_urenas.findViewById(R.id.casesField);
            samDeathsField = (EditText) inflated_urenas.findViewById(R.id.deathsField);
        }

        if (is_ureni) {
            ViewGroup ureni_parent = (ViewGroup) findViewById(R.id.linearLayoutURENI);
            View inflated_ureni = inflater.inflate(R.layout.nutrition_weekly_urenam, ureni_parent);
            ureniLabel = (TextView) inflated_ureni.findViewById(R.id.levelLabel);
            ureniLabel.setText(getString(R.string.nutrition_weekly_ureni));
            samcScreeningField = (EditText) inflated_ureni.findViewById(R.id.screeningField);
            samcCasesField = (EditText) inflated_ureni.findViewById(R.id.casesField);
            samcDeathsField = (EditText) inflated_ureni.findViewById(R.id.deathsField);
        }

        // setup invalid inputs checks
        setupInvalidInputChecks();

        saveAndSubmitButton = (Button) findViewById(R.id.saveAndSubmitButton);
        saveAndSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	// ensure data is OK
                if (!checkInputsAndCoherence()) { return; }

                // save data to DB
                storeReportData();

                finish();
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
    	report.save();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nutrition_weekly_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        Log.d(TAG, "buildSMSText");
        NutritionWeeklyReportData report = NutritionWeeklyReportData.get();
        return String.format(Constants.SMS_NUTRITION_WEEKLY_REPORT,
                             report.mam_screening,
                             report.sam_screening,
                             report.sam_screening,
                             report.mam_cases,
                             report.mam_deaths,
                             report.sam_cases,
                             report.sam_deaths,
                             report.samc_cases,
                             report.samc_deaths);
    }
}
