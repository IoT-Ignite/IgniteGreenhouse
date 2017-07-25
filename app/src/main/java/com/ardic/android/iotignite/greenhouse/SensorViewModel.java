package com.ardic.android.iotignite.greenhouse;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */

public class SensorViewModel {

    private String sensorId;
    private String sensorValue;
    private Boolean isSensorOnline;


    public SensorViewModel(String sensorId, String sensorValue, Boolean isSensorOnline) {
        this.sensorId = sensorId;
        this.sensorValue = sensorValue;
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

}
