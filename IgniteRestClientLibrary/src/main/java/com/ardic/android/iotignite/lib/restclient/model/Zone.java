package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Zone {

    @SerializedName("fixed")
    private boolean fixed;

    @SerializedName("uncachedZone")
    private UncachedZone uncachedZone;

    @SerializedName("id")
    private String id;
}
