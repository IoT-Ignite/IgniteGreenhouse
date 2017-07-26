package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by codemania on 7/24/17.
 */

public class Node {

    @SerializedName("nodeId")
    private String nodeId;

    @SerializedName("connected")
    private int connected;

    @SerializedName("description")
    private String description;

    @SerializedName("things")
    private List<Thing> things;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getConnected() {
        return connected;
    }

    public void setConnected(int connected) {
        this.connected = connected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId='" + nodeId + '\'' +
                ", connected=" + connected +
                ", description='" + description + '\'' +
                ", things=" + things +
                '}';
    }
}
