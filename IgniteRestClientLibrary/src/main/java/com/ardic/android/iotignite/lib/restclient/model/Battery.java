package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Battery {

    @SerializedName("scale")
    private String scale;

    @SerializedName("level")
    private String level;

    @SerializedName("source")
    private String source;

    @SerializedName("voltage")
    private String voltage;

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "scale='" + scale + '\'' +
                ", level='" + level + '\'' +
                ", source='" + source + '\'' +
                ", voltage='" + voltage + '\'' +
                '}';
    }
}
