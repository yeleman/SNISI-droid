package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritionMonthlyReportData extends ReportData {

    NutritionURENAMReportData urenamReport = NutritionURENAMReportData.get();
    NutritionURENASReportData urenasReport = NutritionURENASReportData.get();
    NutritionURENIReportData ureniReport = NutritionURENIReportData.get();
    NutritionInputsReportData inputsReport = NutritionInputsReportData.get();

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionMonthlyReportData.class.getSimpleName());

    boolean has_urenam = false;
    boolean has_urenas = false;
    boolean has_ureni = false;

    public NutritionMonthlyReportData() {}

    public static NutritionMonthlyReportData get() {
        NutritionMonthlyReportData report = getUniqueRecord(NutritionMonthlyReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionMonthlyReportData();
            report.updateMetaData();
            report.save();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    @Override
    protected String buildName() {
        return "Nut Monthy";
    }

    protected Boolean isComplete(){


        Boolean urenam, urenas, ureni, inputs;
        urenam = urenas = ureni = true;

        if (has_urenam) { urenam = urenamReport.isComplete(); }
        if (has_urenas) { urenas = urenasReport.isComplete(); }
        if (has_ureni) { ureni = ureniReport.isComplete(); }
        inputs = inputsReport.isComplete();

        Log.d(TAG, String.valueOf(urenam));
        Log.d(TAG, String.valueOf(urenas));
        Log.d(TAG, String.valueOf(ureni));
        Log.d(TAG, String.valueOf(inputs));

        return urenam && urenas && ureni && inputs;
    }

    protected Boolean atLeastOneIsComplete(){
        return urenamReport.atLeastOneIsCmplete() ||
               urenasReport.atLeastOneIsCmplete() ||
               ureniReport.atLeastOneIsCmplete() ||
               inputsReport.atLeastOneIsCmplete();
    }

    protected void resetReportData() {
        urenamReport.resetReportData();
        urenasReport.resetReportData();
        ureniReport.resetReportData();
        inputsReport.resetReportData();
    }

    public String buildSMSText() {
        return urenamReport.buildSMSText() + Constants.SPACER +
               urenasReport.buildSMSText() + Constants.SPACER +
               ureniReport.buildSMSText() + Constants.SPACER +
               inputsReport.buildSMSText();
    }

    public void updateUren(boolean has_urenam, boolean has_urenas, boolean has_ureni) {
        this.has_urenam = has_urenam;
        this.has_urenas = has_urenas;
        this.has_ureni = has_ureni;
        this.save();
    }
}
