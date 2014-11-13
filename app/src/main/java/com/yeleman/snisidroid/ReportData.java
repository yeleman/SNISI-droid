package com.yeleman.snisidroid;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class ReportData extends SugarRecord {

    @Ignore
    private static final String TAG = Constants.getLogTag("ReportData");

    private String name;
    private String status;
    private Date modifiedOn;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    protected String buildName() {
        return "Unknown";
    }

    public void updateName() {
        this.setName(this.buildName());
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public static <T> T getUniqueRecord(Class<T> type) {
        return findById(type, (long) 1);
    }

    public Date getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public void updateModifiedOn() {
        this.setModifiedOn(new Date());
    }

    public void updateMetaData() {
        this.updateName();
        this.updateModifiedOn();
    }

    public String buildSMSText() { return "-"; }
}
