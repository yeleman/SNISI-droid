package com.yeleman.snisidroid;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class Constants {

    private final static String TAG = Constants.getLogTag("Constants");

    public static final int SMS_SUCCESS = 0;
    public static final int SMS_WARNING = 1;
    public static final int SMS_ERROR = 2;
    public static final int SMS_UNKNOWN = 3;

    public static final String server_number = "70062552";
    public static final String server_url = "http://snisi.sante.gov.ml";
    public static final String resource_url = "%1$s/resources";
    public static final String SPACER = " ";
    public static final String SUB_SPACER = "-";
    public static final int MIN_CHARS_PASSWORD = 4;
    public static final int MIN_CHARS_USERNAME = 3;
    public static final String SMS_FORMAT = "%1$s %2$s %3$s %4$s";

    public static final String SMS_KEYWORD_NUT_WEEKLY = "nut w";
    public static final String SMS_KEYWORD_NUT_MONTHLY = "nut m";

    public static final String SMS_SENT_INTENT = "com.yeleman.snisidroid.SMS_SENT";
    public static final String SMS_DELIVERED_INTENT = "com.yeleman.snisidroid.SMS_DELIVERED";

    public static final int NB_SECONDS_WAIT_FOR_REPLY = 150;
    public static final String SMS_CHANGE_PASSWRD = "passwd %1$s %2$s %3$s";
    public static final String SMS_NUTRITION_MONTHLY_REPORT = "nut-%1$s-%2$s-%3$s-%4$s-%5$s-%6$s-%7$s-%8$s";

    public static final String getLogTag(String activity) {
    	return String.format("SNISILog-%s", activity);
    }

    public static int getSMSStatus(String message) {
        // for status, we need [:]
        if (!message.contains("[") || !message.contains("]") || !message.contains(":")) {
            return SMS_UNKNOWN;
        }

        int semiColon = message.indexOf(":");
        int endingBracket = message.indexOf("]");
        if (semiColon < 0 || endingBracket < 0) {
            return SMS_UNKNOWN;
        }
        String statusText = message.substring(semiColon + 1, endingBracket);
        Log.d(TAG, "Status: " + statusText);

        if (statusText.equals("OK")) {
            return SMS_SUCCESS;
        }

        if (statusText.equals("/!\\")) {
            return SMS_WARNING;
        }

        if (statusText.equals("ECHEC")) {
            return SMS_ERROR;
        }

        return SMS_UNKNOWN;
    }

    public static int getColorForStatus(int status) {
        switch (status) {
            case SMS_SUCCESS:
                return Color.rgb(71, 166, 42); // green
            case SMS_WARNING:
                return Color.rgb(208, 113, 42); // orange
            case SMS_ERROR:
                return Color.RED;
            case SMS_UNKNOWN:
            default:
                return -1;
        }
    }
   public static String getCompleteStatus(Boolean status) {
       if (status) {
           return "X";
       } else {
           return "  ";
       }
   }

    public static void updateButtonCompletion(Button button, boolean is_complete) {
        int color = getColorForStatus((is_complete) ? SMS_SUCCESS : SMS_UNKNOWN);
        if (color != -1) {
            button.setTextColor(color);
        }
    }

    public static String buildCompleteSMSText(String smsKeyword, String username,
                                              String password, String smsData) {
        return String.format(SMS_FORMAT,
                             smsKeyword,
                             username,
                             password,
                             smsData);
    }

    public static String stringFromFloat(float data) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(2);
        return nf.format(data);
    }

    public static String stringFromInteger(int data) {
        return String.valueOf(data);
    }

    public static int integerFromReport(int data) {
        if(data < 0) {
            data = 0;
        }
        return data;
    }

    public static float floatFromReport(float data) {
        if(data < 0) {
            data = (float) 0.00;
        }
        return data;
    }
}
