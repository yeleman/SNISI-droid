package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritionInputsReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritionInputsReportData.class.getSimpleName());
        
        // Plumpy Nut
        int plumpy_nut_initial = -1;
        int plumpy_nut_received = -1;
        int plumpy_nut_used = -1;
        int plumpy_nut_lost = -1;
        // Milk_F75
        int milk_f75_initial = -1;
        int milk_f75_received = -1;
        int milk_f75_used = -1;
        int milk_f75_lost = -1;
        // Milk_F100
        int milk_f100_initial = -1;
        int milk_f100_received = -1;
        int milk_f100_used = -1;
        int milk_f100_lost = -1;

        // Resomal
        int resomal_initial = -1;
        int resomal_received = -1;
        int resomal_used = -1;
        int resomal_lost = -1;
        // Plumpy_Sup
        int plumpy_sup_initial = -1;
        int plumpy_sup_received = -1;
        int plumpy_sup_used = -1;
        int plumpy_sup_lost = -1;
        // Supercereal
        float supercereal_initial = -1;
        float supercereal_received = -1;
        float supercereal_used = -1;
        float supercereal_lost = -1;
        // Supercereal_Plus
        int supercereal_plus_initial = -1;
        int supercereal_plus_received = -1;
        int supercereal_plus_used = -1;
        int supercereal_plus_lost = -1;
        // Oil
        int oil_initial = -1;
        int oil_received = -1;
        int oil_used = -1;
        int oil_lost = -1;
        // Amoxycilline 125mg Vials
        int amoxycilline_125_vials_initial = -1;
        int amoxycilline_125_vials_received = -1;
        int amoxycilline_125_vials_used = -1;
        int amoxycilline_125_vials_lost = -1;
        // Amoxycilline 250mg Caps
        int amoxycilline_250_caps_initial = -1;
        int amoxycilline_250_caps_received = -1;
        int amoxycilline_250_caps_used = -1;
        int amoxycilline_250_caps_lost = -1;
        // Albendazole 400mg
        int albendazole_400_initial = -1;
        int albendazole_400_received = -1;
        int albendazole_400_used = -1;
        int albendazole_400_lost = -1;
        // VitA 100K UI Injectable
        int vita_100_injectable_initial = -1;
        int vita_100_injectable_received = -1;
        int vita_100_injectable_used = -1;
        int vita_100_injectable_lost = -1;
        // VitA 200K UI Injectable
        int vita_200_injectable_initial = -1;
        int vita_200_injectable_received = -1;
        int vita_200_injectable_used = -1;
        int vita_200_injectable_lost = -1;
        // Iron_Folic_Acid
        int iron_folic_acid_initial = -1;
        int iron_folic_acid_received = -1;
        int iron_folic_acid_used = -1;
        int iron_folic_acid_lost = -1;
        boolean input_is_complete = false;


    public NutritionInputsReportData() {}

    public static NutritionInputsReportData get() {
        NutritionInputsReportData report = getUniqueRecord(NutritionInputsReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritionInputsReportData();
            report.safeSave();
        } else {
            Log.d(TAG, "Record exist in Database.");
        }
        return report;
    }

    @Override
    protected String buildName() {
        return "Situation Stocks";
    }

    protected Boolean isComplete(){
        return input_is_complete;
    }

    protected Boolean atLeastOneIsComplete(){
        return input_is_complete;
    }
    
    protected void resetReportData() {
        NutritionInputsReportData report = NutritionInputsReportData.get();
        report.input_is_complete = false;
        report.safeSave();
    }

    public String buildSMSText() {
        return Constants.stringFromInteger(plumpy_nut_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_nut_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_nut_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_nut_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f75_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f75_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f75_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f75_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f100_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f100_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f100_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(milk_f100_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(resomal_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(resomal_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(resomal_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(resomal_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_sup_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_sup_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_sup_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(plumpy_sup_lost) + Constants.SUB_SPACER +
               Constants.stringFromFloat(supercereal_initial) + Constants.SUB_SPACER +
               Constants.stringFromFloat(supercereal_received) + Constants.SUB_SPACER +
               Constants.stringFromFloat(supercereal_used) + Constants.SUB_SPACER +
               Constants.stringFromFloat(supercereal_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(supercereal_plus_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(supercereal_plus_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(supercereal_plus_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(supercereal_plus_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(oil_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(oil_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(oil_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(oil_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_125_vials_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_125_vials_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_125_vials_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_125_vials_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_250_caps_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_250_caps_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_250_caps_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(amoxycilline_250_caps_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(albendazole_400_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(albendazole_400_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(albendazole_400_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(albendazole_400_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_100_injectable_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_100_injectable_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_100_injectable_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_100_injectable_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_200_injectable_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_200_injectable_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_200_injectable_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(vita_200_injectable_lost) + Constants.SUB_SPACER +
               Constants.stringFromInteger(iron_folic_acid_initial) + Constants.SUB_SPACER +
               Constants.stringFromInteger(iron_folic_acid_received) + Constants.SUB_SPACER +
               Constants.stringFromInteger(iron_folic_acid_used) + Constants.SUB_SPACER +
               Constants.stringFromInteger(iron_folic_acid_lost);
    }
}
