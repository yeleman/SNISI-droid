package com.yeleman.snisidroid;

import com.orm.SugarApp;
import com.orm.SugarContext;

import android.util.Log;

public class SNISIApp extends SugarApp {

    private static final String TAG = Constants.getLogTag("SNISIApp");

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Init Database");
        Log.d("Sugar", "DB init");

        SugarContext.init(this);

        Log.d("Sugar", "DB inited");
        Log.i(TAG, "DB inited");
        ReportData x = ReportData.findById(ReportData.class, 1);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }
}
