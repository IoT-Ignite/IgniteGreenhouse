package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by yavuz.erzurumlu on 7/13/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**

{
  "adminAreas": [
    {
      "code": "string",
      "name": "string"
    }
  ],
  "allDevicesAllowed": true,
  "appStore": true,
  "appStoreSize": 0,
  "brand": "string",
  "code": "string",
  "contentStore": true,
  "contentStoreSize": 0,
  "createdBy": "string",
  "createdDate": "string",
  "devicesWithoutDataGroupAllowed": true,
  "enabled": true,
  "firstName": "string",
  "flowAppSize": 0,
  "flowProfilePolicySize": 0,
  "keys": {
    "apiKey": "string",
    "secretKey": "string"
  },
  "lastModifiedBy": "string",
  "lastModifiedDate": "string",
  "lastName": "string",
  "licenseCount": 0,
  "licenseMonth": 0,
  "links": [
    {
      "href": "string",
      "rel": "string",
      "templated": true
    }
  ],
  "mail": "string",
  "registerType": "string",
  "roles": [
    {
      "desc": "string",
      "name": "string"
    }
  ],
  "tenantDomain": "string",
  "trustAppSize": 0,
  "trustUrlSize": 0
}
 */
public class SysUserInfo {

    @SerializedName("adminAreas")
    private List<AdminArea> adminAreasList;

    @SerializedName("allDevicesAllowed")
    private boolean allDevicesAllowed;

    @SerializedName("appStore")
    private boolean appStore;

    @SerializedName("appStoreSize")
    private int appStoreSize;

    @SerializedName("brand")
    private String brand;

    @SerializedName("code")
    private String code;

    @SerializedName("contentStore")
    private boolean contentStore;

    @SerializedName("contentStoreSize")
    private int contentStoreSize;

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("devicesWithoutDataGroupAllowed")
    private boolean devicesWithoutDataGroupAllowed;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("flowAppSize")
    private int flowAppSize;

    @SerializedName("flowProfilePolicySize")
    private int flowProfilePolicySize;

    @SerializedName("keys")
    private Keys keys;

    @SerializedName("lastModifiedBy")
    private String lastModifiedBy;

    @SerializedName("lastModifiedDate")
    private String lastModifiedDate;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("licenseCount")
    private int licenseCount;

    @SerializedName("licenseMonth")
    private int licenseMonth;

    @SerializedName("links")
    private List<Link> links;

    @SerializedName("mail")
    private String mail;

    @SerializedName("registerType")
    private String registerType;

    @SerializedName("roles")
    private List<Role> roles;

    @SerializedName("tenantDomain")
    private String tenantDomain;

    @SerializedName("trustAppSize")
    private int trustAppSize;

    @SerializedName("trustUrlSize")
    private int trustUrlSize;

    private int responseCode;

    public List<AdminArea> getAdminAreasList() {
        return adminAreasList;
    }

    public void setAdminAreasList(List<AdminArea> adminAreasList) {
        this.adminAreasList = adminAreasList;
    }

    public boolean isAllDevicesAllowed() {
        return allDevicesAllowed;
    }

    public void setAllDevicesAllowed(boolean allDevicesAllowed) {
        this.allDevicesAllowed = allDevicesAllowed;
    }

    public boolean isAppStore() {
        return appStore;
    }

    public void setAppStore(boolean appStore) {
        this.appStore = appStore;
    }

    public int getAppStoreSize() {
        return appStoreSize;
    }

    public void setAppStoreSize(int appStoreSize) {
        this.appStoreSize = appStoreSize;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isContentStore() {
        return contentStore;
    }

    public void setContentStore(boolean contentStore) {
        this.contentStore = contentStore;
    }

    public int getContentStoreSize() {
        return contentStoreSize;
    }

    public void setContentStoreSize(int contentStoreSize) {
        this.contentStoreSize = contentStoreSize;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isDevicesWithoutDataGroupAllowed() {
        return devicesWithoutDataGroupAllowed;
    }

    public void setDevicesWithoutDataGroupAllowed(boolean devicesWithoutDataGroupAllowed) {
        this.devicesWithoutDataGroupAllowed = devicesWithoutDataGroupAllowed;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getFlowAppSize() {
        return flowAppSize;
    }

    public void setFlowAppSize(int flowAppSize) {
        this.flowAppSize = flowAppSize;
    }

    public int getFlowProfilePolicySize() {
        return flowProfilePolicySize;
    }

    public void setFlowProfilePolicySize(int flowProfilePolicySize) {
        this.flowProfilePolicySize = flowProfilePolicySize;
    }

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        this.keys = keys;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLicenseCount() {
        return licenseCount;
    }

    public void setLicenseCount(int licenseCount) {
        this.licenseCount = licenseCount;
    }

    public int getLicenseMonth() {
        return licenseMonth;
    }

    public void setLicenseMonth(int licenseMonth) {
        this.licenseMonth = licenseMonth;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getTenantDomain() {
        return tenantDomain;
    }

    public void setTenantDomain(String tenantDomain) {
        this.tenantDomain = tenantDomain;
    }

    public int getTrustAppSize() {
        return trustAppSize;
    }

    public void setTrustAppSize(int trustAppSize) {
        this.trustAppSize = trustAppSize;
    }

    public int getTrustUrlSize() {
        return trustUrlSize;
    }

    public void setTrustUrlSize(int trustUrlSize) {
        this.trustUrlSize = trustUrlSize;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "SysUserInfo{" +
                "adminAreasList=" + adminAreasList +
                ", allDevicesAllowed=" + allDevicesAllowed +
                ", appStore=" + appStore +
                ", appStoreSize=" + appStoreSize +
                ", brand='" + brand + '\'' +
                ", code='" + code + '\'' +
                ", contentStore=" + contentStore +
                ", contentStoreSize=" + contentStoreSize +
                ", createdBy='" + createdBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", devicesWithoutDataGroupAllowed=" + devicesWithoutDataGroupAllowed +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", flowAppSize=" + flowAppSize +
                ", flowProfilePolicySize=" + flowProfilePolicySize +
                ", keys=" + keys +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate='" + lastModifiedDate + '\'' +
                ", lastName='" + lastName + '\'' +
                ", licenseCount=" + licenseCount +
                ", licenseMonth=" + licenseMonth +
                ", links=" + links +
                ", mail='" + mail + '\'' +
                ", registerType='" + registerType + '\'' +
                ", roles=" + roles +
                ", tenantDomain='" + tenantDomain + '\'' +
                ", trustAppSize=" + trustAppSize +
                ", trustUrlSize=" + trustUrlSize +
                ", responseCode=" + responseCode +
                '}';
    }
}
