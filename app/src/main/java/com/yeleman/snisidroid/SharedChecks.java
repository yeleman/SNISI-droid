/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yeleman.snisidroid;

import java.util.Vector;
import java.util.GregorianCalendar;
import java.util.Calendar;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.DatePicker;


public class SharedChecks {


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
