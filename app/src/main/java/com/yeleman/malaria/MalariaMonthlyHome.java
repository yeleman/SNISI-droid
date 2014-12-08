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

    private Button consultationReportButton;
    private Button inpatientReportButton;
    private Button distributedBednetsReportButton;
    private Button deathReportButton;
    private Button stockoutReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_monthly_home);
        setTitle(String.format(getString(R.string.malaria_app_label),
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
        consultationReportButton = (Button) findViewById(R.id.consultationReportButton);
        consultationReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(consultationReportButton, malariaReport.consultation_is_complete);
        inpatientReportButton = (Button) findViewById(R.id.inpatientReportButton);
        inpatientReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(inpatientReportButton, malariaReport.impatient_is_complete);
        deathReportButton = (Button) findViewById(R.id.deathReportButton);
        deathReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(deathReportButton, malariaReport.death_is_comple);
        distributedBednetsReportButton = (Button) findViewById(R.id.distributedBednetsReportButton);
        distributedBednetsReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(distributedBednetsReportButton, malariaReport.mild_is_complete);
        stockoutReportButton = (Button) findViewById(R.id.stockoutReportButton);
        stockoutReportButton.setOnClickListener(this);
        Constants.updateButtonCompletion(stockoutReportButton, malariaReport.stockout_is_complete);
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
            case R.id.consultationReportButton:
                activity = MalariaConsultationReport.class;
                break;
            case R.id.inpatientReportButton:
                activity = MalariaInpatientReport.class;
                break;
            case R.id.deathReportButton:
                activity = MalariaDeathReport.class;
                break;
            case R.id.distributedBednetsReportButton:
                activity = MalariaMonthlyInputsReport.class;
                break;
            case R.id.stockoutReportButton:
                activity = MalariaStockoutReport.class;
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
