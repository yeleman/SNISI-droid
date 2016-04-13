package com.yeleman.nutrition;

import android.util.Log;

import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.ReportData;

public class NutritionWeeklyReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionWeeklyReportData.class.getSimpleName());

    int mam_screening = -1;
    int mam_cases = -1;
    int mam_deaths = -1;

    int sam_screening = -1;
    int sam_cases = -1;
    int sam_deaths = -1;

    int samc_screening = -1;
    int samc_cases = -1;
    int samc_deaths = -1;

    boolean is_complete;

    public NutritionWeeklyReportData() {}

    public static NutritionWeeklyReportData get() {
        NutritionWeeklyReportData report = getUniqueRecord(NutritionWeeklyReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionWeeklyReportData();
            report.safeSave();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    @Override
    protected String buildName() {
        return "Hebdo NUT";
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
        return Constants.stringFromReport(mam_screening) + Constants.SPACER +
               Constants.stringFromReport(mam_cases) + Constants.SPACER +
               Constants.stringFromReport(mam_deaths) + Constants.SPACER +
               Constants.stringFromReport(sam_screening) + Constants.SPACER +
               Constants.stringFromReport(sam_cases) + Constants.SPACER +
               Constants.stringFromReport(sam_deaths) + Constants.SPACER +
               Constants.stringFromReport(samc_screening) + Constants.SPACER +
               Constants.stringFromReport(samc_cases) + Constants.SPACER +
               Constants.stringFromReport(samc_deaths);
    }
}
