package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by HakkiYavuz on 11.7.2017.
 */

public class DromDevice {
    private String configurationId;
    private String deviceId;
    private String tenantDomain;

    public DromDevice(String configurationId, String deviceId, String tenantDomain) {
        this.configurationId = configurationId;
        this.deviceId = deviceId;
        this.tenantDomain = tenantDomain;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTenantDomain() {
        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {
        this.tenantDomain = tenantDomain;
    }
}
