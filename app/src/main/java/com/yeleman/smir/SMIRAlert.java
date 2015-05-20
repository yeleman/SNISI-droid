package com.yeleman.smir;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;


public class SMIRAlert extends CheckedFormActivity {
    private final static String TAG = Constants.getLogTag("SMIRAlert");

    private Spinner spinner;
    private EditText inputCase;
    private EditText inputConfirmed;
    private EditText inputDeath;
    protected Button  submitButton;

    private static final String[] order_diseases = {
            Constants.ebola, Constants.acute_flaccid_paralysis,
            Constants.influenza_a_h1n1, Constants.cholera,
            Constants.red_diarrhea, Constants.measles, Constants.yellow_fever,
            Constants.neonatal_tetanus, Constants.meningitis,
            Constants.rabies, Constants.acute_measles_diarrhea,
            Constants.other_notifiable_disease
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(String.format(getString(R.string.sub_app_name_smir),
                getString(R.string.smir_alert)));
        setContentView(R.layout.smir_alert);

        setupSMSReceiver();
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI SMIRAlert");

        spinner = (Spinner) findViewById(R.id.spinner);
        inputCase = (EditText) findViewById(R.id.inputCase);
        inputConfirmed = (EditText) findViewById(R.id.inputConfirmed);
        inputDeath = (EditText) findViewById(R.id.inputDeath);

        List<String> list = new ArrayList<String>();
        list.add("EBOLA");
        list.add("PFA");
        list.add("Grippe A H1N1");
        list.add("Choléra");
        list.add("Diarrhée rouge");
        list.add("Rougeole");
        list.add("Fièvre jaune");
        list.add("TNN");
        list.add("Méningite");
        list.add("Rage");
        list.add("Diarrhée sévère rougeole");
        list.add("Autre MADO");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

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
                requestPasswordAndTransmitSMS(activity, "Alert",
                        Constants.SMIR_ALERT, buildSMSText());
            }
        });
    }
    
    protected void setupInvalidInputChecks() {
        setAssertPositiveInteger(inputCase);
        setAssertPositiveInteger(inputConfirmed);
        setAssertPositiveInteger(inputDeath);
    }
    
    protected boolean ensureDataCoherence(){
        boolean isEnsureDataCoherence;
        isEnsureDataCoherence = mustBeInferiorOrEqual(inputCase,
                inputConfirmed, inputCase) &&
                mustBeInferiorOrEqual(inputCase, inputDeath, inputConfirmed) &&
                allIsNotZero(inputCase, inputConfirmed, inputDeath);
              
    return isEnsureDataCoherence;
    }

    public String buildSMSText() {
      return String.valueOf(order_diseases[spinner.getSelectedItemPosition()]) + Constants.SPACER +
             Constants.stringFromReport(integerFromField(inputCase)) + Constants.SPACER +
             Constants.stringFromReport(integerFromField(inputConfirmed)) + Constants.SPACER +
             Constants.stringFromReport(integerFromField(inputDeath));
    }
}
