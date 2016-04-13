package com.yeleman.malaria;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;

/**
 * Created by fad on 08/12/14.
 */
public class MalariaForm extends CheckedFormActivity {

    private final static String Tag = Constants.getLogTag("MalariaForm");
    protected static final String PW = "pw";

    protected EditText malariaTotalConsultationField;
    protected EditText malariaTotalMalariaCasesField;
    protected EditText malariaTotalTestedMalariaCasesField;
    protected EditText malariaTotalConfirmedMalariaCasesField;
    protected EditText malariaTotalSimpleMalariaCasesField;
    protected EditText malariaTotalSevereMalariaCasesField;
    protected EditText malariaTotalActtreatedMalariaCasesField;
    protected EditText malariaTotalInpatientField;
    protected EditText malariaTotalMalariaInpatientField;
    protected EditText malariaTotalDeathField;
    protected EditText malariaTotalMalariaDeathField;
    protected EditText malariaTotalDistributedBednetsField;

    protected TextView actChildrenLabel;
    protected TextView actYouthLabel;
    protected TextView adultLabel;
    protected TextView artemetherLabel;
    protected TextView quinineLabel;
    protected TextView serumLabel;
    protected TextView bednetLabel;
    protected TextView rdtLabel;
    protected TextView spLabel;

    protected RadioButton actChildrenYesField;
    protected RadioButton actChildrenNoField;
    protected RadioButton actYouthYesField;
    protected RadioButton actYouthNoField;
    protected RadioButton adultYesField;
    protected RadioButton adultNoField;
    protected RadioButton artemetherYesField;
    protected RadioButton artemetherNoField;
    protected RadioButton quinineYesField;
    protected RadioButton quinineNoField;
    protected RadioButton serumYesField;
    protected RadioButton serumNoField;
    protected RadioButton bednetYesField;
    protected RadioButton bednetNoField;
    protected RadioButton rdtYesField;
    protected RadioButton rdtNoField;
    protected RadioButton spYesField;
    protected RadioButton spNoField;
    protected EditText anc1Field;
    protected EditText sp1Field;
    protected EditText sp2Field;

    protected Button saveButton;

    public String getAge() { return ""; }

    protected int getTotalSimpleMalariaCases() {
        /*if (getAge() == PW){
            return 0;
        }*/
        return integerFromField(malariaTotalSimpleMalariaCasesField);
    }

    protected boolean ensureMalariaCoherence() {

        int totalSimpleMalariaCases = getTotalSimpleMalariaCases();
        // Cas de Palu supérieur au total toutes causes
        if (!mustBeInferiorOrEqual(malariaTotalMalariaCasesField,
                malariaTotalMalariaCasesField,
                malariaTotalConsultationField)) {
            return false;
        }
        /* if (getAge() != PW) {
            // Cas de Palu simple supérieur au total toutes causes"
            if (!mustBeInferiorOrEqual(malariaTotalSimpleMalariaCasesField,
                    malariaTotalSimpleMalariaCasesField,
                    malariaTotalConsultationField)) {
                return false;
            }
            // Cas de Palu simple supérieur au total suspectés
            if (!mustBeInferiorOrEqual(malariaTotalSimpleMalariaCasesField,
                    malariaTotalSimpleMalariaCasesField,
                    malariaTotalMalariaCasesField)) {
                return false;
            }
        }*/
        // Cas de Palu simple supérieur au total toutes causes
        if (!mustBeInferiorOrEqual(malariaTotalSimpleMalariaCasesField,
                                          malariaTotalSimpleMalariaCasesField,
                                          malariaTotalConsultationField)) {
            return false;
        }
        // Cas de Palu simple supérieur au total suspectés
        if (!mustBeInferiorOrEqual(malariaTotalSimpleMalariaCasesField,
                                          malariaTotalSimpleMalariaCasesField,
                                          malariaTotalMalariaCasesField)) {
            return false;
        }
        // Cas de Palu grave supérieur au total toutes causes
        if (!mustBeInferiorOrEqual(malariaTotalSevereMalariaCasesField,
                malariaTotalSevereMalariaCasesField,
                malariaTotalConsultationField)) {
            return false;
        }
        // Cas de Palu grave supérieur au total suspectés
        if (!mustBeInferiorOrEqual(malariaTotalSevereMalariaCasesField,
                malariaTotalSevereMalariaCasesField,
                malariaTotalMalariaCasesField)) {
            return false;
        }
        // Cas de Palu testés supérieur au total suspectés
        if (!mustBeInferiorOrEqual(malariaTotalTestedMalariaCasesField,
                malariaTotalTestedMalariaCasesField,
                malariaTotalMalariaCasesField)) {
            return false;
        }
        // Cas de Palu confirmés supérieur au total suspectés
        if (!mustBeInferiorOrEqual(malariaTotalConfirmedMalariaCasesField,
                malariaTotalConfirmedMalariaCasesField,
                malariaTotalMalariaCasesField)) {
            return false;
        }
        // Cas de Palu simple + grave supérieurs au total suspectés
        int totalSimpleCaseAndServerCase = totalSimpleMalariaCases +
                integerFromField(malariaTotalSevereMalariaCasesField);
        int totalMalariaCases = integerFromField(malariaTotalMalariaCasesField);
        if (totalSimpleCaseAndServerCase > totalMalariaCases) {
            String errorMsg =  String.format("Cas de Palu simple + grave (%d) supérieurs" +
                            " au total suspectés (%d).",
                    totalSimpleCaseAndServerCase, totalMalariaCases);
            fireErrorDialog(this, errorMsg, malariaTotalMalariaCasesField);
            return false;
        }
        // Cas de Palu confirmés supérieur au total testés
        if (!mustBeInferiorOrEqual(malariaTotalConfirmedMalariaCasesField,
                malariaTotalConfirmedMalariaCasesField,
                malariaTotalTestedMalariaCasesField)) {
            return false;
        }
        // Cas de Palu simple + grave supérieurs au total confirmés
        int totalConfirmedMalariaCases = integerFromField(malariaTotalConfirmedMalariaCasesField);
        if (totalSimpleCaseAndServerCase != totalConfirmedMalariaCases) {
            String errorMsg =  String.format("Cas de Palu simple + grave (%d) différents" +
                            " du total confirmés (%d).",
                    totalSimpleCaseAndServerCase, totalConfirmedMalariaCases);
            fireErrorDialog(this, errorMsg, malariaTotalConfirmedMalariaCasesField);
            return false;
        }
        // Cas de Palu traités supérieur au total testés
        if (!mustBeInferiorOrEqual(malariaTotalActtreatedMalariaCasesField,
                malariaTotalActtreatedMalariaCasesField,
                malariaTotalTestedMalariaCasesField)) {
            return false;
        }
        // Cas de Palu traités supérieur au total confirmés
        if (!mustBeInferiorOrEqual(malariaTotalActtreatedMalariaCasesField,
                malariaTotalActtreatedMalariaCasesField,
                malariaTotalConfirmedMalariaCasesField)) {
            return false;
        }
        // Hospitalisations Palu supérieur aux hospit. toutes causes
        if (!mustBeInferiorOrEqual(malariaTotalMalariaInpatientField,
                malariaTotalMalariaInpatientField,
                malariaTotalInpatientField)) {
            return false;
        }
        // Décès Palu supérieur aux décès toutes causes
        if (!mustBeInferiorOrEqual(malariaTotalMalariaDeathField,
                malariaTotalMalariaDeathField,
                malariaTotalDeathField)) {
            return false;
        }
        return true;
    }

}
