package com.yeleman.smir;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.R;
import com.yeleman.snisidroid.Constants;


public class SMIRHome extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("SMIRHome");

    protected EditText confirmedEbolaField;
    protected EditText deathEbolaField;
    protected EditText confirmedAcuteFlaccidParalysisField;
    protected EditText deathAcuteFlaccidParalysisField;
    protected EditText confirmedInfluenzaAH1n1Field;
    protected EditText deathInfluenzaAH1n1Field;
    protected EditText confirmedCholeraField;
    protected EditText deathCholeraField;
    protected EditText confirmedRedDiarrheaField;
    protected EditText deathRedDiarrheaField;
    protected EditText confirmedMeaslesField;
    protected EditText deathMeaslesField;
    protected EditText confirmedYellowFeverField;
    protected EditText deathYellowFeverField;
    protected EditText confirmedNeonatalTetanusField;
    protected EditText deathNeonatalTetanusField;
    protected EditText confirmedMeningitisField;
    protected EditText deathMeningitisField;
    protected EditText confirmedRabiesField;
    protected EditText deathRabiesField;
    protected EditText confirmedacuteMeaslesDiarrheaField;
    protected EditText deathacuteMeaslesDiarrheaField;
    protected TextView labelDate;
    protected DatePicker datePicker;
    protected Button  submitButton;
    private EditText confirmedOtherNotifiableDiseaseField;
    private EditText deathOtherNotifiableDiseaseField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smir_home);
        setTitle(String.format(getString(R.string.sub_app_name_smir),
                getString(R.string.smir_weekly_report_label)));

        setupSMSReceiver();
        setupUI();
   }

    protected void setupUI() {
        Log.d(TAG, "setupUI SMIRHome");

        confirmedEbolaField = (EditText) findViewById(R.id.confirmedEbolaField);
        deathEbolaField = (EditText) findViewById(R.id.deathEbolaField);
        confirmedAcuteFlaccidParalysisField = (EditText) findViewById(R.id.confirmedAcuteFlaccidParalysisField);
        deathAcuteFlaccidParalysisField = (EditText) findViewById(R.id.deathAcuteFlaccidParalysisField);
        confirmedInfluenzaAH1n1Field = (EditText) findViewById(R.id.confirmedInfluenzaAH1n1Field);
        deathInfluenzaAH1n1Field = (EditText) findViewById(R.id.deathInfluenzaAH1n1Field);
        confirmedCholeraField = (EditText) findViewById(R.id.confirmedCholeraField);
        deathCholeraField = (EditText) findViewById(R.id.deathCholeraField);
        confirmedRedDiarrheaField = (EditText) findViewById(R.id.confirmedRedDiarrheaField);
        deathRedDiarrheaField = (EditText) findViewById(R.id.deathRedDiarrheaField);
        confirmedMeaslesField = (EditText) findViewById(R.id.confirmedMeaslesField);
        deathMeaslesField = (EditText) findViewById(R.id.deathMeaslesField);
        confirmedYellowFeverField = (EditText) findViewById(R.id.confirmedYellowFeverField);
        deathYellowFeverField = (EditText) findViewById(R.id.deathYellowFeverField);
        confirmedNeonatalTetanusField = (EditText) findViewById(R.id.confirmedNeonatalTetanusField);
        deathNeonatalTetanusField = (EditText) findViewById(R.id.deathNeonatalTetanusField);
        confirmedMeningitisField = (EditText) findViewById(R.id.confirmedMeningitisField);
        deathMeningitisField = (EditText) findViewById(R.id.deathMeningitisField);
        confirmedRabiesField = (EditText) findViewById(R.id.confirmedRabiesField);
        deathRabiesField = (EditText) findViewById(R.id.deathRabiesField);
        confirmedacuteMeaslesDiarrheaField = (EditText) findViewById(R.id.confirmedacuteMeaslesDiarrheaField);
        deathacuteMeaslesDiarrheaField = (EditText) findViewById(R.id.deathacuteMeaslesDiarrheaField);
        confirmedOtherNotifiableDiseaseField = (EditText) findViewById(R.id.confirmedOtherNotifiableDiseaseField);
        deathOtherNotifiableDiseaseField = (EditText) findViewById(R.id.deathOtherNotifiableDiseaseField);
        labelDate = (TextView) findViewById(R.id.labelDate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        // setup invalid inputs checks
        setupInvalidInputChecks();

        final CheckedFormActivity activity = this;
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK
                if (!checkInputsAndCoherence()) { return; }

                // transmit SMS
                requestPasswordAndTransmitSMS(activity, "SMIR",
                        Constants.SMIR_WEEK, buildSMSText());
            }
        });
    }
    
    protected void setupInvalidInputChecks() {
        
        setAssertPositiveInteger(confirmedEbolaField);
        setAssertPositiveInteger(deathEbolaField);
        setAssertPositiveInteger(confirmedAcuteFlaccidParalysisField);
        setAssertPositiveInteger(deathAcuteFlaccidParalysisField);
        setAssertPositiveInteger(confirmedInfluenzaAH1n1Field);
        setAssertPositiveInteger(deathInfluenzaAH1n1Field);
        setAssertPositiveInteger(confirmedCholeraField);
        setAssertPositiveInteger(deathCholeraField);
        setAssertPositiveInteger(confirmedRedDiarrheaField);
        setAssertPositiveInteger(deathRedDiarrheaField);
        setAssertPositiveInteger(confirmedMeaslesField);
        setAssertPositiveInteger(deathMeaslesField);
        setAssertPositiveInteger(confirmedYellowFeverField);
        setAssertPositiveInteger(deathYellowFeverField);
        setAssertPositiveInteger(confirmedNeonatalTetanusField);
        setAssertPositiveInteger(deathNeonatalTetanusField);
        setAssertPositiveInteger(confirmedMeningitisField);
        setAssertPositiveInteger(deathMeningitisField);
        setAssertPositiveInteger(confirmedRabiesField);
        setAssertPositiveInteger(deathRabiesField);
        setAssertPositiveInteger(confirmedacuteMeaslesDiarrheaField);
        setAssertPositiveInteger(deathacuteMeaslesDiarrheaField);
        setAssertPositiveInteger(confirmedOtherNotifiableDiseaseField);
        setAssertPositiveInteger(deathOtherNotifiableDiseaseField);
    }
    
    protected boolean ensureDataCoherence(){
        boolean isEnsureDataCoherence;
        
        isEnsureDataCoherence =
            mustBeInferiorOrEqual(confirmedEbolaField, 
                           deathEbolaField, confirmedEbolaField) &&
            mustBeInferiorOrEqual(confirmedAcuteFlaccidParalysisField, 
                           deathAcuteFlaccidParalysisField, confirmedAcuteFlaccidParalysisField) &&
            mustBeInferiorOrEqual(confirmedInfluenzaAH1n1Field, 
                           deathInfluenzaAH1n1Field, confirmedInfluenzaAH1n1Field) &&
            mustBeInferiorOrEqual(confirmedCholeraField, 
                           deathCholeraField, confirmedCholeraField) &&
            mustBeInferiorOrEqual(confirmedRedDiarrheaField, 
                           deathRedDiarrheaField, confirmedRedDiarrheaField) &&
            mustBeInferiorOrEqual(confirmedMeaslesField, 
                           deathMeaslesField, confirmedMeaslesField) &&
            mustBeInferiorOrEqual(confirmedYellowFeverField, 
                           deathYellowFeverField, confirmedYellowFeverField) &&
            mustBeInferiorOrEqual(confirmedNeonatalTetanusField, 
                           deathNeonatalTetanusField, confirmedNeonatalTetanusField) &&
            mustBeInferiorOrEqual(confirmedMeningitisField, 
                           deathMeningitisField, confirmedMeningitisField) &&
            mustBeInferiorOrEqual(confirmedRabiesField, 
                           deathRabiesField, confirmedRabiesField) &&
            mustBeInferiorOrEqual(confirmedacuteMeaslesDiarrheaField, 
                           deathacuteMeaslesDiarrheaField, confirmedacuteMeaslesDiarrheaField) &&
            mustBeInferiorOrEqual(confirmedOtherNotifiableDiseaseField,
                           deathOtherNotifiableDiseaseField, confirmedOtherNotifiableDiseaseField) &&
            checkDateIsNotFriday(datePicker, labelDate);
        return isEnsureDataCoherence;
    }
    
    public String buildSMSText() {
        return datePicker.getDayOfMonth() + Constants.DATE_SPACER +
               datePicker.getMonth() + 1 + Constants.DATE_SPACER +
               datePicker.getYear() + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedEbolaField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathEbolaField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedAcuteFlaccidParalysisField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathAcuteFlaccidParalysisField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedInfluenzaAH1n1Field)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathInfluenzaAH1n1Field)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedCholeraField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathCholeraField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedRedDiarrheaField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathRedDiarrheaField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedMeaslesField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathMeaslesField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedYellowFeverField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathYellowFeverField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedNeonatalTetanusField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathNeonatalTetanusField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedMeningitisField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathMeningitisField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedRabiesField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathRabiesField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedacuteMeaslesDiarrheaField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathacuteMeaslesDiarrheaField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(deathOtherNotifiableDiseaseField)) + Constants.SPACER +
               Constants.stringFromReport(integerFromField(confirmedOtherNotifiableDiseaseField));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // required for settings to work
        getMenuInflater().inflate(R.menu.smir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Android HW button for settings
        switch (item.getItemId()) {
            case R.id.menu_alert:
                Intent a = new Intent(SMIRHome.this, SMIRAlert.class);
                startActivity(a);
                break;
        }
        return true;
    }
}
