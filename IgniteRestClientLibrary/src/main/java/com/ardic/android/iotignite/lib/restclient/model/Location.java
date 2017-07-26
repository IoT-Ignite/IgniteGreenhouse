package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Location {

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("provider")
    private String provider;

    @SerializedName("userCreateDate")
    private long userCreateDate;

    @SerializedName("links")
    private List<Link> links;


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public long getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(long userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", provider='" + provider + '\'' +
                ", userCreateDate=" + userCreateDate +
                ", links=" + links +
                '}';
    }
}
