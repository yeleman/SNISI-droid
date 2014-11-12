package com.yeleman.snisidroid;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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
    public static final String SMS_NUTRITION_SPACER="-";
    public static final String SMS_NUTRITION_WEEKLY_REPORT = "nut %1$d %2$d %3$d %4$d %5$d %6$d %7$d %8$d %9$d%";
    // public static final String SMS_NUTRITION_URENAM_REPORT = "%1$d-%2$d-%3$d-%4$d-%5$d-%6$d-%7$d-%8$d-%9$d-%10$d-%11$d-%12$d-%13$d-%14$d-%15$d-%16$d-%17$d-%18$d-%19$d-%20$d-%21$d-%22$d-%23$d-%24$d-%25$d-%26$d-%27$d-%28$d-%29$d-%30$d-%31$d-%32$d-%33$d-%34$d-%35$d-%36$d-%37$d-%38$d-%39$d-%40$d-%41$d-%42$d-%43$d-%44$d-%45$d-%46$d-%47$d-%48$d-%49$d-%50$d-%51$d-%52$d-%53$d-%54$d-%55$d-%56$d-%57$d-%58$d-%59$d-%60$d-%61$d-%62$d-%63d";
    // public static final String SMS_NUTRITION_URENAS_REPORT = "%1$d-%2$d-%3$d-%4$d-%5$d-%6$d-%7$d-%8$d-%9$d-%10$d-%11$d-%12$d-%13$d-%14$d-%15$d-%16$d-%17$d-%18$d-%19$d-%20$d-%21$d-%22$d-%23$d-%24$d-%25$d-%26$d-%27$d-%28$d-%29$d-%30$d-%31$d-%32$d";
    // public static final String SMS_NUTRITION_URENI_REPORT = "%1$d-%2$d-%3$d-%4$d-%5$d-%6$d-%7$d-%8$d-%9$d-%10$d-%11$d-%12$d-%13$d-%14$d-%15$d-%16$d-%17$d-%18$d-%19$d-%20$d-%21$d-%22$d-%23$d-%24$d-%25$d-%26$d-%27$d-%28$d-%29$d-%30$d-%31$d-%32$d-%33$d-%34$d-%35$d-%36$d-%37$d-%38$d-%39$d-%40$d-%41$d-%42$d-%43$d-%44$d-%45$d-%46$d-%47$d-%48$d";
    // public static final String SMS_NUTRITION_INPUTS_REPORT = "%1$d-%2$d-%3$d-%4$d-%5$d-%6$d-%7$d-%8$d-%9$d-%10$d-%11$d-%12$d-%13$d-%14$d-%15$d-%16$d-%17$d-%18$d-%19$d-%20$d-%21$d-%22$d-%23$d-%24$d-%25$d-%26$d-%27$d-%28$d-%29$d-%30$d-%31$d-%32$d-%33$d-%34$d-%35$d-%36$d-%37$d-%38$d-%39$d-%40$d-%41$d-%42$d-%43$d-%44$d-%45$d-%46$d-%47$d-%48$d-%49$d-%50$d-%51$d-%52$d-%53$d-%54$d-%55$d-%56$d";
    //'kw', 'kw2', 'username', 'password', 'month', 'year', 'urenam_data', 'urenas_data', 'ureni_data', 'stocks_data'
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
}
