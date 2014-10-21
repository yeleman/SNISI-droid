/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yeleman.smir;

import java.util.Vector;
import java.util.GregorianCalendar;
import java.util.Calendar;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.DatePicker;


public class SharedChecks {

    protected boolean _check_unsigned_int(TextView widget) {
        try {
            return Integer.parseInt(widget.getText().toString()) >= 0;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    protected boolean _check_unsigned_int_message(TextView widget, String fieldName, Vector<String> errors) {
        if (_check_unsigned_int(widget)) {
            return true;
        }
        errors.add("«" + fieldName + "» doit être un entier postif ou nul.");
        return false;
    }

    protected static boolean _check_not_valid(TextView widgetcas, TextView widgetdeces) {
        return Integer.parseInt(widgetdeces.getText().toString()) <= Integer.parseInt(widgetcas.getText().toString());
    }

    protected static boolean _check_isValid_message(TextView widgetcas, TextView widgetdeces, String fieldName, Vector<String> errors) {
        if (_check_not_valid(widgetcas, widgetdeces)) {
            return true;
        }
        errors.add("«" + fieldName + "» Nombre de décès ne doit pas être supérieur au nombre cas.");
        return false;
    }

    protected static boolean _check_not_empty(TextView widget) {
        return widget.getText().toString().length() > 0;
    }

    protected static boolean _check_not_empty_message(TextView widget, String fieldName, Vector<String> errors) {
        if (_check_not_empty(widget)) {
            return true;
        }
        errors.add("«" + fieldName + "» ne doit pas être vide.");
        return false;
    }

    protected static boolean _check_min_characters(TextView widget, int nb_chars) {
        return widget.getText().toString().length() >= nb_chars;
    }

    protected static boolean _check_min_characters_message(TextView widget, int nb_chars, String fieldName, Vector<String> errors) {
        if (_check_min_characters(widget, nb_chars)) {
            return true;
        }
        errors.add("«" + fieldName + "» doit être composé d'au moins " + nb_chars + " caractères.");
        return false;
    }

    protected static boolean _check_date_is_friday(DatePicker widget) {
        GregorianCalendar adate = new GregorianCalendar(widget.getYear(), widget.getMonth(), widget.getDayOfMonth());
        return adate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    protected static boolean _check_date_is_friday_message(DatePicker widget, String fieldName, Vector<String> errors) {
        if (_check_date_is_friday(widget)) {
            return true;
        }
        errors.add("«" + fieldName + "» doit être un vendredi.");
        return false;
    }

    public static boolean is_empty(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);
        // length 0 means there is no text
        if (text.isEmpty()) {
            editText.setError("Champs requis");
            return false;
        }
        return true;
    }


}
