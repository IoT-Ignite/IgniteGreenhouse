package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class UncachedZone {

    @SerializedName("cachable")
    private boolean cacheable;

    @SerializedName("fixed")
    private boolean fixed;

    @SerializedName("id")
    private String id;

    public boolean isCacheable() {
        return cacheable;
    }

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UncachedZone{" +
                "cacheable=" + cacheable +
                ", fixed=" + fixed +
                ", id='" + id + '\'' +
                '}';
    }
}
