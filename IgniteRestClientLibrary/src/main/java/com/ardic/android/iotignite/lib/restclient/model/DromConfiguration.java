package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by HakkiYavuz on 11.7.2017.
 */

public class DromConfiguration {

    private String configuration;
    private String name;
    private String tenantDomain;

    public DromConfiguration(DromConfigurationParameters configuration, String name, String tenantDomain) {
        this.configuration = configuration.getConfigJsonStr();
        this.name = name;
        this.tenantDomain = tenantDomain;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantDomain() {
        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {
        this.tenantDomain = tenantDomain;
    }
}
