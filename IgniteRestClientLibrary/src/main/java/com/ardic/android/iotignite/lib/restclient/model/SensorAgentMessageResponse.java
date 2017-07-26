package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/14/17.
 */

public class SensorAgentMessageResponse {

    @SerializedName("response")
    private String response;

    @SerializedName("links")
    private List<Link> links;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "SensorAgentMessageResponse{" +
                "response='" + response + '\'' +
                ", links=" + links +
                '}';
    }
}
