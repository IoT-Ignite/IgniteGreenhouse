package com.ardic.android.iotignite.greenhouse;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */

public class GatewayViewModel {

    private String gatewayId;
    private Boolean isGatewayOnline;


    public GatewayViewModel(String gatewayId, Boolean isGatewayOnline) {
        this.gatewayId = gatewayId;
        this.isGatewayOnline = isGatewayOnline;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public Boolean isGatewayOnline() {
        return isGatewayOnline;
    }

}
