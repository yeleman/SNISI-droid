package com.yeleman.nutrition;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.query.Select;
import static com.orm.SugarRecord.save;

import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.ReportData;

public class NutritionWeeklyReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionWeeklyReportData.class.getSimpleName());

    int mam_screening = -1;
    int sam_screening = -1;
    int samc_screening = -1;

    int mam_cases = -1;
    int mam_deaths = -1;

    int sam_cases = -1;
    int sam_deaths = -1;

    int samc_cases = -1;
    int samc_deaths = -1;

    public NutritionWeeklyReportData() {}

    public static NutritionWeeklyReportData get() {
        NutritionWeeklyReportData report = getUniqueRecord(NutritionWeeklyReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionWeeklyReportData();
            report.save();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    @Override
    protected String buildName() {
        return "Nut Hebdo";
    }
}



