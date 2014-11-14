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
        return urenamReport.atLeastOneIsComplete() ||
               urenasReport.atLeastOneIsComplete() ||
               ureniReport.atLeastOneIsComplete() ||
               inputsReport.atLeastOneIsComplete();
    }

    protected void resetReportData() {
        urenamReport.resetReportData();
        urenasReport.resetReportData();
        ureniReport.resetReportData();
        inputsReport.resetReportData();
    }

    public String buildSMSText() {
        String smsText = "";

        if (has_urenam) {
            smsText += urenamReport.buildSMSText();
        } else {
            smsText += "-";
        }
        smsText +=  Constants.SPACER;

        if (has_urenas) {
            smsText += urenasReport.buildSMSText();
        } else {
            smsText += "-";
        }
        smsText +=  Constants.SPACER;

        if (has_ureni) {
            smsText += ureniReport.buildSMSText();
        } else {
            smsText += "-";
        }
        smsText +=  Constants.SPACER + inputsReport.buildSMSText();
        return smsText;
    }

    public void updateUren(boolean has_urenam, boolean has_urenas, boolean has_ureni) {
        this.has_urenam = has_urenam;
        this.has_urenas = has_urenas;
        this.has_ureni = has_ureni;
        this.save();
    }
    // Start
    protected int totalStartMURENI() {
        return ureniReport.totalStartM();
    }
    protected int totalStartFURENI() {
        return ureniReport.totalStartF();
    }
    protected int totalStartURENI() {
        return totalStartFURENI() + totalStartMURENI();
    }
    protected int totalStartMURENAMAndRENAS() {
        return urenasReport.totalStartM() + urenamReport.totalStartM();
    }
    protected int totalStartFURENAMAndRENAS() {
        return urenasReport.totalStartF() + urenamReport.totalStartF();
    }
    protected int totalStartURENAMAndRENAS() {
        return totalStartMURENAMAndRENAS() + totalStartFURENAMAndRENAS();
    }
    //  In
    protected int totalInMURENI() {
        return ureniReport.totalInM();
    }
    protected int totalInFURENI() {
        return ureniReport.totalInF();
    }    
    protected int totalInURENI() {
        return totalInMURENI() + totalInFURENI();
    }
    protected int totalInMURENAMAndRENAS() {
        return urenasReport.totalInM() + urenamReport.totalInM();
    }
    protected int totalInFURENAMAndRENAS() {
        return urenasReport.totalInF() + urenamReport.totalInF();
    }
    protected int totalInURENAMAndRENAS() {
        return totalInFURENAMAndRENAS() + totalInMURENAMAndRENAS();
    }
    // Out
    protected int totalOutMURENI() {
        return ureniReport.totalOutM();
    }
    protected int totalOutFURENI() {
        return ureniReport.totalOutF();
    }
    protected int totalOutURENI() {
        return totalOutMURENI() + totalOutFURENI();
    }
    protected int totalOutMURENAMAndRENAS() {
        return urenasReport.totalOutM() + urenamReport.totalOutM();
    }
    protected int totalOutFURENAMAndRENAS() {
        return urenasReport.totalOutF() + urenamReport.totalOutF();
    }

    protected int totalOutURENAMAndRENAS() {
        return totalOutMURENAMAndRENAS() + totalOutFURENAMAndRENAS();
    }
    // End
    protected int totalEndMURENI() {
        return ureniReport.totalEndM();
    }
    protected int totalEndFURENI() {
        return ureniReport.totalEndF();
    }
    protected int totalEndURENI() {
        return totalEndFURENI() + totalEndMURENI();
    }
    protected int totalEndMURENAMAndRENAS() {
        return urenasReport.totalEndM() + urenamReport.totalEndM();
    }
    protected int totalEndFURENAMAndRENAS() {
        return urenasReport.totalEndF() + urenamReport.totalEndF();
    }
    protected int totalEndURENAMAndRENAS() {
        return totalEndMURENAMAndRENAS() + totalEndFURENAMAndRENAS();
    }
}
