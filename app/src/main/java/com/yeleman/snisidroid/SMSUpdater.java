package com.yeleman.snisidroid;

import android.telephony.SmsMessage;

import java.util.ArrayList;

public interface SMSUpdater
{
    void gotSms(ArrayList<SmsMessage> messages);
    void gotSMSStatusUpdate(int status, String message);
}
