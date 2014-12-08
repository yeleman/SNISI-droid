package com.yeleman.malaria;

import android.widget.Button;
import android.widget.EditText;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;

/**
 * Created by fad on 08/12/14.
 */
public class MalariaForm extends CheckedFormActivity {

    private final static String Tag = Constants.getLogTag("MalariaForm");
    
    protected EditText consultationU5Field;
    protected EditText consultationO5Field;
    protected EditText consultationPwField;
    protected EditText totalCasesU5Field;
    protected EditText totalCasesO5Field;
    protected EditText totalCasesPwField;
    protected EditText testedCaseU5Field;
    protected EditText testedCaseO5Field;
    protected EditText testedCasePwField;
    protected EditText confirmedCaseU5Field;
    protected EditText confirmedCaseO5Field;
    protected EditText confirmedCasePwField;
    protected EditText simpleCaseU5Field;
    protected EditText simpleCaseO5Field;
    protected EditText severeCaseU5Field;
    protected EditText severeCaseO5Field;
    protected EditText severeCasePwField;
    protected EditText acttreatedCaseU5Field;
    protected EditText acttreatedCaseO5Field;
    protected EditText acttreatedCasePwField;
    protected Button saveButton;

}
