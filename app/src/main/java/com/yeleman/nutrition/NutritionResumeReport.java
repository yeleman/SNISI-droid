package com.yeleman.nutrition;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

import org.w3c.dom.Text;

/**
 * Created by fad on 14/11/14.
 */
public class NutritionResumeReport extends Activity {
    private final static String TAG = Constants.getLogTag("NutritionResumeReport");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_resume_report);
        Log.d(TAG, "onCreate NutritionResumeReport");
        setupUI();
    }

    protected void setupUI() {
        Log.d(TAG, "setupUI NutritionResumeReport");
        NutritionMonthlyReportData report = NutritionMonthlyReportData.get();

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // URENAM + URENAS
        ViewGroup urenam_and_urenas_parent = (ViewGroup) findViewById(R.id.tableURENAMAndURENAS);
        View inflated_urenam_and_urenas = inflater.inflate(R.layout.nutrition_resume_unit, urenam_and_urenas_parent);
        TextView startTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.startTotalsValue);
        startTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalStartURENAMAndRENAS()));
        TextView startTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.startTotalValueF);
        startTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalStartFURENAMAndRENAS()));
        TextView startTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.startTotalValueM);
        startTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalStartMURENAMAndRENAS()));

        TextView admissionTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.admissionTotalsValue);
        admissionTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalInURENAMAndRENAS()));
        TextView admissionTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.admissionTotalValueF);
        admissionTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalInFURENAMAndRENAS()));
        TextView admissionTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.admissionTotalValueM);
        admissionTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalInMURENAMAndRENAS()));

        TextView outTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.outTotalsValue);
        outTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalOutURENAMAndRENAS()));
        TextView outTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.outTotalValueF);
        outTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalOutFURENAMAndRENAS()));
        TextView outTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.outTotalValueM);
        outTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalOutMURENAMAndRENAS()));

        TextView endTotalsValueUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.endTotalsValue);
        endTotalsValueUrenasAndURENAM.setText(String.valueOf(report.totalEndURENAMAndRENAS()));
        TextView endTotalValueFUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.endTotalValueF);
        endTotalValueFUrenasAndURENAM.setText(String.valueOf(report.totalEndFURENAMAndRENAS()));
        TextView endTotalValueMUrenasAndURENAM = (TextView) inflated_urenam_and_urenas.findViewById(R.id.endTotalValueM);
        endTotalValueMUrenasAndURENAM.setText(String.valueOf(report.totalEndMURENAMAndRENAS()));

        // URENI
        ViewGroup ureni_parent = (ViewGroup) findViewById(R.id.tableURENI);
        View inflated_ureni = inflater.inflate(R.layout.nutrition_resume_unit, ureni_parent);
        TextView ureniLabel = (TextView) inflated_ureni.findViewById(R.id.titleLabelUREN);
        ureniLabel.setText("URENI");
        TextView startTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.startTotalsValue);
        startTotalsValueUreni.setText(String.valueOf(report.totalStartURENI()));
        TextView startTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.startTotalValueF);
        startTotalValueFUreni.setText(String.valueOf(report.totalStartFURENI()));
        TextView startTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.startTotalValueM);
        startTotalValueMUreni.setText(String.valueOf(report.totalStartMURENI()));

        TextView admissionTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.admissionTotalsValue);
        admissionTotalsValueUreni.setText(String.valueOf(report.totalInURENI()));
        TextView admissionTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.admissionTotalValueF);
        admissionTotalValueFUreni.setText(String.valueOf(report.totalInFURENI()));
        TextView admissionTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.admissionTotalValueM);
        admissionTotalValueMUreni.setText(String.valueOf(report.totalInMURENI()));

        TextView outTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.outTotalsValue);
        outTotalsValueUreni.setText(String.valueOf(report.totalOutURENI()));
        TextView outTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.outTotalValueF);
        outTotalValueFUreni.setText(String.valueOf(report.totalOutFURENI()));
        TextView outTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.outTotalValueM);
        outTotalValueMUreni.setText(String.valueOf(report.totalOutMURENI()));

        TextView endTotalsValueUreni = (TextView) inflated_ureni.findViewById(R.id.endTotalsValue);
        endTotalsValueUreni.setText(String.valueOf(report.totalEndURENI()));
        TextView endTotalValueFUreni = (TextView) inflated_ureni.findViewById(R.id.endTotalValueF);
        endTotalValueFUreni.setText(String.valueOf(report.totalEndFURENI()));
        TextView endTotalValueMUreni = (TextView) inflated_ureni.findViewById(R.id.endTotalValueM);
        endTotalValueMUreni.setText(String.valueOf(report.totalEndMURENI()));

    }
}
