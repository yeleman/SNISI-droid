package com.yeleman.nutrition;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yeleman.snisidroid.CheckedFormActivity;
import com.yeleman.snisidroid.Constants;
import com.yeleman.snisidroid.R;

public class NutritionURENForm extends CheckedFormActivity {

    private final static String TAG = Constants.getLogTag("NutritionURENForm");

    protected static final String URENAM = "urenam";
    protected static final String URENAS = "urenas";
    protected static final String URENI = "ureni";

    protected static final String EXSAM = "exsam";
    protected static final String O59 = "o59";
    protected static final String PW = "pw";
    protected static final String U23O6 = "u23o6";
    protected static final String U59O23 = "u59o23";
    protected static final String U59O6 = "u59o6";
    protected static final String U6 = "u6";

    protected TextView transferredLabel;
    protected TextView referredLabel;

    protected EditText totalStartMField;
    protected EditText totalStartFField;
    protected EditText newCasesField;
    protected EditText returnedField;
    protected EditText totalInMField;
    protected EditText totalInFField;
    protected EditText transferredField;
    protected EditText healedField;
    protected EditText deceasedField;
    protected EditText abandonField;
    protected EditText notRespondingField;
    protected EditText totalOutMField;
    protected EditText totalOutFField;
    protected EditText referredField;
    protected EditText totalEndMField;
    protected EditText totalEndFField;

    protected Button saveButton;

    public String getUREN() { return URENI; }
    public String getAge() { return O59; }

    /* Accessor for all fields so we can override some with zeros */
    protected int getTotalStartM() {
        if (getAge() == PW) {
            return 0;
        }
        return integerFromField(totalStartMField);
    }
    protected int getTotalStartF() {
        return integerFromField(totalStartFField);
    }
    protected int getNewCases() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(newCasesField);
    }
    protected int getReturned() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(returnedField);
    }
    protected int getTotalInM() {
        if (getAge() == EXSAM || getAge() == PW) {
            return 0;
        }
        return integerFromField(totalInMField);
    }
    protected int getTotalInF() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(totalInFField);
    }
    protected int getTransferred() {
        if (getUREN() == URENAM) {
            return 0;
        }
        return integerFromField(transferredField);
    }
    protected int getHealed() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(healedField);
    }
    protected int getDeceased() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(deceasedField);
    }
    protected int getAbandon() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(abandonField);
    }
    protected int getNotResponding() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(notRespondingField);
    }
    protected int getTotalOutM() {
        if (getAge() == EXSAM || getAge() == PW) {
            return 0;
        }
        return integerFromField(totalOutMField);
    }
    protected int getTotalOutF() {
        if (getAge() == EXSAM) {
            return 0;
        }
        return integerFromField(totalOutFField);
    }
    protected int getReferred() {
        return integerFromField(referredField);
    }
    protected int getTotalEndM() {
        if (getAge() == PW) {
            return 0;
        }
        return integerFromField(totalEndMField);
    }
    protected int getTotalEndF() {
        return integerFromField(totalEndFField);
    }

    /* sub totals to ease check calculations */
    protected int getTotalStart() {
        return getTotalStartM() + getTotalStartF();
    }
    protected int getTotalIn() {
        return getTotalInM() + getTotalInF();
    }
    protected int getGrandTotalIn() {
        return getTotalIn() + getTransferred();
    }
    protected int getTotalOut() {
        return getTotalOutM() + getTotalOutF();
    }
    protected int getGrandTotalOut() {
        return getTotalOut() + getReferred();
    }
    protected int getTotalEnd() {
        return getTotalEndM() + getTotalEndF();
    }

    /* commonly used break downs */
    protected int getNewCasesAndReturned() {
        return getNewCases() + getReturned();
    }
    protected int getAllOutReasons() {
        return getHealed() + getDeceased() + getAbandon() + getNotResponding();
    }
    protected int getAllAvailable() {
        return getTotalStart() + getGrandTotalIn();
    }
    protected int getStartInNotOut() {
        return getTotalStart() + getGrandTotalIn() - getGrandTotalOut();
    }

    protected boolean ensureURENCoherence() {

        Log.d(TAG, "totatStart: " + getTotalStart());
        Log.d(TAG, "totalIn: " + getTotalIn());
        Log.d(TAG, "grandTotalIn: " + getGrandTotalIn());
        Log.d(TAG, "totalOut: " + getTotalOut());
        Log.d(TAG, "grandTotalOut: " + getGrandTotalOut());
        Log.d(TAG, "totatEnd: " + getTotalEnd());

        // newCases + returned == totalIn
        int newCasesAndReturned = getNewCasesAndReturned();
        int totalIn = getTotalIn();
        if (newCasesAndReturned != totalIn) {
            String errorMsg = String.format(getString(R.string.error_must_be_equal,
                    newCasesField.getHint() + " + " + returnedField.getHint(),
                    newCasesAndReturned,
                    "total admis ", totalIn));
            fireErrorDialog(this, errorMsg, newCasesField);
            return false;
        }

        // Détails sorties
        // allOut == totalOut
        int totalOut = getTotalOut();
        int allOutReasons = getAllOutReasons();
        if (allOutReasons != totalOut) {
            String errorMsg = String.format(getString(R.string.error_must_be_equal,
                    "guéris, décès, abandons, non-resp.",
                    allOutReasons, "total sorties", totalOut));
            fireErrorDialog(this, errorMsg, healedField);
            return false;
        }

        // Sorties inferieur ou egal à PEC
        int totalStart = getTotalStart();
        int grandTotalIn = getGrandTotalIn();
        int allAvail = getAllAvailable();
        int grandTotalOut = getGrandTotalOut();
        if (grandTotalOut > allAvail) {
            String errorMsg = String.format("total sorties général (%1$d) ne peut pas dépasser le " +
                    "total début + admissions (%2$d)", grandTotalOut, allAvail);
            fireErrorDialog(this, errorMsg, newCasesField);
            return false;
        }

        // Total fin de mois
        // totalEnd = totalStart + grand_totalIn - grand_totalOut
        int totalEnd = getTotalEnd();
        int startInNotOut = getStartInNotOut();
        if (totalEnd != startInNotOut) {
            String errorMsg = String.format("total fin de mois (%1$d) doit être égal au début " +
                    "+ admissions - sorties (%2$d)", totalEnd, startInNotOut);
            fireErrorDialog(this, errorMsg, totalStartFField);
            return false;
        }
        return true;
    }

}
