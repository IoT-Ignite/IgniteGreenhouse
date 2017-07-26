package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Field;

/**
 {
 "code": "string",
 "name": "string"
 }
 */

public class AdminArea {

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
        return "AdminArea{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
