package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Policy {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
