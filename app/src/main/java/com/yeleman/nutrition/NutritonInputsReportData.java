package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class NutritonInputsReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            NutritonInputsReportData.class.getSimpleName());
        
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
        int supercereal_initial = -1;
        int supercereal_received = -1;
        int supercereal_used = -1;
        int supercereal_lost = -1;
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
        Boolean input_is_complete = false;


    public NutritonInputsReportData() {}

    public static NutritonInputsReportData get() {
        NutritonInputsReportData report = getUniqueRecord(NutritonInputsReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new NutritonInputsReportData();
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
        return false;
    }

    protected void resetReportData() {
        NutritonInputsReportData report = NutritonInputsReportData.get();
        report.input_is_complete = false;
        report.save();
    }
}
