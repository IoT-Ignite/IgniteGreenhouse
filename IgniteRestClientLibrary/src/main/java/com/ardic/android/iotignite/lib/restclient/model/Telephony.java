package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Telephony {

    @SerializedName("networkRoaming")
    private boolean networkRoaming;

    @SerializedName("simOperator")
    private String simOperator;

    @SerializedName("networkOperatorName")
    private String networkOperatorName;

    @SerializedName("msisdn")
    private String msisdn;

    @SerializedName("simState")
    private String simState;

    @SerializedName("simserialNumber")
    private String simSerialNumber;


    public boolean isNetworkRoaming() {
        return networkRoaming;
    }

    public void setNetworkRoaming(boolean networkRoaming) {
        this.networkRoaming = networkRoaming;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        this.simOperator = simOperator;
    }

    public String getNetworkOperatorName() {
        return networkOperatorName;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSimState() {
        return simState;
    }

    public void setSimState(String simState) {
        this.simState = simState;
    }

    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    public void setSimSerialNumber(String simSerialNumber) {
        this.simSerialNumber = simSerialNumber;
    }

    @Override
    public String toString() {
        return "Telephony{" +
                "networkRoaming=" + networkRoaming +
                ", simOperator='" + simOperator + '\'' +
                ", networkOperatorName='" + networkOperatorName + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", simState='" + simState + '\'' +
                ", simSerialNumber='" + simSerialNumber + '\'' +
                '}';
    }
}
