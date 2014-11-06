package com.yeleman.nutrition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 29/10/14.
 */
public class NutritionURENIReport extends CheckedFormActivity implements View.OnClickListener {
    private final static String TAG = Constants.getLogTag("NutritionURENIReport");


    private Button u6ReportButton;
    private Button u59o6ReportButton;
    private Button o59ReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_ureni_report);
        Log.d(TAG, "onCreate NutritionURENIReport");

        setupSMSReceiver();
        setupUI();
    }

    @Override
    public void onResume() {
        super.onResume(); 
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionURENIReport");

        String status = "  ";

        u6ReportButton = (Button) findViewById(R.id.u6URENIButton);
        u6ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report), new Object[]{"0-6m", status}));
        u6ReportButton.setOnClickListener(this);

        u59o6ReportButton = (Button) findViewById(R.id.u59URENiButton);
        u59o6ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report), new Object[]{"23-59m", status}));
        u59o6ReportButton.setOnClickListener(this);

        o59ReportButton = (Button) findViewById(R.id.o59URENiButton);
        o59ReportButton.setText(String.format(getString(R.string.nutrition_fillout_report), new Object[]{"59m+", status}));
        o59ReportButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Object activity = null;
        switch (view.getId()) {
            case R.id.u6URENIButton:
                activity = NutritionURENIReport.class;
                break;
            case R.id.o59URENiButton:
                activity = NutritionURENIReport.class;
                break;
            case R.id.u59URENiButton:
                activity = NutritionURENIReport.class;
                break;
        }
        Intent intent = new Intent(
                getApplicationContext(),
                (Class<?>) activity);
        startActivity(intent);


    }
}