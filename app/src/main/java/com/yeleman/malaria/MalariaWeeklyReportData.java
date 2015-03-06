package com.yeleman.malaria;

import android.util.Log;

import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.ReportData;

/**
 * Created by fad on 10/02/15.
 */
public class MalariaWeeklyReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            MalariaWeeklyReportData.class.getCanonicalName());
    
    int u5_total_confirmed_malaria_cases_d1 = -1;
    int o5_total_confirmed_malaria_cases_d1 = -1;
    int pw_total_confirmed_malaria_cases_d1 = -1;
    int u5_total_confirmed_malaria_cases_d2 = -1;
    int o5_total_confirmed_malaria_cases_d2 = -1;
    int pw_total_confirmed_malaria_cases_d2 = -1;
    int u5_total_confirmed_malaria_cases_d3 = -1;
    int o5_total_confirmed_malaria_cases_d3 = -1;
    int pw_total_confirmed_malaria_cases_d3 = -1;
    int u5_total_confirmed_malaria_cases_d4 = -1;
    int o5_total_confirmed_malaria_cases_d4 = -1;
    int pw_total_confirmed_malaria_cases_d4 = -1;
    int u5_total_confirmed_malaria_cases_d5 = -1;
    int o5_total_confirmed_malaria_cases_d5 = -1;
    int pw_total_confirmed_malaria_cases_d5 = -1;
    int u5_total_confirmed_malaria_cases_d6 = -1;
    int o5_total_confirmed_malaria_cases_d6 = -1;
    int pw_total_confirmed_malaria_cases_d6 = -1;
    int u5_total_confirmed_malaria_cases_d7 = -1;
    int o5_total_confirmed_malaria_cases_d7 = -1;
    int pw_total_confirmed_malaria_cases_d7 = -1;
    boolean is_complete = false;
    
    public MalariaWeeklyReportData() {}

    public static MalariaWeeklyReportData get() {
        MalariaWeeklyReportData report = getUniqueRecord(MalariaWeeklyReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new MalariaWeeklyReportData();
            report.safeSave();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    @Override
    protected String buildName() {
        return "Hebdo Malaria";
    }

    protected Boolean isComplete(){
        return is_complete;
    }

    protected Boolean atLeastOneIsComplete(){
        return is_complete;
    }

    protected void resetReportData() {
        this.is_complete = false;
        this.safeSave();
    }

    public String buildSMSText() {
        return Constants.stringFromReport(u5_total_confirmed_malaria_cases_d1) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d1) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d1) + Constants.SHARP_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases_d2) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d2) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d2) + Constants.SHARP_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases_d3) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d3) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d3) + Constants.SHARP_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases_d4) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d4) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d4) + Constants.SHARP_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases_d5) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d5) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d5) + Constants.SHARP_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases_d6) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d6) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d6) + Constants.SHARP_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases_d7) + Constants.SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases_d7) + Constants.SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases_d7);
    }
}
