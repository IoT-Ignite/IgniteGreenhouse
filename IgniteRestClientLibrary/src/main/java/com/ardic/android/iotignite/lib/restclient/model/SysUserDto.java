package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

public class SysUserDto {

    @SerializedName("adminAreas")
    private List<AdminArea> adminAreas;

    @SerializedName("allDevicesAllowed")
    private boolean allDeviceAllowed;

    @SerializedName("code")
    private String code;

    @SerializedName("createdDateTime")
    private DateTime createdDateTime;

    @SerializedName("devicesWithoutDataGroupAllowed")
    private boolean devicesWithoutDataGroupAllowed;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("keys")
    private Keys keys;

    @SerializedName("lastModifiedDateTime")
    private DateTime lastModifiedDateTime;

    @SerializedName("mail")
    private String mail;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("licenseCount")
    private int licenseCount;

    @SerializedName("password")
    private String password;

    @SerializedName("roles")
    private List<Role> roles;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AdminArea> getAdminAreas() {
        return adminAreas;
    }

    public void setAdminAreas(List<AdminArea> adminAreas) {
        this.adminAreas = adminAreas;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }

    public DateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(DateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public DateTime getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(DateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public int getLicenseCount() {
        return licenseCount;
    }

    public void setLicenseCount(int licenseCount) {
        this.licenseCount = licenseCount;
    }

    public boolean isAllDeviceAllowed() {
        return allDeviceAllowed;
    }

    public void setAllDeviceAllowed(boolean allDeviceAllowed) {
        this.allDeviceAllowed = allDeviceAllowed;
    }

    public boolean isDevicesWithoutDataGroupAllowed() {
        return devicesWithoutDataGroupAllowed;
    }

    public void setDevicesWithoutDataGroupAllowed(boolean devicesWithoutDataGroupAllowed) {
        this.devicesWithoutDataGroupAllowed = devicesWithoutDataGroupAllowed;
    }

    @Override
    public String toString() {
        return "SysUserDto{" +
                "code='" + code + '\'' +
                ", mail='" + mail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", adminAreas=" + adminAreas +
                ", roles=" + roles +
                ", keys=" + keys +
                ", createdDateTime=" + createdDateTime +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", licenseCount=" + licenseCount +
                ", allDeviceAllowed=" + allDeviceAllowed +
                ", devicesWithoutDataGroupAllowed=" + devicesWithoutDataGroupAllowed +
                '}';
    }
}
