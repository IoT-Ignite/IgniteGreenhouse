package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by yavuz.erzurumlu on 7/10/17.
 *
 * {
 "appkey": "bfe94d171fbb4815b32cf191d6adfdc5",
 "enabled": true,
 "links": []
 }
 */

public class AppKey {

    @SerializedName("appkey")
    private String appKey;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("links")
    private JSONObject[] links;

    private int responseCode;

    public String getAppKey() {
        return appKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public JSONObject[] getLinks() {
        return links;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "AppKey{" +
                "appKey='" + appKey + '\'' +
                ", enabled=" + enabled +
                ", links=" + Arrays.toString(links) +
                ", responseCode=" + responseCode +
                '}';
    }
}
