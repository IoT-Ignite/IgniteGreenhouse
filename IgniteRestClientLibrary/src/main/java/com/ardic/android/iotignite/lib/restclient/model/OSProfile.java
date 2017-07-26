package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class OSProfile {

    @SerializedName("hardware")
    private String hardware;

    @SerializedName("host")
    private String host;

    @SerializedName("display")
    private String display;

    @SerializedName("product")
    private String product;

    @SerializedName("board")
    private String board;

    @SerializedName("model")
    private String model;

    @SerializedName("device")
    private String device;

    @SerializedName("serial")
    private String serial;

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "OSProfile{" +
                "hardware='" + hardware + '\'' +
                ", host='" + host + '\'' +
                ", display='" + display + '\'' +
                ", product='" + product + '\'' +
                ", board='" + board + '\'' +
                ", model='" + model + '\'' +
                ", device='" + device + '\'' +
                ", serial='" + serial + '\'' +
                '}';
    }
}
