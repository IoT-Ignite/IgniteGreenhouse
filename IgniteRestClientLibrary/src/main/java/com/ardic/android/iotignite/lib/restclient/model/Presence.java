package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Presence {

    @SerializedName("state")
    private String state;

    @SerializedName("clientIp")
    private String clientIp;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Override
    public String toString() {
        return "Presence{" +
                "state='" + state + '\'' +
                ", clientIp='" + clientIp + '\'' +
                '}';
    }
}
