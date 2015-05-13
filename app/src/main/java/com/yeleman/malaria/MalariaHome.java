package com.yeleman.malaria;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.Popups;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 04/12/14.
 */
public class MalariaHome extends Activity {

    private final static String TAG = Constants.getLogTag("Malaria");
    private Button weeklyReportButton;
    private Button monthlyReportButton;
    private Button webSiteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate Malaria");
        setContentView(R.layout.malaria_home);
        setTitle(getString(R.string.malaria_app_label));
        seputUI();
    }

    protected void seputUI() {
        Log.d(TAG, "setupUI Malaria");

       // Log.d("Sugar", com.orm.util.NamingHelper.toSQLNameDefault("MalariaStockoutReport"));
        weeklyReportButton = (Button) findViewById(R.id.weeklyReportButton);
        //weeklyReportButton.setEnabled(false);
        monthlyReportButton = (Button) findViewById(R.id.monthlyReportButton);
        webSiteButton = (Button) findViewById(R.id.webSiteButton);

        final Activity activity = this;

        weeklyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MalariaWeeklyReport.class);
                startActivity(intent);
            }
        });
        
        monthlyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        MalariaMonthlyHome.class);
                startActivity(intent);
            }
        });

        webSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malariaDataUrl = String.format("%1$s/malaria/dashboard/", Constants.server_url);
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(malariaDataUrl));
                Popups.startIntentIfOnline(activity, intent);
            }
        });
    }
}
