package com.yeleman.snisidroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SMSDeliveredReceiver extends BroadcastReceiver {

    private static final String TAG = Constants.getLogTag("SMSDeliveredReceiver");
    private SMSUpdater mSmsUpdater;

    public SMSDeliveredReceiver(SMSUpdater u)
    {
        super();
        mSmsUpdater = u;
        Log.d(TAG, "SMSDeliveredReceiver instanciated");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive SMS_DELIVERED");
        int feedback_status = Constants.SMS_UNKNOWN;
        String feedback_message = "";
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                feedback_status = Constants.SMS_SUCCESS;
                feedback_message = "SMS reçu par le serveur.";
                break;
            case Activity.RESULT_CANCELED:
                feedback_status = Constants.SMS_ERROR;
                feedback_message = "SMS non reçu par le serveur.";
                break;
        }
        if (feedback_status == Constants.SMS_SUCCESS) {
            Toast.makeText(context, feedback_message, Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, feedback_message);

        mSmsUpdater.gotSMSStatusUpdate(feedback_status, feedback_message);
    }
}
