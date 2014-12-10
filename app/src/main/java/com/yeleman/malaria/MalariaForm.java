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

    protected TextView textView;
    protected TextView actYouthLabel;
    protected RadioButton radioButtonYesChildren;
    protected RadioButton radioButtonNoChildren;
    protected RadioButton radioButtonYesYouth;
    protected RadioButton radioButtonNoYouth;
    protected RadioButton radioButtonYesAdult;
    protected RadioButton radioButtonNoAdult;
    protected RadioButton radioButtonYesArtemether;
    protected RadioButton radioButtonNoArtemether;
    protected RadioButton radioButtonYesQuinine;
    protected RadioButton radioButtonNoQuinine;
    protected RadioButton radioButtonYesSurum;
    protected RadioButton radioButtonNoSurum;
    protected RadioButton radioButtonYesBednet;
    protected RadioButton radioButtonNoBednet;
    protected RadioButton radioButtonYesRdt;
    protected RadioButton radioButtonNoRdt;
    protected RadioButton radioButtonYesSp;
    protected RadioButton radioButtonNoSp;
    protected RadioButton radioButtonYesAnc1;
    protected RadioButton radioButtonNoAnc1;
    protected RadioButton radioButtonYesSp1;
    protected RadioButton radioButtonNoSp1;
    protected RadioButton radioButtonYesSp2;
    protected RadioButton radioButtonNoSp2;

    protected Button saveButton;

}
