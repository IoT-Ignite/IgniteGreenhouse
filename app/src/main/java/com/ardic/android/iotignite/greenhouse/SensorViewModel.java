package com.ardic.android.iotignite.greenhouse;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */

public class SensorViewModel {


    private String sensorType;
    private String sensorId;
    private String nodeId;
    private String sensorValue;
    private String sensorLastSyncDateString;
    private Boolean isSensorOnline;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public SensorViewModel(String sensorId, String sensorType, String nodeId, String sensorValue, Date sensorLastSyncDate, Boolean isSensorOnline) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.nodeId = nodeId;
        this.sensorValue = sensorValue;
        this.sensorLastSyncDateString = sdf.format(sensorLastSyncDate);
        this.isSensorOnline = isSensorOnline;
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

    public String getSensorLastSyncDateString() {
        return sensorLastSyncDateString;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public void setSensorLastSyncDateString(Date sensorLastSyncDate) {
        this.sensorLastSyncDateString = sdf.format(sensorLastSyncDate);
    }

    public void setSensorOnline(Boolean sensorOnline) {
        isSensorOnline = sensorOnline;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
}
