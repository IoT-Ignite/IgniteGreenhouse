package com.ardic.android.iotignite.lib.restclient.model;

/**
 * Created by yavuz.erzurumlu on 7/11/17.
 * {
 "appKey": "string",
 "brand": "string",
 "firstName": "string",
 "lastName": "string",
 "mail": "string",
 "password": "string",
 "profileName": "string"
 }
 */

public class UserCreateCredentials {

    private String appKey;
    private String brand;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String profileName;

    public UserCreateCredentials(String appKey, String brand, String firstName, String lastName, String mail, String password, String profileName) {
        this.appKey = appKey;
        this.brand = brand;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.profileName = profileName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Override
    public String toString() {
        return "UserCreateCredentials{" +
                "appKey='" + appKey + '\'' +
                ", brand='" + brand + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", profileName='" + profileName + '\'' +
                '}';
    }
}
