package com.yeleman.nutrition;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yeleman.smir.SMIRHome;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.Popups;
import com.yeleman.snisidroid.Preferences;
import com.yeleman.snisidroid.R;


public class NutritionHome extends ActionBarActivity {

    private final static String TAG = Constants.getLogTag("NutritionHome");
	private Button weeklyReportButton;
	private Button monthlyReportButton;
    private Button webSiteButton;
    private boolean is_urenam, is_urenas, is_ureni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate NutritionHome");
        setContentView(R.layout.nutrition_home);
        setTitle(getString(R.string.nutrition_app_label));

        setUrenLevels();

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
            setupUI();
        }
    }

    protected void setUrenLevels() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        is_urenam = sharedPrefs.getBoolean("hc_is_urenam", false);
        is_urenas = sharedPrefs.getBoolean("hc_is_urenas", false);
        is_ureni = sharedPrefs.getBoolean("hc_is_ureni", false);
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionHome");

        // might have changed
        setUrenLevels();

        NutritionMonthlyReportData reportMonthly = NutritionMonthlyReportData.get();

        if (reportMonthly.has_urenam != is_urenam) {
            Log.i(TAG, "URENAM has change in preference");
            NutritionURENAMReportData reportURENAM = NutritionURENAMReportData.get();
            NutritionInputsReportData reportInput = NutritionInputsReportData.get();
            reportInput.deleteAll(reportInput.getClass());
            if (!is_urenam){
              try {
                reportURENAM.deleteAll(reportURENAM.getClass());
              } catch (Exception e){
                  Log.i(TAG, String.valueOf(e));
              }
            }
        }
        if (reportMonthly.has_urenas != is_urenas){
            Log.i(TAG, "URENAS has change in preference");
            NutritionURENASReportData reportURENAS = NutritionURENASReportData.get();
            if (!is_urenas){
              try {
                    reportURENAS.deleteAll(reportURENAS.getClass());
              } catch (Exception e){
                  Log.i(TAG, String.valueOf(e));
              }
            }
        }
        if (reportMonthly.has_ureni != is_ureni){
            Log.i(TAG, "URENI has change in preference");
            NutritionURENIReportData reportURENI = NutritionURENIReportData.get();
            NutritionInputsReportData reportInput = NutritionInputsReportData.get();
            reportInput.deleteAll(reportInput.getClass());
            if (!is_ureni){
              try {
                  Log.d(TAG, "DELETING URENI/INPUT Reports");
                  reportURENI.deleteAll(reportURENI.getClass());
              } catch (Exception e){
                  Log.i(TAG, String.valueOf(e));
              }
            }
        }
        reportMonthly.updateUren(is_urenam, is_urenas, is_ureni);

    	weeklyReportButton = (Button) findViewById(R.id.weeklyReportButton);
    	monthlyReportButton = (Button) findViewById(R.id.monthlyReportButton);
        webSiteButton = (Button) findViewById(R.id.webSiteButton);

        final Activity activity = this;

    	weeklyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        NutritionWeeklyReport.class);
                startActivity(intent);
            }
        });

        monthlyReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        NutritionMonthlyHome.class);
                startActivity(intent);
            }
        });

        webSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nutritionDataUrl = String.format("%1$s/nutrition/dashboard/", Constants.server_url);
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(nutritionDataUrl));
                Popups.startIntentIfOnline(activity, intent);
            }
        });
    }
}
