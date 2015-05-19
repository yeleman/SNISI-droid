package com.yeleman.malaria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 05/12/14.
 */
public class MalariaMonthlyHome extends CheckedFormActivity implements View.OnClickListener {

    private final static String TAG = Constants.getLogTag("MalariaMonthlyHome");

    private Button u5ReportButton;
    private Button o5ReportButton;
    private Button pwReportButton;
    private Button stockoutReportButton;
    private Button cpnSpReportButton;
    private Button SubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_monthly_home);
        setTitle(String.format(getString(R.string.sub_app_name_malaria),
                 getString(R.string.malaria_monthly_report_label)));
        Log.d(TAG, "onCreate MalariaMonthlyHome");
        MalariaReportData monthlyReport = MalariaReportData.get();
        if (monthlyReport.atLeastOneIsComplete()) {
            requestForResumeReport(this, MalariaReportData.get());
        }
        setupSMSReceiver();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionMonthlyHome");

        MalariaReportData malariaReport = MalariaReportData.get();
        u5ReportButton = (Button) findViewById(R.id.u5ReportButton);
        u5ReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(u5ReportButton, malariaReport.u5_is_complete);
        o5ReportButton = (Button) findViewById(R.id.o5ReportButton);
        o5ReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(o5ReportButton, malariaReport.o5_is_complete);
        pwReportButton = (Button) findViewById(R.id.pwReportButton);
        pwReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(pwReportButton, malariaReport.pw_is_complete);
        stockoutReportButton = (Button) findViewById(R.id.stockoutReportButton);
        stockoutReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(stockoutReportButton, malariaReport.stockout_is_complete);

        cpnSpReportButton = (Button) findViewById(R.id.cpnSpReportButton);
        cpnSpReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(cpnSpReportButton, malariaReport.cpn_sp_is_complete);

        SubmitButton = (Button) findViewById(R.id.SubmitButton);
        SubmitButton.setEnabled(false);

        // Submit Button
        final CheckedFormActivity activity = this;
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // transmit SMS
                requestPasswordAndTransmitSMS(activity, MalariaReportData.get().getName(),
                        Constants.SMS_KEYWORD_MALIRIA_MONTHLY, buildSMSText());
            }
        });
        MalariaReportData monthlyReport = MalariaReportData.get();
        if (monthlyReport.isComplete()) {
            SubmitButton.setEnabled(true);
        }
    }

    protected void resetReportData(){
        Log.i(TAG, "resetReportData");
        MalariaReportData malariaReport = MalariaReportData.get();
        malariaReport.resetReportData();
        setupUI();
    }

    @Override
    public void onClick(View view) {
        Object activity = null;

        switch (view.getId()) {
            case R.id.u5ReportButton:
                activity = MalariaU5Report.class;
                break;
            case R.id.o5ReportButton:
                activity = MalariaO5Report.class;
                break;
            case R.id.pwReportButton:
                activity = MalariaPwReport.class;
                break;
            case R.id.stockoutReportButton:
                activity = MalariaStockoutReport.class;
                break;
            case R.id.cpnSpReportButton:
                activity = MalariaCPNSPReport.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }

    protected String buildSMSText() {
        MalariaReportData report = MalariaReportData.get();
        return report.buildSMSText();
    }
}
