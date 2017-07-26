package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/14/17.
 */

public class EndUser {

    @SerializedName("links")
    private List<Link> links;

    @SerializedName("content")
    private List<EndUserContent> contents;

    @SerializedName("page")
    private Page page;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<EndUserContent> getContents() {
        return contents;
    }

    public void setContents(List<EndUserContent> contents) {
        this.contents = contents;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "EndUser{" +
                "links=" + links +
                ", contents=" + contents +
                ", page=" + page +
                '}';
    }
}
