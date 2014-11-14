package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritionURENAMReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionURENAMReportData.class.getSimpleName());

    int u23o6_total_start_m = -1;
    int u23o6_total_start_f = -1;
    int u23o6_new_cases = -1;
    int u23o6_returned = -1;
    int u23o6_total_in_m = -1;
    int u23o6_total_in_f = -1;
    int u23o6_healed = -1;
    int u23o6_deceased = -1;
    int u23o6_abandon = -1;
    int u23o6_not_responding = -1;
    int u23o6_total_out_m = -1;
    int u23o6_total_out_f = -1;
    int u23o6_referred = -1;
    int u23o6_total_end_m = -1;
    int u23o6_total_end_f = -1;
    boolean u23o6_is_complete = false;
    // 23-59 months
    int u59o23_total_start_m = -1;
    int u59o23_total_start_f = -1;
    int u59o23_new_cases = -1;
    int u59o23_returned = -1;
    int u59o23_total_in_m = -1;
    int u59o23_total_in_f = -1;
    int u59o23_healed = -1;
    int u59o23_deceased = -1;
    int u59o23_abandon = -1;
    int u59o23_not_responding = -1;
    int u59o23_total_out_m = -1;
    int u59o23_total_out_f = -1;
    int u59o23_referred = -1;
    int u59o23_total_end_m = -1;
    int u59o23_total_end_f = -1;
    boolean u59o23_is_complete = false;
    // 59+ months
    int o59_total_start_m = -1;
    int o59_total_start_f = -1;
    int o59_new_cases = -1;
    int o59_returned = -1;
    int o59_total_in_m = -1;
    int o59_total_in_f = -1;
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
    // Pregnant & Breast Feeding Women
    int pw_total_start_f = -1;
    int pw_new_cases = -1;
    int pw_returned = -1;
    int pw_total_in_f = -1;
    int pw_healed = -1;
    int pw_deceased = -1;
    int pw_abandon = -1;
    int pw_not_responding = -1;
    int pw_total_out_f = -1;
    int pw_referred = -1;
    int pw_total_end_f = -1;
    boolean pw_is_complete = false;
    // Former SAM
    int exsam_total_start_m = -1;
    int exsam_total_start_f = -1;
    // int exsam_total_out_m = -1;
    // int exsam_total_out_f = -1;
    int exsam_referred = -1;
    int exsam_total_end_m = -1;
    int exsam_total_end_f = -1;
    boolean exsam_is_complete = false;

    public NutritionURENAMReportData() {
    }

    public static NutritionURENAMReportData get() {
        NutritionURENAMReportData report = getUniqueRecord(NutritionURENAMReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionURENAMReportData();
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

    protected Boolean isComplete() {
        return pw_is_complete &&
               o59_is_complete &&
               u23o6_is_complete &&
               u59o23_is_complete &&
               exsam_is_complete;
    }

    protected Boolean atLeastOneIsComplete() {
        return pw_is_complete ||
               o59_is_complete ||
               u23o6_is_complete ||
               u59o23_is_complete ||
               exsam_is_complete;
    }

    protected void resetReportData() {
        this.pw_is_complete = false;
        this.o59_is_complete = false;
        this.u23o6_is_complete = false;
        this.u59o23_is_complete = false;
        this.exsam_is_complete = false;
        this.save();
    }

    public String buildSMSText() {
        Log.d(TAG, "buildSMSText");
        return Constants.stringFromInteger(u23o6_total_start_m) + Constants.SUB_SPACER +
        	   Constants.stringFromInteger(u23o6_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_new_cases) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_returned) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_total_in_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_total_in_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_healed) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_deceased) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_abandon) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_not_responding) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_total_out_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_total_out_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_total_end_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u23o6_total_end_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_start_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_new_cases) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_returned) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_in_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_in_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_healed) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_deceased) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_abandon) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_not_responding) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_out_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_out_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_end_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(u59o23_total_end_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_start_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_new_cases) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_returned) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_in_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_in_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_healed) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_deceased) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_abandon) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_not_responding) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_out_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_out_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_end_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(o59_total_end_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_new_cases) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_returned) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_total_in_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_healed) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_deceased) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_abandon) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_not_responding) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_total_out_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(pw_total_end_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(exsam_total_start_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(exsam_total_start_f) + Constants.SUB_SPACER +
               Constants.stringFromInteger(exsam_referred) + Constants.SUB_SPACER +
               Constants.stringFromInteger(exsam_total_end_m) + Constants.SUB_SPACER +
               Constants.stringFromInteger(exsam_total_end_f);
    }
}
