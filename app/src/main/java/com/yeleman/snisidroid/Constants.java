package com.yeleman.snisidroid;

import android.graphics.Color;
import android.util.Log;

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
    public static final int MIN_CHARS_PASSWORD = 4;

    public static final String SMS_SENT_INTENT = "com.yeleman.snisidroid.SMS_SENT";
    public static final String SMS_DELIVERED_INTENT = "com.yeleman.snisidroid.SMS_DELIVERED";

    public static final int NB_SECONDS_WAIT_FOR_REPLY = 60;

    public static final String SMS_CHANGE_PASSWRD = "passwd %1$s %2$s %3$s";
    public static final String SMS_NUTRITION_WEEKLY_REPORT = "nut %1$d %2$d %3$d %4$d %5$d %6$d %7$d %8$d %9$d%";

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
}
