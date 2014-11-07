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
        Boolean u59o6_is_complete = false;
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
        Boolean o59_is_complete = false;

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

    protected Boolean atLeastOneIsCmplete(){
        return u59o6_is_complete || o59_is_complete;
    }
    
    protected void resetReportData() {
        NutritionURENASReportData report = NutritionURENASReportData.get();
        report.u59o6_is_complete = false;
        report.o59_is_complete = false;
        report.save();
    }
}
