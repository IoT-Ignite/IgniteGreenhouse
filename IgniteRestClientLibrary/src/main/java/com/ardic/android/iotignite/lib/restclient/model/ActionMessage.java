package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */
/*
{
  "nodeId": "string",
  "params": "string",
  "sensorId": "string",
  "type": "string"
}
 */
public class ActionMessage {

    private String nodeId;
    private String params;
    private String sensorId;
    private String type;


    public ActionMessage(String nodeId, String params, String sensorId, String type) {
        this.nodeId = nodeId;
        this.params = params;
        this.sensorId = sensorId;
        this.type = type;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
