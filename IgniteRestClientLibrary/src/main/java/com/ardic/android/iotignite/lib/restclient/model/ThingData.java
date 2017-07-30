package com.ardic.android.iotignite.lib.restclient.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codemania on 7/24/17.
 */

/*

     "deviceId": "b8:27:eb:df:c6:11@iotigniteagent",
      "command": "SensorData",
      "data": "[\"25.0\"]",
      "createDate": 1500906057332,
      "nodeId": "GreenHouse1",
      "sensorId": "Temperature",
      "cloudDate": 1500906075432
 */

public class ThingData {

    private static final String TAG = ThingData.class.getSimpleName();
    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("command")
    private String command;

    @SerializedName("data")
    private String data;

    @SerializedName("createDate")
    private long createDate;

    @SerializedName("nodeId")
    private String nodeId;

    @SerializedName("sensorId")
    private String sensorId;

    @SerializedName("cloudDate")
    private long cloudDate;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getData() {


        ArrayList<String> dataList = new ArrayList<>();
        JSONArray dataArray = null;

        if (!TextUtils.isEmpty(data)) {
            try {
                dataArray = new JSONArray(data);

                for (int i = 0; i < dataArray.length(); i++) {
                    dataList.add(dataArray.getString(i));
                }

            } catch (JSONException e) {
                Log.e(TAG, "JSONException:" + e);
            }
        }
        return dataList;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public long getCloudDate() {
        return cloudDate;
    }

    public void setCloudDate(long cloudDate) {
        this.cloudDate = cloudDate;
    }

    @Override
    public String toString() {
        return "ThingData{" +
                "deviceId='" + deviceId + '\'' +
                ", command='" + command + '\'' +
                ", data='" + data + '\'' +
                ", createDate=" + createDate +
                ", nodeId='" + nodeId + '\'' +
                ", sensorId='" + sensorId + '\'' +
                ", cloudDate=" + cloudDate +
                '}';
    }
}
