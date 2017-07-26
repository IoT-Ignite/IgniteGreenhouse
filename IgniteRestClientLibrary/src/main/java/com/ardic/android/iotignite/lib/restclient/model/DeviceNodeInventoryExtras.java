package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by codemania on 7/24/17.
 */

public class DeviceNodeInventoryExtras {

    @SerializedName("nodes")
    private List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "DeviceNodeInventoryExtras{" +
                "nodes=" + nodes +
                '}';
    }
}
