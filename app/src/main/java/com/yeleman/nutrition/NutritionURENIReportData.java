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
        Boolean u6_is_complete = false;
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
        Boolean u59o6_is_complete = false;
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
        Boolean o59_is_complete = false;

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

    protected Boolean atLeastOneIsCmplete(){
        return o59_is_complete || o59_is_complete || u6_is_complete;
    }

    protected void resetReportData() {
        NutritionURENIReportData report = NutritionURENIReportData.get();
        report.u59o6_is_complete = false;
        report.o59_is_complete = false;
        report.u6_is_complete = false;
        report.save();
    }
}
