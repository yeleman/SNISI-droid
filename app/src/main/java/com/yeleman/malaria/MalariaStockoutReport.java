package com.yeleman.malaria;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;
import com.yeleman.snisidroid.ReportData;

/**
 * Created by fad on 05/12/14.
 */
public class MalariaStockoutReport extends MalariaForm {
    private final static String TAG = Constants.getLogTag("MalariaStockoutReport");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_stockout_report);
        Log.d(TAG, "onCreate MalariaStockoutReport");
        seputUI();
    }

    public void seputUI(){
        Log.d(TAG, "setupUI MalariaStockoutReport");


        // int r = radioGroupChildren.getMeasuredState();
        
        // View.OnClickListener listener = new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         RadioButton rb = (RadioButton) v;
        //         Toast.makeText(MalariaStockoutReport.this, rb.getText(),
        //                 Toast.LENGTH_SHORT).show();

        //         // get selected radio button from radioGroup
        //         int selectedId = radioGroupChildren.getCheckedRadioButtonId();

        //         // find the radiobutton by returned id
        //         rBNoChildren = (RadioButton) findViewById(selectedId);

        //         Toast.makeText(MalariaStockoutReport.this,
        //                 rBNoChildren.getText(), Toast.LENGTH_SHORT).show();
        //     }
        // };

        // rBNoChildren = (RadioButton) findViewById(R.id.radioButtonNoChildren);
        // rBNoChildren.setOnClickListener(listener);
        // rBYesChildren = (RadioButton) findViewById(R.id.radioButtonYesChildren);
        // rBYesChildren.setOnClickListener(listener);

        radioButtonYesChildren = (RadioButton) findViewById(R.id.radioButtonYesChildren);
        radioButtonYesChildren.setHint("cd");
        radioButtonNoChildren = (RadioButton) findViewById(R.id.radioButtonNoChildren);
        radioButtonYesYouth = (RadioButton) findViewById(R.id.radioButtonYesYouth);
        radioButtonNoYouth = (RadioButton) findViewById(R.id.radioButtonNoYouth);
        radioButtonYesAdult = (RadioButton) findViewById(R.id.radioButtonYesAdult);
        radioButtonNoAdult = (RadioButton) findViewById(R.id.radioButtonNoAdult);
        radioButtonYesArtemether = (RadioButton) findViewById(R.id.radioButtonYesArtemether);
        radioButtonNoArtemether = (RadioButton) findViewById(R.id.radioButtonNoArtemether);
        radioButtonYesQuinine = (RadioButton) findViewById(R.id.radioButtonYesQuinine);
        radioButtonNoQuinine = (RadioButton) findViewById(R.id.radioButtonNoQuinine);
        radioButtonYesSurum = (RadioButton) findViewById(R.id.radioButtonYesSurum);
        radioButtonNoSurum = (RadioButton) findViewById(R.id.radioButtonNoSurum);
        radioButtonYesBednet = (RadioButton) findViewById(R.id.radioButtonYesBednet);
        radioButtonNoBednet = (RadioButton) findViewById(R.id.radioButtonNoBednet);
        radioButtonYesRdt = (RadioButton) findViewById(R.id.radioButtonYesRdt);
        radioButtonNoRdt = (RadioButton) findViewById(R.id.radioButtonNoRdt);
        radioButtonYesSp = (RadioButton) findViewById(R.id.radioButtonYesSp);
        radioButtonNoSp = (RadioButton) findViewById(R.id.radioButtonNoSp);
        radioButtonYesAnc1 = (RadioButton) findViewById(R.id.radioButtonYesAnc1);
        radioButtonNoAnc1 = (RadioButton) findViewById(R.id.radioButtonNoAnc1);
        radioButtonYesSp1 = (RadioButton) findViewById(R.id.radioButtonYesSp1);
        radioButtonNoSp1 = (RadioButton) findViewById(R.id.radioButtonNoSp1);
        radioButtonYesSp2 = (RadioButton) findViewById(R.id.radioButtonYesSp2);
        radioButtonNoSp2 = (RadioButton) findViewById(R.id.radioButtonNoSp2);


        MalariaReportData report = MalariaReportData.get();
        if (report.stockout_is_complete) {
            restoreReportData();
        }
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // ensure data is OK

                if (!checkInputsAndCoherence()) {
                    return;
                }
                //isInvalide();
                // save data to DB
                storeReportData();
                finish();
            }
        });
    }

    protected boolean checkInputsAndCoherence() {
        // remove focus so to remove
        removeFocusFromFields();

        RadioGroup radioGroupChildren = (RadioGroup) findViewById(R.id.radioGroupChildren);
        radioButtonNoChildren.setError("AHHH");
        radioGroupChildren.requestFocus();

        setupInvalidInputChecks();
        // check radio button
        if (!ensureValidInputs(true)) {
            Log.d(TAG, "Invalid inputs");
            return false;
        }
        if (!ensureDataCoherence()) {
            Log.d(TAG, "Not coherent inputs");
            return false;
        }
        Log.i(TAG, "data looks good");
        return true;
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");

        MalariaReportData report = MalariaReportData.get();
        report.updateMetaData();

        report.malaria_stockout_act_children = getValue(radioButtonYesChildren, radioButtonNoChildren);
        report.malaria_stockout_act_youth = getValue(radioButtonYesYouth, radioButtonNoYouth);
        report.malaria_stockout_act_adult = getValue(radioButtonYesAdult, radioButtonNoAdult);
        report.malaria_stockout_artemether = getValue(radioButtonYesArtemether, radioButtonNoArtemether);
        report.malaria_stockout_quinine = getValue(radioButtonYesQuinine, radioButtonNoQuinine);
        report.malaria_stockout_serum = getValue(radioButtonYesSurum, radioButtonNoSurum);
        report.malaria_stockout_bednet = getValue(radioButtonYesBednet, radioButtonNoBednet);
        report.malaria_stockout_rdt = getValue(radioButtonYesRdt, radioButtonNoRdt);
        report.malaria_stockout_sp = getValue(radioButtonYesSp, radioButtonNoSp);
        report.malaria_total_anc_1 = getValue(radioButtonYesAnc1, radioButtonNoAnc1);
        report.malaria_total_sp_1 = getValue(radioButtonYesSp1, radioButtonNoSp1);
        report.malaria_total_sp_2 = getValue(radioButtonYesSp2, radioButtonNoSp2);
        report.stockout_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();

        setCheckOnRadioButton(radioButtonYesChildren, radioButtonNoChildren, report.malaria_stockout_act_children);
        setCheckOnRadioButton(radioButtonYesYouth, radioButtonNoYouth, report.malaria_stockout_act_youth);
        setCheckOnRadioButton(radioButtonYesAdult, radioButtonNoAdult, report.malaria_stockout_act_adult);
        setCheckOnRadioButton(radioButtonYesArtemether, radioButtonNoArtemether, report.malaria_stockout_artemether);
        setCheckOnRadioButton(radioButtonYesQuinine, radioButtonNoQuinine, report.malaria_stockout_quinine);
        setCheckOnRadioButton(radioButtonYesSurum, radioButtonNoSurum, report.malaria_stockout_serum);
        setCheckOnRadioButton(radioButtonYesBednet, radioButtonNoBednet, report.malaria_stockout_bednet);
        setCheckOnRadioButton(radioButtonYesRdt, radioButtonNoRdt, report.malaria_stockout_rdt);
        setCheckOnRadioButton(radioButtonYesSp, radioButtonNoSp, report.malaria_stockout_sp);
        setCheckOnRadioButton(radioButtonYesAnc1, radioButtonNoAnc1, report.malaria_total_anc_1);
        setCheckOnRadioButton(radioButtonYesSp1, radioButtonNoSp1, report.malaria_total_sp_1);
        setCheckOnRadioButton(radioButtonYesSp2, radioButtonNoSp2, report.malaria_total_sp_2);
    }
    
    public void setupInvalidInputChecks() {
        assertAtLeastOneSelected(radioButtonYesChildren, radioButtonNoChildren);
        assertAtLeastOneSelected(radioButtonYesYouth, radioButtonNoYouth);
        assertAtLeastOneSelected(radioButtonYesAdult, radioButtonNoAdult);
        assertAtLeastOneSelected(radioButtonYesArtemether, radioButtonNoArtemether);
        assertAtLeastOneSelected(radioButtonYesQuinine, radioButtonNoQuinine);
        assertAtLeastOneSelected(radioButtonYesSurum, radioButtonNoSurum);
        assertAtLeastOneSelected(radioButtonYesBednet, radioButtonNoBednet);
        assertAtLeastOneSelected(radioButtonYesRdt, radioButtonNoRdt);
        assertAtLeastOneSelected(radioButtonYesSp, radioButtonNoSp);
        assertAtLeastOneSelected(radioButtonYesAnc1, radioButtonNoAnc1);
        assertAtLeastOneSelected(radioButtonYesSp1, radioButtonNoSp1);
        assertAtLeastOneSelected(radioButtonYesSp2, radioButtonNoSp2);
    }

    protected int getValue(RadioButton buttonYes, RadioButton buttonNo) {
        if (buttonNo.isChecked()) {
            return 0;
        }
        if (buttonYes.isChecked()) {
            return 1;
        }
        return -1;
    }

    protected boolean assertAtLeastOneSelected (RadioButton buttonYes, RadioButton buttonNo) {
        if (getValue(buttonYes, buttonNo) == -1) {
            buttonNo.setError("Selectionner au mois un");
            return false;
        }
        return true;
    }

    protected boolean ensureDataCoherence() {
        return true;
    };

    protected void setCheckOnRadioButton(RadioButton radioButtonYes,
                                         RadioButton radioButtonNo, int data){
        Log.d(TAG, String.valueOf(data));
        if (data == 1){
            radioButtonYes.setChecked(true);
        }
        if (data == 0) {
            radioButtonNo.setChecked(true);
        }
    }
}
