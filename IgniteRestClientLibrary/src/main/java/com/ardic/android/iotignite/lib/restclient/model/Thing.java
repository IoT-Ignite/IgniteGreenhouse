package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by codemania on 7/24/17.
 */


public class Thing {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("vendor")
    private String vendor;

    @SerializedName("connected")
    private int connected;

    @SerializedName("description")
    private String description;

    @SerializedName("actuator")
    private boolean actuator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getConnected() {
        return connected;
    }

    public void setConnected(int connected) {
        this.connected = connected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActuator() {
        return actuator;
    }

    public void setActuator(boolean actuator) {
        this.actuator = actuator;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", vendor='" + vendor + '\'' +
                ", connected=" + connected +
                ", description='" + description + '\'' +
                ", actuator=" + actuator +
                '}';
    }
}
