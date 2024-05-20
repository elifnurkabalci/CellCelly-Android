package com.i2i.cellcelly.model;

import android.widget.Spinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("surname")
    @Expose
    String surname;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("msisdn")
    @Expose
    String msisdn;
    @SerializedName("securityKey")
    @Expose
    String sequrityquestion;
    @SerializedName("packageId")
    @Expose
    long packageID;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public String getMsisdn() {
        return msisdn;
    }
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getSequrityquestion(){ return sequrityquestion; }
    public void setSequrityquestion(String sequrityquestion){ this.sequrityquestion = sequrityquestion; }
    public long getPackageID(){ return packageID; }
    public void setPackageID(long packageID){ this.packageID = packageID; }

    //public String getSpinner(){ return packageID.toString(); }
    //public void setSpinner(String spinner){ this.packageID = spinner; }
}
