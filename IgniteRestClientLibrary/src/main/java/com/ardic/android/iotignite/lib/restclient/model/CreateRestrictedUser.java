package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class CreateRestrictedUser {

    @SerializedName("activationCode")
    private String activationCode;

    @SerializedName("apiKey")
    private String apiKey;

    @SerializedName("secretKey")
    private String secretKey;

    @SerializedName("sysUserDto")
    private SysUserDto sysUserDto;

    private int responseCode;

    public SysUserDto getSysUserDto() {
        return sysUserDto;
    }

    public void setSysUserDto(SysUserDto sysUserDto) {
        this.sysUserDto = sysUserDto;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "CreateRestrictedUser{" +
                "sysUserDto=" + sysUserDto +
                ", secretKey='" + secretKey + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }
}
