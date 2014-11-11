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

    protected Boolean atLeastOneIsCmplete() {
        return pw_is_complete ||
                o59_is_complete ||
                u23o6_is_complete ||
                u59o23_is_complete ||
                exsam_is_complete;
    }

    protected void resetReportData() {
        NutritionURENAMReportData report = NutritionURENAMReportData.get();
        report.pw_is_complete = false;
        report.o59_is_complete = false;
        report.u23o6_is_complete = false;
        report.u59o23_is_complete = false;
        report.exsam_is_complete = false;
        report.save();
    }

    // protected String buildSMSText() {
    //     Log.d(TAG, "buildSMSText");
    //     return String.format(Constants.SMS_NUTRITION_WEEKLY_REPORT,
    //                     u23o6_total_start_f,
    //                     u23o6_new_cases,
    //                     u23o6_returned,
    //                     u23o6_total_in_m,
    //                     u23o6_total_in_f,
    //                     u23o6_healed,
    //                     u23o6_deceased,
    //                     u23o6_abandon,
    //                     u23o6_not_responding,
    //                     u23o6_total_out_m,
    //                     u23o6_total_out_f,
    //                     u23o6_referred,
    //                     u23o6_total_end_m,
    //                     u23o6_total_end_f,
    //                     u59o23_total_start_m,
    //                     u59o23_total_start_f,
    //                     u59o23_new_cases,
    //                     u59o23_returned,
    //                     u59o23_total_in_m,
    //                     u59o23_total_in_f,
    //                     u59o23_healed,
    //                     u59o23_deceased,
    //                     u59o23_abandon,
    //                     u59o23_not_responding,
    //                     u59o23_total_out_m,
    //                     u59o23_total_out_f,
    //                     u59o23_referred,
    //                     u59o23_total_end_m,
    //                     u59o23_total_end_f,
    //                     o59_total_start_m,
    //                     o59_total_start_f,
    //                     o59_new_cases,
    //                     o59_returned,
    //                     o59_total_in_m,
    //                     o59_total_in_f,
    //                     o59_healed,
    //                     o59_deceased,
    //                     o59_abandon,
    //                     o59_not_responding,
    //                     o59_total_out_m,
    //                     o59_total_out_f,
    //                     o59_referred,
    //                     o59_total_end_m,
    //                     o59_total_end_f,
    //                     pw_total_start_f,
    //                     pw_new_cases,
    //                     pw_returned,
    //                     pw_total_in_f,
    //                     pw_healed,
    //                     pw_deceased,
    //                     pw_abandon,
    //                     pw_not_responding,
    //                     pw_total_out_f,
    //                     pw_referred,
    //                     pw_total_end_f,
    //                     exsam_total_start_m,
    //                     exsam_total_start_f,
    //                     //exsam_total_out_m,
    //                     //exsam_total_out_f,
    //                     exsam_referred,
    //                     exsam_total_end_m,
    //                     exsam_total_end_f);
    // }
}