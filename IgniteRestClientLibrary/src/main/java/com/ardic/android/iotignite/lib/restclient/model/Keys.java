package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

import com.google.gson.annotations.SerializedName;

/**
 * "keys": {
 * "apiKey": "string",
 * "secretKey": "string"
 * },
 */
public class Keys {

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("secretKey")
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "Keys{" +
                "apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
