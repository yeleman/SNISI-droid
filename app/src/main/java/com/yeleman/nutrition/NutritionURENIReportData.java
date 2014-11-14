package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritionURENIReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionURENIReportData.class.getSimpleName());

        //  0-6 months
        int u6_total_start_m = -1;
        int u6_total_start_f = -1;
        int u6_new_cases = -1;
        int u6_returned = -1;
        int u6_total_in_m = -1;
        int u6_total_in_f = -1;
        int u6_referred = -1;
        int u6_healed = -1;
        int u6_deceased = -1;
        int u6_abandon = -1;
        int u6_not_responding = -1;
        int u6_total_out_m = -1;
        int u6_total_out_f = -1;
        int u6_transferred = -1;
        int u6_total_end_m = -1;
        int u6_total_end_f = -1;
        boolean u6_is_complete = false;
        //  6-59 months
        int u59o6_total_start_m = -1;
        int u59o6_total_start_f = -1;
        int u59o6_new_cases = -1;
        int u59o6_returned = -1;
        int u59o6_total_in_m = -1;
        int u59o6_total_in_f = -1;
        int u59o6_referred = -1;
        int u59o6_healed = -1;
        int u59o6_deceased = -1;
        int u59o6_abandon = -1;
        int u59o6_not_responding = -1;
        int u59o6_total_out_m = -1;
        int u59o6_total_out_f = -1;
        int u59o6_transferred = -1;
        int u59o6_total_end_m = -1;
        int u59o6_total_end_f = -1;
        boolean u59o6_is_complete = false;
        //  Over 59 months
        int o59_total_start_m = -1;
        int o59_total_start_f = -1;
        int o59_new_cases = -1;
        int o59_returned = -1;
        int o59_total_in_m = -1;
        int o59_total_in_f = -1;
        int o59_referred = -1;
        int o59_healed = -1;
        int o59_deceased = -1;
        int o59_abandon = -1;
        int o59_not_responding = -1;
        int o59_total_out_m = -1;
        int o59_total_out_f = -1;
        int o59_transferred = -1;
        int o59_total_end_m = -1;
        int o59_total_end_f = -1;
        boolean o59_is_complete = false;

    public NutritionURENIReportData() {}

    public static NutritionURENIReportData get() {
        NutritionURENIReportData report = getUniqueRecord(NutritionURENIReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionURENIReportData();
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
        return o59_is_complete && o59_is_complete && u6_is_complete;
    }

    protected Boolean atLeastOneIsComplete(){
        return o59_is_complete || o59_is_complete || u6_is_complete;
    }

    protected void resetReportData() {
        this.u59o6_is_complete = false;
        this.o59_is_complete = false;
        this.u6_is_complete = false;
        this.save();
    }

    public String buildSMSText() {
        return Constants.stringFromInteger(u6_total_start_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_start_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_new_cases) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_returned) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_in_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_in_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_referred) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_healed) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_deceased) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_abandon) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_not_responding) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_out_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_out_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_transferred) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_end_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u6_total_end_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_start_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_start_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_new_cases) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_returned) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_in_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_in_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_referred) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_healed) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_deceased) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_abandon) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_not_responding) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_out_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_out_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_transferred) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_end_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(u59o6_total_end_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_start_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_start_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_new_cases) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_returned) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_in_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_in_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_referred) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_healed) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_deceased) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_abandon) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_not_responding) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_out_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_out_f) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_transferred) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_end_m) + Constants.SUB_SPACER + 
               Constants.stringFromInteger(o59_total_end_f);
    }

    protected int totalStartM() {
        return o59_total_start_m +
                u59o6_total_start_m +
                u6_total_start_m;
    }

    protected int totalStartF() {
        return o59_total_start_f +
               u59o6_total_start_f +
               u6_total_start_f;
    }

    protected int totalStart() {
        return totalStartF() + totalStartM();
    }

    protected int totalInM() {
        return o59_total_in_m +
               u59o6_total_in_m +
               u6_total_in_m;
    }
    protected int totalInF() {
        return o59_total_in_f +
               u59o6_total_in_f +
               u6_total_in_f;
    }

    protected int totalIn() {
        return totalInF() + totalInM();
    }

    protected int totalOutM() {
        return o59_total_out_m +
               u59o6_total_out_m +
               u6_total_out_m;
    }

    protected int totalOutF() {
        return o59_total_out_f +
               u59o6_total_out_f +
               u6_total_out_f;
    }

    protected int totalOut() {
        return totalOutF() + totalOutM();
    }

    protected int totalEndM() {
        return o59_total_end_m +
               u59o6_total_end_m +
               u6_total_end_m;
    }
    protected int totalEndF() {
        return o59_total_end_f +
               u59o6_total_end_f +
               u6_total_end_f;
    }

    protected int totalEnd() {
        return totalEndM() + totalEndF();
    }
}
