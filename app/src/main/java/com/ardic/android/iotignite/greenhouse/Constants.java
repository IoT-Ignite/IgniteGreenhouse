package com.ardic.android.iotignite.greenhouse;

/**
 * Created by codemania on 7/19/17.
 */

public class Constants {
    public static final String PATTERN_EMAIL = "[a-zA-Z0-9._-]+@[a-z-]+\\.+[a-z]+";
    public static final String MASTER_TENANT_DOMAIN = "ignite.com_greenhouse_iot-ignite.com";
    public static final String MASTER_TENANT_BRAND = "IgniteGreenhouse";
    public static final String MASTER_TENANT_PROFILE = "Default";
    public static final String MASTER_TENANT_USER = "greenhouse@iot-ignite.com";
    public static final String MASTER_TENANT_PASS = "gr33nhous3";
    public static final String MASTER_APP_KEY = "2bb69ddce24f4021a1c6b77f1ab9302c";
    public static final String MASTER_POLICY = "Default";
    public static final String MASTER_PROFILE = "Default";
    public static final String USER_CONFIG_MODE = "none";
    public static final int ANIM_FRAME_COUNT = 18;


    public static final int READ_QR_CODE = 1;
    public static final int CREATE_NEW_ACCOUNT = 2;
    /**
     * Shared Pref names.
     */
    public static final String IS_REMEMBER_ME_CHECKED = "is-remember-me-checked";
    public static final String REMEMBER_MAIL = "remember-mail";
    public static final String REMEMBER_PASSWORD = "remember-password";

    public class Actions {
        public static final String ACTION_SIGN_UP_SUCCESS = "sign-up-success";
        public static final String ACTION_GW_QR_CODE_SUCCESS = "gw-qr-code";
    }

    public class Extra {
        public static final String EXTRA_USERNAME = "username";
        public static final String EXTRA_PASSWORD = "password";
        public static final String EXTRA_GW_QR_CODE = "gw-qr-data";
    }


}
