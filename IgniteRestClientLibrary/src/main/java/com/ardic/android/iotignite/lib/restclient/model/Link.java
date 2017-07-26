package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Link {

    @SerializedName("href")
    private String href;

    @SerializedName("rel")
    private String rel;

    @SerializedName("templated")
    private String templated;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getTemplated() {
        return templated;
    }

    public void setTemplated(String templated) {
        this.templated = templated;
    }

    @Override
    public String toString() {
        return "Link{" +
                "href='" + href + '\'' +
                ", rel='" + rel + '\'' +
                ", templated='" + templated + '\'' +
                '}';
    }
}
