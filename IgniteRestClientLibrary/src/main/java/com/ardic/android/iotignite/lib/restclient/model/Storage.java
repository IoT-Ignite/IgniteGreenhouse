package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class Storage {

    @SerializedName("availIntMemSize")
    private String availIntMemSize;

    @SerializedName("totalExtMemSize")
    private String totalExtMemSize;

    @SerializedName("totalIntMemSize")
    private String totalIntMemSize;

    @SerializedName("isExtMemAvail")
    private boolean isExtMemAvail;

    @SerializedName("availExtMemSize")
    private String availExtMemSize;

    public String getAvailIntMemSize() {
        return availIntMemSize;
    }

    public void setAvailIntMemSize(String availIntMemSize) {
        this.availIntMemSize = availIntMemSize;
    }

    public String getTotalExtMemSize() {
        return totalExtMemSize;
    }

    public void setTotalExtMemSize(String totalExtMemSize) {
        this.totalExtMemSize = totalExtMemSize;
    }

    public String getTotalIntMemSize() {
        return totalIntMemSize;
    }

    public void setTotalIntMemSize(String totalIntMemSize) {
        this.totalIntMemSize = totalIntMemSize;
    }

    public boolean isExtMemAvail() {
        return isExtMemAvail;
    }

    public void setExtMemAvail(boolean extMemAvail) {
        isExtMemAvail = extMemAvail;
    }

    public String getAvailExtMemSize() {
        return availExtMemSize;
    }

    public void setAvailExtMemSize(String availExtMemSize) {
        this.availExtMemSize = availExtMemSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "availIntMemSize='" + availIntMemSize + '\'' +
                ", totalExtMemSize='" + totalExtMemSize + '\'' +
                ", totalIntMemSize='" + totalIntMemSize + '\'' +
                ", isExtMemAvail=" + isExtMemAvail +
                ", availExtMemSize='" + availExtMemSize + '\'' +
                '}';
    }
}
