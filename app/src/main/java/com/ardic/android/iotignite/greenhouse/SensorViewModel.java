package com.ardic.android.iotignite.greenhouse;

import java.util.Date;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */

public class SensorViewModel {


    private String sensorType;
    private String sensorId;
    private String nodeId;
    private String sensorValue;
    private Date sensorLastSyncDate;
    private Boolean isSensorOnline;


    public SensorViewModel(String sensorId, String sensorType, String nodeId, String sensorValue, Date sensorLastSyncDate, Boolean isSensorOnline) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.nodeId = nodeId;
        this.sensorValue = sensorValue;
        this.sensorLastSyncDate = sensorLastSyncDate;
        this.isSensorOnline = isSensorOnline;
    }

    public SensorViewModel() {
    }

    public String getSensorId() {
        return sensorId;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public Boolean isSensorOnline() {
        return isSensorOnline;
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public Date getSensorLastSyncDate() {
        return sensorLastSyncDate;
    }


}
