package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritionURENASReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionURENASReportData.class.getSimpleName());
        
        // 6-59 months
        int u59o6_total_start_m = -1;
        int u59o6_total_start_f = -1;
        int u59o6_new_cases = -1;
        int u59o6_returned = -1;
        int u59o6_total_in_m = -1;
        int u59o6_total_in_f = -1;
        int u59o6_transferred = -1;
        int u59o6_healed = -1;
        int u59o6_deceased = -1;
        int u59o6_abandon = -1;
        int u59o6_not_responding = -1;
        int u59o6_total_out_m = -1;
        int u59o6_total_out_f = -1;
        int u59o6_referred = -1;
        int u59o6_total_end_m = -1;
        int u59o6_total_end_f = -1;
        boolean u59o6_is_complete = false;
        // Pregnant & Breast Feeding Women
        int o59_total_start_m = -1;
        int o59_total_start_f = -1;
        int o59_new_cases = -1;
        int o59_returned = -1;
        int o59_total_in_m = -1;
        int o59_total_in_f = -1;
        int o59_transferred = -1;
        int o59_healed = -1;
        int o59_deceased = -1;
        int o59_abandon = -1;
        int o59_not_responding = -1;
        int o59_total_out_m = -1;
        int o59_total_out_f = -1;
        int o59_referred = -1;
        int o59_total_end_m = -1;
        int o59_total_end_f = -1;
        boolean o59_is_complete = false;

    public NutritionURENASReportData() {}

    public static NutritionURENASReportData get() {
        NutritionURENASReportData report = getUniqueRecord(NutritionURENASReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionURENASReportData();
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
        return u59o6_is_complete && o59_is_complete;
    }

    protected Boolean atLeastOneIsComplete(){
        return u59o6_is_complete || o59_is_complete;
    }
    
    protected void resetReportData() {
        this.u59o6_is_complete = false;
        this.o59_is_complete = false;
        this.save();
    }

    @Override
    public String buildSMSText() {
        return Constants.stringFromInteger(u59o6_total_start_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_new_cases) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_returned) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_in_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_in_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_transferred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_healed) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_deceased) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_abandon) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_not_responding) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_out_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_out_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_end_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o6_total_end_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_start_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_new_cases) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_returned) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_in_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_in_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_transferred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_healed) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_deceased) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_abandon) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_not_responding) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_out_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_out_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_end_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_end_f);
    }

    protected int totalStartM() {
         return Constants.integerFromReport(o59_total_start_m) +
                Constants.integerFromReport(u59o6_total_start_m);
    }

    protected int totalStartF() {
         return Constants.integerFromReport(o59_total_start_f) +
                Constants.integerFromReport(u59o6_total_start_f);
    }

    protected int totalStart() {
         return totalStartF() + totalStartM();
    }

    protected int totalInM() {
         return Constants.integerFromReport(o59_total_in_m) +
                Constants.integerFromReport(u59o6_total_in_m);
    }
    protected int totalInF() {
         return Constants.integerFromReport(o59_total_in_f) +
                Constants.integerFromReport(u59o6_total_in_f);
    }

    protected int totalIn() {
        return totalInF() + totalInM();
    }

    protected int totalOutM() {
         return Constants.integerFromReport(o59_total_out_m) +
                Constants.integerFromReport(u59o6_total_out_m);
    }

    protected int totalOutF() {
         return Constants.integerFromReport(o59_total_out_f) +
                Constants.integerFromReport(u59o6_total_out_f);
    }

    protected int totalOut() {
         return totalOutF() + totalOutM();
    }

    protected int totalEndM() {
        return Constants.integerFromReport(o59_total_end_m) +
               Constants.integerFromReport(u59o6_total_end_m);
    }
    protected int totalEndF() {
        return Constants.integerFromReport(o59_total_end_f) +
               Constants.integerFromReport(u59o6_total_end_f);
    }

    protected int totalEnd() {
        return totalEndM() + totalEndF();
    }
}
