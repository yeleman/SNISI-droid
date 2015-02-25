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
        setTitle(String.format(getString(R.string.sub_app_name_malaria),
                getString(R.string.malaria_stockout_label)));
        Log.d(TAG, "onCreate MalariaStockoutReport");
        seputUI();
    }

    public void seputUI(){
        Log.d(TAG, "setupUI MalariaStockoutReport");

        actChildrenLabel = (TextView) findViewById(R.id.actChildrenLabel);
        actChildrenLabel.setFocusableInTouchMode(true);
        actChildrenLabel.setFocusable(true);
        actYouthLabel = (TextView) findViewById(R.id.actYouthLabel);
        actYouthLabel.setFocusableInTouchMode(true);
        actYouthLabel.setFocusable(true);
        adultLabel = (TextView) findViewById(R.id.adultLabel);
        adultLabel.setFocusableInTouchMode(true);
        adultLabel.setFocusable(true);
        artemetherLabel = (TextView) findViewById(R.id.artemetherLabel);
        artemetherLabel.setFocusableInTouchMode(true);
        artemetherLabel.setFocusable(true);
        quinineLabel = (TextView) findViewById(R.id.quinineLabel);
        quinineLabel.setFocusableInTouchMode(true);
        quinineLabel.setFocusable(true);
        serumLabel = (TextView) findViewById(R.id.serumLabel);
        serumLabel.setFocusableInTouchMode(true);
        serumLabel.setFocusable(true);
        bednetLabel = (TextView) findViewById(R.id.bednetLabel);
        bednetLabel.setFocusableInTouchMode(true);
        bednetLabel.setFocusable(true);
        rdtLabel = (TextView) findViewById(R.id.rdtLabel);
        rdtLabel.setFocusableInTouchMode(true);
        rdtLabel.setFocusable(true);
        spLabel = (TextView) findViewById(R.id.spLabel);
        spLabel.setFocusableInTouchMode(true);
        spLabel.setFocusable(true);
        anc1Label = (TextView) findViewById(R.id.anc1Label);
        anc1Label.setFocusableInTouchMode(true);
        anc1Label.setFocusable(true);
        sp1Label = (TextView) findViewById(R.id.sp1Label);
        sp1Label.setFocusableInTouchMode(true);
        sp1Label.setFocusable(true);
        sp2Label = (TextView) findViewById(R.id.sp2Label);
        sp2Label.setFocusableInTouchMode(true);
        sp2Label.setFocusable(true);

        actChildrenYesField = (RadioButton) findViewById(R.id.actChildrenYesField);
        actChildrenNoField = (RadioButton) findViewById(R.id.actChildrenNoField);
        actYouthYesField = (RadioButton) findViewById(R.id.actYouthYesField);
        actYouthNoField = (RadioButton) findViewById(R.id.actYouthNoField);
        adultYesField = (RadioButton) findViewById(R.id.adultYesField);
        adultNoField = (RadioButton) findViewById(R.id.adultNoField);
        artemetherYesField = (RadioButton) findViewById(R.id.artemetherYesField);
        artemetherNoField = (RadioButton) findViewById(R.id.artemetherNoField);
        quinineYesField = (RadioButton) findViewById(R.id.quinineYesField);
        quinineNoField = (RadioButton) findViewById(R.id.quinineNoField);
        serumYesField = (RadioButton) findViewById(R.id.serumYesField);
        serumNoField = (RadioButton) findViewById(R.id.serumNoField);
        bednetYesField = (RadioButton) findViewById(R.id.bednetYesField);
        bednetNoField = (RadioButton) findViewById(R.id.bednetNoField);
        rdtYesField = (RadioButton) findViewById(R.id.rdtYesField);
        rdtNoField = (RadioButton) findViewById(R.id.rdtNoField);
        spYesField = (RadioButton) findViewById(R.id.spYesField);
        spNoField = (RadioButton) findViewById(R.id.spNoField);
        anc1YesField = (RadioButton) findViewById(R.id.anc1YesField);
        anc1NoField = (RadioButton) findViewById(R.id.anc1NoField);
        sp1YesField = (RadioButton) findViewById(R.id.sp1YesField);
        sp1NoField = (RadioButton) findViewById(R.id.sp1NoField);
        sp2YesField = (RadioButton) findViewById(R.id.sp2YesField);
        sp2NoField = (RadioButton) findViewById(R.id.sp2NoField);

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
        if (!assertAtLeastOneSelected(actChildrenYesField, actChildrenNoField, actChildrenLabel) ||
            !assertAtLeastOneSelected(actYouthYesField, actYouthNoField, actYouthLabel) ||
            !assertAtLeastOneSelected(adultYesField, adultNoField, adultLabel) ||
            !assertAtLeastOneSelected(artemetherYesField, artemetherNoField, artemetherLabel) ||
            !assertAtLeastOneSelected(quinineYesField, quinineNoField, quinineLabel) ||
            !assertAtLeastOneSelected(serumYesField, serumNoField, serumLabel) ||
            !assertAtLeastOneSelected(bednetYesField, bednetNoField, bednetLabel) ||
            !assertAtLeastOneSelected(rdtYesField, rdtNoField, rdtLabel) ||
            !assertAtLeastOneSelected(spYesField, spNoField, spLabel) ||
            !assertAtLeastOneSelected(anc1YesField, anc1NoField, anc1Label) ||
            !assertAtLeastOneSelected(sp1YesField, sp1NoField, sp1Label) ||
            !assertAtLeastOneSelected(sp2YesField, sp2NoField, sp2Label)) {
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

        report.malaria_stockout_act_children = integerFromRadioButtons(actChildrenYesField, actChildrenNoField);
        report.malaria_stockout_act_youth = integerFromRadioButtons(actYouthYesField, actYouthNoField);
        report.malaria_stockout_act_adult = integerFromRadioButtons(adultYesField, adultNoField);
        report.malaria_stockout_artemether = integerFromRadioButtons(artemetherYesField, artemetherNoField);
        report.malaria_stockout_quinine = integerFromRadioButtons(quinineYesField, quinineNoField);
        report.malaria_stockout_serum = integerFromRadioButtons(serumYesField, serumNoField);
        report.malaria_stockout_bednet = integerFromRadioButtons(bednetYesField, bednetNoField);
        report.malaria_stockout_rdt = integerFromRadioButtons(rdtYesField, rdtNoField);
        report.malaria_stockout_sp = integerFromRadioButtons(spYesField, spNoField);
        report.malaria_total_anc_1 = integerFromRadioButtons(anc1YesField, anc1NoField);
        report.malaria_total_sp_1 = integerFromRadioButtons(sp1YesField, sp1NoField);
        report.malaria_total_sp_2 = integerFromRadioButtons(sp2YesField, sp2NoField);
        report.stockout_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();
        checkRadioButtonFromReportData(actChildrenYesField, actChildrenNoField, report.malaria_stockout_act_children);
        checkRadioButtonFromReportData(actYouthYesField, actYouthNoField, report.malaria_stockout_act_youth);
        checkRadioButtonFromReportData(adultYesField, adultNoField, report.malaria_stockout_act_adult);
        checkRadioButtonFromReportData(artemetherYesField, artemetherNoField, report.malaria_stockout_artemether);
        checkRadioButtonFromReportData(quinineYesField, quinineNoField, report.malaria_stockout_quinine);
        checkRadioButtonFromReportData(serumYesField, serumNoField, report.malaria_stockout_serum);
        checkRadioButtonFromReportData(bednetYesField, bednetNoField, report.malaria_stockout_bednet);
        checkRadioButtonFromReportData(rdtYesField, rdtNoField, report.malaria_stockout_rdt);
        checkRadioButtonFromReportData(spYesField, spNoField, report.malaria_stockout_sp);
        checkRadioButtonFromReportData(anc1YesField, anc1NoField, report.malaria_total_anc_1);
        checkRadioButtonFromReportData(sp1YesField, sp1NoField, report.malaria_total_sp_1);
        checkRadioButtonFromReportData(sp2YesField, sp2NoField, report.malaria_total_sp_2);
    }

    public void setupInvalidInputChecks() {
        // ACT CHILDREN
        actChildrenYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(actChildrenLabel));
        actChildrenNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(actChildrenLabel));

        actYouthYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(actYouthLabel));
        actYouthNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(actYouthLabel));
        adultYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(adultLabel));
        adultNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(adultLabel));
        artemetherYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(artemetherLabel));
        artemetherNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(artemetherLabel));
        quinineYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(quinineLabel));
        quinineNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(quinineLabel));
        serumYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(serumLabel));
        serumNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(serumLabel));
        bednetYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(bednetLabel));
        bednetNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(bednetLabel));
        rdtYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(rdtLabel));
        rdtNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(rdtLabel));
        spYesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(spLabel));
        spNoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(spLabel));
        anc1YesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(anc1Label));
        anc1NoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(anc1Label));
        sp1YesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(sp1Label));
        sp1NoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(sp1Label));
        sp2YesField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(sp2Label));
        sp2NoField.setOnCheckedChangeListener(Constants.getResetTextViewCheckListener(sp2Label));

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
        if (reportDataValue == 1){
            radioButtonYes.setChecked(true);
        }
        if (reportDataValue == 0) {
            radioButtonNo.setChecked(true);
        }
    }
}
