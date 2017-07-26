package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Wifi {

    @SerializedName("leaseDuration")
    private String leaseDuration;

    @SerializedName("mtu")
    private String mtu;

    @SerializedName("dns1")
    private String dns1;

    @SerializedName("dns2")
    private String dns2;

    @SerializedName("networkType")
    private String networkType;

    @SerializedName("currentWifiApnSsid")
    private String currentWifiApnSsid;

    @SerializedName("currentWifiApnHiddenSsid")
    private boolean currentWifiApnHiddenSsid;

    @SerializedName("gateway")
    private String gateway;

    @SerializedName("server")
    private String server;

    @SerializedName("netmask")
    private String netmask;

    @SerializedName("ip")
    private String ip;

    public String getLeaseDuration() {
        return leaseDuration;
    }

    public void setLeaseDuration(String leaseDuration) {
        this.leaseDuration = leaseDuration;
    }

    public String getMtu() {
        return mtu;
    }

    public void setMtu(String mtu) {
        this.mtu = mtu;
    }

    public String getDns1() {
        return dns1;
    }

    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }

    public String getDns2() {
        return dns2;
    }

    public void setDns2(String dns2) {
        this.dns2 = dns2;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getCurrentWifiApnSsid() {
        return currentWifiApnSsid;
    }

    public void setCurrentWifiApnSsid(String currentWifiApnSsid) {
        this.currentWifiApnSsid = currentWifiApnSsid;
    }

    public boolean isCurrentWifiApnHiddenSsid() {
        return currentWifiApnHiddenSsid;
    }

    public void setCurrentWifiApnHiddenSsid(boolean currentWifiApnHiddenSsid) {
        this.currentWifiApnHiddenSsid = currentWifiApnHiddenSsid;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Wifi{" +
                "leaseDuration='" + leaseDuration + '\'' +
                ", mtu='" + mtu + '\'' +
                ", dns1='" + dns1 + '\'' +
                ", dns2='" + dns2 + '\'' +
                ", networkType='" + networkType + '\'' +
                ", currentWifiApnSsid='" + currentWifiApnSsid + '\'' +
                ", currentWifiApnHiddenSsid=" + currentWifiApnHiddenSsid +
                ", gateway='" + gateway + '\'' +
                ", server='" + server + '\'' +
                ", netmask='" + netmask + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
