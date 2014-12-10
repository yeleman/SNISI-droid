package com.yeleman.malaria;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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

        textView = (TextView) findViewById(R.id.children);
        textView.setFocusableInTouchMode(true);
        textView.setFocusable(true);
        radioButtonYesChildren = (RadioButton) findViewById(R.id.radioButtonYesChildren);
        radioButtonNoChildren = (RadioButton) findViewById(R.id.radioButtonNoChildren);

        actYouthLabel = (TextView) findViewById(R.id.actYouthLabel);
        actYouthLabel.setFocusableInTouchMode(true);
        actYouthLabel.setFocusable(true);
        // actYouthYesField
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

        setupInvalidInputChecks();


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

        // if aucun des deux checked
        if (!assertAtLeastOneSelected(radioButtonYesChildren, radioButtonNoChildren, textView) ||
            !assertAtLeastOneSelected(radioButtonYesYouth, radioButtonNoYouth, actYouthLabel) ||
            !assertAtLeastOneSelected(radioButtonYesAdult, radioButtonNoAdult, textView) ||
            !assertAtLeastOneSelected(radioButtonYesArtemether, radioButtonNoArtemether, textView) ||
            !assertAtLeastOneSelected(radioButtonYesQuinine, radioButtonNoQuinine, textView) ||
            !assertAtLeastOneSelected(radioButtonYesSurum, radioButtonNoSurum, textView) ||
            !assertAtLeastOneSelected(radioButtonYesBednet, radioButtonNoBednet, textView) ||
            !assertAtLeastOneSelected(radioButtonYesRdt, radioButtonNoRdt, textView) ||
            !assertAtLeastOneSelected(radioButtonYesSp, radioButtonNoSp, textView) ||
            !assertAtLeastOneSelected(radioButtonYesAnc1, radioButtonNoAnc1, textView) ||
            !assertAtLeastOneSelected(radioButtonYesSp1, radioButtonNoSp1, textView) ||
            !assertAtLeastOneSelected(radioButtonYesSp2, radioButtonNoSp2, textView)) {
                Log.d(TAG, "Invalid inputs, radio button check missing");
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
        report.malaria_stockout_act_children = integerFromRadioButtons(radioButtonYesChildren, radioButtonNoChildren);
        report.malaria_stockout_act_youth = integerFromRadioButtons(radioButtonYesYouth, radioButtonNoYouth);
        report.malaria_stockout_act_adult = integerFromRadioButtons(radioButtonYesAdult, radioButtonNoAdult);
        report.malaria_stockout_artemether = integerFromRadioButtons(radioButtonYesArtemether, radioButtonNoArtemether);
        report.malaria_stockout_quinine = integerFromRadioButtons(radioButtonYesQuinine, radioButtonNoQuinine);
        report.malaria_stockout_serum = integerFromRadioButtons(radioButtonYesSurum, radioButtonNoSurum);
        report.malaria_stockout_bednet = integerFromRadioButtons(radioButtonYesBednet, radioButtonNoBednet);
        report.malaria_stockout_rdt = integerFromRadioButtons(radioButtonYesRdt, radioButtonNoRdt);
        report.malaria_stockout_sp = integerFromRadioButtons(radioButtonYesSp, radioButtonNoSp);
        report.malaria_total_anc_1 = integerFromRadioButtons(radioButtonYesAnc1, radioButtonNoAnc1);
        report.malaria_total_sp_1 = integerFromRadioButtons(radioButtonYesSp1, radioButtonNoSp1);
        report.malaria_total_sp_2 = integerFromRadioButtons(radioButtonYesSp2, radioButtonNoSp2);
        report.stockout_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();
        checkRadioButtonFromReportData(radioButtonYesChildren, radioButtonNoChildren, report.malaria_stockout_act_children);
        checkRadioButtonFromReportData(radioButtonYesYouth, radioButtonNoYouth, report.malaria_stockout_act_youth);
        checkRadioButtonFromReportData(radioButtonYesAdult, radioButtonNoAdult, report.malaria_stockout_act_adult);
        checkRadioButtonFromReportData(radioButtonYesArtemether, radioButtonNoArtemether, report.malaria_stockout_artemether);
        checkRadioButtonFromReportData(radioButtonYesQuinine, radioButtonNoQuinine, report.malaria_stockout_quinine);
        checkRadioButtonFromReportData(radioButtonYesSurum, radioButtonNoSurum, report.malaria_stockout_serum);
        checkRadioButtonFromReportData(radioButtonYesBednet, radioButtonNoBednet, report.malaria_stockout_bednet);
        checkRadioButtonFromReportData(radioButtonYesRdt, radioButtonNoRdt, report.malaria_stockout_rdt);
        checkRadioButtonFromReportData(radioButtonYesSp, radioButtonNoSp, report.malaria_stockout_sp);
        checkRadioButtonFromReportData(radioButtonYesAnc1, radioButtonNoAnc1, report.malaria_total_anc_1);
        checkRadioButtonFromReportData(radioButtonYesSp1, radioButtonNoSp1, report.malaria_total_sp_1);
        checkRadioButtonFromReportData(radioButtonYesSp2, radioButtonNoSp2, report.malaria_total_sp_2);
    }
    
    public void setupInvalidInputChecks() {
        // ACT CHILDREN
        radioButtonNoChildren.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(textView));
        radioButtonYesChildren.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(textView));

        radioButtonNoYouth.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(actYouthLabel));
        radioButtonYesYouth.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(actYouthLabel));
    }

    protected int integerFromRadioButtons(RadioButton buttonYes, RadioButton buttonNo) {
        if (buttonNo.isChecked()) {
            return 0;
        }
        if (buttonYes.isChecked()) {
            return 1;
        }
        return -1;
    }

    protected boolean assertAtLeastOneSelected (RadioButton buttonYes, RadioButton buttonNo, TextView textView) {
        if (integerFromRadioButtons(buttonYes, buttonNo) == -1) {
            textView.setError("requis");
            textView.requestFocus();
            return false;
        }
        return true;
    }

    protected boolean ensureDataCoherence() {
        return true;
    };

    protected void checkRadioButtonFromReportData(RadioButton radioButtonYes,
                                                  RadioButton radioButtonNo,
                                                  int reportDataValue){
        Log.d(TAG, String.valueOf(reportDataValue));
        if (reportDataValue == 1){
            radioButtonYes.setChecked(true);
        }
        if (reportDataValue == 0) {
            radioButtonNo.setChecked(true);
        }
    }
}
