package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by codemania on 7/24/17.
 */

/*

{
  "code": null,
  "subCode": null,
  "status": "OK",
  "description": null,
  "data": {
    "deviceId": "b8:27:eb:df:c6:11@iotigniteagent",
    "command": "SensorData",
    "data": "[\"25.0\"]",
    "createDate": 1500905934586,
    "nodeId": "GreenHouse1",
    "sensorId": "Temperature",
    "cloudDate": 1500905955388
  },
  "ok": true
}
 */

public class LastThingData {

    @SerializedName("code")
    private String code;

    @SerializedName("subCode")
    private String subCode;

    @SerializedName("status")
    private String status;

    @SerializedName("description")
    private String description;

    @SerializedName("data")
    private ThingData data;

    @SerializedName("ok")
    private boolean ok;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThingData getData() {
        return data;
    }

    public void setData(ThingData data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "LastThingData{" +
                "code='" + code + '\'' +
                ", subCode='" + subCode + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", data=" + data +
                ", ok=" + ok +
                '}';
    }
}
