package com.ardic.android.iotignite.greenhouse;

/**
 * Created by perihan.mirkelam on 25.07.2017.
 */

public class GatewayViewModel {

    private String gatewayLabel;
    private String gatewayId;
    private Boolean isGatewayOnline;


    public GatewayViewModel(String gatewayLabel, String gatewayId, Boolean isGatewayOnline) {
        this.gatewayLabel = gatewayLabel;
        this.gatewayId = gatewayId;
        this.isGatewayOnline = isGatewayOnline;
    }

    public String getGatewayLabel() {
        return gatewayLabel;
    }
    
    public String getGatewayId() {
        return gatewayId;
    }

    public Boolean isGatewayOnline() {
        return isGatewayOnline;
    }

}
