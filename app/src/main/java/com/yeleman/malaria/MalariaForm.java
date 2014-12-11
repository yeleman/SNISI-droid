package com.yeleman.malaria;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;

import org.w3c.dom.Text;

/**
 * Created by fad on 08/12/14.
 */
public class MalariaForm extends CheckedFormActivity {

    private final static String Tag = Constants.getLogTag("MalariaForm");
    
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
    protected TextView anc1Label;
    protected TextView sp1Label;
    protected TextView sp2Label;

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
    protected RadioButton anc1YesField;
    protected RadioButton anc1NoField;
    protected RadioButton sp1YesField;
    protected RadioButton sp1NoField;
    protected RadioButton sp2YesField;
    protected RadioButton sp2NoField;

    protected Button saveButton;

}
