package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yavuz.erzurumlu on 7/14/17.
 */

public class EndUserContent {

    @SerializedName("mail")
    private String mail;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("identityNo")
    private String identityNo;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("adminArea")
    private AdminArea adminArea;

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("links")
    private List<Link> links;

    @SerializedName("code")
    private String code;

    @SerializedName("activationCode")
    private String activationCode;

    @SerializedName("activated")
    private boolean activated;

    @SerializedName("createDate")
    private long createDate;

    @SerializedName("activationDate")
    private long activationDate;

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

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AdminArea getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(AdminArea adminArea) {
        this.adminArea = adminArea;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(long activationDate) {
        this.activationDate = activationDate;
    }

    @Override
    public String toString() {
        return "EndUserContent{" +
                "mail='" + mail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", enabled=" + enabled +
                ", adminArea=" + adminArea +
                ", profile=" + profile +
                ", links=" + links +
                ", code='" + code + '\'' +
                ", activationCode='" + activationCode + '\'' +
                ", activated=" + activated +
                ", createDate=" + createDate +
                ", activationDate=" + activationDate +
                '}';
    }
}
