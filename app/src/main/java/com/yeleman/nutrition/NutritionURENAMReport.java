package com.yeleman.nutrition;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

import static com.yeleman.snisidroid.Constants.getCompleteStatus;

/**
 * Created by fad on 29/10/14.
 */
public class NutritionURENAMReport extends CheckedFormActivity implements View.OnClickListener {

    private final static String TAG = Constants.getLogTag("NutritionURENAMReport");

    private Button u23o6ReportButton;
    private Button u59o23ReportButton;
    private Button o59ReportButton;
    private Button pwReportButton;
    private Button exsamReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_urenam_report);
        setTitle(String.format(getString(R.string.sub_app_name_nut), "URENAM"));
        Log.d(TAG, "onCreate NutritionURENAMReport");
       // Bundle extras = getIntent().getExtras();
       // restoreReport = extras.getString("restoreReport");

        setupSMSReceiver();
        setupUI();
    }
    
    @Override
    public void onResume() {
        super.onResume(); 
        setupUI();
    }

    @SuppressLint("StringFormatMatches")
    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionMonthlyHome");

        NutritonURENAMReportData report = NutritonURENAMReportData.get();

        u23o6ReportButton = (Button) findViewById(R.id.u6o23URENAMButton);
        u23o6ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                         new Object[]{getString(R.string.u23o6), getCompleteStatus(report.u23o6_is_complete)}));
        u23o6ReportButton.setOnClickListener(this);

        u59o23ReportButton = (Button) findViewById(R.id.u59o23URENAMButton);
        u59o23ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                         new Object[]{getString(R.string.u59o23), getCompleteStatus(report.u59o23_is_complete)}));
        u59o23ReportButton.setOnClickListener(this);

        o59ReportButton = (Button) findViewById(R.id.o59URENAMButton);
        o59ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                         new Object[]{getString(R.string.o59), getCompleteStatus(report.o59_is_complete)}));
        o59ReportButton.setOnClickListener(this);

        pwReportButton = (Button) findViewById(R.id.pwURENAMButton);
        pwReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                         new Object[]{getString(R.string.pw), getCompleteStatus(report.pw_is_complete)}));
        pwReportButton.setOnClickListener(this);

        exsamReportButton = (Button) findViewById(R.id.exmaURENAMButton);
        exsamReportButton.setText(String.format(getString(R.string.nutrition_fillout_report),
                         new Object[]{getString(R.string.exsam), getCompleteStatus(report.exsam_is_complete)}));
        exsamReportButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        Object activity = null;
        switch (view.getId()) {
            case R.id.u6o23URENAMButton:
                activity = NutritonU23O6URENAMReport.class;
                break;
            case R.id.u59o23URENAMButton:
                activity = NutritionU59O23URENAMReport.class;
                break;
            case R.id.o59URENAMButton:
                activity = NutritionO59URENAMReport.class;
                break;
            case R.id.pwURENAMButton:
                activity = NutritionPWURENAMReport.class;
                break;
            case R.id.exmaURENAMButton:
                activity = NutritionExsamURENAMReport.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);
    }
}
