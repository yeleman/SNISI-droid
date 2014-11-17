package com.yeleman.nutrition;

import com.yeleman.snisidroid.Constants;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    boolean has_urenam = false;
    boolean has_urenas = false;
    boolean has_ureni = false;

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

    protected Boolean isComplete() {


        Boolean urenam, urenas, ureni, inputs;
        urenam = urenas = ureni = true;

        if (has_urenam) { urenam = urenamReport.isComplete(); }
        if (has_urenas) { urenas = urenasReport.isComplete(); }
        if (has_ureni) { ureni = ureniReport.isComplete(); }
        inputs = inputsReport.isComplete();

        Log.d(TAG, String.valueOf(urenam));
        Log.d(TAG, String.valueOf(urenas));
        Log.d(TAG, String.valueOf(ureni));
        Log.d(TAG, String.valueOf(inputs));

        return urenam && urenas && ureni && inputs;
    }

    protected Boolean atLeastOneIsComplete() {
        return urenamReport.atLeastOneIsComplete() ||
               urenasReport.atLeastOneIsComplete() ||
               ureniReport.atLeastOneIsComplete() ||
               inputsReport.atLeastOneIsComplete();
    }

    protected void resetReportData() {
        urenamReport.resetReportData();
        urenasReport.resetReportData();
        ureniReport.resetReportData();
        inputsReport.resetReportData();
    }

    public String buildSMSText() {
        String smsText = "";

        if (has_urenam) {
            smsText += urenamReport.buildSMSText();
        } else {
            smsText += "-";
        }
        smsText +=  Constants.SPACER;

        if (has_urenas) {
            smsText += urenasReport.buildSMSText();
        } else {
            smsText += "-";
        }
        smsText +=  Constants.SPACER;

        if (has_ureni) {
            smsText += ureniReport.buildSMSText();
        } else {
            smsText += "-";
        }
        smsText +=  Constants.SPACER + inputsReport.buildSMSText();
        return smsText;
    }

    public void updateUren(boolean has_urenam, boolean has_urenas, boolean has_ureni) {
        this.has_urenam = has_urenam;
        this.has_urenas = has_urenas;
        this.has_ureni = has_ureni;
        this.save();
    }
    // Start
    protected int totalStartMURENI() {
        return ureniReport.totalStartM();
    }
    protected int totalStartFURENI() {
        return ureniReport.totalStartF();
    }
    protected int totalStartURENI() {
        return totalStartFURENI() + totalStartMURENI();
    }
    protected int totalStartMURENAMAndRENAS() {
        return urenasReport.totalStartM() + urenamReport.totalStartM();
    }
    protected int totalStartFURENAMAndRENAS() {
        return urenasReport.totalStartF() + urenamReport.totalStartF();
    }
    protected int totalStartURENAMAndRENAS() {
        return totalStartMURENAMAndRENAS() + totalStartFURENAMAndRENAS();
    }
    //  In
    protected int totalInMURENI() {
        return ureniReport.totalInM();
    }
    protected int totalInFURENI() {
        return ureniReport.totalInF();
    }    
    protected int totalInURENI() {
        return totalInMURENI() + totalInFURENI();
    }
    protected int totalInMURENAMAndRENAS() {
        return urenasReport.totalInM() + urenamReport.totalInM();
    }
    protected int totalInFURENAMAndRENAS() {
        return urenasReport.totalInF() + urenamReport.totalInF();
    }
    protected int totalInURENAMAndRENAS() {
        return totalInFURENAMAndRENAS() + totalInMURENAMAndRENAS();
    }
    // Out
    protected int totalOutMURENI() {
        return ureniReport.totalOutM();
    }
    protected int totalOutFURENI() {
        return ureniReport.totalOutF();
    }
    protected int totalOutURENI() {
        return totalOutMURENI() + totalOutFURENI();
    }
    protected int totalOutMURENAMAndRENAS() {
        return urenasReport.totalOutM() + urenamReport.totalOutM();
    }
    protected int totalOutFURENAMAndRENAS() {
        return urenasReport.totalOutF() + urenamReport.totalOutF();
    }

    protected int totalOutURENAMAndRENAS() {
        return totalOutMURENAMAndRENAS() + totalOutFURENAMAndRENAS();
    }
    // End
    protected int totalEndMURENI() {
        return ureniReport.totalEndM();
    }
    protected int totalEndFURENI() {
        return ureniReport.totalEndF();
    }
    protected int totalEndURENI() {
        return totalEndFURENI() + totalEndMURENI();
    }
    protected int totalEndMURENAMAndRENAS() {
        return urenasReport.totalEndM() + urenamReport.totalEndM();
    }
    protected int totalEndFURENAMAndRENAS() {
        return urenasReport.totalEndF() + urenamReport.totalEndF();
    }
    protected int totalEndURENAMAndRENAS() {
        return totalEndMURENAMAndRENAS() + totalEndFURENAMAndRENAS();
    }

    protected int balancePlumpy() {
        return (inputsReport.plumpy_nut_initial + inputsReport.plumpy_nut_received) -
               (inputsReport.plumpy_nut_used + inputsReport.plumpy_nut_lost);
    }

    protected int balanceMilkF75() {
        return (inputsReport.milk_f75_initial +
                inputsReport.milk_f75_received) -
               (inputsReport.milk_f75_used + 
                inputsReport.milk_f75_lost);
    }
    
    protected int balanceMilkF100() {
        return (inputsReport.milk_f100_initial +
                inputsReport.milk_f100_received) -
               (inputsReport.milk_f100_used + 
                inputsReport.milk_f100_lost);
    }
    protected int balanceResomal() {
        return (inputsReport.resomal_initial +
                inputsReport.resomal_received) -
               (inputsReport.resomal_used + 
                inputsReport.resomal_lost);
    }

    protected int balancePlumpySup() {
        return (inputsReport.plumpy_sup_initial +
                inputsReport.plumpy_sup_received) -
               (inputsReport.plumpy_sup_used +
                inputsReport.plumpy_sup_lost);
    }

    protected float balanceSupercereal() {
        return (inputsReport.supercereal_initial +
                inputsReport.supercereal_received) -
               (inputsReport.supercereal_used + 
                inputsReport.supercereal_lost);
    }
    protected int balanceSupercerealPlus() {
        return (inputsReport.supercereal_plus_initial +
                inputsReport.supercereal_plus_received) -
               (inputsReport.supercereal_plus_used +
                inputsReport.supercereal_plus_lost);
    }
    protected int balanceOil() {
        return (inputsReport.oil_initial + 
                inputsReport.oil_received) -
               (inputsReport.oil_used + 
                inputsReport.oil_lost);
    }
    protected int balanceAmoxycilline125mgVials() {
        return (inputsReport.amoxycilline_125_vials_initial +
                inputsReport.amoxycilline_125_vials_received) -
               (inputsReport.amoxycilline_125_vials_used + 
                inputsReport.amoxycilline_125_vials_lost);
    }
    protected int balanceAmoxycilline250mgCaps() {
        return (inputsReport.amoxycilline_250_caps_initial +
                inputsReport.amoxycilline_250_caps_received) -
               (inputsReport.amoxycilline_250_caps_used +
               inputsReport.amoxycilline_250_caps_lost);
    }
    protected int balanceAlbendazole400mg() {
        return (inputsReport.albendazole_400_initial + 
                inputsReport.albendazole_400_received) -
               (inputsReport.albendazole_400_used + 
                inputsReport.albendazole_400_lost);
    }
    protected int balanceVita100KUiInjectable() {
        return (inputsReport.vita_100_injectable_initial +
                inputsReport.vita_100_injectable_received) -
               (inputsReport.vita_100_injectable_used +
                inputsReport.vita_100_injectable_lost);
    }
    protected int balanceVita200KUiInjectable() {
        return (inputsReport.vita_200_injectable_initial +
                inputsReport.vita_200_injectable_received) -
               (inputsReport.vita_200_injectable_used +
                inputsReport.vita_200_injectable_lost);
    }
    protected int balanceIronFolicAcid() {
        return (inputsReport.iron_folic_acid_initial +
                inputsReport.iron_folic_acid_received) -
               (inputsReport.iron_folic_acid_used +
                inputsReport.iron_folic_acid_lost);
    }
}
