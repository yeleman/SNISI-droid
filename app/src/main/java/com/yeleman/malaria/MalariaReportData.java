package com.yeleman.malaria;

import com.yeleman.snisidroid.Constants;

import android.util.Log;
import com.orm.dsl.Ignore;
import com.yeleman.snisidroid.ReportData;

public class MalariaReportData extends ReportData {

    @Ignore
    private final static String TAG = Constants.getLogTag(
            MalariaReportData.class.getCanonicalName());
    
    // consultation
    int o5_total_consultation = -1;
    int pw_total_consultation = -1;
    int u5_total_consultation = -1;

    int u5_total_malaria_cases = -1;
    int o5_total_malaria_cases = -1;
    int pw_total_malaria_cases = -1;
    
    int o5_total_simple_malaria_cases = -1;
    int u5_total_simple_malaria_cases = -1;
    
    int o5_total_severe_malaria_cases = -1;
    int u5_total_severe_malaria_cases = -1;
    int pw_total_severe_malaria_cases = -1;
    
    int o5_total_tested_malaria_cases = -1;
    int u5_total_tested_malaria_cases = -1;
    int pw_total_tested_malaria_cases = -1;
    
    int o5_total_confirmed_malaria_cases = -1;
    int u5_total_confirmed_malaria_cases = -1;
    int pw_total_confirmed_malaria_cases = -1;
    
    int o5_total_acttreated_malaria_cases = -1;
    int u5_total_acttreated_malaria_cases = -1;
    int pw_total_acttreated_malaria_cases = -1;
    boolean consultation_is_complete = false;
    // Hospitalisations
    int o5_total_inpatient = -1;
    int u5_total_inpatient = -1;
    int pw_total_inpatient = -1;
    int o5_total_malaria_impatient = -1;
    int u5_total_malaria_impatient = -1;
    int pw_total_malaria_impatient = -1;
    boolean impatient_is_complete = false;
    // Décès
    int o5_total_death = -1;
    int u5_total_death = -1;
    int pw_total_death = -1;
    int o5_total_malaria_death = -1;
    int u5_total_malaria_death = -1;
    int pw_total_malaria_death = -1;
    boolean death_is_comple = false;
    // MILD
    int u5_malaria_total_distributed_bednets = -1;
    int pw_malaria_total_distributed_bednets = -1;
    boolean mild_is_complete = false;
    // Rupture de Stock
    int malaria_distributed_bednets_label = -1;
    int malaria_stockout_act_children = -1;
    int malaria_stockout_act_youth = -1;
    int malaria_stockout_act_adult = -1;
    int malaria_stockout_artemether = -1;
    int malaria_stockout_quinine = -1;
    int malaria_stockout_serum = -1;
    int malaria_stockout_bednet = -1;
    int malaria_stockout_rdt = -1;
    int malaria_stockout_sp = -1;
    int malaria_total_anc_1 = -1;
    int malaria_total_sp_1 = -1;
    int malaria_total_sp_2 = -1;
    boolean stockout_is_complete = false;

    public MalariaReportData() {}

    public static MalariaReportData get() {
        MalariaReportData report = getUniqueRecord(MalariaReportData.class);
        if (report == null) {
            Log.d(TAG, "No Record in DB. Creating.");
            report = new MalariaReportData();
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
        return this.consultation_is_complete &&
                this.impatient_is_complete &&
                this.death_is_comple &&
                this.mild_is_complete &&
                this.stockout_is_complete;
    }

    protected Boolean atLeastOneIsComplete(){
        return this.consultation_is_complete ||
               this.impatient_is_complete ||
               this.death_is_comple ||
               this.mild_is_complete ||
               this.stockout_is_complete;
    }
    
    protected void resetReportData() {
        MalariaReportData report = MalariaReportData.get();
        report.consultation_is_complete = false;
        report.impatient_is_complete = false;
        report.death_is_comple = false;
        report.mild_is_complete = false;
        report.stockout_is_complete = false;
        report.safeSave();
    }

    public String buildSMSText() {
        return Constants.stringFromReport(o5_total_consultation) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_consultation) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_consultation) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_simple_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_simple_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_severe_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_severe_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_severe_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_tested_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_tested_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_tested_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_confirmed_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_confirmed_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_confirmed_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_acttreated_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_acttreated_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_acttreated_malaria_cases) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_inpatient) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_inpatient) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_inpatient) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_malaria_impatient) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_malaria_impatient) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_malaria_impatient) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_death) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_death) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_death) + Constants.SUB_SPACER +
               Constants.stringFromReport(o5_total_malaria_death) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_total_malaria_death) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_total_malaria_death) + Constants.SUB_SPACER +
               Constants.stringFromReport(u5_malaria_total_distributed_bednets) + Constants.SUB_SPACER +
               Constants.stringFromReport(pw_malaria_total_distributed_bednets) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_distributed_bednets_label) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_act_children) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_act_youth) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_act_adult) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_artemether) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_quinine) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_serum) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_bednet) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_rdt) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_stockout_sp) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_total_anc_1) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_total_sp_1) + Constants.SUB_SPACER +
               Constants.stringFromReport(malaria_total_sp_2);
    }
}
