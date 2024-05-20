package com.i2i.cellcelly.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kotlin.jvm.internal.SerializedIr;

public class PackageInfoRequest {
    public PackageInfoRequest(int data, int amountSms, int minute,int packageId,String packageName,int duration) {
        this.data = data;
        this.sms = sms;
        this.minute = minute;
        this.packageId = packageId;
        this.packageName = packageName;
        this.duration = duration;
    }
    @SerializedName("data")
    @Expose
    int data;
    @SerializedName("sms")
    @Expose
    int sms;
    @SerializedName("minute")
    @Expose
    int minute;
    @SerializedName("packageID")
    @Expose
    int packageId;
    @SerializedName("packageName")
    @Expose
    String packageName;

    @SerializedName("duration")
    @Expose
    int duration;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getAmountVoice() {
        return minute;
    }

    public void setAmountVoice(int minute) {
        this.minute = minute;
    }

    public int getAmountSms() {
        return sms;
    }

    public void setAmountSms(int amountSms) {
        this.sms = amountSms;
    }

    public int getAmountData() {
        return data;
    }

    public void setAmountData(int amountData) {
        this.data = amountData;
    }
}
