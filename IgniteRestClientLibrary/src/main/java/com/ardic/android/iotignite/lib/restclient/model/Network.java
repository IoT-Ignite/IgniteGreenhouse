package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Network {

    @SerializedName("telephony")
    private Telephony telephony;

    @SerializedName("wifi")
    private Wifi wifi;

    @SerializedName("bluetooth")
    private Bluetooth bluetooth;

    public Telephony getTelephony() {
        return telephony;
    }

    public void setTelephony(Telephony telephony) {
        this.telephony = telephony;
    }

    public Wifi getWifi() {
        return wifi;
    }

    public void setWifi(Wifi wifi) {
        this.wifi = wifi;
    }

    public Bluetooth getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Bluetooth bluetooth) {
        this.bluetooth = bluetooth;
    }

    @Override
    public String toString() {
        return "Network{" +
                "telephony=" + telephony +
                ", wifi=" + wifi +
                ", bluetooth=" + bluetooth+
                '}';
    }
}
