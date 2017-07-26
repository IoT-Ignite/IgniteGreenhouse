package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Device {

    @SerializedName("links")
    private List<Link> links;

    @SerializedName("content")
    private List<DeviceContent> deviceContents;

    @SerializedName("page")
    private Page page;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<DeviceContent> getDeviceContents() {
        return deviceContents;
    }

    public void setDeviceContents(List<DeviceContent> deviceContents) {
        this.deviceContents = deviceContents;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Device{" +
                "links=" + links +
                ", deviceContents=" + deviceContents +
                ", page=" + page +
                '}';
    }
}
