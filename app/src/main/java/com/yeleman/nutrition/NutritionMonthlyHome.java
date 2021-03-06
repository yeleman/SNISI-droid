package com.yeleman.nutrition;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.Preferences;
import com.yeleman.snisidroid.R;

public class NutritionMonthlyHome extends CheckedFormActivity implements View.OnClickListener {

    private final static String TAG = Constants.getLogTag("NutritionMonthlyHome");

    private Button urenasReportButton;
    private Button ureniReportButton;
    private Button urenamReportButton;
    private Button inputsReportButton;
    private Button resumeReportButton;
    private Button saveAndSubmitButton;

    private boolean is_urenam, is_urenas, is_ureni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_monthly_home);
        setTitle(String.format(getString(R.string.sub_app_name_nut),
                               getString(R.string.nutrition_monthly_report_label)));
        Log.d(TAG, "onCreate NutritionMonthlyHome");

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        is_urenam = sharedPrefs.getBoolean("hc_is_urenam", false);
        is_urenas = sharedPrefs.getBoolean("hc_is_urenas", false);
        is_ureni = sharedPrefs.getBoolean("hc_is_ureni", false);

        if (!is_urenam && !is_urenas && !is_ureni) {
            AlertDialog.Builder prefCheckBuilder = new AlertDialog.Builder(this);
            prefCheckBuilder.setCancelable(false);
            prefCheckBuilder.setTitle(
                    getString(R.string.nutrition_level_missing_title));
            prefCheckBuilder.setMessage(
                    getString(R.string.nutrition_level_missing_body));
            prefCheckBuilder.setIcon(R.drawable.ic_launcher);
            prefCheckBuilder.setPositiveButton(R.string.go_to_preferences,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog (auto)
                            // close the nutrition activity
                            finish();
                            // go to preferences
                            Intent intent = new Intent(
                                    getApplicationContext(),
                                    Preferences.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog prefCheckDialog = prefCheckBuilder.create();
            prefCheckDialog.show();
        } else {
            Log.d(TAG, "requestForResumeReport NutritionURENAMReportData");
            NutritionMonthlyReportData monthlyReport = NutritionMonthlyReportData.get();
            monthlyReport.updateUren(is_urenam, is_urenas, is_ureni);
            if (monthlyReport.atLeastOneIsComplete()) {
                requestForResumeReport(this, NutritionMonthlyReportData.get());
            }
            setupSMSReceiver();
            setupUI();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionMonthlyHome");

        urenamReportButton = (Button) findViewById(R.id.monthlyURENAMButton);
        urenasReportButton = (Button) findViewById(R.id.monthlyURENASButton);
        ureniReportButton = (Button) findViewById(R.id.monthlyURENIButton);
        inputsReportButton = (Button) findViewById(R.id.monthlyInputsButton);
        resumeReportButton = (Button) findViewById(R.id.resumeReportButton);
        resumeReportButton.setEnabled(false);
        saveAndSubmitButton = (Button) findViewById(R.id.saveAndSubmitButton);
        saveAndSubmitButton.setEnabled(false);

        // URENAM
        if (is_urenam) {
            NutritionURENAMReportData urenamReport = NutritionURENAMReportData.get();
            urenamReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                     getString(R.string.urenam)));
            Constants.updateButtonCompletion(urenamReportButton, urenamReport.isComplete());
            urenamReportButton.setOnClickListener(this);
        } else {
            urenamReportButton.setVisibility(View.GONE);
        }

        // URENAS
        if (is_urenas) {
            NutritionURENASReportData urenasReport = NutritionURENASReportData.get();
            urenasReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                     getString(R.string.urenas)));
            Constants.updateButtonCompletion(urenasReportButton, urenasReport.isComplete());
            urenasReportButton.setOnClickListener(this);
        } else {
            urenasReportButton.setVisibility(View.GONE);
        }

        // URENI
        if (is_ureni) {
            NutritionURENIReportData ureniReport = NutritionURENIReportData.get();
            ureniReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                    getString(R.string.ureni)));
            Constants.updateButtonCompletion(ureniReportButton, ureniReport.isComplete());
            ureniReportButton.setOnClickListener(this);
        } else{
            ureniReportButton.setVisibility(View.GONE);
        }
        // Resume Report
        resumeReportButton.setOnClickListener(this);

        // Stocks
        inputsReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                getString(R.string.inputs)));
        NutritionInputsReportData inputsReport = NutritionInputsReportData.get();
        Constants.updateButtonCompletion(inputsReportButton, inputsReport.isComplete());
        inputsReportButton.setOnClickListener(this);

        // Submit Button
        final CheckedFormActivity activity = this;
        saveAndSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // transmit SMS
                requestPasswordAndTransmitSMS(activity, NutritionMonthlyReportData.get().getName(),
                                              Constants.SMS_KEYWORD_NUT_MONTHLY, buildSMSText());
            }
        });

        // Update UREN levels
        NutritionMonthlyReportData monthlyReport = NutritionMonthlyReportData.get();
        monthlyReport.updateUren(is_urenam, is_urenas, is_ureni);
        if (monthlyReport.isComplete()) {
            resumeReportButton.setEnabled(true);
            saveAndSubmitButton.setEnabled(true);
        }
    }

    protected void resetReportData(){
        Log.i(TAG, "resetReportData");
        NutritionMonthlyReportData monthlyReport = NutritionMonthlyReportData.get();
        monthlyReport.resetReportData();
        monthlyReport.updateUren(is_urenam, is_urenas, is_ureni);
        setupUI();
    }

    public void onClick(View view) {
        Object activity = null;

        switch (view.getId()) {
            case R.id.monthlyURENAMButton:
                activity = NutritionURENAMReport.class;
                break;
            case R.id.monthlyURENASButton:
                activity = NutritionURENASReport.class;
                break;
            case R.id.monthlyURENIButton:
                activity = NutritionURENIReport.class;
                break;
            case R.id.monthlyInputsButton:
                activity = NutritionInputsReport.class;
                break;
            case R.id.resumeReportButton:
                activity = NutritionSummaryReport.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }

    protected String buildSMSText() {
        NutritionMonthlyReportData report = NutritionMonthlyReportData.get();
        return report.buildSMSText();
    }
}
