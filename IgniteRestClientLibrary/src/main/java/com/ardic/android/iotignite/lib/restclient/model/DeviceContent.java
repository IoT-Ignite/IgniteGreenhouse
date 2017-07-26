package com.ardic.android.iotignite.lib.restclient.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class DeviceContent {

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("status")
    private String status;

    @SerializedName("osVersion")
    private String osVersion;

    @SerializedName("model")
    private String model;

    @SerializedName("label")
    private String labeL;

    @SerializedName("modeAppVersion")
    private String modeAppVersion;

    @SerializedName("lockStatus")
    private boolean lockStatus;

    @SerializedName("mandatoryLockStatus")
    private boolean mandatoryLockStatus;

    @SerializedName("lostStatus")
    private boolean lostStatus;

    @SerializedName("createdDate")
    private long createdDate;

    @SerializedName("lastModifiedDate")
    private long lastModifiedDate;

    @SerializedName("detailLastModifiedDate")
    private long detailLastModifiedDate;

    @SerializedName("presence")
    private Presence presence;

    @SerializedName("location")
    private Location location;

    @SerializedName("battery")
    private Battery battery;

    @SerializedName("network")
    private Network network;

    @SerializedName("storage")
    private Storage storage;

    @SerializedName("osProfile")
    private OSProfile osProfile;

    @SerializedName("currentUser")
    private User currentUser;

    @SerializedName("users")
    private List<User> users;

    @SerializedName("adminArea")
    private AdminArea adminArea;

    @SerializedName("activePolicy")
    private Policy activePolicy;

    @SerializedName("afexMode")
    private String afexMode;

    @SerializedName("deviceTimezone")
    private String deviceTimeZone;

    @SerializedName("deviceCurrentTime")
    private String deviceCurrentTime;

    @SerializedName("links")
    private List<Link> links;

    @SerializedName("code")
    private String code;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLabeL() {
        return labeL;
    }

    public void setLabeL(String labeL) {
        this.labeL = labeL;
    }

    public String getModeAppVersion() {
        return modeAppVersion;
    }

    public void setModeAppVersion(String modeAppVersion) {
        this.modeAppVersion = modeAppVersion;
    }

    public boolean isLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public boolean isMandatoryLockStatus() {
        return mandatoryLockStatus;
    }

    public void setMandatoryLockStatus(boolean mandatoryLockStatus) {
        this.mandatoryLockStatus = mandatoryLockStatus;
    }

    public boolean isLostStatus() {
        return lostStatus;
    }

    public void setLostStatus(boolean lostStatus) {
        this.lostStatus = lostStatus;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public long getDetailLastModifiedDate() {
        return detailLastModifiedDate;
    }

    public void setDetailLastModifiedDate(long detailLastModifiedDate) {
        this.detailLastModifiedDate = detailLastModifiedDate;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public OSProfile getOsProfile() {
        return osProfile;
    }

    public void setOsProfile(OSProfile osProfile) {
        this.osProfile = osProfile;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public AdminArea getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(AdminArea adminArea) {
        this.adminArea = adminArea;
    }

    public Policy getActivePolicy() {
        return activePolicy;
    }

    public void setActivePolicy(Policy activePolicy) {
        this.activePolicy = activePolicy;
    }

    public String getAfexMode() {
        return afexMode;
    }

    public void setAfexMode(String afexMode) {
        this.afexMode = afexMode;
    }

    public String getDeviceTimeZone() {
        return deviceTimeZone;
    }

    public void setDeviceTimeZone(String deviceTimeZone) {
        this.deviceTimeZone = deviceTimeZone;
    }

    public String getDeviceCurrentTime() {
        return deviceCurrentTime;
    }

    public void setDeviceCurrentTime(String deviceCurrentTime) {
        this.deviceCurrentTime = deviceCurrentTime;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DeviceContent{" +
                "deviceId='" + deviceId + '\'' +
                ", status='" + status + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", model='" + model + '\'' +
                ", labeL='" + labeL + '\'' +
                ", modeAppVersion='" + modeAppVersion + '\'' +
                ", lockStatus=" + lockStatus +
                ", mandatoryLockStatus=" + mandatoryLockStatus +
                ", lostStatus=" + lostStatus +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", detailLastModifiedDate=" + detailLastModifiedDate +
                ", presence=" + presence +
                ", location=" + location +
                ", battery=" + battery +
                ", network=" + network +
                ", storage=" + storage +
                ", osProfile=" + osProfile +
                ", currentUser=" + currentUser +
                ", users=" + users +
                ", adminArea=" + adminArea +
                ", activePolicy=" + activePolicy +
                ", afexMode='" + afexMode + '\'' +
                ", deviceTimeZone='" + deviceTimeZone + '\'' +
                ", deviceCurrentTime='" + deviceCurrentTime + '\'' +
                ", links=" + links +
                ", code='" + code + '\'' +
                '}';
    }
}
