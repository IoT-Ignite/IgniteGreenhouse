package com.ardic.android.iotignite.greenhouse;

import android.util.Log;

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
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");


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


}
