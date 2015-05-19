package com.yeleman.malaria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

/**
 * Created by fad on 19/05/15.
 */
public class MalariaCPNSPReport extends MalariaForm {
    private final static String TAG = Constants.getLogTag("MalariaCPNSPReport");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malaria_cpn_sp);
        setTitle(String.format(getString(R.string.sub_app_name_malaria),
                getString(R.string.malaria_cpn_sp_label)));
        Log.d(TAG, "onCreate MalariaCPNSPReport");
        seputUI();
    }

    private void seputUI() {
        Log.d(TAG, "setupUI MalariaCPNSPReport");

        anc1Field = (EditText) findViewById(R.id.anc1Field);
        sp1Field = (EditText) findViewById(R.id.sp1Field);
        sp2Field = (EditText) findViewById(R.id.sp2Field);
        setupInvalidInputChecks();

        MalariaReportData report = MalariaReportData.get();
        if (report.cpn_sp_is_complete) {
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
                // save data to DB
                storeReportData();
                finish();
            }
        });
    }

    protected boolean checkInputsAndCoherence() {
        // remove focus so to remove
        removeFocusFromFields();

        if (!ensureValidInputs(true)) {
            Log.d(TAG, "Invalid inputs");
            return false;
        }
        Log.i(TAG, "data looks good");
        return true;
    }

    protected void storeReportData() {
        Log.d(TAG, "storeReportData");

        MalariaReportData report = MalariaReportData.get();
        report.updateMetaData();

        report.malaria_total_anc_1 = integerFromField(anc1Field);
        report.malaria_total_sp_1 = integerFromField(sp1Field);
        report.malaria_total_sp_2 = integerFromField(sp2Field);
        report.cpn_sp_is_complete = true;
        report.safeSave();
        Log.d(TAG, "storeReportData -- end");
    }

    protected void restoreReportData() {
        Log.d(TAG, "restoreReportData");
        MalariaReportData report = MalariaReportData.get();
        setTextOnField(anc1Field, report.malaria_total_anc_1);
        setTextOnField(sp1Field, report.malaria_total_sp_1);
        setTextOnField(sp2Field, report.malaria_total_sp_2);
    }

    public void setupInvalidInputChecks() {

        setAssertPositiveInteger(anc1Field);
        setAssertPositiveInteger(sp1Field);
        setAssertPositiveInteger(sp2Field);
    }

    protected boolean ensureDataCoherence() {
        return true;
    };


}
