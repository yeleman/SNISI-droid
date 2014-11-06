package com.yeleman.nutrition;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.Popups;
import com.yeleman.snisidroid.Preferences;
import com.yeleman.snisidroid.R;

import static com.yeleman.snisidroid.Constants.getCompleteStatus;

/**
 * Created by fad on 29/10/14.
 */
public class NutritionMonthlyHome extends CheckedFormActivity implements View.OnClickListener {

    private final static String TAG = Constants.getLogTag("NutritionMonthlyHome");

    private Button urenasReportButton;
    private Button ureniReportButton;
    private Button urenamReportButton;
    private Button inputsReportButton;

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
            //TODO Add check URENI URENAS and Inputs for requestForResumeReport

            NutritonURENAMReportData report = NutritonURENAMReportData.get();
            Log.d(TAG, "requestForResumeReport NutritonURENAMReportData");  
            if (report.pw_is_complete || report.exsam_is_complete ||
                report.o59_is_complete || report.u23o6_is_complete ||
                report.u59o23_is_complete) {
                    requestForResumeReport(this, NutritonURENAMReportData.get());
            }
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

        if (is_urenam) {
            NutritonURENAMReportData urenamReport = NutritonURENAMReportData.get();
            urenamReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                     getString(R.string.urenam)));
            Constants.updateButtonCompletion(urenamReportButton, urenamReport.isComplete());
            urenamReportButton.setOnClickListener(this);
        } else {
            urenamReportButton.setVisibility(View.GONE);
        }
        if (is_urenas) {
            NutritonURENASReportData urenasReport = NutritonURENASReportData.get();
            urenasReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                     getString(R.string.urenas)));
            Constants.updateButtonCompletion(urenasReportButton, urenasReport.isComplete());
            urenasReportButton.setOnClickListener(this);
        } else {
            urenasReportButton.setVisibility(View.GONE);
        }
        if (is_ureni) {
            NutritonURENIReportData ureniReport = NutritonURENIReportData.get();
            ureniReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                     getString(R.string.ureni)));
            Constants.updateButtonCompletion(ureniReportButton, ureniReport.isComplete());
            ureniReportButton.setOnClickListener(this);
        } else{
            ureniReportButton.setVisibility(View.GONE);
        }

        NutritonInputsReportData inputsReport = NutritonInputsReportData.get();
        inputsReportButton = (Button) findViewById(R.id.monthlyInputsButton);
        inputsReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                                                     getString(R.string.inputs)));
        Constants.updateButtonCompletion(inputsReportButton, inputsReport.isComplete());
        inputsReportButton.setOnClickListener(this);
    }

    protected void resetReportData(){
        Log.i(TAG, "resetReportData");
        NutritonURENAMReportData urenamReport = NutritonURENAMReportData.get();
        urenamReport.resetReportData();
        NutritonURENASReportData urenasReport = NutritonURENASReportData.get();
        urenasReport.resetReportData();
        NutritonURENIReportData ureniReport = NutritonURENIReportData.get();
        ureniReport.resetReportData();
        NutritonInputsReportData inputsreport = NutritonInputsReportData.get();
        inputsreport.resetReportData();
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
            case R.id.saveAndSubmitButton:
                activity = NutritionInputsReport.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }
}
