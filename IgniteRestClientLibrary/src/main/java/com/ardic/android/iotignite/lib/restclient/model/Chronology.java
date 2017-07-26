package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Chronology {

    @SerializedName("zone")
    private Zone zone;

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Chronology{" +
                "zone=" + zone +
                '}';
    }
}
