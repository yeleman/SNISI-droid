package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritonURENAMReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritonURENAMReportData.class.getSimpleName());

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
    // Pregnant & Breast Feeding Women
    int pw_total_start_m = -1;
    int pw_total_start_f = -1;
    int pw_new_cases = -1;
    int pw_returned = -1;
    int pw_total_in_m = -1;
    int pw_total_in_f = -1;
    int pw_healed = -1;
    int pw_deceased = -1;
    int pw_abandon = -1;
    int pw_not_responding = -1;
    int pw_total_out_m = -1;
    int pw_total_out_f = -1;
    int pw_referred = -1;
    int pw_total_end_m = -1;
    int pw_total_end_f = -1;
    // Former SAM
    int exsam_total_start_m = -1;
    int exsam_total_start_f = -1;
    int exsam_total_out_m = -1;
    int exsam_total_out_f = -1;
    int exsam_referred = -1;
    int exsam_total_end_m = -1;
    int exsam_total_end_f = -1;

    public NutritonURENAMReportData() {}

    public static NutritonURENAMReportData get() {
        NutritonURENAMReportData report = getUniqueRecord(NutritonURENAMReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritonURENAMReportData();
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
}
