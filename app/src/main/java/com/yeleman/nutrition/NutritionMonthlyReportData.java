package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritionMonthlyReportData extends ReportData {

    NutritionURENAMReportData urenamReport = NutritionURENAMReportData.get();
    NutritionURENASReportData urenasReport = NutritionURENASReportData.get();
    NutritionURENIReportData ureniReport = NutritionURENIReportData.get();
    NutritionInputsReportData inputsReport = NutritionInputsReportData.get();

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionMonthlyReportData.class.getSimpleName());
    

    public NutritionMonthlyReportData() {}

    public static NutritionMonthlyReportData get() {
        NutritionMonthlyReportData report = getUniqueRecord(NutritionMonthlyReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionMonthlyReportData();
            report.updateMetaData();
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
        return urenamReport.atLeastOneIsCmplete() &&
               urenasReport.atLeastOneIsCmplete() &&
               ureniReport.atLeastOneIsCmplete() &&
               inputsReport.atLeastOneIsCmplete();
    }

    protected Boolean atLeastOneIsCmplete(){
        return urenamReport.atLeastOneIsCmplete() ||
               urenasReport.atLeastOneIsCmplete() ||
               ureniReport.atLeastOneIsCmplete() ||
               inputsReport.atLeastOneIsCmplete();
    }

    protected void resetReportData() {
        urenamReport.resetReportData();
        urenasReport.resetReportData();
        ureniReport.resetReportData();
        inputsReport.resetReportData();
    }
}
