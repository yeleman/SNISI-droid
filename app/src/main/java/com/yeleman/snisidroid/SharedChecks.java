/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yeleman.snisidroid;

import android.widget.EditText;


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
