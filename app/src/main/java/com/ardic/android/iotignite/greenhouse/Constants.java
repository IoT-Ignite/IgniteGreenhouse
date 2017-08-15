package com.ardic.android.iotignite.greenhouse;

/**
 * Created by codemania on 7/19/17.
 */

public class Constants {
    public static final String PATTERN_EMAIL = "[a-zA-Z0-9._-]+@[a-z-]+\\.+[a-z]+";
    public static final String MASTER_TENANT_DOMAIN = "enter-your-tenant-domain-here";
    public static final String MASTER_TENANT_BRAND = "enter-your-tenant-brand-here";
    public static final String MASTER_TENANT_PROFILE = "Default";
    public static final String MASTER_TENANT_USER = "enter-your-tenant-user-name-here";
    public static final String MASTER_TENANT_PASS = "enter-your-tenant-password-here";
    public static final String MASTER_APP_KEY = "enter-your-tenant-app-4key-here";
    public static final String MASTER_POLICY = "Default";
    public static final String MASTER_PROFILE = "Default";
    public static final String USER_CONFIG_MODE = "none";
    public static final int ANIM_FRAME_COUNT = 18;
    public static final int DROM_TRY_COUNT = 5;
    public static final int SENSOR_TRY_COUNT = 5;
    public static final String ONLINE_DEVICE = "ONLINE";
    public static final String OFFLINE_DEVICE = "OFFLINE";

    public static final int READ_QR_CODE = 1;
    public static final int CREATE_NEW_ACCOUNT = 2;

    public static final String GREENHOUSE_NODE = "IgniteGreenhouse";
    public static final String LAST_DATA_TEMP_PREFIX = "°C";
    public static final String LAST_DATA_HUM_PREFIX = "%";
    public static final String GREENHOUSE_TEMPERATURE_THINGTYPE = "GHT-Temperature";
    public static final String GREENHOUSE_HUMIDITY_THINGTYPE = "GHT-Humidity";

    public static final int CAMERA_PERMISSION_REQUEST = 1;


    public static final long GATEWAY_DASHBOARD_UPDATE_TIME = 30000L;
    public static final long SENSOR_DASHBOARD_UPDATE_TIME = 10000L;


    /**
     * Shared Pref names.
     */
    public static final String IS_REMEMBER_ME_CHECKED = "is-remember-me-checked";
    public static final String REMEMBER_MAIL = "remember-mail";
    public static final String REMEMBER_PASSWORD = "remember-password";

    public class Actions {
        public static final String ACTION_SIGN_UP_SUCCESS = "sign-up-success";
        public static final String ACTION_GW_QR_CODE_SUCCESS = "gw-qr-code-success";
        public static final String ACTION_SENSOR_QR_CODE_SUCCESS = "sensor-qr-code-success";
        public static final String ACTION_GW_QR_CODE = "gw-qr-code";
        public static final String ACTION_QR_CODE_FAILURE = "qr-code-failure";
        public static final String ACTION_CAMERA_PERMISSION_FAILURE = "camera-permission-failure";
        public static final String ACTION_SENSOR_QR_CODE = "sensor-qr-code";
        public static final String ACTION_MESSAGE_NODE_ID = "Configurator";
        public static final String ACTION_MESSAGE_THING_ID = "Configurator Thing";
        public static final String ACTION_MESSAGE_TYPE = "Configurator";
    }

    public class Extra {
        public static final String EXTRA_USERNAME = "username";
        public static final String EXTRA_PASSWORD = "password";
        public static final String EXTRA_GW_QR_CODE = "gw-qr-data";
        public static final String EXTRA_SENSOR_QR_CODE = "sensor-qr-data";
        public static final String EXTRA_DEVICE_ID = "device-id";
        public static final String EXTRA_DEVICE_CODE = "device-code";
        public static final String EXTRA_GATEWAY_LABEL = "gateway-label";
        public static final String EXTRA_USER_MAIL = "user-mail";
    }

    public class ActionMessage {
        public static final String MESSAGE_ID = "messageId";
        public static final String ADD_DEVICE = "addDevice";
        public static final String NODE_ID = "nodeId";
        public static final String THINGS = "things";
        public static final String THING_CODE = "thingCode";
        public static final String THING_ID = "thingId";
        public static final String MESSAGE = "message";
    }


}
