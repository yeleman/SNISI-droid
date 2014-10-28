package com.yeleman.snisidroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.telephony.SmsMessage;

import java.util.ArrayList;

class SMSReceiver extends BroadcastReceiver
{
    private static final String TAG = Constants.getLogTag("SMSReceiver");

    private SMSUpdater mSmsUpdater;

    public SMSReceiver(SMSUpdater u)
    {
        super();
        mSmsUpdater = u;
        Log.d(TAG, "SMSReceiver instanciated");
    }

    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "OnReceive called.");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String serverNumber = sharedPrefs.getString("serverPhoneNumber", Constants.server_number);

        Bundle bundle=intent.getExtras();
        Object[] messages=(Object[])bundle.get("pdus");

        ArrayList<SmsMessage> smsMessages = new ArrayList<SmsMessage>();

        for(Object messageBytes : messages) {
            SmsMessage anSms = SmsMessage.createFromPdu((byte[]) messageBytes);
            // check if sender is the server
            if (anSms.getOriginatingAddress().replace("+223", "")
                     .equals(serverNumber.replace("+223", ""))) {
                smsMessages.add(anSms);
            }
        }

        // only notify updater is there are actual messages
        if (smsMessages.size() > 0) {
            mSmsUpdater.gotSms(smsMessages);
        }
    }
}


