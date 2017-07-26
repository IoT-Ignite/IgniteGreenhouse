package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Profile {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("policy")
    private Policy policy;


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

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", policy=" + policy +
                '}';
    }
}
